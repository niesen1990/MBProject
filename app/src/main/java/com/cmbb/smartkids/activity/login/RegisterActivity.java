package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.HomeActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.model.userinfo.LoginBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.google.gson.Gson;
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

    private void initView() {
        etVerify = (EditText) findViewById(R.id.et_register_check);
        etPwd = (EditText) findViewById(R.id.et_register_set_password);
        etNickName = (EditText) findViewById(R.id.et_register_set_nickname);
        findViewById(R.id.btn_register).setOnClickListener(this);
    }

    private void initData() {
        if (getIntent() != null) {
            phone = getIntent().getStringExtra("phone");
        }
    }

    @Override
    public void onClick(View v) {
        String verify = etVerify.getText().toString();
        String pwd = etPwd.getText().toString();
        String nickName = etNickName.getText().toString();
        if (TextUtils.isEmpty(verify)) {
            showToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 12) {
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
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Log.i("map", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.User.REGISTER_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideWaitDialog();
                        showToast(R.string.service_error);
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Log.i("result", "result = " + result);
                    Gson gson = new Gson();
                    final LoginBaseModel loginBaseModel = gson.fromJson(result, LoginBaseModel.class);
                    if (loginBaseModel.getCode().equals("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast(loginBaseModel.getContext().getPresentation());
                                MApplication.token = loginBaseModel.getContext().getToken();
                                MApplication.rongToken = loginBaseModel.getContext().getRongyunToken();
                                MApplication.userStatus = loginBaseModel.getContext().getUserStatus();
                                // 保存SP
                                SPCache.putString(Constants.SharePreference.USER_TOKEN, loginBaseModel.getContext().getToken());
                                SPCache.putString(Constants.SharePreference.RONG_TOKEN, loginBaseModel.getContext().getRongyunToken());
                                SPCache.putInt(Constants.SharePreference.USER_AUTHORITY, loginBaseModel.getContext().getUserStatus());
                                ApiNetwork.loginRongYun(MApplication.rongToken);
                                ApiNetwork.getUserInfoList();
                                Intent intent = new Intent(RegisterActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        });

                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    hideWaitDialog();
                                    showToast(loginBaseModel.getContext().getPresentation());
                                } catch (NullPointerException e) {

                                }
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(R.string.service_error);
                        }
                    });
                }
            }
        });
    }
}
