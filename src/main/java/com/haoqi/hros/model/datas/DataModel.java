package com.haoqi.hros.model.datas;



public class DataModel {
    private  String name;
    private Integer sum;


    public DataModel() {
        this.name = null;
        this.sum = 0;
    }

    public DataModel(String name, Integer sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSum() {
        return sum;
    }

    public void setSum(Integer sum) {
        this.sum = sum;
    }
}
