package com.example.ben.jpushdemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.example.ben.jpushdemo.constant.JPushConstant;
import com.example.ben.jpushdemo.event.JPushEvent;
import com.example.ben.jpushdemo.listener.JPushEventListener;
import com.example.ben.jpushdemo.event.JPushMessageEvent;
import com.example.ben.jpushdemo.event.JPushTagsAliasEvent;
import com.example.ben.jpushdemo.manager.CsjJPushManager;

/**
 * @author Ben
 * @date 2018/2/8
 */

public class GetMessageService extends Service implements JPushEventListener {

    private static final String TAG = "GetMessageService";

    @Override
    public void onCreate() {
        super.onCreate();
        CsjJPushManager.getInstance().setJPushEventListener(this);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onEvent(JPushEvent event) {
        switch (event.getEventType()) {
            case JPushConstant.JPUSH_MESSAGE:
                JPushMessageEvent messageEvent = (JPushMessageEvent) event.getData();
                switch (messageEvent.getType()) {
                    case JPushConstant.ACTION_REGISTRATION_ID:
                        Log.e(TAG, "ACTION_REGISTRATION_ID: " + messageEvent.getMsgContent());
                        break;
                    case JPushConstant.ACTION_MESSAGE_RECEIVED:
                        Log.e(TAG, "ACTION_MESSAGE_RECEIVED: " + messageEvent.getMsgContent());
                        break;
                    default:
                        break;
                }
                break;
            case JPushConstant.JPUSH_TAGS_ALIAS:
                JPushTagsAliasEvent tagsAliasEvent = (JPushTagsAliasEvent) event.getData();
                switch (tagsAliasEvent.getType()) {
                    case JPushConstant.onTagOperatorResult:
                        if (tagsAliasEvent.getMessage().getErrorCode()==0){
                            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        }
                        Log.e(TAG, "onTagOperatorResult: "+tagsAliasEvent.getMessage());
                        break;
                    case JPushConstant.onCheckTagOperatorResult:
                        if (tagsAliasEvent.getMessage().getErrorCode()==0){
                            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        }
                        Log.e(TAG, "onCheckTagOperatorResult: "+tagsAliasEvent.getMessage());
                        break;
                    case JPushConstant.onAliasOperatorResult:
                        if (tagsAliasEvent.getMessage().getErrorCode()==0){
                            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        }
                        Log.e(TAG, "onAliasOperatorResult: "+tagsAliasEvent.getMessage());
                        break;
                    case JPushConstant.onMobileNumberOperatorResult:
                        if (tagsAliasEvent.getMessage().getErrorCode()==0){
                            Toast.makeText(this, "成功", Toast.LENGTH_SHORT).show();
                        }
                        Log.e(TAG, "onMobileNumberOperatorResult: "+tagsAliasEvent.getMessage());
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }
    }
}
