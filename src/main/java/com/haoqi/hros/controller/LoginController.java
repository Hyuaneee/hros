package com.haoqi.hros.controller;

import com.haoqi.hros.model.Hr;
import com.haoqi.hros.model.RespBean;
import com.haoqi.hros.service.HrService;
import com.wf.captcha.ArithmeticCaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@RestController
public class LoginController {

    @Autowired
    HrService hrService;

    private static Logger Loggerlogger = Logger.getLogger("com.haoqi.hros.controller.LoginController");

    @GetMapping("/login")
    public RespBean login(){
        return RespBean.error("尚未登录,请登录!");
    }
    @GetMapping(value = "/auth/code")
    public Map getCode(HttpServletRequest request){
        // 算术类型 https://gitee.com/whvse/EasyCaptcha
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(111, 36);
        // 几位数运算，默认是两位
        captcha.setLen(2);
        // 获取运算的结果
        String result = captcha.text();
        Loggerlogger.warning("生成的验证码："+result);
        result = result.equals("0.0") ? "0":result;

        // 保存
        // 验证码信息
        Map<String,Object> imgResult = new HashMap<String,Object>(2){{
            put("img", captcha.toBase64());

        }};
        request.getSession().setAttribute("yanzhengma",result+"");
        Loggerlogger.warning("校验码为："+result);
        return imgResult;
    }

    @PostMapping("/register")
    @ResponseBody
    public RespBean register(Hr hr){
        Hr hr1 = hrService.getHrByUsername(hr.getUsername());
        if(hr1 != null){
            return RespBean.error("用户名已注册");
        }else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            String encode = encoder.encode(hr.getPassword());
            hr.setUserface("https://imgsa.baidu.com/forum/pic/item/a832bc315c6034a8df786e5ac31349540823766e.jpg");
            hr.setPassword(encode);
            hr.setEnabled(true);
            hrService.insertHr(hr);
            return RespBean.ok("注册成功");
        }
    }

}
