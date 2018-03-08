package com.ray.library.rxbus;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

public  final class RxBus {
    private static Bus sBus; //主线程post
    private static Bus ioBus; //子线程post

    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }
    public static synchronized Bus getIO() {
        if (ioBus == null) {
            ioBus = new Bus(ThreadEnforcer.ANY);
        }
        return ioBus;
    }
    public static void register(Object o){
        get().register(o);
        getIO().register(o);
    }
    public static void unregister(Object o){
        get().unregister(o);
        getIO().unregister(o);
    }
}