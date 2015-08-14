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
public class EredarRightAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private List<EredarRightModel> mList = new ArrayList<>();

    public EredarRightAdapter(Context context, List<EredarRightModel> list) {
        mContext = context;
        updateData(list);
    }

    public void updateData(List<EredarRightModel> list) {
        if (list == null || list.size() == 0) {
            return;
        }
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return EredarRightViewHolder.create(mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((EredarRightViewHolder) holder).onBindViewHolder(mContext, mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
