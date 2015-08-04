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

public class RegisterActivity extends MActivity {
    private final String TAG = RegisterActivity.class.getSimpleName();
    private EditText etVerify, etPwd, etNickName;
    private String phone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView(){
        etVerify = (EditText) findViewById(R.id.et_register_check);
        etPwd = (EditText) findViewById(R.id.et_register_set_password);
        etNickName = (EditText) findViewById(R.id.et_register_set_nickname);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    private void initData(){
        if(getIntent() != null){
            phone = getIntent().getStringExtra("phone");
        }
    }

    @Override
    public void onClick(View v) {
        String verify = etVerify.getText().toString();
        String pwd = etPwd.getText().toString();
        String nickName = etNickName.getText().toString();
        if(TextUtils.isEmpty(verify)){
            showToast("请输入验证码");
            return;
        }
        if(TextUtils.isEmpty(pwd)){
            showToast("请输入密码");
            return;
        }
        if(pwd.length() < 6 || pwd.length() > 12){
            showToast("密码长度不正确");
            return;
        }
        if (TextUtils.isEmpty(nickName)) {
            showToast("昵称不能为空");
            return;
        }
        if (nickName.getBytes().length > 21) {
            showToast("您输入的昵称过长");
            return;
        }
        showWaitDialog("正在注册...");
        Map<String, String> params = new HashMap<>();
        params.put("registerPhone", phone);
        params.put("registerPassword", pwd);
        params.put("security", verify);
        params.put("nick", nickName);
        OkHttp.asyncPost(Constants.User.REGISTER_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                hideWaitDialog();

                //响应成功
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
}
