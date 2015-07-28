package com.cmbb.smartkids;

import android.app.Application;

import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.log.LogWrapper;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.facebook.stetho.Stetho;
import com.pgyersdk.crash.PgyCrashManager;
import com.umeng.analytics.MobclickAgent;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/28 上午9:04
 */
public class MApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        initStetho();
        initUmengAnalytics();
        initLog();
    }

    /**
     * 初始化Stetho
     */
    private void initStetho() {
        Stetho.initialize(Stetho.newInitializerBuilder(this)
                .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
                .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
                .build());
    }

    /**
     * 初始化图片处理Glide
     */
    private void initUmengAnalytics() {
        MobclickAgent.setDebugMode(true);
    }

    /**
     * 蒲公英内测
     */
    private void initPgyer() {
        String appId = "myappid";  //蒲公英注册或上传应用获取的AppId
        PgyCrashManager.register(this, appId);
    }

    /**
     * 初始化 日志
     */
    private void initLog() {
        Log.setLogNode(new LogWrapper());
    }

    /**
     * 初始化sharepreference
     * 用法：
     * int count = SpCache.getInt(ACTIVITY_CREATE_COUNT, 0) + 1;
     * SpCache.putInt(ACTIVITY_CREATE_COUNT, count);
     */
    private void initSharePreference() {
        SPCache.init(this);
    }
}
