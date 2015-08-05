package com.cmbb.smartkids.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.post.PlateListActivity;
import com.cmbb.smartkids.activity.setting.SettingActivity;
import com.cmbb.smartkids.activity.user.UserAttentionActivity;
import com.cmbb.smartkids.activity.user.UserCollectionActivity;
import com.cmbb.smartkids.activity.user.UserInfoActivity;
import com.cmbb.smartkids.activity.user.UserPublishActivity;
import com.cmbb.smartkids.adapter.HomeAutoScrollBannerAdapter;
import com.cmbb.smartkids.adapter.HomeFragmentPagerAdapter;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.active.ActiveFragment;
import com.cmbb.smartkids.fragment.homeplate.HomeBannerModel;
import com.cmbb.smartkids.fragment.homeplate.HomeListFragment;
import com.cmbb.smartkids.fragment.message.MessageListFragment;
import com.cmbb.smartkids.fragment.tools.FragmentHomeTools;
import com.cmbb.smartkids.model.userinfo.UserInfoDetailModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.widget.autoscroll.AutoScrollViewPager;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;
import com.cmbb.smartkids.widget.indicator.CirclePageIndicator;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends MActivity {

    private static Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private DrawerLayout mDrawerLayout;

    NavigationView navigationView;
    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    MengCoordinatorLayout coordinatorlayout;

    // TabLayout
    TabLayout tabLayout;

    // 顶部
    RelativeLayout rlTab;

    //底部
    TextView btnAdd;
    TextView btnHome;
    TextView btnActive;
    TextView btnMaster;
    TextView btnTools;

    // content
    ViewPager viewPager;

    // content adapter
    HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;
    HomeFragmentPagerAdapter mActiveFragmentPagerAdapter;
    HomeFragmentPagerAdapter mMasterFragmentPagerAdapter;
    HomeFragmentPagerAdapter mToolsFragmentPagerAdapter;

    // home content
    Fragment[] homeFragments;
    String[] homeTitles;

    // active content
    Fragment[] activeFragments;
    String[] activeTitles;

    // master content
    Fragment[] masterFragments;
    String[] masterTitles;

    // tools content
    Fragment[] toolsFragments;
    String[] toolsTitles;

    // User 用户数据
    ImageView ivHead;
    TextView tvNick;
    TextView tvStatus;
    RelativeLayout headBac;
    UserInfoDetailModel userInfoDetailModel;

    // Banner Receiver


    HomeAutoScrollBannerAdapter homeAutoScrollBannerAdapter;
    BroadcastReceiver bannerReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            List<HomeBannerModel> cacheBanner = intent.getParcelableArrayListExtra(Constants.Home.BANNER_DATA);
            homeAutoScrollBannerAdapter.setData(cacheBanner);
        }
    };
    // Userinfo Receiver
    BroadcastReceiver userInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.i("userinfo", "userinfo flag = " + intent.getBooleanExtra(Constants.NETWORK_FLAG, false));
            if (intent.getBooleanExtra(Constants.NETWORK_FLAG, false)) {
                userInfoDetailModel = intent.getParcelableExtra(Constants.Home.USERINFO_DATA);
                tvNick.setText(userInfoDetailModel.getNike());
                switch (userInfoDetailModel.getUserStatus()) {
                    case 1:
                        tvStatus.setText("备孕中");
                        break;
                    case 2:
                        tvStatus.setText("怀孕中");
                        break;
                    case 3:
                        tvStatus.setText("已出生");
                        break;
                }
                GlideTool.loadImage(HomeActivity.this, userInfoDetailModel.getUserSmallHeadImg(), ivHead, true);
            } else {
                showToast(intent.getStringExtra(Constants.NETWORK_FAILURE));
            }
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mActiveFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mMasterFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mToolsFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        initData();
        initView();
    }

    private void initView() {
        // 用户数据
        ivHead = (ImageView) findViewById(R.id.iv_head);
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvStatus = (TextView) findViewById(R.id.tv_status);
        headBac = (RelativeLayout) findViewById(R.id.head_bac);
        headBac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, UserInfoActivity.class);
                if (null != userInfoDetailModel)
                    intent.putExtra("user", userInfoDetailModel);
                startActivity(intent);
            }
        });
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        // 设置Navigation
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        collapsingToolbarLayout.setTitle(getResources().getString(R.string.app_name));
        collapsingToolbarLayout.setExpandedTitleColor(android.R.color.transparent);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        rlTab = (RelativeLayout) findViewById(R.id.rl_tab);
        btnAdd = (TextView) findViewById(R.id.btn_add);
        btnHome = (TextView) findViewById(R.id.btn_home);

        // content
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        if (viewPager != null) {
            viewPager.setAdapter(mHomeFragmentPagerAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }
        //初始化btnHome
        btnHome.setSelected(true);
        btnActive = (TextView) findViewById(R.id.btn_active);
        btnMaster = (TextView) findViewById(R.id.btn_master);
        btnTools = (TextView) findViewById(R.id.btn_tools);

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTab.setVisibility(View.GONE);
                expandToolbar();
                coordinatorlayout.setAllowForScrool(true);
                // 设置底部Button显示
                btnHome.setSelected(true);
                btnActive.setSelected(false);
                btnMaster.setSelected(false);
                btnTools.setSelected(false);
                // 设置内容
                viewPager.setAdapter(mHomeFragmentPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });
        btnActive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTab.setVisibility(View.VISIBLE);
                collapseToolbar();
                coordinatorlayout.setAllowForScrool(false);
                // 设置底部Button显示
                btnHome.setSelected(false);
                btnActive.setSelected(true);
                btnMaster.setSelected(false);
                btnTools.setSelected(false);
                // 设置内容
                viewPager.setAdapter(mActiveFragmentPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);

            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, PlateListActivity.class);
                startActivity(intent);
            }
        });


        btnMaster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTab.setVisibility(View.VISIBLE);
                collapseToolbar();
                coordinatorlayout.setAllowForScrool(false);
                // 设置底部Button显示
                btnHome.setSelected(false);
                btnActive.setSelected(false);
                btnMaster.setSelected(true);
                btnTools.setSelected(false);
                // 设置内容
                viewPager.setAdapter(mMasterFragmentPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });

        btnTools.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTab.setVisibility(View.GONE);
                collapseToolbar();
                coordinatorlayout.setAllowForScrool(false);
                // 设置底部Button显示
                btnHome.setSelected(false);
                btnActive.setSelected(false);
                btnMaster.setSelected(false);
                btnTools.setSelected(true);
                // 设置内容
                viewPager.setAdapter(mToolsFragmentPagerAdapter);
                tabLayout.setupWithViewPager(viewPager);
            }
        });


        AutoScrollViewPager scrollPager = (AutoScrollViewPager) findViewById(R.id.scroll_pager);
        homeAutoScrollBannerAdapter = new HomeAutoScrollBannerAdapter(this);
        scrollPager.setAdapter(homeAutoScrollBannerAdapter);
        scrollPager.startAutoScroll(4000);
        CirclePageIndicator mIndicator = (CirclePageIndicator) findViewById(R.id.tab_indicator);
        mIndicator.setFillColor(getResources().getColor(R.color.colorPrimary));
        mIndicator.setStrokeWidth(1.0f);
        mIndicator.setStrokeColor(getResources().getColor(R.color.colorPrimary));
        mIndicator.setSnap(true);
        mIndicator.setViewPager(scrollPager);
        mIndicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int arg0) {
                // TODO Auto-generated method stub

            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
                // TODO Auto-generated method stub

            }
        });
    }

    private void initData() {

        // 初始化用户信息
        ApiNetwork.getUserInfo(this);
        homeFragments = new Fragment[1];
        homeFragments[0] = new HomeListFragment(true);
        homeTitles = new String[1];
        homeTitles[0] = "";
        mHomeFragmentPagerAdapter.addFragment(homeFragments, homeTitles);

        activeFragments = new Fragment[2];
        activeFragments[0] = new ActiveFragment();
        activeFragments[1] = new MessageListFragment();
        activeTitles = new String[2];
        activeTitles[0] = "动态";
        activeTitles[1] = "消息";
        mActiveFragmentPagerAdapter.addFragment(activeFragments, activeTitles);

        masterFragments = new Fragment[2];
        masterFragments[0] = new HomeListFragment();
        masterFragments[1] = new HomeListFragment();
        masterTitles = new String[2];
        masterTitles[0] = "达人";
        masterTitles[1] = "专家";
        mMasterFragmentPagerAdapter.addFragment(masterFragments, masterTitles);

        toolsFragments = new Fragment[1];
        toolsFragments[0] = new FragmentHomeTools();
        toolsTitles = new String[1];
        toolsTitles[0] = "";
        mToolsFragmentPagerAdapter.addFragment(toolsFragments, toolsTitles);

    }


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter(Constants.Home.BANNER_DATA_INTENT);
        registerReceiver(bannerReceiver, intentFilter);

        IntentFilter intentFilter2 = new IntentFilter(Constants.Home.USERINFO_DATA_INTENT);
        registerReceiver(userInfoReceiver, intentFilter2);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(bannerReceiver);
        unregisterReceiver(userInfoReceiver);
    }

    /**
     * 设置侧滑栏
     *
     * @param navigationView NavigationView
     */
    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // 关闭Navigation
                        mDrawerLayout.closeDrawers();
                        if (menuItem.getItemId() == R.id.nav_attention) {
                            Intent intent = new Intent(HomeActivity.this, UserAttentionActivity.class);
                            startActivity(intent);
                        } else if (menuItem.getItemId() == R.id.nav_collection) {
                            Intent intent = new Intent(HomeActivity.this, UserCollectionActivity.class);
                            startActivity(intent);
                        } else if (menuItem.getItemId() == R.id.nav_publish) {
                            Intent intent = new Intent(HomeActivity.this, UserPublishActivity.class);
                            startActivity(intent);
                        } else if (menuItem.getItemId() == R.id.nav_setting) {
                            Intent intent = new Intent(HomeActivity.this, SettingActivity.class);
                            startActivity(intent);
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home: // 控制DrawerLayout
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onBackPressed() {
        if (!isQuit) {
            isQuit = true;
            showToastShort(R.string.back_more_quit);
            TimerTask task = null;
            task = new TimerTask() {
                @Override
                public void run() {
                    isQuit = false;
                }
            };
            timer.schedule(task, 2000);
        } else {
            finish();
            System.exit(0);
        }
    }

    /**
     * 关闭Toolbar
     */
    public void collapseToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();

        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        if (behavior != null) {
            behavior.onNestedFling(coordinatorlayout, appBarLayout, null, 0, 10000, true);
        }
    }

    /**
     * 展开Toolbar
     */
    public void expandToolbar() {
        CoordinatorLayout.LayoutParams params = (CoordinatorLayout.LayoutParams) appBarLayout.getLayoutParams();
        AppBarLayout.Behavior behavior = (AppBarLayout.Behavior) params.getBehavior();
        if (behavior != null) {
            behavior.onNestedFling(coordinatorlayout, appBarLayout, null, 0, -10000, false);
        }
    }

}
