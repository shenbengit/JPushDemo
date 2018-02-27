package com.example.ben.jpushdemo.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ben.jpushdemo.R;
import com.example.ben.jpushdemo.manager.CsjJPushManager;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Ben
 */
public class SecondActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "SecondActivity";

    /**
     * 增加
     */
    public static final int ACTION_ADD = 1;
    /**
     * 覆盖
     */
    public static final int ACTION_SET = 2;
    /**
     * 删除部分
     */
    public static final int ACTION_DELETE = 3;
    /**
     * 删除所有
     */
    public static final int ACTION_CLEAN = 4;
    /**
     * 查询
     */
    public static final int ACTION_GET = 5;

    public static final int ACTION_CHECK = 6;

    public static final int DELAY_SEND_ACTION = 1;

    public static final int DELAY_SET_MOBILE_NUMBER_ACTION = 2;

    private TextView tv_appkey;
    private TextView tv_vision;
    private TextView tv_package;
    private TextView tv_imei;
    private TextView tv_deviceId;
    private EditText et_tag;
    private Button btn_addtag;
    private Button btn_settag;
    private Button btn_deletetag;
    private Button btn_getalltag;
    private Button btn_cleantag;
    private Button btn_checktag;
    private EditText et_alias;
    private Button btn_setalias;
    private Button btn_getalias;

    private Button btn_deletealias;
    private String registrationId = null;
    private String appkey = null;
    private String vision = null;
    private String packageName = null;
    private String imei = null;
    private String deviceId = null;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_second;
    }

    /**
     * 判断字符串是否为null
     *
     * @param value
     * @return
     */
    private String isNull(String value) {
        return TextUtils.isEmpty(value) ? "" : value;
    }

    @Override
    protected void initView() {

        tv_appkey = (TextView) findViewById(R.id.tv_appkey);
        tv_vision = (TextView) findViewById(R.id.tv_vision);
        tv_package = (TextView) findViewById(R.id.tv_package);
        tv_imei = (TextView) findViewById(R.id.tv_imei);
        tv_deviceId = (TextView) findViewById(R.id.tv_deviceId);
        et_tag = (EditText) findViewById(R.id.et_tag);
        btn_addtag = (Button) findViewById(R.id.btn_addtag);
        btn_settag = (Button) findViewById(R.id.btn_settag);
        btn_deletetag = (Button) findViewById(R.id.btn_deletetag);
        btn_getalltag = (Button) findViewById(R.id.btn_getalltag);
        btn_cleantag = (Button) findViewById(R.id.btn_cleantag);
        btn_checktag = (Button) findViewById(R.id.btn_checktag);
        et_alias = (EditText) findViewById(R.id.et_alias);
        btn_setalias = (Button) findViewById(R.id.btn_setalias);
        btn_getalias = (Button) findViewById(R.id.btn_getalias);
        btn_deletealias = (Button) findViewById(R.id.btn_deletealias);

        btn_addtag.setOnClickListener(this);
        btn_settag.setOnClickListener(this);
        btn_deletetag.setOnClickListener(this);
        btn_getalltag.setOnClickListener(this);
        btn_cleantag.setOnClickListener(this);
        btn_checktag.setOnClickListener(this);
        btn_setalias.setOnClickListener(this);
        btn_getalias.setOnClickListener(this);
        btn_deletealias.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        int sequence = (int) System.currentTimeMillis();
        Set<String> tags = null;
        String alias = null;

        switch (v.getId()) {
            case R.id.btn_addtag:
                tags = getInputTags();
                if (tags == null) {
                    return;
                }
                break;
            case R.id.btn_settag:
                tags = getInputTags();
                if (tags == null) {
                    return;
                }
                CsjJPushManager.getInstance().setTags(sequence, tags);
                break;
            case R.id.btn_deletetag:
                tags = getInputTags();
                if (tags == null) {
                    return;
                }
                break;
            case R.id.btn_getalltag:
                CsjJPushManager.getInstance().getAllTags(sequence);
                break;
            case R.id.btn_cleantag:

                break;
            case R.id.btn_checktag:
                tags = getInputTags();
                if (tags == null) {
                    return;
                }
                break;
            case R.id.btn_setalias:
                alias = getInputAlias();
                if (TextUtils.isEmpty(alias)) {
                    return;
                }
                CsjJPushManager.getInstance().setAlias(sequence, alias);
                break;
            case R.id.btn_getalias:
                CsjJPushManager.getInstance().getAlias(sequence);
                break;
            case R.id.btn_deletealias:
                break;
            default:
                return;

        }
    }

    /**
     * 获取输入的Tag
     *
     * @return
     */
    private Set<String> getInputTags() {
        String tags = et_tag.getText().toString().trim();
        if (TextUtils.isEmpty(tags)) {
            Toast.makeText(this, "请输入Tag", Toast.LENGTH_SHORT).show();
            return null;
        }
        Set<String> setTags = new LinkedHashSet<String>();

        String[] strs = tags.split(",");
        for (String str : strs) {
            if (!isValidTagAndAlias(str)) {
                Toast.makeText(this, "tag无效", Toast.LENGTH_SHORT).show();
                return null;
            }
            setTags.add(str);
        }
        if (setTags.isEmpty()) {
            Toast.makeText(this, "tag不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }
        return setTags;
    }

    private String getInputAlias() {
        String alias = et_alias.getText().toString().trim();
        if (TextUtils.isEmpty(alias)) {
            Toast.makeText(this, "Alias不能为空", Toast.LENGTH_SHORT).show();
            return null;
        }
        if (!isValidTagAndAlias(alias)) {
            Toast.makeText(this, "Alias无效，重新输入", Toast.LENGTH_SHORT).show();
            return null;
        }
        return alias;
    }

    /**
     * 校验Tag Alias 只能是数字,英文字母和中文
     *
     * @param s
     * @return
     */
    public static boolean isValidTagAndAlias(String s) {
        Pattern p = Pattern.compile("^[\u4E00-\u9FA50-9a-zA-Z_!@#$&*+=.|]+$");
        Matcher m = p.matcher(s);
        return m.matches();
    }
}
