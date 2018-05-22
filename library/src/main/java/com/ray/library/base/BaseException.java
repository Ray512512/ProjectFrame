package com.ray.library.base;

/**
 * Created by Ray on 2018/5/22.
 */
public class BaseException extends Exception {
    private String msg;

    public BaseException(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return msg;
    }
}
