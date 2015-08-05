package com.cmbb.smartkids.activity.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListFragment;
import com.cmbb.smartkids.fragment.caselist.CaseTypeListFragment;
import com.cmbb.smartkids.fragment.caselist.CaseTypeListViewHolder;
import com.cmbb.smartkids.fragment.caselist.CaseTypeModel;

public class ToolsCaseActivity extends MActivity implements CaseTypeListViewHolder.OnCaseTypeClickListener {

    private final String TAG = ToolsCaseActivity.class.getSimpleName();


    private FrameLayout lvRight;
    private FrameLayout lvLeft;


    @Override
    public int getLayoutId() {
        return R.layout.activity_tools_case;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        lvRight = (FrameLayout) findViewById(R.id.lv_case_right);
        lvLeft = (FrameLayout) findViewById(R.id.lv_case_left);
        Fragment fragmentLeft = new CaseTypeListFragment(false, this);
        getSupportFragmentManager().beginTransaction().add(R.id.lv_case_left, fragmentLeft).commit();
    }

    @Override
    public void onCaseTypeClick(View view) {
        getSupportFragmentManager().beginTransaction().replace(R.id.lv_case_right, new CaseDetailListFragment(false, (CaseTypeModel) view.getTag())).commit();
    }
}
