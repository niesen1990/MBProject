package com.cmbb.smartkids.activity.master;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.eredar.EredarLeftBaseModel;
import com.cmbb.smartkids.fragment.eredar.EredarTypeModel;
import com.cmbb.smartkids.model.eredar.AddEredarBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MasterAddActivity extends MActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_master_add;
    }

    private Spinner spinner;
    private EditText masterAddApplicationDoyen;
    private EditText masterAddInputTel;
    private EditText masterAddInputWeichat;
    private EditText masterAddInputQq;

    private void assignViews() {
        spinner = (Spinner) findViewById(R.id.spinner);
        masterAddApplicationDoyen = (EditText) findViewById(R.id.master_add_application_doyen);
        masterAddInputTel = (EditText) findViewById(R.id.master_add_input_tel);
        masterAddInputWeichat = (EditText) findViewById(R.id.master_add_input_weichat);
        masterAddInputQq = (EditText) findViewById(R.id.master_add_input_qq);
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
        attemptMasterType();
        showAlertDialog("申请达人协议", getResources().getString(R.string.daren_shenqing_xieyi));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_master_add, menu);
        return true;
    }

    private int masterType;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_submit:
                attemptApply(masterType, masterAddApplicationDoyen.getText().toString(), masterAddInputTel.getText().toString(), masterAddInputWeichat.getText().toString(), masterAddInputQq.getText().toString());
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void attemptApply(int masterType, String reason, String phone, String weixin, String qq) {
        if (TextUtils.isEmpty(masterType + "")) {
            showToast("请选择达人类型");
            return;
        }
        if (TextUtils.isEmpty(reason)) {
            showToast("请输入申请理由");
            return;
        }
        if (TextUtils.isEmpty(phone)) {
            showToast("请输入手机号码");
            return;
        }
        if (TextUtils.isEmpty(weixin)) {
            showToast("请输入微信号");
            return;
        }
        if (TextUtils.isEmpty(qq)) {
            showToast("请输入QQ号");
            return;
        }
        showWaitDialog("正在提交中...");
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", masterType + "");
        body.put("context", reason);
        body.put("phone", phone);
        body.put("weixin", weixin);
        body.put("qq", qq);
        OkHttp.asyncPost(Constants.User.EREDARADDEREDAR_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideWaitDialog();
                        showToast(getResources().getString(R.string.rc_network_error));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    final AddEredarBaseModel addEredarBaseModel = gson.fromJson(result, AddEredarBaseModel.class);
                    if (addEredarBaseModel.getCode().equals("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast(addEredarBaseModel.getContext().getPresentation());
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast(addEredarBaseModel.getContext().getPresentation());
                            }
                        });
                    }
                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                }
            }
        });
    }

    /**
     * 显示发帖规则
     *
     * @param title
     * @param content
     */
    public void showAlertDialog(String title, String content) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(content)
                .setCancelable(false)
                .setPositiveButton("同意", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }


    private void attemptMasterType() {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", 1 + "");
        OkHttp.asyncPost(Constants.Master.EREDARFINDTYPE_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        showToast(getResources().getString(R.string.rc_network_error));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();

                    Gson gson = new Gson();
                    final EredarLeftBaseModel data = gson.fromJson(result, EredarLeftBaseModel.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (data.getCode().equals("1")) {
                                mMasterTypeModels = data.getContext();
                                SimpleSpinnerAdapter simpleSpinnerAdapter = new SimpleSpinnerAdapter();
                                spinner.setAdapter(simpleSpinnerAdapter);
                                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                    @Override
                                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                        masterType = mMasterTypeModels.get(position).getEredarType();
                                    }

                                    @Override
                                    public void onNothingSelected(AdapterView<?> parent) {

                                    }
                                });
                            } else {
                                showToast("获取达人列表失败");
                            }
                        }
                    });

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                }
            }
        });
    }

    ArrayList<EredarTypeModel> mMasterTypeModels = new ArrayList<>();

    class SimpleSpinnerAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return mMasterTypeModels.size();
        }

        @Override
        public EredarTypeModel getItem(int position) {
            return mMasterTypeModels.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = getLayoutInflater().inflate(R.layout.layout_text_one, parent, false);
            TextView textView = (TextView) view.findViewById(R.id.tv_left);
            textView.setText(getItem(position).getName());
            return view;
        }
    }
}
