package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.network.OkHttp;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ForgetActivity extends MActivity  {
    private final String TAG = ForgetActivity.class.getSimpleName();
    private EditText etVerify, etPwd;
    private String phone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_forget;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();

    }

    private void initView(){
        etVerify = (EditText) findViewById(R.id.et_forget_check);
        etPwd = (EditText) findViewById(R.id.et_forget_psw);
        findViewById(R.id.btn_forget_commit).setOnClickListener(this);
    }

    private void initData(){
        if(getIntent() != null)
            phone = getIntent().getStringExtra("phone");
    }

    @Override
    public void onClick(View v) {
        String verify = etVerify.getText().toString();
        final String pwd = etPwd.getText().toString();
        if(TextUtils.isEmpty(verify)){
            showToast("请输入验证码");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            showToast("请输入新密码");
            return;
        }
        if(pwd.length() < 6 || pwd.length() > 12){
            showToast("密码的长度不正确");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("security", verify);
        showWaitDialog("密码修改中...");
        OkHttp.asyncPost(Constants.User.SUBMITPWD_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);
            }

            @Override
            public void onResponse(Response response) throws IOException {

                //响应成功
                updatePwd(pwd);
            }
        });
    }

    private void updatePwd(String pwd){
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", pwd);
        OkHttp.asyncPost(Constants.User.UPDATEPWD_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                hideWaitDialog();
                // 响应成功
                Intent intent = new Intent(ForgetActivity.this, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

}
