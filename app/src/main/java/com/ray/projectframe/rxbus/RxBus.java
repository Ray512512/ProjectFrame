package com.ray.projectframe.rxbus;

import com.hwangjr.rxbus.Bus;
import com.hwangjr.rxbus.entity.EventType;
import com.hwangjr.rxbus.entity.SubscriberEvent;
import com.hwangjr.rxbus.finder.Finder;
import com.hwangjr.rxbus.thread.ThreadEnforcer;

import java.util.Map;
import java.util.Set;

public  final class RxBus {
    private static Bus sBus;
    
    public static synchronized Bus get() {
        if (sBus == null) {
            sBus = new Bus();
        }
        return sBus;
    }

}