package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.HomeFragmentPagerAdapter;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.homeattention.plate.PlateAttentionListFragment;
import com.cmbb.smartkids.fragment.homeattention.user.UserAttentionListFragment;

public class UserAttentionActivity extends MActivity {
    private Toolbar tlMainCustom;
    private TabLayout tabs;
    private ViewPager viewPager;
    private HomeFragmentPagerAdapter mHomeFragmentPagerAdapter;

    private Fragment[] mFragments;
    private String[] mTitles;

    private void assignViews() {
        tlMainCustom = (Toolbar) findViewById(R.id.tl_main_custom);
        tabs = (TabLayout) findViewById(R.id.tabs);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        mHomeFragmentPagerAdapter = new HomeFragmentPagerAdapter(getSupportFragmentManager());
        mFragments = new Fragment[2];
        mFragments[0] = new UserAttentionListFragment();
        mFragments[1] = new PlateAttentionListFragment();
        mTitles = new String[2];
        mTitles[0] = "关注用户";
        mTitles[1] = "关注板块";
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
        getMenuInflater().inflate(R.menu.menu_user_attention, menu);
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
