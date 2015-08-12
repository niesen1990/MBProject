package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.HomeFragmentPagerAdapter;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.homecollection.casecollection.CaseListFragment;
import com.cmbb.smartkids.fragment.homecollection.samecity.SameCityListFragment;
import com.cmbb.smartkids.fragment.homecollection.wonderful.WonderCollectionListFragment;

public class UserCollectionActivity extends MActivity {

    private TabLayout tabs;
    private ViewPager viewPager;
    private HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;

    private Fragment[] mFragments;
    private String[] mTitles;

    private void assignViews() {
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mFragments = new Fragment[4];
        mFragments[0] = new WonderCollectionListFragment();
        mFragments[1] = new SameCityListFragment();
        mFragments[2] = new SameCityListFragment();
        mFragments[3] = new CaseListFragment();
        mTitles = new String[4];
        mTitles[0] = "话题";
        mTitles[1] = "同龄";
        mTitles[2] = "同城";
        mTitles[3] = "案例";
        mHomeFragmentPagerAdapter.addFragment(mFragments, mTitles);
        if (viewPager != null) {
            viewPager.setAdapter(mHomeFragmentPagerAdapter);
            tabs.setupWithViewPager(viewPager);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_attention;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_user_attention, menu);
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
