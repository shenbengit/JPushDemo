package com.example.ben.jpushdemo.factory;

import android.content.Context;

import com.example.ben.jpushdemo.listener.JPushListener;
import com.example.ben.jpushdemo.impl.JPushListenerImpl;

/**
 * @author Ben
 * @date 2018/2/8
 */

public class CsjMessageFactory {

    public static JPushListener newInstance(Context context) {
        return initJPush(context);
    }

    private static JPushListener initJPush(Context context) {
        return JPushListenerImpl.newInstance(context);
    }
}
