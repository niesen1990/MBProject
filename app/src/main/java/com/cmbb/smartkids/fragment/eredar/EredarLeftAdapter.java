package com.cmbb.smartkids.fragment.eredar;

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
public class EredarLeftAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    EredarLeftViewHolder.OnTypeClickListener mOnTypeClickListener;

    private List<EredarTypeModel> mList = new ArrayList<>();

    public List<EredarTypeModel> getList() {
        return mList;
    }

    public void setList(List<EredarTypeModel> list) {
        mList = list;
    }

    public EredarLeftAdapter(Context context, EredarLeftViewHolder.OnTypeClickListener onTypeClickListener, List<EredarTypeModel> list) {
        mContext = context;
        mOnTypeClickListener = onTypeClickListener;
        updateData(list);
    }

    public void updateData(List<EredarTypeModel> list) {
        if (null == list || list.size() == 0) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return EredarLeftViewHolder.create(mContext, parent, mOnTypeClickListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EredarLeftViewHolder) holder).onBindViewHolder(mContext, mList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
