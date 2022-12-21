package com.example.smartcity1222.bean;

public class Data {
    String carNum;
    String phone;
    String address;

    public Data(String carNum, String phone, String address) {
        this.carNum = carNum;
        this.phone = phone;
        this.address = address;
    }

    public String getCarNum() {
        return carNum;
    }

    public void setCarNum(String carNum) {
        this.carNum = carNum;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
