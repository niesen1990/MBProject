package com.cmbb.smartkids.fragment.search.casepost;

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
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
import com.cmbb.smartkids.model.search.SearchModel;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class SearchCaseListViewHolder extends RecyclerView.ViewHolder {
    private RelativeLayout mRlCaseUp;
    private RelativeLayout rvRoot;
    private TextView mTvCaseName;
    private TextView mTvCaseTime;
    private TextView mTvCaseReplayCount;


    private SearchCaseListViewHolder(View view) {
        super(view);
        mRlCaseUp = (RelativeLayout) view.findViewById(R.id.rl_case_up);
        rvRoot = (RelativeLayout) view.findViewById(R.id.rv_root);
        mTvCaseName = (TextView) view.findViewById(R.id.tv_case_name);
        mTvCaseTime = (TextView) view.findViewById(R.id.tv_case_time);
        mTvCaseReplayCount = (TextView) view.findViewById(R.id.tv_case_replay_count);
    }

    public static SearchCaseListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_case_right_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchModel entry = (SearchModel) v.getTag();
                CaseDetailListModel caseDetailListModel = new CaseDetailListModel();
                caseDetailListModel.setStore(entry.getStore());
                caseDetailListModel.setContext(entry.getContext());
                caseDetailListModel.setDate(entry.getDate());
                caseDetailListModel.setSmallImg(entry.getSmallImg());
                caseDetailListModel.setUserId(entry.getUserId());
                caseDetailListModel.setBigImg(entry.getBigImg());
                caseDetailListModel.setUserSmallHeadImg(entry.getUserSmallHeadImg());
                caseDetailListModel.setAttent(entry.getAttent());
                caseDetailListModel.setId(entry.getId());
                caseDetailListModel.setAuthority(entry.getAuthority());
                caseDetailListModel.setNike(entry.getNike());
                Intent intent = new Intent(context, ReplayCaseActivity.class);
                intent.putExtra("model", caseDetailListModel);
                context.startActivity(intent);
            }
        });
        return new SearchCaseListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final SearchModel entry) {
        rvRoot.setTag(entry);
        mTvCaseName.setText(entry.getTitle());
        mTvCaseTime.setText(entry.getDate());
        mTvCaseReplayCount.setText(entry.getRelpys() + "");
    }

}
