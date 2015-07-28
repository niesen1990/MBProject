package com.cmbb.smartkids.activity;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

public class HomeActivity extends MActivity {
    private DrawerLayout mDrawerLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home_menu);
        // 设置Navigation
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        if (navigationView != null) {
            setupDrawerContent(navigationView);
        }
    }


    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        // 关闭Navigation
                        mDrawerLayout.closeDrawers();
                        if (menuItem.getItemId() == R.id.nav_attention) {
                        } else if (menuItem.getItemId() == R.id.nav_collection) {
                        } else if (menuItem.getItemId() == R.id.nav_collection) {
                        } else if (menuItem.getItemId() == R.id.nav_setting) {
                        }
                        return true;
                    }
                });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // 控制DrawerLayout
        if (id == android.R.id.home) {
            mDrawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
