package com.cmbb.smartkids.activity.setting;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.TDevice;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

/**
 * Created by javon on 2015/8/5.
 */
public class SettingDetailActivity extends MActivity {
    private final String TAG = SettingDetailActivity.class.getSimpleName();
    private LinearLayout llAbout, llDeclaration, llSuggest;
    private EditText etSuggest;
    private TextView tvVersion, tvContent;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_setting_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        llAbout = (LinearLayout) findViewById(R.id.ll_setting_about);
        llDeclaration = (LinearLayout) findViewById(R.id.ll_setting_declaration);
        llSuggest = (LinearLayout) findViewById(R.id.ll_setting_suggest);
        etSuggest = (EditText) findViewById(R.id.et_setting_suggest);
        tvVersion = (TextView) findViewById(R.id.tv_setting_about_version);
        tvContent = (TextView) findViewById(R.id.tv_setting_about_content);
        findViewById(R.id.tv_setting_suggest_commit).setOnClickListener(this);
    }

    private void initData() {
        Bundle bundle = null;
        if (getIntent() != null && getIntent().getExtras() != null) {
            bundle = getIntent().getExtras();
        }
        String flags = bundle.getString("flags");
        String title = bundle.getString("title");
        String notifyTitle = bundle.getString("notify_title");
        String notifyContent = bundle.getString("notify_content");
        if (getCustomBar() != null)
            getCustomBar().setTitle(title);
        if ("about".equals(flags)) {
            llAbout.setVisibility(View.VISIBLE);
            tvVersion.setText("当前版本" + TDevice.getVersionName());
        } else if ("declaration".equals(flags)) {
            llDeclaration.setVisibility(View.VISIBLE);
        } else if ("suggest".equals(flags)) {
            llSuggest.setVisibility(View.VISIBLE);
        } else if ("notify".equals(flags)) {
            llAbout.setVisibility(View.VISIBLE);
            tvVersion.setText(notifyTitle);
            tvContent.setText(notifyContent);
        }
    }

    @Override
    public void onClick(View v) {
        String suggestContent = etSuggest.getText().toString();
        if (TextUtils.isEmpty(suggestContent)) {
            showToast("请输入反馈的内容");
            return;
        }
        showWaitDialog("提交中...");
        ApiNetwork.sendSuggestion(suggestContent, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("请检测网络");
                        hideWaitDialog();
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast("提交成功");
                        hideWaitDialog();
                    }
                });
            }
        });
    }
}
