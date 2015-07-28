package com.cmbb.smartkids.mbproject;

import android.app.Application;

import com.facebook.stetho.Stetho;
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
}
