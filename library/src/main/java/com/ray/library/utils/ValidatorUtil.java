package com.ray.library.utils;

import android.text.TextUtils;

import java.util.regex.Pattern;

public class ValidatorUtil {

    /**
     * 正则表达式:验证真实姓名（中文名）
     */
    public static final String REGEX_REALRNAME = "([\\u4E00-\\u9FA5]{1}[ ]{0,1}[\\u4E00-\\u9FA5]{1}([ ]{0,1}[\\u4E00-\\u9FA5]{1}[ ]{0,1}){0,1}([\\u4E00-\\u9FA5]{1}){0,1})";

    /**
     * 正则表达式:验证座机号
     */
    public static final String REGEX_LINE_PHONE = "^(010|02[012345789]|031[0-9]|0335|0349|035[0-9]|037[012345679]|039[1234568]|041[12456789]|042[179]|043[1-9]|045[1-9]|046[4789]|047[0-9]|048[23]|051[0-9]|052[37]|053[0-9]|054[36]|055[0-9]|056[1-6]|057[0-9]|0580|059[1-9]|063[1-5]|066[0238]|069[12]|0701|071[0-9]|072[248]|073[01456789]|074[3-6]|075[0-9]|076[023689]|077[0-9]|079[0-9]|081[23678]|082[567]|083[0-9]|085[1-9]|087[0-9]|088[3678]|089[1-8]|090[123689]|091[12345679]|093[0-9]|094[13]|095[1-5]|097[012345679]|099[0-9])[-| |]{0,1}\\d{7,8}$";

    /**
     * 正则表达式:验证手机号
     */
    private static final String REGEX_MOBILE = "^\\s*$|^(0|86|\\+86|17951)?\\s*(13[0-9]|(15[^4,\\D])|(17[0-9])|(19[0-9])|18[0-9]|14[57])\\s*\\d{4}\\s*\\d{4}\\s*$";
//    public static final String REGEX_MOBILE = "(^[1]([3|5|8|7|4|9][0-9]{1})[ ]{0,8}[0-9]{4}[ ]{0,8}[0-9]{4})|(^[1]([3|5|8|7|4][0-9]{2})[ ]{0,8}[0-9]{4}[ ]{0,8}[0-9]{3})";

    /**
     * 正则表达式:验证验证码
     */
    public static final String REGEX_VERIFY_CODE = "^[0-9]{4}$";

    /**
     * 正则表达式:验证货物名称(10个汉字)
     */
    public static final String REGEX_CARGO = "^[\u4e00-\u9fa5]{1,10}$";

    /**
     * 正则表达式:验证身份证
     */
    public static final String REGEX_ID_CARD = "(^([1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3})|([1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x))$)|(^[1-9]\\d{7}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}$)";

    /**
     * 正则表达式:验证重量体积
     */
    public static final String REGEX_WEIGHT_VOLUME = "^([1-9]\\d{0,3}|0)(\\.\\d)?$";
    /**
     * 正则表达式:验证价格
     */
    public static final String REGEX_PRICE = "^[1-9]\\d{0,4}$";


    /**
     * 正则表达式:验证备注（20汉字）
     */
    public static final String REGEX_REMARK = "^([\\u4e00-\\u9fa50-9a-zA-Z\\\\?%&=\\\\-_,.\\uff0c\\u3002]{0,20})$";


    /**
     * 校验座机号
     * @param linePhone
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isLinePhone(String linePhone) {
        return Pattern.matches(REGEX_LINE_PHONE, linePhone);
    }

    /**
     * 校验真实姓名
     * @param realname
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isRealName(String realname) {
        return Pattern.matches(REGEX_REALRNAME, realname);
    }


    /**
     * 校验手机号
     * @param mobile
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMobile(String mobile) {
        if(!TextUtils.isEmpty(mobile)&&mobile.length()==11){
            return true;
        }
        return false;
//        return Pattern.matches(REGEX_MOBILE, mobile);
    }

    /**
     * 校验验证码
     * @param verifyCode
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isVerifyCode(String verifyCode) {
        return Pattern.matches(REGEX_VERIFY_CODE, verifyCode);
    }


    /**
     * 校验身份证
     * @param idCard
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isIDCard(String idCard) {
        return Pattern.matches(REGEX_ID_CARD, idCard);
    }


    /**
     * 校验货物名称
     * @param chinese
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isCargoName(String chinese) {
        return Pattern.matches(REGEX_CARGO, chinese);
    }


    /**
     * 校验重量体积
     * @param weight
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isWeightOrVolume(String weight) {
        return Pattern.matches(REGEX_WEIGHT_VOLUME, weight);
    }


    /**
     * 校验价格
     * @param price
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPrice(String price) {
        return Pattern.matches(REGEX_PRICE, price);
    }

    /**
     * 校验备注
     * @param remark
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isRemark(String remark) {
        return Pattern.matches(REGEX_REALRNAME, remark);
    }

}