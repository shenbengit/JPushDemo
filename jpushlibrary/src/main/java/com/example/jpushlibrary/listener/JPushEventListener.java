package com.example.jpushlibrary.listener;


import com.example.jpushlibrary.event.JPushEvent;

/**
 * @author Ben
 * @date 2018/2/24
 */

public interface JPushEventListener {
    /**
     * 得到事件
     * @param event
     */
    void onEvent(JPushEvent event);
}
