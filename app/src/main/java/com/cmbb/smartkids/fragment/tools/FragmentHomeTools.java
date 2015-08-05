package com.cmbb.smartkids.fragment.tools;


import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.tools.ToolsCaseActivity;
import com.cmbb.smartkids.activity.tools.ToolsMapActivity;
import com.cmbb.smartkids.activity.tools.ToolsSuggestionsActivity;
import com.cmbb.smartkids.activity.tools.ToolsVaccinationActivity;
import com.cmbb.smartkids.base.MFragment;


public class FragmentHomeTools extends MFragment {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_tools, container, false);
        initialView(view);
        return view;
    }

    private void initialView(View view) {
        view.findViewById(R.id.toolss_tv1).setOnClickListener(this);
        view.findViewById(R.id.toolss_tv2).setOnClickListener(this);
        view.findViewById(R.id.toolss_tv4).setOnClickListener(this);
        view.findViewById(R.id.toolss_tv5).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.toolss_tv1://急症室
                Intent intentCase = new Intent(getActivity(), ToolsCaseActivity.class);
                startActivity(intentCase);
                return;
            case R.id.toolss_tv2://导航
                Intent intentMap = new Intent(getActivity(), ToolsMapActivity.class);
                startActivity(intentMap);
                return;
            case R.id.toolss_tv4://育儿建议
                Intent intentSuggestions = new Intent(getActivity(), ToolsSuggestionsActivity.class);
                startActivity(intentSuggestions);
                return;
            case R.id.toolss_tv5://疫苗
                Intent intentVaccination = new Intent(getActivity(), ToolsVaccinationActivity.class);
                startActivity(intentVaccination);
                return;

        }

    }

}
