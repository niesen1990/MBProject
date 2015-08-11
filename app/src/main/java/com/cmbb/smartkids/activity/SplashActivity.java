package com.cmbb.smartkids.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.umeng.message.IUmengRegisterCallback;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by N.Sun
 */
public class SplashActivity extends MActivity {

    private final static String TAG = SplashActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mPushAgent.enable(mRegisterCallback);
        initTask();
    }


    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }


    // 友盟推送注册器
    private IUmengRegisterCallback mRegisterCallback = new IUmengRegisterCallback() {

        @Override
        public void onRegistered(String registrationId) {
            Log.e("mRegisterCallback", "token:" + mPushAgent.getRegistrationId());

        }
    };

    private void initTask() {
        // 引导界面
        final boolean isFirstInto = SPCache.getBoolean(Constants.SharePreference.IS_FIRST_INTO, true);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isFirstInto) {
                    startActivity(new Intent(SplashActivity.this, GuideActivity.class));
                    finish();
                } else {
                    // 登陆
                    MApplication.token = SPCache.getString(Constants.SharePreference.USER_TOKEN, "");
                    MApplication.rongToken = SPCache.getString(Constants.SharePreference.RONG_TOKEN, "");
                    MApplication.eredar = SPCache.getInt(Constants.SharePreference.USER_EREDAR, 0);
                    MApplication.userStatus = SPCache.getInt(Constants.SharePreference.USER_USERSTATUS, 0);
                    MApplication.authority = SPCache.getInt(Constants.SharePreference.USER_AUTHORITY, 0);

                    if (TextUtils.isEmpty(MApplication.token)) {
                        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        ApiNetwork.login(MApplication.token);
                        ApiNetwork.getUserInfoList();
                        startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    }
                    finish();
                }
            }
        };
        timer.schedule(timerTask, 1500);
    }
}
