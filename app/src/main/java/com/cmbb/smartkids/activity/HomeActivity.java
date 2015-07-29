package com.cmbb.smartkids.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.test.EntryListFragment;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class HomeActivity extends MActivity {

    private static Boolean isQuit = false;// 退出应用标识符
    private Timer timer = new Timer();// 程序退出定时器
    private DrawerLayout mDrawerLayout;

    AppBarLayout appBarLayout;
    CollapsingToolbarLayout collapsingToolbarLayout;
    MengCoordinatorLayout coordinatorlayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);
        // 设置Navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }

        // 设置TabView
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        if (viewPager != null) {
            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
        }

        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        appBarLayout = (AppBarLayout) findViewById(R.id.appbar);
        final RelativeLayout rlTab = (RelativeLayout) findViewById(R.id.rl_tab);
        final RelativeLayout rlTitlebar = (RelativeLayout) findViewById(R.id.rl_titlebar);
        TextView btn_add = (TextView) findViewById(R.id.btn_add);
        TextView btnHome = (TextView) findViewById(R.id.btn_home);
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTitlebar.setVisibility(View.GONE);
                rlTab.setVisibility(View.VISIBLE);
                collapseToolbar();
                coordinatorlayout.setAllowForScrool(false);
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rlTitlebar.setVisibility(View.VISIBLE);
                rlTab.setVisibility(View.GONE);
                expandToolbar();
                coordinatorlayout.setAllowForScrool(true);

            }
        });

        //getSupportFragmentManager().beginTransaction().add(R.id.frame_container, new EntryListFragment()).commit();
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


    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getSupportFragmentManager());
        Fragment fragment = new EntryListFragment();
        adapter.addFragment(fragment, "摄影");
        fragment = new EntryListFragment();
        adapter.addFragment(fragment, "绘本");
        fragment = new EntryListFragment();
        adapter.addFragment(fragment, "美食");
        viewPager.setAdapter(adapter);
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // 关闭Navigation
                        mDrawerLayout.closeDrawers();
                        if (menuItem.getItemId() == R.id.nav_attention) {
                        } else if (menuItem.getItemId() == R.id.nav_collection) {
                        } else if (menuItem.getItemId() == R.id.nav_collection) {
                        } else if (menuItem.getItemId() == R.id.nav_setting) {
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
        // 控制DrawerLayout
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // 程序退出的控制
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_BACK) {
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
        return false;
    }

    static class Adapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragments = new ArrayList<>();
        private final List<String> mFragmentTitles = new ArrayList<>();

        public Adapter(FragmentManager fm) {
            super(fm);
        }

        public void addFragment(Fragment fragment, String title) {
            mFragments.add(fragment);
            mFragmentTitles.add(title);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitles.get(position);
        }
    }
}
