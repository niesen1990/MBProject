package com.cmbb.smartkids.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.search.casepost.SearchCaseFragment;
import com.cmbb.smartkids.fragment.search.post.SearchPostFragment;
import com.cmbb.smartkids.fragment.search.user.SearchUserFragment;

/**
 * Created by javon on 2015/8/6.
 */
public class SearchActivity extends MActivity implements TextWatcher {
    private EditText etSearch;
    private ImageView ivClear;
    private FrameLayout flSearchContent;

    int type = 1;


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
        ((RadioGroup) findViewById(R.id.rg_searche)).setOnCheckedChangeListener(onChangeListener);
        ivClear = (ImageView) findViewById(R.id.iv_search_clear);
        ivClear.setOnClickListener(this);
        flSearchContent = (FrameLayout) findViewById(R.id.fl_search_content);
    }

    private RadioGroup.OnCheckedChangeListener onChangeListener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.rb_search_user:
                    type = 1;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchUserFragment(false, etSearch.getText().toString())).commit();
                    break;
                case R.id.rb_search_topic:
                    type = 2;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchPostFragment(false, etSearch.getText().toString())).commit();
                    break;
                case R.id.rb_search_case:
                    type = 3;
                    getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchCaseFragment(false, etSearch.getText().toString())).commit();
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
        if (TextUtils.isEmpty(s)) {
            ivClear.setVisibility(View.INVISIBLE);
        } else {
            ivClear.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        switch (type) {
            case 1:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchUserFragment(false, s.toString())).commit();
                break;
            case 2:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchPostFragment(false, s.toString())).commit();
                break;
            case 3:
                getSupportFragmentManager().beginTransaction().replace(R.id.fl_search_content, new SearchCaseFragment(false, s.toString())).commit();
                break;
        }
    }
}
