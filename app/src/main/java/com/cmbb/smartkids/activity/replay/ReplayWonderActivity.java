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
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.ImagePreviewActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.PostDetail;
import com.cmbb.smartkids.fragment.replay.ReplayBaseModel;
import com.cmbb.smartkids.fragment.replay.ReplayListFragment;
import com.cmbb.smartkids.fragment.replay.ReplayListViewHolder;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.RankTools;
import com.cmbb.smartkids.tools.ShareUtils;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.umeng.socialize.controller.UMSocialService;
import com.umeng.socialize.sso.UMSsoHandler;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ReplayWonderActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener, ReplayListViewHolder.OnReplayItemClickListener, ReplayListViewHolder.OnReplayClickListener {

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
                Log.i("PostDetail", "PostDetail = " + mPostDetail);
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

    // 底部发送
    private EditText etSendContent;
    private ImageView ivAdd;
    private TextView btnSend;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_replay_wonder;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPostModel = getIntent().getParcelableExtra("model");
        mController = ShareUtils.instanceOf(this);
        assignViews();
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        headContainer = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
        headContainer.setLayoutParams(params);
        mReplayListFragment = new ReplayListFragment(true, mPostDetail, mPostModel, headContainer, sort, this, this);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, mReplayListFragment).commitAllowingStateLoss();
        ApiNetwork.getWonderReplayDetail(this, mPostModel);
    }

    private void assignViews() {
        etSendContent = (EditText) findViewById(R.id.et_send_content);
        ivAdd = (ImageView) findViewById(R.id.iv_add);
        ivAdd.setOnClickListener(this);
        ivAdd.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                send_image = null;
                ivAdd.setImageResource(android.R.drawable.ic_menu_add);
                return true;
            }
        });
        btnSend = (TextView) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(this);
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
                startActivityForResult(intent, 10);
            }
        });
        btnSpot = (TextView) findViewById(R.id.btn_spot);
    }


    int mParentReplyId = -1;

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_send:
                String conent = etSendContent.getText().toString();
                if (TextUtils.isEmpty(conent)) {
                    showToast("请输入回复内容");
                    return;
                } else {
                    btnSend.setClickable(false);
                    sendMessage(mParentReplyId, conent);
                }
                break;
            case R.id.iv_add:
                PhotoPickerIntent intent = new PhotoPickerIntent(ReplayWonderActivity.this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, REQUEST_CODE);
                break;
        }
    }

    private void setHeadViewData(LinearLayout linearLayout) {
        if (mPostDetail == null) {
            showToast("帖子已经删除");
            return;
        }
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
        PicassoTool.loadImage(this, mPostModel.getUserSmallHeadImg(), mRivHead, true);
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
                        mTvHeaderType.setText("系统管理员");
                        break;
                    case 2:
                        mTvHeaderType.setText("小编");
                        break;
                    case 3:
                        mTvHeaderType.setText("萌宝用户");
                        break;
                    case 4:
                        mTvHeaderType.setText("专家");
                        break;
                    case 5:
                        mTvHeaderType.setText("在线小编");
                        break;
                    case 6:
                        mTvHeaderType.setText("萌主");
                        break;
                    case 7:
                        mTvHeaderType.setText("实习萌主");
                        break;
                    default:
                        mTvHeaderType.setText("萌宝用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
        setHeadContent(linearLayout);
        //--------------------
    }

    ArrayList<String> pagerUrls = new ArrayList<>();

    private void setHeadContent(LinearLayout llContainer) {
        pagerUrls.clear();
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
                            ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_replay_head_img, null);
                            //ImageView imageView = new ImageView(this);
                            imageView.setAdjustViewBounds(true);
                            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                            imageView.setLayoutParams(params);
                            shareImgUrl = cache[k];
                            pagerUrls.add(cache[k]);
                            Log.i("width", "width = " + cache[k + 1] + " height = " + cache[k + 2]);
                            //PicassoTool.loadImage(this, cache[k], imageView, false);
                            PicassoTool.loadImageWithSize(this, cache[k], imageView, (int) Double.parseDouble(cache[k + 1]), (int) Double.parseDouble(cache[k + 2]), false);

                            imageView.setTag(R.id.image, pagerUrls.size() - 1);
                            imageView.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    int position = (int) v.getTag(R.id.image);
                                    Intent intent = new Intent(ReplayWonderActivity.this, ImagePreviewActivity.class);
                                    intent.putExtra("index", position);
                                    intent.putExtra("data", pagerUrls);
                                    startActivity(intent);
                                }
                            });
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
                ImageView imageView = (ImageView) getLayoutInflater().inflate(R.layout.activity_replay_head_img, null);
                //ImageView imageView = new ImageView(this);
                imageView.setAdjustViewBounds(true);
                imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
                imageView.setLayoutParams(params);
                imageView.setTag(R.id.image, pagerUrls.size() - 1);
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int position = (int) v.getTag(R.id.image);
                        Intent intent = new Intent(ReplayWonderActivity.this, ImagePreviewActivity.class);
                        intent.putExtra("index", position);
                        intent.putExtra("data", pagerUrls);
                        startActivity(intent);
                    }
                });
                if (imgUrl.split(",").length == 4) {
                    shareImgUrl = imgUrl.split(",")[1];
                    pagerUrls.add(imgUrl.split(",")[1]);
                    //PicassoTool.loadImage(this, imgUrl.split(",")[1], imageView, false);
                    PicassoTool.loadImageWithSize(this, imgUrl.split(",")[1], imageView, (int) Double.parseDouble(imgUrl.split(",")[2]), (int) Double.parseDouble(imgUrl.split(",")[3]), false);

                    llContainer.addView(imageView);
                    TextView textView = (TextView) getLayoutInflater().inflate(R.layout.activity_post_detail_head_text, null);
                    textView.setLayoutParams(params);
                    textView.setTextIsSelectable(true);
                    //textView.setPadding(0, 6, 0, 6);
                    textView.setText(imgUrl.split(",")[0]);
                    llContainer.addView(textView);
                } else {
                    shareImgUrl = imgUrl.split(",")[0];
                    pagerUrls.add(imgUrl.split(",")[0]);
                    //PicassoTool.loadImage(this, imgUrl.split(",")[0], imageView, false);
                    PicassoTool.loadImageWithSize(this, imgUrl.split(",")[0], imageView, (int) Double.parseDouble(imgUrl.split(",")[1]), (int) Double.parseDouble(imgUrl.split(",")[2]), false);
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
        unregisterReceiver(postDetailReceiver);
        /*for (int i = 0; i < headContainer.getChildCount(); i++) {
            if (headContainer.getChildAt(i) instanceof ImageView) {
                recycleBitmap((ImageView) headContainer.getChildAt(i));
            }
        }*/
        super.onDestroy();
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
                if (TextUtils.isEmpty(mPostDetail.getContext())) {
                    ShareUtils.setShareContent(mPostDetail.getTitle(), mPostModel.getPlateName(), sb.toString(), shareImgUrl);
                } else {
                    ShareUtils.setShareContent(mPostDetail.getTitle(), mPostDetail.getContext(), sb.toString(), shareImgUrl);
                }
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

                for (int i = 0; i < headContainer.getChildCount(); i++) {
                    if (headContainer.getChildAt(i) instanceof ImageView) {
                        recycleBitmap((ImageView) headContainer.getChildAt(i));
                    }
                }
                LinearLayout.LayoutParams paramsCache = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                LinearLayout headCache = (LinearLayout) getLayoutInflater().inflate(R.layout.activity_replay_list_head, null);
                headCache.setLayoutParams(paramsCache);
                setHeadContent(headCache);

                getSupportFragmentManager().beginTransaction().replace(R.id.container, new ReplayListFragment(true, mPostDetail, mPostModel, headCache, sort, this, this)).commit();
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

    public final static int REQUEST_CODE = 1;
    private ArrayList<String> imgs = new ArrayList<>();
    private File send_image;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 10 && resultCode == 10) {
            ArrayList<ReplayModel> replayMessageModels = data.getParcelableArrayListExtra("data");
            mReplayListFragment.mReplayListProvider.initSuccess(replayMessageModels);
            return;
        }

        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                imgs = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                if (imgs.size() == 0) return;
                send_image = new File(imgs.get(0));
                PicassoTool.loadImage(this, imgs.get(0), ivAdd, false);
            }
        }

        UMSsoHandler ssoHandler = mController.getConfig().getSsoHandler(requestCode);
        if (ssoHandler != null) {
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }


    @Override
    public void onReplayItemClick(View view) {
        /*ReplayModel replayModel = (ReplayModel) view.getTag();
        Intent intent = new Intent(ReplayWonderActivity.this, ReplayAddActivity.class);
        intent.putExtra("model", mPostModel);
        intent.putExtra("id", replayModel.getId());
        intent.putExtra("floor", replayModel.getFloor());
        startActivityForResult(intent, 10);*/
        mParentReplyId = -1;
        etSendContent.setHint("没事写两句...");
        etSendContent.setText("");

    }

    @Override
    public void onReplayClick(View view) {
        try {
            ReplayModel replayModel = (ReplayModel) view.getTag();
            mParentReplyId = replayModel.getId();
            etSendContent.setHint("回复" + replayModel.getFloor() + "楼...");

        } catch (Exception e) {

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

        /*for (int i = 0; i < headContainer.getChildCount(); i++) {
            if (headContainer.getChildAt(i) instanceof ImageView) {
                recycleBitmap((ImageView) headContainer.getChildAt(i));
            }
        }*/

        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "AddReplys", body, send_image, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        hideWaitDialog();
                        btnSend.setClickable(true);
                        showToast(getResources().getString(R.string.rc_network_error));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {


                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    try {
                        final ReplayBaseModel replayMessageBaseModel = gson.fromJson(result, ReplayBaseModel.class);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                btnSend.setClickable(true);

                                try {
                                    if (replayMessageBaseModel.getCode().equals("1")) {
                                        showToast("回复成功");
                                        mParentReplyId = -1;
                                        send_image = null;

                                        etSendContent.setHint("没事写两句...");
                                        etSendContent.setText("");
                                        ivAdd.setImageResource(android.R.drawable.ic_menu_add);
                                        /*for (int i = 0; i < replayMessageBaseModel.getContext().size(); i++) {
                                            Log.i("replay_result", "replay_result = " + replayMessageBaseModel.getContext().get(i).toString());
                                            if (i)
                                        }*/
                                        if (replayMessageBaseModel != null && replayMessageBaseModel.getContext().size() > 0) {
                                            mReplayListFragment.mReplayListProvider.initSuccess(replayMessageBaseModel.getContext());
                                            mReplayListFragment.mReplayListProvider.setId(replayMessageBaseModel.getContext().get(replayMessageBaseModel.getContext().size() - 1).getId());
                                            Log.i("replay_id", "replay_id = " + mReplayListFragment.mReplayListProvider.getId());
                                            mReplayListFragment.mRecyclerView.scrollToPosition(1);
                                            mReplayListFragment.mReplayListProvider.setIsEnd(false);

                                            /*mReplayListFragment.mReplayListProvider.getData().removeAll(mReplayListFragment.mReplayListProvider.getCacheReplayModels());
                                            mReplayListFragment.mReplayListProvider.setId(mReplayListFragment.mReplayListProvider.getCacheId());
                                            mReplayListFragment.mReplayListAdapter.notifyDataSetChanged();*/
                                        }

                                    } else {
                                        showToast("回复失败");
                                    }
                                } catch (NullPointerException e) {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            btnSend.setClickable(true);
                                        }
                                    });
                                }
                            }
                        });
                    } catch (Exception e) {

                    }

                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            btnSend.setClickable(true);
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                }
            }
        });
    }


}
