package com.cmbb.smartkids.fragment.expert;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.master.MasterTypeModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class ExpertTypeListFragment extends CommonFragment<MasterTypeModel> {
    boolean need;
    ExpertTypeListViewHolder.OnTypeClickListener mOnTypeClickListener;


    public ExpertTypeListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ExpertTypeListFragment(boolean need, ExpertTypeListViewHolder.OnTypeClickListener mOnTypeClickListener) {
        this.need = need;
        this.mOnTypeClickListener = mOnTypeClickListener;
    }

    @Override
    protected DataController<MasterTypeModel> onGenerateDataController() {
        return new ExpertListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<MasterTypeModel> controller) {
        return new ExpertListAdapter(getActivity(), controller, false, mOnTypeClickListener);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
