package com.cmbb.smartkids.fragment.guid;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
/**
 * Created by N.Sun
 */
public class GuideFragmentAdapter extends FragmentPagerAdapter {


    private static final String TAG = GuideFragmentAdapter.class.getSimpleName();

    private FragmentGuideOne mFragmentGuideOne;
    private FragmentGuideTwo mFragmentGuideTwo;
    private FragmentGuideThree mFragmentGuideThree;

    public GuideFragmentAdapter(FragmentManager fm) {
        super(fm);
        mFragmentGuideOne = new FragmentGuideOne();
        mFragmentGuideTwo = new FragmentGuideTwo();
        mFragmentGuideThree = new FragmentGuideThree();
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return mFragmentGuideOne;
            case 1:
                return mFragmentGuideTwo;
            case 2:
                return mFragmentGuideThree;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }

}
