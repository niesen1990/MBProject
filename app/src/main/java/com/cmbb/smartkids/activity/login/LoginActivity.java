package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.ShareUtils;
import com.cmbb.smartkids.tools.Utils;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.UMSsoHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by javon on 2015/7/31.
 */
public class LoginActivity extends MActivity {
    private final String TAG = LoginActivity.class.getSimpleName();

    private EditText etLoginPhone, etLoginPsw;
    private TextView btnLogin, btnForgetPassword, btnGoRegister;
    private ImageView btnLoginQq, btnLoginSina, btnLoginWx;
    private UMSocialService mController;
    private SHARE_MEDIA media;
    private String uid = "";// 获取第三方平台用户id
    private String userName = ""; // 获取第三方平台userName;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();

    }

    private void initView() {
        etLoginPhone = (EditText) findViewById(R.id.et_login_phone);
        etLoginPsw = (EditText) findViewById(R.id.et_login_psw);
        btnLogin = (TextView) findViewById(R.id.btn_login);
        btnForgetPassword = (TextView) findViewById(R.id.btn_forget_password);
        btnGoRegister = (TextView) findViewById(R.id.btn_go_register);
        btnLoginQq = (ImageView) findViewById(R.id.btn_login_qq);
        btnLoginSina = (ImageView) findViewById(R.id.btn_login_sina);
        btnLoginWx = (ImageView) findViewById(R.id.btn_login_wx);
    }

    private void initData() {
        mController = ShareUtils.instanceOf(this);
    }

    private void addListener() {
        btnLogin.setOnClickListener(this);
        btnForgetPassword.setOnClickListener(this);
        btnGoRegister.setOnClickListener(this);
        btnLoginQq.setOnClickListener(this);
        btnLoginSina.setOnClickListener(this);
        btnLoginWx.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_login:
                String account = etLoginPhone.getText().toString();
                String pwd = etLoginPsw.getText().toString();
                sendLoginRequest(account, pwd);
                break;
            case R.id.btn_forget_password:
                intent = new Intent(LoginActivity.this, PhoneVerifyActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_go_register:

                break;
            case R.id.btn_login_qq:
                media = SHARE_MEDIA.QQ;
                ShareUtils.addQQQZonePlatform();
                authToPlatform();
                break;
            case R.id.btn_login_sina:
                media = SHARE_MEDIA.SINA;
                authToPlatform();
                break;
            case R.id.btn_login_wx:
                media = SHARE_MEDIA.WEIXIN;
                ShareUtils.addWXPlatform();
                authToPlatform();
                break;
        }
    }


    /**
     * 第三方平台授权
     */
    private void authToPlatform() {
        mController.doOauthVerify(this, media, new SocializeListeners.UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.e(TAG, "i come here");
            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                if (bundle != null && !TextUtils.isEmpty(bundle.getString("uid"))) {
                    mController.login(LoginActivity.this, share_media, loginListener);
                } else {
                    showToastShort("授权失败");
                }

            }

            @Override
            public void onError(SocializeException e, SHARE_MEDIA share_media) {
                showToastShort("授权失败");
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media) {
                showToastShort("已取消授权");
            }
        });

    }

    /**
     * 第三方登录
     */
    private SocializeListeners.SocializeClientListener loginListener = new SocializeListeners.SocializeClientListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, SocializeEntity socializeEntity) {
            if (status == 200) {
                showToastShort("登录成功");
                mController.getPlatformInfo(LoginActivity.this, media, uMDataListener);
            } else {
                showToastShort("登录失败");
            }
        }
    };

    /**
     * 第三方登录
     * 获取来自第三方用户的信息
     */
    private SocializeListeners.UMDataListener uMDataListener = new SocializeListeners.UMDataListener() {
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, Map<String, Object> info) {
            if (media == SHARE_MEDIA.SINA || media == SHARE_MEDIA.QQ) {
                uid = info.get("uid").toString();
                userName = info.get("screen_name").toString();
            } else if (media == SHARE_MEDIA.WEIXIN) {
                uid = info.get("openid").toString();
                userName = info.get("nickname").toString();
            }
            handleLogin(uid, userName);
        }
    };

    /**
     * 第三方平台登录
     *
     * @param uid
     * @param userName
     */
    private void handleLogin(String uid, String userName) {
        showWaitDialog();
        Map<String, String> params = new HashMap<>();
        params.put("openId", uid);
        params.put("nick", userName);
        OkHttp.asyncPost(Constants.User.LOGINS_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                hideWaitDialog();

            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private void sendLoginRequest(String account, String pwd) {
        if (TextUtils.isEmpty(account)) {
            showToast("请输入手机号码");
            return;
        }
        if (!Utils.isMobileNo(account)) {
            showToast("请输入正确的手机号码");
            return;
        }
        if (TextUtils.isEmpty(pwd)) {
            showToast("请输入密码");
            return;
        }
        showWaitDialog("正在登录...");
        Map<String, String> params = new HashMap<>();
        params.put("registerPhone", account);
        params.put("registerPassword", pwd);
        OkHttp.asyncPost(Constants.User.LOGINS_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);

            }

            @Override
            public void onResponse(Response response) throws IOException {

            }
        });
    }

}
