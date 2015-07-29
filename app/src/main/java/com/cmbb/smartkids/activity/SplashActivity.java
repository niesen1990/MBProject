package com.cmbb.smartkids.activity;

import android.content.Intent;
import android.os.Bundle;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.tools.sp.SPCache;
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
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                    finish();
                }
            }
        };
        timer.schedule(timerTask, 1500);
    }
}
