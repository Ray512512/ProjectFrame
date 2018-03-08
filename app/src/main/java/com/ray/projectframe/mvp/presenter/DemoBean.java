package com.ray.projectframe.mvp.presenter;

import java.io.Serializable;

/**
 * Created by Ray on 2017/5/5.
 * email：1452011874@qq.com
 */

public class DemoBean implements Serializable{

    public DemoBean(String user_id) {
        this.user_id = user_id;
    }

    private double lng,lat;
    /**
     * user_id : 617
     * account : null
     * password : e10adc3949ba59abbe56e057f20f883e
     * pay_password : null
     * face : null
     * ext0 : 862805032502352
     * nickname : null
     * integral : 0
     * prestige : 0
     * money : 0
     * rank_id : 1
     * gold : 0
     * frozen_money : null
     * frozen_gold : null
     * reg_time : 1494488632
     * reg_ip : null
     * last_time : null
     * frozen_money_time : null
     * frozen_gold_time : null
     * closed : 0
     * uc_id : 0
     * email : null
     * mobile : 17775526994
     * ping_num : 0
     * post_num : 0
     * lock_num : 0
     * invite1 : null
     * invite2 : null
     * invite3 : null
     * invite4 : null
     * invite5 : null
     * invite6 : 0
     * token : 0
     * fuid1 : 0
     * fuid2 : 0
     * fuid3 : 0
     * is_lock : 0
     * is_lock_time : null
     * cleartime : 0
     * yhmonth : null
     */

    private String user_id;
    private Object account;
    private String password;
    private Object pay_password;
    private Object face;
    private String ext0; // 这个代表白名单 0-否 1-可以访问任意wifi
    private Object nickname;
    private String integral;
    private String prestige;
    private String money;
    private String rank_id;
    private String gold;
    private Object frozen_money;
    private Object frozen_gold;
    private String reg_time;
    private Object reg_ip;
    private Object last_time;
    private Object frozen_money_time;
    private Object frozen_gold_time;
    private String closed;
    private String uc_id;
    private Object email;
    private String mobile;
    private String ping_num;
    private String post_num;
    private String lock_num;
    private Object invite1;
    private Object invite2;
    private Object invite3;
    private Object invite4;
    private Object invite5;
    private String invite6;
    private String token;
    private String fuid1;
    private String fuid2;
    private String fuid3;
    private String is_lock;
    private Object is_lock_time;
    private String cleartime;
    private Object yhmonth;
    private int ext01;

    public int getExt01() {
        return ext01;
    }

    public void setExt01(int ext01) {
        this.ext01 = ext01;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }




    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public Object getAccount() {
        return account;
    }

    public void setAccount(Object account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Object getPay_password() {
        return pay_password;
    }

    public void setPay_password(Object pay_password) {
        this.pay_password = pay_password;
    }

    public Object getFace() {
        return face;
    }

    public void setFace(Object face) {
        this.face = face;
    }

    public String getExt0() {
        return ext0;
    }

    public void setExt0(String ext0) {
        this.ext0 = ext0;
    }

    public Object getNickname() {
        return nickname;
    }

    public void setNickname(Object nickname) {
        this.nickname = nickname;
    }

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getPrestige() {
        return prestige;
    }

    public void setPrestige(String prestige) {
        this.prestige = prestige;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getRank_id() {
        return rank_id;
    }

    public void setRank_id(String rank_id) {
        this.rank_id = rank_id;
    }

    public String getGold() {
        return gold;
    }

    public void setGold(String gold) {
        this.gold = gold;
    }

    public Object getFrozen_money() {
        return frozen_money;
    }

    public void setFrozen_money(Object frozen_money) {
        this.frozen_money = frozen_money;
    }

    public Object getFrozen_gold() {
        return frozen_gold;
    }

    public void setFrozen_gold(Object frozen_gold) {
        this.frozen_gold = frozen_gold;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public Object getReg_ip() {
        return reg_ip;
    }

    public void setReg_ip(Object reg_ip) {
        this.reg_ip = reg_ip;
    }

    public Object getLast_time() {
        return last_time;
    }

    public void setLast_time(Object last_time) {
        this.last_time = last_time;
    }

    public Object getFrozen_money_time() {
        return frozen_money_time;
    }

    public void setFrozen_money_time(Object frozen_money_time) {
        this.frozen_money_time = frozen_money_time;
    }

    public Object getFrozen_gold_time() {
        return frozen_gold_time;
    }

    public void setFrozen_gold_time(Object frozen_gold_time) {
        this.frozen_gold_time = frozen_gold_time;
    }

    public String getClosed() {
        return closed;
    }

    public void setClosed(String closed) {
        this.closed = closed;
    }

    public String getUc_id() {
        return uc_id;
    }

    public void setUc_id(String uc_id) {
        this.uc_id = uc_id;
    }

    public Object getEmail() {
        return email;
    }

    public void setEmail(Object email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPing_num() {
        return ping_num;
    }

    public void setPing_num(String ping_num) {
        this.ping_num = ping_num;
    }

    public String getPost_num() {
        return post_num;
    }

    public void setPost_num(String post_num) {
        this.post_num = post_num;
    }

    public String getLock_num() {
        return lock_num;
    }

    public void setLock_num(String lock_num) {
        this.lock_num = lock_num;
    }

    public Object getInvite1() {
        return invite1;
    }

    public void setInvite1(Object invite1) {
        this.invite1 = invite1;
    }

    public Object getInvite2() {
        return invite2;
    }

    public void setInvite2(Object invite2) {
        this.invite2 = invite2;
    }

    public Object getInvite3() {
        return invite3;
    }

    public void setInvite3(Object invite3) {
        this.invite3 = invite3;
    }

    public Object getInvite4() {
        return invite4;
    }

    public void setInvite4(Object invite4) {
        this.invite4 = invite4;
    }

    public Object getInvite5() {
        return invite5;
    }

    public void setInvite5(Object invite5) {
        this.invite5 = invite5;
    }

    public String getInvite6() {
        return invite6;
    }

    public void setInvite6(String invite6) {
        this.invite6 = invite6;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getFuid1() {
        return fuid1;
    }

    public void setFuid1(String fuid1) {
        this.fuid1 = fuid1;
    }

    public String getFuid2() {
        return fuid2;
    }

    public void setFuid2(String fuid2) {
        this.fuid2 = fuid2;
    }

    public String getFuid3() {
        return fuid3;
    }

    public void setFuid3(String fuid3) {
        this.fuid3 = fuid3;
    }

    public String getIs_lock() {
        return is_lock;
    }

    public void setIs_lock(String is_lock) {
        this.is_lock = is_lock;
    }

    public Object getIs_lock_time() {
        return is_lock_time;
    }

    public void setIs_lock_time(Object is_lock_time) {
        this.is_lock_time = is_lock_time;
    }

    public String getCleartime() {
        return cleartime;
    }

    public void setCleartime(String cleartime) {
        this.cleartime = cleartime;
    }

    public Object getYhmonth() {
        return yhmonth;
    }

    public void setYhmonth(Object yhmonth) {
        this.yhmonth = yhmonth;
    }
}
