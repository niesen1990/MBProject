package com.cmbb.smartkids.activity.post;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

public class PlateListActivity extends MActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_plate_list;
    }


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_plate_list, menu);
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
