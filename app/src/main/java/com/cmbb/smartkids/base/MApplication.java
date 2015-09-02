package com.cmbb.smartkids.base;

import android.app.Application;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.baidu.mapapi.SDKInitializer;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.setting.SettingDetailActivity;
import com.cmbb.smartkids.rong.RongCloudEvent;
import com.cmbb.smartkids.rong.RongInfoContext;
import com.cmbb.smartkids.rong.message.DeAgreedFriendRequestMessage;
import com.cmbb.smartkids.rong.message.DeContactNotificationMessageProvider;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.log.LogWrapper;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.facebook.stetho.Stetho;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.UmengNotificationClickHandler;
import com.umeng.message.entity.UMessage;

import java.util.Map;

import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imlib.ipc.RongExceptionHandler;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/28 上午9:04
 */
public class MApplication extends Application {
    private static MApplication instance = null;
    private static Context mContext = null;
    private PushAgent mPushAgent;

    public static String token = "";
    public static String rongToken = "";
    public static int authority;// 用户权限
    public static int eredar;// 达人权限
    public static int userStatus;//用户状态

    @Override
    public void onCreate() {
        super.onCreate();
        if ("com.cmbb.smartkids".equals(TDevice.getCurProcessName(getApplicationContext())) || "io.rong.push".equals(TDevice.getCurProcessName(getApplicationContext()))) {

            instance = this;
            mContext = getApplicationContext();
            initLog();
//            initStetho();

            initSharePreference();
            //初始化百度地图
            // 在使用 SDK 各组间之前初始化 context 信息，传入 ApplicationContext
            SDKInitializer.initialize(this);
            //初始化umeng推送
            initPushAgent();
            // 初始化融云
            initRong();

            /* 必须在使用 RongIM 的进程注册回调、注册自定义消息等 */
            if ("com.cmbb.smartkids".equals(TDevice.getCurProcessName(getApplicationContext()))) {
                RongCloudEvent.init(this);
                RongInfoContext.init(this);
                Thread.setDefaultUncaughtExceptionHandler(new RongExceptionHandler(this));
                try {
                    RongIM.registerMessageType(DeAgreedFriendRequestMessage.class);
                    RongIM.registerMessageTemplate(new DeContactNotificationMessageProvider());

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            initUmengAnalytics();
        }

    }

    private void initRong() {
        RongIM.init(this);
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

    private void initUmengAnalytics() {
        MobclickAgent.setDebugMode(false);
        MobclickAgent.setCatchUncaughtExceptions(true);
    }


    /**
     * 友盟推送
     */
    private void initPushAgent() {
        mPushAgent = PushAgent.getInstance(this);
        mPushAgent.setDebugMode(true);

        /**
         * 该Handler是在IntentService中被调用，故
         * 1. 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * 2. IntentService里的onHandleIntent方法是并不处于主线程中，因此，如果需调用到主线程，需如下所示;
         * 	      或者可以直接启动Service
         * */
        UmengMessageHandler messageHandler = new UmengMessageHandler() {
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        UTrack.getInstance(getApplicationContext()).trackMsgClick(msg);
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public Notification getNotification(Context context, UMessage msg) {
                switch (msg.builder_id) {
                    case 0:
                        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context)
                                .setLargeIcon(getAppIcon())
                                .setSmallIcon(R.mipmap.ic_launcher)
                                .setContentTitle(msg.title)
                                .setContentText(msg.text);
                        mBuilder.setAutoCancel(true);
                        Notification mNotification = mBuilder.build();
                        return mNotification;
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, msg);
                }
            }
        };
        mPushAgent.setMessageHandler(messageHandler);

        /**
         * 该Handler是在BroadcastReceiver中被调用，故
         * 如果需启动Activity，需添加Intent.FLAG_ACTIVITY_NEW_TASK
         * */
        UmengNotificationClickHandler notificationClickHandler = new UmengNotificationClickHandler() {
            @Override
            public void dealWithCustomAction(Context context, UMessage msg) {
                //Toast.makeText(context, "i cclick :" + msg.custom, Toast.LENGTH_LONG).show();
                handleNotificationClick(msg);
            }
        };
        mPushAgent.setNotificationClickHandler(notificationClickHandler);
    }

    private Bitmap getAppIcon() {
        BitmapDrawable bitmapDrawable;
        Bitmap appIcon;
        bitmapDrawable = (BitmapDrawable) MApplication.getContext().getApplicationInfo().loadIcon(RongContext.getInstance().getPackageManager());
        appIcon = bitmapDrawable.getBitmap();
        return appIcon;
    }


    private void handleNotificationClick(UMessage msg) {
        Map<String, String> params = msg.extra;
        String notifyTitle = params.get("title");
        String notifyContent = params.get("content");
        Intent intent = new Intent(getContext(), SettingDetailActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtra("flags", "notify");
        intent.putExtra("title", "萌宝派");
        intent.putExtra("notify_title", notifyTitle);
        intent.putExtra("notify_content", notifyContent);
        startActivity(intent);
    }


    /**
     * @return instance
     * 获取Application 单例
     */
    public static MApplication getInstance() {
        return instance;
    }

    public static Context getContext() {
        return mContext;
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
