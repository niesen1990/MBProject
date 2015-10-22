package com.cmbb.smartkids.activity.user;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.record.RecordListFragment;

public class UserRecordActivity extends MActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_record;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new RecordListFragment(false)).commit();
    }

}
