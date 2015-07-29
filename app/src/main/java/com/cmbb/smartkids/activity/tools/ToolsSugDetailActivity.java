package com.cmbb.smartkids.activity.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

public class ToolsSugDetailActivity extends MActivity {
    private String[] details_week_huli;
    private String[] details_year_huli;
    private String[] details_week_weiyang;
    private String[] details_year_weiyang;
    private String[] details_week_boys_zhibiao;
    private String[] details_week_girls_zhibiao;
    private String[] details_year_boys_zhibiao;
    private String[] details_year_girls_zhibiao;

    private String[] splits_week_huli;
    private String[] split_year_huli;
    private String[] split_boys_week_zhibiao;
    private String[] split_girls_week_zhibiao;
    private String[] split_year_boys_zhibiao;
    private String[] split_year_girls_zhibiao;
    private String[] split_year_weiyang;

    private TextView huli_detail;
    private TextView weiyang_detail;
    private TextView boys_zhibiao_shengao;
    private TextView boys_zhibiao_tizhong;
    private TextView boys_zhibiao_zuogao;
    private TextView boys_zhibiao_touwei;
    private TextView boys_zhibiao_xiongwei;

    private TextView girls_zhibiao_shengao;
    private TextView girls_zhibiao_tizhong;
    private TextView girls_zhibiao_zuogao;
    private TextView girls_zhibiao_touwei;
    private TextView girls_zhibiao_xiongwei;
    private int position;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tools_suggestion_detail;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }

    private void initView() {
        huli_detail = (TextView) findViewById(R.id.huli_detail);
        weiyang_detail = (TextView) findViewById(R.id.weiyang_detail);
        boys_zhibiao_shengao = (TextView) findViewById(R.id.boys_zhibiao_shengao);
        boys_zhibiao_tizhong = (TextView) findViewById(R.id.boys_zhibiao_tizhong);
        boys_zhibiao_zuogao = (TextView) findViewById(R.id.boys_zhibiao_zuogao);
        boys_zhibiao_touwei = (TextView) findViewById(R.id.boys_zhibiao_touwei);
        boys_zhibiao_xiongwei = (TextView) findViewById(R.id.boys_zhibiao_xiongwei);

        girls_zhibiao_shengao = (TextView) findViewById(R.id.girls_zhibiao_shengao);
        girls_zhibiao_tizhong = (TextView) findViewById(R.id.girls_zhibiao_tizhong);
        girls_zhibiao_zuogao = (TextView) findViewById(R.id.girls_zhibiao_zuogao);
        girls_zhibiao_touwei = (TextView) findViewById(R.id.girls_zhibiao_touwei);
        girls_zhibiao_xiongwei = (TextView) findViewById(R.id.girls_zhibiao_xiongwei);

    }

    private void initData() {
        Intent intent = getIntent();
        position = intent.getIntExtra("position", 0);
        getData();

        if (position == 145) {
            split_year_huli = details_year_huli[0].split("@");
            split_year_boys_zhibiao = details_year_boys_zhibiao[0].split(",");
            split_year_girls_zhibiao = details_year_girls_zhibiao[0].split(",");
            split_year_weiyang = details_year_weiyang[0].split("@");
            setYearData();
        } else if (position == 146) {
            split_year_huli = details_year_huli[1].split("@");
            split_year_boys_zhibiao = details_year_boys_zhibiao[1].split(",");
            split_year_girls_zhibiao = details_year_girls_zhibiao[1].split(",");
            split_year_weiyang = details_year_weiyang[1].split("@");
            setYearData();
        } else if (position == 147) {
            split_year_huli = details_year_huli[2].split("@");
            split_year_boys_zhibiao = details_year_boys_zhibiao[2].split(",");
            split_year_girls_zhibiao = details_year_girls_zhibiao[2].split(",");
            split_year_weiyang = details_year_weiyang[2].split("@");
            setYearData();
        } else {
            splits_week_huli = details_week_huli[position].split("@");
            split_boys_week_zhibiao = details_week_boys_zhibiao[position].split(",");
            split_girls_week_zhibiao = details_week_girls_zhibiao[position].split(",");
            setWeekData();
        }
    }


    private void setWeekData() {
        huli_detail.append(" ");
        for (int i = 0; i < splits_week_huli.length; i++) {
            huli_detail.append(splits_week_huli[i].toString() + "\n");
        }
        weiyang_detail.append(details_week_weiyang[position]);
        boys_zhibiao_shengao.append(split_boys_week_zhibiao[0]);
        boys_zhibiao_tizhong.append(split_boys_week_zhibiao[1]);
        boys_zhibiao_zuogao.append(split_boys_week_zhibiao[2]);
        boys_zhibiao_touwei.append(split_boys_week_zhibiao[3]);
        boys_zhibiao_xiongwei.append(split_boys_week_zhibiao[4]);

        girls_zhibiao_shengao.append(split_girls_week_zhibiao[0]);
        girls_zhibiao_tizhong.append(split_girls_week_zhibiao[1]);
        girls_zhibiao_zuogao.append(split_girls_week_zhibiao[2]);
        girls_zhibiao_touwei.append(split_girls_week_zhibiao[3]);
        girls_zhibiao_xiongwei.append(split_girls_week_zhibiao[4]);
    }

    private void setYearData() {
        huli_detail.append(" ");
        for (int i = 0; i < split_year_huli.length; i++) {
            huli_detail.append(split_year_huli[i].toString() + "\n");
        }
        for (int i = 0; i < split_year_weiyang.length; i++) {
            weiyang_detail.append(split_year_weiyang[i].toString() + "\n");
        }
        boys_zhibiao_shengao.append(split_year_boys_zhibiao[0]);
        boys_zhibiao_tizhong.append(split_year_boys_zhibiao[1]);
        boys_zhibiao_zuogao.append(split_year_boys_zhibiao[2]);
        boys_zhibiao_touwei.append(split_year_boys_zhibiao[3]);
        boys_zhibiao_xiongwei.append(split_year_boys_zhibiao[4]);

        girls_zhibiao_shengao.append(split_year_girls_zhibiao[0]);
        girls_zhibiao_tizhong.append(split_year_girls_zhibiao[1]);
        girls_zhibiao_zuogao.append(split_year_girls_zhibiao[2]);
        girls_zhibiao_touwei.append(split_year_girls_zhibiao[3]);
        girls_zhibiao_xiongwei.append(split_year_girls_zhibiao[4]);
    }

    private void getData() {
        details_week_huli = getResources().getStringArray(R.array.hu_li_0_144);
        details_year_huli = getResources().getStringArray(R.array.hu_li_0_3);
        details_week_weiyang = getResources().getStringArray(R.array.wei_yang_0_144);
        details_year_weiyang = getResources().getStringArray(R.array.wei_yang_0_3);
        details_week_boys_zhibiao = getResources().getStringArray(R.array.zhi_biao_boy_0_36);
        details_week_girls_zhibiao = getResources().getStringArray(R.array.zhi_biao_girl_0_36);
        details_year_boys_zhibiao = getResources().getStringArray(R.array.zhi_biao_boy_0_3);
        details_year_girls_zhibiao = getResources().getStringArray(R.array.zhi_biao_girl_0_3);
    }

}