package com.cmbb.smartkids.activity.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.bean.StatusCode;
import com.umeng.socialize.controller.UMServiceFactory;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;
import com.umeng.socialize.exception.SocializeException;
import com.umeng.socialize.sso.QZoneSsoHandler;
import com.umeng.socialize.sso.SinaSsoHandler;
import com.umeng.socialize.sso.UMQQSsoHandler;
import com.umeng.socialize.sso.UMSsoHandler;
import com.umeng.socialize.weixin.controller.UMWXHandler;

import java.util.Map;
import java.util.Set;

/**
 * Created by javon on 2015/7/31.
 */
public class LoginActivity extends MActivity {

    private EditText etLoginPhone, etLoginPsw;
    private TextView btnLogin, btnForgetPassword, btnGoRegister;
    private ImageView btnLoginQq, btnLoginSina, btnLoginWx;
    private UMSocialService mController;
    private final String UmengConstance = "com.umeng.login";
    private SHARE_MEDIA media;


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
        mController = UMServiceFactory.getUMSocialService(UmengConstance);
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
        switch (v.getId()) {
            case R.id.btn_login:

                break;
            case R.id.btn_forget_password:

                break;
            case R.id.btn_go_register:

                break;
            case R.id.btn_login_qq:
                media = SHARE_MEDIA.QQ;
                addQQQZonePlatform();
                authToPlatform();
                break;
            case R.id.btn_login_sina:
                media = SHARE_MEDIA.SINA;
                authToPlatform();
                break;
            case R.id.btn_login_wx:
                media = SHARE_MEDIA.WEIXIN;
                addWXPlatform();
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

            }

            @Override
            public void onComplete(Bundle bundle, SHARE_MEDIA share_media) {
                if(bundle != null && !TextUtils.isEmpty(bundle.getString("uid"))){
                    mController.login(LoginActivity.this, share_media, loginListener);
                }else{
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
            if(status == 200){
                showToastShort("登录成功");
                mController.getPlatformInfo(LoginActivity.this, media, uMDataListener);
            }else{
                showToastShort("登录失败");
            }
        }
    };

    /**
     * 第三方登录
     * 获取来自第三方用户的信息
     */
    private SocializeListeners.UMDataListener uMDataListener = new SocializeListeners.UMDataListener(){
        @Override
        public void onStart() {

        }

        @Override
        public void onComplete(int status, Map<String, Object> info) {

        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if(ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }
    /**
     * QQ QQ空间授权
     * @return
     */
    private void addQQQZonePlatform() {
        // 添加QQ支持, 并且设置QQ分享内容的target url
        UMQQSsoHandler qqSsoHandler = new UMQQSsoHandler(this,
                Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
//        qqSsoHandler.setTargetUrl("http://www.umeng.com/social");
        qqSsoHandler.addToSocialSDK();

        // 添加QZone平台
        QZoneSsoHandler qZoneSsoHandler = new QZoneSsoHandler(this, Constants.Share.QQ_APP_KEY, Constants.Share.QQ_APPSECRET);
        qZoneSsoHandler.addToSocialSDK();

        //要分享的平台
        mController.getConfig().setPlatforms(SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
                SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.SINA);
        mController.openShare(this, false);

    }

    /**
     * 微信 朋友圈授权
     * @return
     */
    private void addWXPlatform() {
        // 注意：在微信授权的时候，必须传递appSecret
        // wx967daebe835fbeac是你在微信开发平台注册应用的AppID, 这里需要替换成你注册的AppID
        // 添加微信平台
        UMWXHandler wxHandler = new UMWXHandler(this, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxHandler.addToSocialSDK();

        // 支持微信朋友圈
        UMWXHandler wxCircleHandler = new UMWXHandler(this, Constants.Share.WEIXIN_APP_KEY, Constants.Share.WEIXIN_APPSECRET);
        wxCircleHandler.setToCircle(true);
        wxCircleHandler.addToSocialSDK();
    }


    //+++++++++++++++++++++++++++分享功能+++++++++++++++++++++++++++++++++++++++
    private void configPlatforms() {
        // 添加新浪SSO授权
        mController.getConfig().setSsoHandler(new SinaSsoHandler());
        // 添加QQ、QZone平台
        addQQQZonePlatform();

        // 添加微信、微信朋友圈平台
        addWXPlatform();
    }

    /**
     * 调用postShare分享。跳转至分享编辑页，然后再分享。</br> [注意]<li>
     * 对于新浪，豆瓣，人人，腾讯微博跳转到分享编辑页，其他平台直接跳转到对应的客户端
     *//*
    private void postShare() {
        CustomShareBoard shareBoard = new CustomShareBoard(getActivity());
        shareBoard.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
    }

    *//**
     * 直接分享，底层分享接口。如果分享的平台是新浪、腾讯微博、豆瓣、人人，则直接分享，无任何界面弹出； 其它平台分别启动客户端分享</br>
     *//*
    private void directShare() {
        mController.directShare(this, mPlatform, new SocializeListeners.SnsPostListener() {

            @Override
            public void onStart() {
            }

            @Override
            public void onComplete(SHARE_MEDIA platform, int eCode, SocializeEntity entity) {
                String showText = "分享成功";
                if (eCode != StatusCode.ST_CODE_SUCCESSED) {
                    showText = "分享失败 [" + eCode + "]";
                }
                Toast.makeText(getActivity(), showText, Toast.LENGTH_SHORT).show();
            }
        });
    }*/




    //=========================================================
}
