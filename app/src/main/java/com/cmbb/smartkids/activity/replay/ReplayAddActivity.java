package com.cmbb.smartkids.activity.replay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.ReplayBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReplayAddActivity extends MActivity {

    private CardView cardview;
    private ImageView ivAddPic;
    private TextInputLayout textInputLayout;
    private EditText etContentPic;
    private TextView btnSend;

    private int sort = 1;

    private int floor;
    PostModel mPostModel;
    int mParentReplyId;
    private File send_image;

    private void assignViews() {
        cardview = (CardView) findViewById(R.id.cardview);

        ivAddPic = (ImageView) findViewById(R.id.iv_add_pic);
        ivAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(ReplayAddActivity.this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        etContentPic = (EditText) findViewById(R.id.et_content_pic);
        btnSend = (TextView) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String conent = etContentPic.getText().toString();
                if (TextUtils.isEmpty(conent)) {
                    showToast("请输入回复内容");
                    return;
                } else {
                    sendMessage(mParentReplyId, conent);
                }
            }
        });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_replay_add;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPostModel = getIntent().getParcelableExtra("model");
        mParentReplyId = getIntent().getIntExtra("id", -1);
        floor = getIntent().getIntExtra("floor", -1);
        if (floor == -1) {

        } else {
            getSupportActionBar().setTitle("回复" + floor + "楼");
        }

        assignViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_replay_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final static int REQUEST_CODE = 1;
    private ArrayList<String> imgs = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                imgs = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                if (imgs.size() == 0) return;
                send_image = new File(imgs.get(0));
                PicassoTool.loadImage(this, imgs.get(0), ivAddPic, false);
            }
        }
    }


    private void sendMessage(int parentReplyId, String content) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        // 判断是否是回帖
        if (-1 != parentReplyId) {
            body.put("parentReplyId", parentReplyId + "");
        }
        body.put("context", content);
        body.put("sort", sort + "");
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("send", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        showWaitDialog("正在提交中...");
        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "AddReplys", body, send_image, new Callback() {
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
                    final ReplayBaseModel replayMessageBaseModel = gson.fromJson(result, ReplayBaseModel.class);
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            if (replayMessageBaseModel.getCode().equals("1")) {
                                showToast("回复成功");
                                for (int i = 0; i < replayMessageBaseModel.getContext().size(); i++) {
                                    Log.i("replay_result", "replay_result = " + replayMessageBaseModel.getContext().get(i).toString());
                                }
                                Intent intent = new Intent();
                                intent.putParcelableArrayListExtra("data", replayMessageBaseModel.getContext());
                                setResult(10, intent);
                                finish();
                            } else {
                                showToast("回复失败");
                            }
                        }
                    });

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
}
