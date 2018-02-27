package com.example.jpushlibrary.factory;

import android.content.Context;

import com.example.jpushlibrary.impl.JPushListenerImpl;
import com.example.jpushlibrary.listener.JPushListener;

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
