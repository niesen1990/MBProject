package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.model.userinfo.LoginBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ForgetActivity extends MActivity {
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

    private void initView() {
        etVerify = (EditText) findViewById(R.id.et_forget_check);
        etPwd = (EditText) findViewById(R.id.et_forget_psw);
        findViewById(R.id.btn_forget_commit).setOnClickListener(this);
    }

    private void initData() {
        if (getIntent() != null)
            phone = getIntent().getStringExtra("phone");
    }

    @Override
    public void onClick(View v) {
        String verify = etVerify.getText().toString().trim();
        final String pwd = etPwd.getText().toString().trim();
        if (TextUtils.isEmpty(verify)) {
            showToast("请输入验证码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入新密码");
            return;
        }
        if (pwd.length() < 6 || pwd.length() > 12) {
            showToast("密码的长度不正确");
            return;
        }
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("security", verify);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Log.i("map", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        showWaitDialog("密码修改中...");
        OkHttp.asyncPost(Constants.User.SUBMITPWD_URL, params, TAG, new Callback() {
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
                    Log.i("result", "result1 = " + result);
                    Gson gson = new Gson();
                    final LoginBaseModel loginBaseModel = gson.fromJson(result, LoginBaseModel.class);
                    if (loginBaseModel.getCode().equals("1")) {
                        //响应成功
                        updatePwd(pwd);
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
    }

    private void updatePwd(String pwd) {
        Map<String, String> params = new HashMap<>();
        params.put("phone", phone);
        params.put("password", pwd);
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Log.i("map", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.User.UPDATEPWD_URL, params, TAG, new Callback() {
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
                String result = response.body().string();
                Log.i("result", "result2 = " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideWaitDialog();
                        // 响应成功
                        Intent intent = new Intent(ForgetActivity.this, LoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                    }
                });
            }
        });
    }

}
