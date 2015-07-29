package com.cmbb.smartkids.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.guid.GuideFragmentAdapter;
import com.cmbb.smartkids.widget.indicator.CirclePageIndicator;

/**
 * 项目名称：SmartKids
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/5/13 16:11
 * 修改人：N.Sun
 * 修改时间：2015/5/13 16:11
 * 修改备注：
 */
public class GuideActivity extends MActivity {
    private static final String TAG = GuideActivity.class.getSimpleName();
    ViewPager mViewPager;
    GuideFragmentAdapter mGuideFragmentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        mGuideFragmentAdapter = new GuideFragmentAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mViewPager.setAdapter(mGuideFragmentAdapter);
        CirclePageIndicator mIndicator = (CirclePageIndicator) findViewById(R.id.tab_indicator);
        mIndicator.setViewPager(mViewPager);
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
}
