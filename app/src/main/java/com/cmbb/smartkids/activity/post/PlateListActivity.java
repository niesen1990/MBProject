package com.cmbb.smartkids.activity.post;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.HomeFragmentPagerAdapter;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.platelist.PlateAgeListFragment;
import com.cmbb.smartkids.fragment.platelist.PlateCityListFragment;
import com.cmbb.smartkids.fragment.platelist.PlateWonderListFragment;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;

public class PlateListActivity extends MActivity {

    private MengCoordinatorLayout mCoordinatorlayout;
    private AppBarLayout mAppbar;
    private Toolbar mTlMainCustom;
    private TabLayout mTabs;
    private ViewPager mViewpager;

    // home content
    Fragment[] homeFragments;
    String[] homeTitles;
    HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_plate_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
    }


    private void assignViews() {
        mCoordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        mAppbar = (AppBarLayout) findViewById(R.id.appbar);
        mTlMainCustom = (Toolbar) findViewById(R.id.tl_main_custom);
        mTabs = (TabLayout) findViewById(R.id.tabs);
        mViewpager = (ViewPager) findViewById(R.id.viewpager);
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        // 加入数据
        homeFragments = new Fragment[3];
        homeFragments[0] = new PlateWonderListFragment(false);
        homeFragments[1] = new PlateAgeListFragment(false);
        homeFragments[2] = new PlateCityListFragment(false);
        homeTitles = new String[3];
        homeTitles[0] = "话题";
        homeTitles[1] = "同龄";
        homeTitles[2] = "同城";
        mHomeFragmentPagerAdapter.addFragment(homeFragments, homeTitles);
        if (mViewpager != null) {
            mViewpager.setAdapter(mHomeFragmentPagerAdapter);
            mTabs.setupWithViewPager(mViewpager);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_plate_list, menu);

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
