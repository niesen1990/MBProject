package com.cmbb.smartkids.activity.setting;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.tools.ShareUtils;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.bean.SocializeEntity;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.controller.listener.SocializeListeners;

import java.io.File;

public class SettingActivity extends MActivity {
    private final String TAG = SettingActivity.class.getSimpleName();
    private UMSocialService mController;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mController = ShareUtils.instanceOf(this);
        ShareUtils.addQQQZonePlatform();
        ShareUtils.addWXPlatform();
        findViewById(R.id.rl_set_clear_cache).setOnClickListener(this);
        findViewById(R.id.rl_set_about).setOnClickListener(this);
        findViewById(R.id.rl_set_declaration).setOnClickListener(this);
        findViewById(R.id.rl_set_suggest).setOnClickListener(this);
        findViewById(R.id.rl_set_logout).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_set_clear_cache:
                AlertDialog.Builder dialog = new AlertDialog.Builder(v.getContext());
                dialog.setTitle("确定清除缓存么？");
                dialog.setMessage("清除后您的相册，用户头像以及缓存下来的照片都将重新下载");
                dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        /*File cacheFile = Glide.getPhotoCacheDir(SettingActivity.this);
                        if (cacheFile != null) {
                            cacheFile.delete();
                        }*/
                    }
                });
                dialog.setNegativeButton("取消", null);
                dialog.show();
                break;
            case R.id.rl_set_about:
                Intent about = new Intent(SettingActivity.this, SettingDetailActivity.class);
                about.putExtra("flags", "about");
                about.putExtra("title", "关于萌宝派");
                startActivity(about);
                break;
            case R.id.rl_set_declaration:
                Intent declaration = new Intent(SettingActivity.this, SettingDetailActivity.class);
                declaration.putExtra("flags", "declaration");
                declaration.putExtra("title", "免责声明");
                startActivity(declaration);
                break;
            case R.id.rl_set_suggest:
                Intent suggest = new Intent(SettingActivity.this, SettingDetailActivity.class);
                suggest.putExtra("flags", "suggest");
                suggest.putExtra("title", "意见反馈");
                startActivity(suggest);
                break;
            case R.id.rl_set_logout:
                AlertDialog.Builder diaLogout = new AlertDialog.Builder(v.getContext());
                diaLogout.setTitle("你确定要退出当前账号么？");
                diaLogout.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // 注销
                        sendLogoutRequest();
                    }
                });
                diaLogout.setNegativeButton("取消", null);
                diaLogout.show();
                break;
        }
    }

    /**
     * 登出
     */
    private void sendLogoutRequest() {
        showWaitDialog("注销中...");
        /*Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        Log.i("logout", "logout = " + MApplication.token);
        OkHttp.asyncPost(Constants.User.LOGINOUT_URL, body, TAG, new Callback() {
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
            public void onResponse(final Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (response.isSuccessful()) {
                            try {
                                String result = response.body().string();
                                Log.i("logout", "logout = " + result);
                                if (result.contains("1")) {
                                    //响应成功 数据处理
                                    MApplication.token = "";
                                    MApplication.rongToken = "";
                                    MApplication.userStatus = 0;
                                    MApplication.eredar = 0;
                                    MApplication.authority = 0;
                                    SPCache.clear();
                                    hideWaitDialog();
                                    Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                } else {
                                    hideWaitDialog();
                                    showToast("注销失败");
                                }
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            hideWaitDialog();
                            showToast("注销失败");
                        }
                    }
                });
            }
        });*/
        //响应成功 数据处理
        MApplication.token = "";
        MApplication.rongToken = "";
        MApplication.userStatus = 0;
        MApplication.eredar = 0;
        MApplication.authority = 0;
        SPCache.clear();
        deleteCache(MApplication.getContext());
        deleteAuth(this);

        // 设置第一次启动标识
        SPCache.putBoolean(Constants.SharePreference.IS_FIRST_INTO, false);
        hideWaitDialog();
        Intent intent = new Intent(SettingActivity.this, LoginActivity.class);
        startActivity(intent);
        finish();
    }

    public void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    private void deleteAuth(final Context mContext) {
        mController.deleteOauth(mContext, SHARE_MEDIA.SINA,
                new SocializeListeners.SocializeClientListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, SocializeEntity entity) {
                        if (status == 200) {
                        } else {
                        }
                    }
                });

        mController.deleteOauth(mContext, SHARE_MEDIA.WEIXIN,
                new SocializeListeners.SocializeClientListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, SocializeEntity entity) {
                        if (status == 200) {
                        } else {
                        }
                    }
                });
        mController.deleteOauth(mContext, SHARE_MEDIA.QQ,
                new SocializeListeners.SocializeClientListener() {
                    @Override
                    public void onStart() {
                    }

                    @Override
                    public void onComplete(int status, SocializeEntity entity) {
                        if (status == 200) {
                        } else {
                        }
                    }
                });
    }
}
