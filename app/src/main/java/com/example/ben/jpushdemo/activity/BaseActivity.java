package com.example.ben.jpushdemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.ben.jpushdemo.manager.CsjJPushManager;


/**
 * @author Ben
 * @date 2018/2/8
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        CsjJPushManager.getInstance().init(getApplicationContext());

        initView();
        initData();

    }

    protected abstract int getLayoutId();

    protected abstract void initView();

    protected abstract void initData();
}

