package com.cmbb.smartkids.activity.replay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.ImagePreviewActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
import com.cmbb.smartkids.fragment.replay.PostDetail;
import com.cmbb.smartkids.fragment.replay.ReplayCaseListFragment;
import com.cmbb.smartkids.fragment.replay.ReplayListViewHolder;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.ShareUtils;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

import java.io.IOException;
import java.util.ArrayList;

public class ReplayCaseActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener, ReplayListViewHolder.OnReplayItemClickListener {

    CaseDetailListModel mCaseDetailListModel;
    PostDetail mPostDetail = new PostDetail();
    ReplayCaseListFragment mReplayListFragment;
    private CollapsingToolbarLayout collapsingToolbar;

    private FloatingActionButton fabReplay;

    private LinearLayout headContainer;

    BroadcastReceiver postDetailReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra(Constants.NETWORK_FLAG, false)) {
                mPostDetail = intent.getParcelableExtra(Constants.Post.POSTDETAIL_DATA);
                setHeadViewData(mPostDetail, headContainer);
            } else {
                showToast(intent.getStringExtra(Constants.NETWORK_FAILURE));
            }
        }
    };

    private ImageView mRivHead;
    private TextView mTvNick;
    private TextView mTvHeaderType;
    private TextView mTvHeaderMessage;
    private ImageView mIvRanktag;
    private ImageView mIvRanklev;
    private TextView mTvHeaderTime;
    private AppBarLayout appbar;

    private TextView btnSpot;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_replay_wonder;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mCaseDetailListModel = getIntent().getParcelableExtra("model");
        mController = ShareUtils.instanceOf(this);
        assignViews();

    }

    private void assignViews() {
        btnSpot = (TextView) findViewById(R.id.btn_spot);
        btnSpot.setVisibility(View.GONE);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        mRivHead = (ImageView) findViewById(R.id.riv_head);
        mTvNick = (TextView) findViewById(R.id.tv_nick);
        mTvHeaderType = (TextView) findViewById(R.id.tv_header_type);
        mTvHeaderMessage = (TextView) findViewById(R.id.tv_header_message);
        mIvRanktag = (ImageView) findViewById(R.id.iv_ranktag);
        mIvRanklev = (ImageView) findViewById(R.id.iv_ranklev);
        mTvHeaderTime = (TextView) findViewById(R.id.tv_header_time);
        fabReplay = (FloatingActionButton) findViewById(R.id.fab_replay);
        fabReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ReplayCaseActivity.this, ReplayAddCaseActivity.class);
                intent.putExtra("model", mCaseDetailListModel);
                startActivityForResult(intent, 1);
            }
        });
    }

    private void setHeadViewData(PostDetail postDetails, LinearLayout linearLayout) {

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mPostDetail.getTitle());
        collapsingToolbar.setExpandedTitleColor(android.R.color.transparent);
        // 设置标题
        GlideTool.loadImage(this, mCaseDetailListModel.getUserSmallHeadImg(), mRivHead, true);
        mTvNick.setText(mCaseDetailListModel.getNike());
        mTvHeaderTime.setText(postDetails.getDate());
        mTvHeaderMessage.setText(postDetails.getRelpys() + "");
        mTvHeaderType.setText("专家");
        setHeadContent(linearLayout, postDetails);
        //--------------------
    }

    ArrayList<String> pagerUrls = new ArrayList<>();

    private void setHeadContent(LinearLayout linearLayout, PostDetail postDetails) {
        pagerUrls.clear();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(TDevice.dip2px(8, this), TDevice.dip2px(8, this), TDevice.dip2px(8, this), TDevice.dip2px(8, this));
        // 添加内容
        TextView textTitle = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textTitle.setLayoutParams(params);
        textTitle.setTextIsSelectable(true);
        textTitle.setText(mPostDetail.getTitle());
        linearLayout.addView(textTitle);

        TextView textContent = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textContent.setLayoutParams(params);
        textContent.setTextIsSelectable(true);
        textContent.setText(mPostDetail.getContext());
        linearLayout.addView(textContent);
        // 添加图片
        String imgUrl = postDetails.getBigImg();

        if (!TextUtils.isEmpty(imgUrl)) {
            if (imgUrl.contains("#")) {
                final String[] imgUrls = imgUrl.split("\\^#\\^");
                for (int j = 0; j < imgUrls.length; j++) {
                    String[] cache = imgUrls[j].split(",");
                    for (int k = 0; k < cache.length; k++) {
                        if (cache[k].contains("bigImage")) {
                            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_image, null);
                            imageView.setLayoutParams(params);
                            shareImgUrl = cache[k];
                            pagerUrls.add(cache[k]);
                            GlideTool.loadImage(this, cache[k], imageView, false);
                            imageView.setTag(R.id.image, pagerUrls.size() - 1);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int position = (int) v.getTag(R.id.image);
                                    Intent intent = new Intent(ReplayCaseActivity.this, ImagePreviewActivity.class);
                                    intent.putExtra("index", position);
                                    intent.putExtra("data", pagerUrls);
                                    startActivity(intent);
                                }
                            });
                            linearLayout.addView(imageView);

                            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                            textView.setLayoutParams(params);
                            textView.setTextIsSelectable(true);
                            if (cache.length >= 4) {
                                textView.setText(cache[0]);
                                linearLayout.addView(textView);
                            }
                        }
                    }
                }
            } else {
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_image, null);
                imageView.setLayoutParams(params);
                if (imgUrl.split(",").length == 4) {
                    shareImgUrl = imgUrl.split(",")[1];
                    pagerUrls.add(imgUrl.split(",")[1]);
                    GlideTool.loadImage(this, imgUrl.split(",")[1], imageView, false);
                    linearLayout.addView(imageView);
                    TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                    textView.setLayoutParams(params);
                    textView.setTextIsSelectable(true);
                    textView.setText(imgUrl.split(",")[0]);
                    linearLayout.addView(textView);
                } else {
                    shareImgUrl = imgUrl.split(",")[0];
                    pagerUrls.add(imgUrl.split(",")[0]);
                    GlideTool.loadImage(this, imgUrl.split(",")[0], imageView, false);
                    linearLayout.addView(imageView);
                }
            }
        }
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textView.setLayoutParams(params);
        textView.setText("精彩回复:");
        textView.setTextColor(getResources().getColor(R.color.gold));
        linearLayout.addView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headContainer = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
        headContainer.setLayoutParams(params);

        mReplayListFragment = new ReplayCaseListFragment(true, mCaseDetailListModel, headContainer, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mReplayListFragment).commitAllowingStateLoss();
        ApiNetwork.getCaseReplayDetail(this, mCaseDetailListModel);
        appbar.addOnOffsetChangedListener(this);
        IntentFilter intentFilter = new IntentFilter(Constants.Post.POSTDETAIL_DATA_INTENT);
        registerReceiver(postDetailReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appbar.removeOnOffsetChangedListener(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(postDetailReceiver);
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        menu.clear();
        if (mPostDetail.getStore() > 0) {
            getMenuInflater().inflate(R.menu.menu_replay_case_with, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_replay_case, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    private String shareImgUrl;
    int sort = 1;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                ShareUtils.configPlatforms();
                StringBuffer sb = new StringBuffer();
                sb.append(Constants.SHARE_URL);
                sb.append("?id=");
                sb.append(mCaseDetailListModel.getId());
                sb.append("&type=");
                sb.append(mCaseDetailListModel.getType());
                sb.append("&areaType=");
                sb.append("HELP");
                ShareUtils.setShareContent(mPostDetail.getTitle(), mPostDetail.getContext(), sb.toString(), shareImgUrl);
                ShareUtils.showShareView();
                break;
            case R.id.action_collect:
                doCollection();
                break;
            case R.id.action_sort:
                sort = sort == 1 ? 2 : 1;
                LinearLayout.LayoutParams paramsCache = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout headCache = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
                headCache.setLayoutParams(paramsCache);
                setHeadContent(headCache, mPostDetail);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReplayCaseListFragment(true, mCaseDetailListModel, headCache, this)).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            mReplayListFragment.getmSwipeRefresh().setEnabled(true);
        } else {
            mReplayListFragment.getmSwipeRefresh().setEnabled(false);
        }
    }

    private UMSocialService mController;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    private void doCollection() {
        if (mPostDetail.getStore() > 0) {
            ApiNetwork.cancelCollection(MApplication.token, mCaseDetailListModel.getId() + "", "HELP", new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result = response.body().string();
                    if (result.contains("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mPostDetail.setStore(0);
                                showToast("取消成功");
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast("取消失败");
                            }
                        });
                    }
                }
            });
        } else {
            ApiNetwork.addCollection(MApplication.token, mCaseDetailListModel.getId() + "", "HELP", "HELP", new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override
                public void onResponse(Response response) throws IOException {
                    String result = response.body().string();
                    if (result.contains("1")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mPostDetail.setStore(1);
                                showToast("收藏成功");
                            }
                        });
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast("收藏失败");
                            }
                        });
                    }
                }
            });
        }

    }

    @Override
    public void onReplayItemClick(View view) {
        ReplayModel replayModel = (ReplayModel) view.getTag();
        Intent intent = new Intent(ReplayCaseActivity.this, ReplayAddCaseActivity.class);
        intent.putExtra("model", mCaseDetailListModel);
        intent.putExtra("id", replayModel.getId());
        intent.putExtra("floor", replayModel.getFloor());
        startActivityForResult(intent, 1);
    }
}
