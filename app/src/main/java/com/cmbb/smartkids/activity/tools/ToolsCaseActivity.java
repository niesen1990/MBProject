package com.cmbb.smartkids.activity.tools;

import android.os.Bundle;
import android.widget.ListView;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

public class ToolsCaseActivity extends MActivity{

    private final String TAG = ToolsCaseActivity.class.getSimpleName();


    private ListView lvLeft;
    private ListView lvRight;


    @Override
    public int getLayoutId() {
        return R.layout.activity_tools_case;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView(){
        lvLeft = (ListView) findViewById(R.id.lv_master_left);
        lvRight = (ListView) findViewById(R.id.lv_master_right);
    }


}
