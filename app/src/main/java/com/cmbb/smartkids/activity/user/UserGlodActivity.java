package com.cmbb.smartkids.activity.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

public class UserGlodActivity extends MActivity {

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_glod;
    }


    @Override
    protected void init(Bundle savedInstanceState) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_glod, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_record) {
            Intent intent = new Intent(this, UserRecordActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
