package com.cmbb.smartkids.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午12:47
 */
public class HomeFragmentPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> mFragments = new ArrayList<>();
    private final List<String> mFragmentTitles = new ArrayList<>();

    public void addFragment(Fragment[] fragment, String[] title) {
        if (fragment.length != title.length) {
            try {
                throw new Exception("fragment and title length must be equels");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return;
        }
        if (mFragments.size() > 0 || mFragmentTitles.size() > 0) {
            mFragments.clear();
            mFragmentTitles.clear();
        }
        for (int i = 0; i < fragment.length; i++) {
            mFragments.add(fragment[i]);
            mFragmentTitles.add(title[i]);
        }
        notifyDataSetChanged();
    }

    public HomeFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
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
