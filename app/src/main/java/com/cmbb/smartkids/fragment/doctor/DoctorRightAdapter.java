package com.cmbb.smartkids.fragment.doctor;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/13 下午2:37
 */
public class DoctorRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<DoctorRightModel> mList = new ArrayList<>();

    public DoctorRightAdapter(Context context, List<DoctorRightModel> list) {
        mContext = context;
        updateData(list);
    }

    public void updateData(List<DoctorRightModel> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return DoctorRightViewHolder.create(mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((DoctorRightViewHolder) holder).onBindViewHolder(mContext, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
