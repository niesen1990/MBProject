package com.cmbb.smartkids.fragment.caselist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.replay.ReplayCaseActivity;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class CaseDetailListViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout mRlCaseUp;
    private RelativeLayout rvRoot;
    private TextView mTvCaseName;
    private TextView mTvCaseTime;
    private TextView mTvCaseReplayCount;


    private CaseDetailListViewHolder(View view) {
        super(view);
        mRlCaseUp = (RelativeLayout) view.findViewById(R.id.rl_case_up);
        rvRoot = (RelativeLayout) view.findViewById(R.id.rv_root);
        mTvCaseName = (TextView) view.findViewById(R.id.tv_case_name);
        mTvCaseTime = (TextView) view.findViewById(R.id.tv_case_time);
        mTvCaseReplayCount = (TextView) view.findViewById(R.id.tv_case_replay_count);
    }

    public static CaseDetailListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_case_right_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CaseDetailListModel entry = (CaseDetailListModel) v.getTag();
                Intent intent = new Intent(context, ReplayCaseActivity.class);
                intent.putExtra("model", entry);
                context.startActivity(intent);
            }
        });
        return new CaseDetailListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final CaseDetailListModel entry) {
        rvRoot.setTag(entry);
        mTvCaseName.setText(entry.getTitle());
        mTvCaseTime.setText(entry.getDate());
        mTvCaseReplayCount.setText(entry.getReplys() + "");
    }

}
