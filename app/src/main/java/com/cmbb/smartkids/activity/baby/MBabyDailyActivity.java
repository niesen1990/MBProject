package com.cmbb.smartkids.activity.baby;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.baby.babydaily.BabyDailyFragment;
import com.cmbb.smartkids.fragment.baby.babylist.BabyListModel;

public class MBabyDailyActivity extends MActivity {

    BabyListModel mBabyListModel;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_mbaby_daily;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        mBabyListModel = getIntent().getParcelableExtra("model");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSupportFragmentManager().beginTransaction().replace(R.id.container, new BabyDailyFragment(false, mBabyListModel)).commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_mbaby_daily, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_add_daily) {
            Intent intent = new Intent(this, AddDailyActivity.class);
            intent.putExtra("model", mBabyListModel);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
