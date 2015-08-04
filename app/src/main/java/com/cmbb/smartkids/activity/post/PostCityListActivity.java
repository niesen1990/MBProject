package com.cmbb.smartkids.activity.post;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.fragment.postlist.wonder.CityPostListFragment;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;

public class PostCityListActivity extends MActivity implements AppBarLayout.OnOffsetChangedListener {

    private MengCoordinatorLayout coordinatorlayout;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView ivHeadBac;
    private TextView title;
    private TextView subtitle;
    private Toolbar tlMainCustom;

    private FrameLayout container;

    CommonFragment mCommonFragment;
    //Model
    private PlateModel mPlateModel;

    private void assignViews() {
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivHeadBac = (ImageView) findViewById(R.id.iv_head_bac);
        title = (TextView) findViewById(R.id.title);
        subtitle = (TextView) findViewById(R.id.subtitle);
        tlMainCustom = (Toolbar) findViewById(R.id.tl_main_custom);
        container = (FrameLayout) findViewById(R.id.container);

        GlideTool.loadImage(this, mPlateModel.getSmallImg(), ivHeadBac, false);
        title.setText(mPlateModel.getTitle());
        subtitle.setText(mPlateModel.getContext());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPlateModel = getIntent().getParcelableExtra("model");
        assignViews();
        collapsingToolbar.setTitle(mPlateModel.getTitle());
        collapsingToolbar.setExpandedTitleColor(android.R.color.transparent);

        mCommonFragment = new CityPostListFragment(false, mPlateModel);
        getSupportFragmentManager().beginTransaction().add(R.id.container, mCommonFragment).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_list, menu);
        return true;
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
            mCommonFragment.getmSwipeRefresh().setEnabled(true);
        } else {
            mCommonFragment.getmSwipeRefresh().setEnabled(false);
        }
    }
}
