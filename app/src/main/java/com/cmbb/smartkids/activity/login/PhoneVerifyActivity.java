package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.model.userinfo.LoginBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.Utils;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PhoneVerifyActivity extends MActivity {
    private final String TAG = PhoneVerifyActivity.class.getSimpleName();
    private boolean isRegister;
    private TextView tvNext;
    private EditText etPhone;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_phone_verify;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        tvNext.setOnClickListener(this);

    }

    private void initView() {
        etPhone = (EditText) findViewById(R.id.et_login_phone);
        tvNext = (TextView) findViewById(R.id.btn_go_registertwo);
    }

    private void initData() {
        if (getIntent() != null) {
            isRegister = getIntent().getBooleanExtra("flags", false);
        }
        if (isRegister) {
            if (getCustomBar() != null) {
                getCustomBar().setTitle("用户注册");
            }
        } else {
            if (getCustomBar() != null) {
                getCustomBar().setTitle("忘记密码");
            }
        }
    }

    @Override
    public void onClick(View v) {
        final String phone = etPhone.getText().toString();
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号码");
            return;
        }
        if (!Utils.isMobileNo(phone)) {
            showToast("手机号码格式不正确");
            return;
        }
        showWaitDialog("获取验证码...");
        Map<String, String> params = new HashMap<String, String>();
        if (isRegister) {  // 获取注册验证码
            params.put("registerPhone", phone);
            OkHttp.asyncPost(Constants.User.VALIDPHONE_URL, params, TAG, new Callback() {
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
                        Gson gson = new Gson();
                        final LoginBaseModel loginBaseModel = gson.fromJson(result, LoginBaseModel.class);
                        if (loginBaseModel.getCode().equals("1")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    //  响应成功
                                    Intent intent = new Intent(PhoneVerifyActivity.this, RegisterActivity.class);
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
                                }
                            });
                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast(loginBaseModel.getContext().getPresentation());
                                }
                            });
                        }
                    }
                }
            });
        } else { // 获取忘记密码验证码
            params.put("phone", phone);
            OkHttp.asyncPost(Constants.User.FORGETPWD_URL, params, TAG, new Callback() {
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
                                    //  响应成功
                                    Intent intent = new Intent(PhoneVerifyActivity.this, ForgetActivity.class);
                                    intent.putExtra("phone", phone);
                                    startActivity(intent);
                                }
                            });

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast(loginBaseModel.getContext().getPresentation());
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
}
