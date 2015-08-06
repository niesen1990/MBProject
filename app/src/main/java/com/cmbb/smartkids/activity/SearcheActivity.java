package com.cmbb.smartkids.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

/**
 * Created by javon on 2015/8/6.
 */
public class SearcheActivity extends MActivity implements TextWatcher{
    private EditText etSearch;
    private ImageView ivClear;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
    }

    private void initView() {
        etSearch = (EditText) findViewById(R.id.et_search_content);
        etSearch.addTextChangedListener(this);
        ((RadioGroup)findViewById(R.id.rg_searche)).setOnCheckedChangeListener(onChangeListener);
        ivClear = (ImageView) findViewById(R.id.iv_search_clear);
        ivClear.setOnClickListener(this);
    }

    private RadioGroup.OnCheckedChangeListener onChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                case R.id.rb_search_user:

                    break;
                case R.id.rb_search_topic:

                    break;
                case R.id.rb_search_case:

                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        etSearch.setText("");
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(TextUtils.isEmpty(s)){
            ivClear.setVisibility(View.INVISIBLE);
        }else{
            ivClear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
