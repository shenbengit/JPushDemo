package com.example.ben.jpushdemo.activity;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ben.jpushdemo.R;
import com.example.ben.jpushdemo.manager.CsjJPushManager;
import com.example.ben.jpushdemo.service.GetMessageService;

/**
 * @author Ben
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {


    private Button btn_init;
    private Button btn_stop;
    private Button btn_resume;
    private Button btn_isStopped;
    private Button btn_registrationId;
    private TextView tv_registrationId;
    private Button btn_jump;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        startService(new Intent(MainActivity.this, GetMessageService.class));
        btn_init = (Button) findViewById(R.id.btn_init);
        btn_stop = (Button) findViewById(R.id.btn_stop);
        btn_resume = (Button) findViewById(R.id.btn_resume);
        btn_isStopped = (Button) findViewById(R.id.btn_isStopped);
        btn_registrationId= (Button) findViewById(R.id.btn_registrationId);
        tv_registrationId= (TextView) findViewById(R.id.tv_registrationId);
        btn_jump = (Button) findViewById(R.id.btn_jump);

        btn_init.setOnClickListener(this);
        btn_stop.setOnClickListener(this);
        btn_resume.setOnClickListener(this);
        btn_isStopped.setOnClickListener(this);
        btn_registrationId.setOnClickListener(this);
        btn_jump.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_init:
                CsjJPushManager.getInstance().init(getApplicationContext());
                break;
            case R.id.btn_stop:
                CsjJPushManager.getInstance().stopPush();
                break;
            case R.id.btn_resume:
                CsjJPushManager.getInstance().resumePush();
                break;
            case R.id.btn_isStopped:
                Toast.makeText(this, "是否停止接收Push: " + CsjJPushManager.getInstance().isPushStopped(), Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_registrationId:
                tv_registrationId.setText("RegistrationId: "+ CsjJPushManager.getInstance().getRegistrationId());
                Log.e("MainActivity", "RegistrationId: "+ CsjJPushManager.getInstance().getRegistrationId());
                break;
            case R.id.btn_jump:
                startActivity(new Intent(MainActivity.this, SecondActivity.class));
                break;
            default:
                break;
        }
    }
}
