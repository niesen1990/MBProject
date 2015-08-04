package com.cmbb.smartkids.activity.replay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.PostDetail;
import com.cmbb.smartkids.fragment.replay.ReplayListFragment;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.tools.log.Log;

public class ReplayWonderActivity extends MActivity {

    PostModel mPostModel;
    PostDetail mPostDetail;
    ReplayListFragment mReplayListFragment;

    private LinearLayout headContainer;

    BroadcastReceiver postDetailReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra(Constants.NETWORK_FLAG, false)) {
                mPostDetail = intent.getParcelableExtra(Constants.Post.POSTDETAIL_DATA);
                Log.i("PostDetail", mPostDetail.toString());
                setHeadViewData(mPostDetail);
            } else {
                showToast(intent.getStringExtra(Constants.NETWORK_FAILURE));
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_replay_wonder;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPostModel = getIntent().getParcelableExtra("model");
        headContainer = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
        mReplayListFragment = new ReplayListFragment(true, mPostModel, headContainer);
        getSupportFragmentManager().beginTransaction().add(R.id.container, mReplayListFragment).commit();
        ApiNetwork.getReplayDetail(this, mPostModel);
    }

    private void setHeadViewData(PostDetail postDetails) {
        // 设置标题
        // 添加图片
        String imgUrl = postDetails.getBigImg();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(48, 12, 48, 12);
        if (!TextUtils.isEmpty(imgUrl)) {
            if (imgUrl.contains("#")) {
                final String[] imgUrls = imgUrl.split("\\^#\\^");
                for (int j = 0; j < imgUrls.length; j++) {
                    String[] cache = imgUrls[j].split(",");
                    for (int k = 0; k < cache.length; k++) {
                        if (cache[k].contains("bigImage")) {
                            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_image, null);
                            imageView.setLayoutParams(params);
                            imageView.setAdjustViewBounds(true);
                            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                            GlideTool.loadImage(this, cache[k], imageView, false);
                            headContainer.addView(imageView);

                            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                            textView.setLayoutParams(params);
                            textView.setTextIsSelectable(true);
                            textView.setPadding(0, 6, 0, 6);
                            if (cache.length >= 4) {
                                textView.setText(cache[0]);
                                headContainer.addView(textView);
                            }
                        }
                    }
                }
            } else {
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_image, null);
                imageView.setLayoutParams(params);
                if (imgUrl.split(",").length == 4) {
                    GlideTool.loadImage(this, imgUrl.split(",")[1], imageView, false);
                    headContainer.addView(imageView);
                    TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                    textView.setLayoutParams(params);
                    textView.setTextIsSelectable(true);
                    textView.setPadding(0, 6, 0, 6);
                    textView.setText(imgUrl.split(",")[0]);
                    headContainer.addView(textView);
                } else {
                    GlideTool.loadImage(this, imgUrl.split(",")[0], imageView, false);
                    headContainer.addView(imageView);
                }
            }
        }
        TextView textView = new TextView(this);
        textView.setLayoutParams(params);
        textView.setText("精彩回复:");
        headContainer.addView(textView);
        //--------------------
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Constants.Post.POSTDETAIL_DATA_INTENT);
        registerReceiver(postDetailReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(postDetailReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_replay_wonder, menu);
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
}