package com.cmbb.smartkids.activity.setting;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.bumptech.glide.Glide;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.network.OkHttp;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SettingActivity extends MActivity {
    private final String TAG = SettingActivity.class.getSimpleName();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
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
                        File cacheFile = Glide.getPhotoCacheDir(SettingActivity.this);
                        if (cacheFile != null) {
                            cacheFile.delete();
                        }
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

    private void sendLogoutRequest() {
        showWaitDialog("注销中...");
        Map<String, String> params = new HashMap<>();
        //        params.put("token", );
        OkHttp.asyncPost(Constants.User.LOGINOUT_URL, params, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                hideWaitDialog();
                showToast(R.string.service_error);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                hideWaitDialog();
                //响应成功 数据处理
            }
        });
    }


}
