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
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.homeplate.HomePlateModel;
import com.cmbb.smartkids.fragment.usercenter.wonderful.WonderPostListFragment;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;

public class PostWonderListActivity extends MActivity {

    private MengCoordinatorLayout coordinatorlayout;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private ImageView ivHeadBac;
    private TextView title;
    private TextView btnAttention;
    private TextView subtitle;
    private Toolbar tlMainCustom;

    private FrameLayout container;

    //Model
    private HomePlateModel mHomePlateModel;

    private void assignViews() {
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        ivHeadBac = (ImageView) findViewById(R.id.iv_head_bac);
        title = (TextView) findViewById(R.id.title);
        btnAttention = (TextView) findViewById(R.id.btn_attention);
        subtitle = (TextView) findViewById(R.id.subtitle);
        tlMainCustom = (Toolbar) findViewById(R.id.tl_main_custom);
        container = (FrameLayout) findViewById(R.id.container);

        GlideTool.loadImage(this, mHomePlateModel.getSmallImg(), ivHeadBac, false);
        title.setText(mHomePlateModel.getTitle());
        subtitle.setText(mHomePlateModel.getContext());
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_post_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mHomePlateModel = getIntent().getParcelableExtra("model");
        assignViews();
        //collapsingToolbar.setTitle(mHomePlateModel.getTitle());
        getSupportFragmentManager().beginTransaction().add(R.id.container, new WonderPostListFragment(false, mHomePlateModel)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_post_list, menu);
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
