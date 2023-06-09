
package com.haoqi.hros.Exception;

import com.haoqi.hros.model.RespBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.logging.Logger;


@RestControllerAdvice
public class GlobalExceptionHandler {

    Logger logger = Logger.getLogger("com.liruilong.hros.Exception.GlobalExceptionHandler");
    @ExceptionHandler(SQLException.class)
    public RespBean sqlException(SQLException e) {
        return RespBean.error("数据库异常,操作失败");
    }
/*    @ExceptionHandler(IllegalStateException.class)
    public void ValidateCodeException(IllegalStateException e) {

        logger.warning("Cannot call sendError() after the response has been committed " +"异常");

    }
    @ExceptionHandler(NullPointerException.class)
    public RespBean ValidateCodeException(NullPointerException e) {
        return RespBean.error("系统错误提示:空指针异常");
    }*/
}
