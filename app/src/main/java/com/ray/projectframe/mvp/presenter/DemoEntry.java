package com.ray.projectframe.mvp.presenter;

/**
 * Created by Ray on 2017/5/11.
 * email：1452011874@qq.com
 */

public class DemoEntry {
    public static int REG_REGIST=1;
    public static int REG_LOGIN=2;
    private int reg; //1注册 2登录
    private String mobile;
    private String password;
    private String ext0; //设备标识

    public DemoEntry(int reg) {
        this.reg = reg;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getReg() {
        return reg;
    }

    public void setReg(int reg) {
        this.reg = reg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getExt0() {
        return ext0;
    }

    public void setExt0(String ext0) {
        this.ext0 = ext0;
    }
}
