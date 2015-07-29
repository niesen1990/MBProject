package com.cmbb.smartkids.activity.tools;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.cmbb.smartkids.R;

import java.util.ArrayList;
import java.util.List;

public class ToolsSuggestionsAdapter extends BaseAdapter {
    private List<Integer> list;

    public ToolsSuggestionsAdapter(List<Integer> list) {
        if(list != null){
            this.list = list;
        }else{
            this.list = new ArrayList<Integer>();;
        }

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_tools_suggestions_item, null);
        }
        TextView tv_count = (TextView) convertView.findViewById(R.id.tv_count);
        tv_count.setText(list.get(position).toString());
        return convertView;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
}
