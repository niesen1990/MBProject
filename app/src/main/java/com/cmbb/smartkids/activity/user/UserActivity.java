package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.HomeFragmentPagerAdapter;
import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.homeplate.HomeEredarModel;
import com.cmbb.smartkids.fragment.postlist.age.SameAgePublishListFragment;
import com.cmbb.smartkids.fragment.postlist.city.SameCityPublishListFragment;
import com.cmbb.smartkids.fragment.postlist.wonder.WonderPublicListFragment;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.RankTools;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class UserActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener {

    // TabLayout
    TabLayout tabLayout;
    // content
    ViewPager viewPager;
    // appbar
    AppBarLayout appbar;

    // home content
    CommonFragment[] activeFragments;
    String[] activeTitles;

    HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;

    // 用户数据
    HomeEredarModel mHomeEredarModel;


    private ImageView ivUserBac;
    private TextView tvNick;
    private ImageView ivRanktag;
    private ImageView ivLv;
    private TextView tvContent;
    private RelativeLayout rvBac;

    private FloatingActionButton fab;

    boolean attention_flag = false;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        mHomeEredarModel = getIntent().getParcelableExtra("user");
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        activeFragments = new CommonFragment[3];
        activeFragments[0] = new WonderPublicListFragment(false, mHomeEredarModel.getId());
        activeFragments[1] = new SameAgePublishListFragment(false, mHomeEredarModel.getId());
        activeFragments[2] = new SameCityPublishListFragment(false, mHomeEredarModel.getId());
        activeTitles = new String[3];
        activeTitles[0] = "话题";
        activeTitles[1] = "同龄";
        activeTitles[2] = "同城";
        mHomeFragmentPagerAdapter.addFragment(activeFragments, activeTitles);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            viewPager.setAdapter(mHomeFragmentPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        // user
        ivUserBac = (ImageView) findViewById(R.id.iv_user_bac);
        GlideTool.loadImage(this, mHomeEredarModel.getUserSmallHeadImg(), ivUserBac, true);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvNick.setText(mHomeEredarModel.getNike());
        ivRanktag = (ImageView) findViewById(R.id.iv_ranktag);
        ivLv = (ImageView) findViewById(R.id.iv_lv);
        tvContent = (TextView) findViewById(R.id.tv_content);
        tvContent.setText(mHomeEredarModel.getEredarName() + "达人");
        rvBac = (RelativeLayout) findViewById(R.id.rv_bac);
        // test
        rvBac.setBackgroundResource(RankTools.getAuthBackground(mHomeEredarModel.getAuthority(), 1));
        try {
            long[] ranks = RankTools.gradeDispose(mHomeEredarModel.getLoginTimes());
            ivLv.setImageResource((int) ranks[2]);
            ivRanktag.setImageResource((int) ranks[1]);
        } catch (Exception e) {

        }

        fab = (FloatingActionButton) findViewById(R.id.fab);
        switch (mHomeEredarModel.getAttention()) {
            case 1:
                attention_flag = true;
                fab.setImageResource(android.R.drawable.ic_menu_delete);
                break;
            case 0:
                attention_flag = false;
                fab.setImageResource(android.R.drawable.ic_menu_add);
                break;
        }
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 取消关注
                showWaitDialog();
                if (attention_flag) {
                    ApiNetwork.CancelUserAttention(MApplication.token, mHomeEredarModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        hideWaitDialog();
                                        if (response.body().string().contains("1")) {
                                            showToast("取消成功");
                                            fab.setImageResource(android.R.drawable.ic_menu_add);
                                        } else {
                                            showToast("取消失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                } else {
                    ApiNetwork.addUserAttention(MApplication.token, mHomeEredarModel.getId() + "", new Callback() {
                        @Override
                        public void onFailure(Request request, IOException e) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast("请检查网络");
                                }
                            });
                        }

                        @Override
                        public void onResponse(final Response response) throws IOException {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        hideWaitDialog();
                                        if (response.body().string().contains("1")) {
                                            showToast("关注成功");
                                            fab.setImageResource(android.R.drawable.ic_menu_delete);
                                        } else {
                                            showToast("关注失败");
                                        }
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                }
                            });
                        }
                    });

                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        appbar.addOnOffsetChangedListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        appbar.removeOnOffsetChangedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user, menu);
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

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
        if (i == 0) {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    activeFragments[0].getmSwipeRefresh().setEnabled(true);
                    break;
                case 1:
                    activeFragments[1].getmSwipeRefresh().setEnabled(true);
                    break;
                case 2:
                    activeFragments[2].getmSwipeRefresh().setEnabled(true);
                    break;
            }
        } else {
            switch (viewPager.getCurrentItem()) {
                case 0:
                    activeFragments[0].getmSwipeRefresh().setEnabled(false);
                    break;
                case 1:
                    activeFragments[1].getmSwipeRefresh().setEnabled(false);
                    break;
                case 2:
                    activeFragments[2].getmSwipeRefresh().setEnabled(false);
                    break;
            }
        }
    }
}
