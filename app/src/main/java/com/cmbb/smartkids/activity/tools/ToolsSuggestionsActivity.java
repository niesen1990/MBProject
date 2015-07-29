package com.cmbb.smartkids.activity.tools;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ToolsSuggestionsActivity extends MActivity implements Serializable {

    private ListView lv;
    private List<Integer> list;
    private ToolsSuggestionsAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_tools_suggestions;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        list = new ArrayList<Integer>();
        getData();
        lv = (ListView) findViewById(R.id.lv_home_tools);
        adapter = new ToolsSuggestionsAdapter(list);
        View v = LayoutInflater.from(this).inflate(R.layout.view_tools_suggestions_footer, null);
        TextView tvFourYears = (TextView) v.findViewById(R.id.tv_four_years);
        TextView tvFiveYears = (TextView) v.findViewById(R.id.tv_five_years);
        TextView tvSixYears = (TextView) v.findViewById(R.id.tv_six_years);
        tvFourYears.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToolsSuggestionsActivity.this, ToolsSugDetailActivity.class);
                intent.putExtra("position", 145);
                startActivity(intent);
            }
        });
        tvFiveYears.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToolsSuggestionsActivity.this, ToolsSugDetailActivity.class);
                intent.putExtra("position", 146);
                startActivity(intent);
            }
        });
        tvSixYears.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ToolsSuggestionsActivity.this, ToolsSugDetailActivity.class);
                intent.putExtra("position", 147);
                startActivity(intent);
            }
        });
        lv.addFooterView(v);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent intent = new Intent(ToolsSuggestionsActivity.this, ToolsSugDetailActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }
        });
    }

    public void getData() {
        for (int i = 1; i <= 144; i++) {
            list.add(i);
        }
    }

}
