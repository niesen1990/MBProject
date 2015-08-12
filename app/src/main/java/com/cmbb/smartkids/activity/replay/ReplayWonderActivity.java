package com.cmbb.smartkids.activity.replay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.PostDetail;
import com.cmbb.smartkids.fragment.replay.ReplayListFragment;
import com.cmbb.smartkids.fragment.replay.ReplayListViewHolder;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.RankTools;
import com.cmbb.smartkids.tools.ShareUtils;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.tools.log.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

import java.io.IOException;

public class ReplayWonderActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener, ReplayListViewHolder.OnReplayItemClickListener {

    PostModel mPostModel;
    PostDetail mPostDetail = new PostDetail();
    ReplayListFragment mReplayListFragment;
    int sort = 1;

    private String shareImgUrl;
    private CollapsingToolbarLayout collapsingToolbar;

    private FloatingActionButton fabReplay;

    private LinearLayout headContainer;

    BroadcastReceiver postDetailReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getBooleanExtra(Constants.NETWORK_FLAG, false)) {
                mPostDetail = intent.getParcelableExtra(Constants.Post.POSTDETAIL_DATA);
                setHeadViewData(headContainer);
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
        mPostModel = getIntent().getParcelableExtra("model");
        mController = ShareUtils.instanceOf(this);
        assignViews();
    }

    private void assignViews() {
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
                Intent intent = new Intent(ReplayWonderActivity.this, ReplayAddActivity.class);
                intent.putExtra("model", mPostModel);
                intent.putExtra("id", -1);
                intent.putExtra("floor", -1);
                startActivityForResult(intent, 1);
            }
        });
        btnSpot = (TextView) findViewById(R.id.btn_spot);

    }

    private void setHeadViewData(LinearLayout linearLayout) {
        btnSpot.setText(mPostDetail.getSpotCount() + "");
        if (mPostDetail.getCurrentUserSpot() >= 1) {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_spot_yellow);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btnSpot.setCompoundDrawables(drawable, null, null, null);
        } else {
            Drawable drawable = getResources().getDrawable(R.drawable.ic_spot_white);
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
            btnSpot.setCompoundDrawables(drawable, null, null, null);
        }
        btnSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mPostDetail.getCurrentUserSpot() >= 1) {
                    ApiNetwork.cancelSpot(MApplication.token, mPostModel.getId() + "", mPostModel.getAreaType(), mPostDetail.getType(), mPostModel.getPortConnector(), new Callback() {
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
                                final String result = response.body().string();
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (result.contains("1")) {
                                            showToast("取消点赞成功");
                                            mPostDetail.setSpotCount(mPostDetail.getSpotCount() - 1);
                                            btnSpot.setText(mPostDetail.getSpotCount() + "");
                                            Drawable drawable = getResources().getDrawable(R.drawable.ic_spot_white);
                                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                            btnSpot.setCompoundDrawables(drawable, null, null, null);
                                            mPostDetail.setCurrentUserSpot(0);
                                        } else {
                                            showToast("取消失败");
                                            btnSpot.setBackgroundResource(R.drawable.ic_spot_yellow);
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
                } else {
                    ApiNetwork.addSpot(MApplication.token, mPostModel.getId() + "", mPostModel.getAreaType(), mPostDetail.getType(), mPostModel.getPortConnector(), new Callback() {
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
                                final String result = response.body().string();
                                Log.i("spot", "spot = " + result);
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        if (result.contains("1")) {
                                            showToast("点赞成功");
                                            mPostDetail.setSpotCount(mPostDetail.getSpotCount() + 1);
                                            btnSpot.setText(mPostDetail.getSpotCount() + "");
                                            Drawable drawable = getResources().getDrawable(R.drawable.ic_spot_yellow);
                                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                            btnSpot.setCompoundDrawables(drawable, null, null, null);
                                            mPostDetail.setCurrentUserSpot(1);
                                        } else {
                                            showToast("点赞失败");
                                            Drawable drawable = getResources().getDrawable(R.drawable.ic_spot_white);
                                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                            btnSpot.setCompoundDrawables(drawable, null, null, null);
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
            }
        });

        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbar.setTitle(mPostDetail.getTitle());
        collapsingToolbar.setExpandedTitleColor(android.R.color.transparent);
        // 设置标题
        GlideTool.loadImage(this, mPostModel.getUserSmallHeadImg(), mRivHead, true);
        mTvNick.setText(mPostModel.getNike());
        mTvHeaderTime.setText(mPostDetail.getDate());
        mTvHeaderMessage.setText(mPostDetail.getRelpys() + "");
        long[] ranks = RankTools.gradeDispose(mPostModel.getLoginTimes());
        mIvRanktag.setImageResource((int) ranks[1]);
        mIvRanklev.setImageResource((int) ranks[2]);

        if (!TextUtils.isEmpty(mPostModel.getEredarName())) {
            mTvHeaderType.setText(mPostModel.getEredarName() + "达人");
        } else {
            try {
                switch (mPostModel.getAuthority()) {
                    case 1:
                        mTvHeaderType.setText(" 系统管理员");
                        break;
                    case 2:
                        mTvHeaderType.setText(" 萌宝小编");
                        break;
                    case 3:
                        mTvHeaderType.setText(" 普通用户");
                        break;
                    case 4:
                        mTvHeaderType.setText(" 专家");
                        break;
                    case 5:
                        mTvHeaderType.setText(" 在线小编");
                        break;
                    default:
                        mTvHeaderType.setText(" 普通用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
        setHeadContent(linearLayout);
        //--------------------
    }

    private void setHeadContent(LinearLayout llContainer) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(TDevice.dip2px(8, this), TDevice.dip2px(8, this), TDevice.dip2px(8, this), TDevice.dip2px(8, this));
        // 添加内容
        TextView textTitle = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textTitle.setLayoutParams(params);
        textTitle.setTextIsSelectable(true);
        textTitle.setText(mPostDetail.getTitle());
        llContainer.addView(textTitle);

        TextView textContent = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textContent.setLayoutParams(params);
        textContent.setTextIsSelectable(true);
        textContent.setText(mPostDetail.getContext());
        llContainer.addView(textContent);
        // 添加图片
        String imgUrl = mPostDetail.getBigImg();

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
                            GlideTool.loadImage(this, cache[k], imageView, false);
                            llContainer.addView(imageView);

                            TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                            textView.setLayoutParams(params);
                            textView.setTextIsSelectable(true);
                            if (cache.length >= 4) {
                                textView.setText(cache[0]);
                                llContainer.addView(textView);
                            }
                        }
                    }
                }
            } else {
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_image, null);
                imageView.setLayoutParams(params);
                if (imgUrl.split(",").length == 4) {
                    shareImgUrl = imgUrl.split(",")[1];
                    GlideTool.loadImage(this, imgUrl.split(",")[1], imageView, false);
                    llContainer.addView(imageView);
                    TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                    textView.setLayoutParams(params);
                    textView.setTextIsSelectable(true);
                    //textView.setPadding(0, 6, 0, 6);
                    textView.setText(imgUrl.split(",")[0]);
                    llContainer.addView(textView);
                } else {
                    shareImgUrl = imgUrl.split(",")[0];
                    GlideTool.loadImage(this, imgUrl.split(",")[0], imageView, false);
                    llContainer.addView(imageView);
                }
            }
        }
        TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
        textView.setLayoutParams(params);
        textView.setText("精彩回复:");
        textView.setTextColor(getResources().getColor(R.color.gold));
        llContainer.addView(textView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headContainer = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
        headContainer.setLayoutParams(params);
        mReplayListFragment = new ReplayListFragment(true, mPostModel, headContainer, sort, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mReplayListFragment).commitAllowingStateLoss();
        ApiNetwork.getWonderReplayDetail(this, mPostModel);
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
            getMenuInflater().inflate(R.menu.menu_replay_wonder_with, menu);
        } else {
            getMenuInflater().inflate(R.menu.menu_replay_wonder, menu);
        }
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_share:
                ShareUtils.configPlatforms();
                StringBuffer sb = new StringBuffer();
                sb.append(Constants.SHARE_URL);
                sb.append("?id=");
                sb.append(mPostModel.getId());
                sb.append("&type=");
                sb.append(mPostModel.getType());
                sb.append("&areaType=");
                sb.append(mPostModel.getAreaType());
                ShareUtils.setShareContent(mPostDetail.getTitle(), mPostDetail.getContext(), sb.toString(), shareImgUrl);
                ShareUtils.showShareView();
                break;
            case R.id.action_collect:
                doCollection();
                break;
            case R.id.action_report:
                doReport();
                break;
            case R.id.action_sort:
                sort = sort == 1 ? 2 : 1;
                LinearLayout.LayoutParams paramsCache = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout headCache = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
                headCache.setLayoutParams(paramsCache);
                setHeadContent(headCache);
                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReplayListFragment(true, mPostModel, headCache, sort, this)).commit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void doCollection() {
        if (mPostDetail.getStore() > 0) {
            ApiNetwork.cancelCollection(MApplication.token, mPostModel.getId() + "", mPostModel.getType(), new Callback() {
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
            ApiNetwork.addCollection(MApplication.token, mPostModel.getId() + "", mPostModel.getAreaType(), mPostModel.getType(), new Callback() {
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

    /**
     * 举报接口带 提醒框
     */
    private void doReport() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        // set title
        alertDialogBuilder.setTitle("提醒");
        // set dialog message
        alertDialogBuilder.setMessage("您确定要举报吗？")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        ApiNetwork.addReport(MApplication.token, mPostModel.getAreaType(), mPostModel.getType(), mPostModel.getId() + "", null, new Callback() {
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
                            public void onResponse(final Response response) throws IOException {
                                if (response.isSuccessful()) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                String result = response.body().string();
                                                if (result.contains("1")) {
                                                    showToast("举报成功");
                                                } else {
                                                    showToast("举报失败");
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
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
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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

    @Override
    public void onReplayItemClick(View view) {
        ReplayModel replayModel = (ReplayModel) view.getTag();
        Intent intent = new Intent(ReplayWonderActivity.this, ReplayAddActivity.class);
        intent.putExtra("model", mPostModel);
        intent.putExtra("id", replayModel.getId());
        intent.putExtra("floor", replayModel.getFloor());
        startActivityForResult(intent, 1);
    }
}
