package com.cmbb.smartkids.activity.baby;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.baby.babylist.BabyListFragment;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.widget.coordinator.MengCoordinatorLayout;

public class MBabyActivity extends MActivity {

    private MengCoordinatorLayout coordinatorlayout;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private RelativeLayout autoRl;
    private ImageView ivHeadBac;
    private TextView title;
    private TextView subtitle;
    private TextView btnAttention;
    private FloatingActionButton fabPublish;

    private PlateModel mPlateModel;

    private void assignViews() {
        coordinatorlayout = (MengCoordinatorLayout) findViewById(R.id.coordinatorlayout);
        appbar = (AppBarLayout) findViewById(R.id.appbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        autoRl = (RelativeLayout) findViewById(R.id.auto_rl);
        ivHeadBac = (ImageView) findViewById(R.id.iv_head_bac);
        PicassoTool.loadImage(this, mPlateModel.getSmallImg(), ivHeadBac, true);
        title = (TextView) findViewById(R.id.title);
        title.setText(mPlateModel.getTitle());
        subtitle = (TextView) findViewById(R.id.subtitle);
        subtitle.setText(mPlateModel.getContext());
        fabPublish = (FloatingActionButton) findViewById(R.id.fab_publish);
        fabPublish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MBabyActivity.this, AddBabyActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mbaby;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mPlateModel = getIntent().getParcelableExtra("model");
        assignViews();
        collapsingToolbar.setTitle(mPlateModel.getTitle());
        collapsingToolbar.setExpandedTitleColor(android.R.color.transparent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BabyListFragment()).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_mbaby, menu);
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
