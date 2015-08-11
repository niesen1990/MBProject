package com.cmbb.smartkids.fragment.master;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class MasterTypeListFragment extends CommonFragment<MasterTypeModel> {
    boolean need;
    MasterTypeListViewHolder.OnTypeClickListener mOnTypeClickListener;


    public MasterTypeListFragment() {
    }

    @SuppressLint("ValidFragment")
    public MasterTypeListFragment(boolean need, MasterTypeListViewHolder.OnTypeClickListener mOnTypeClickListener) {
        this.need = need;
        this.mOnTypeClickListener = mOnTypeClickListener;
    }

    @Override
    protected DataController<MasterTypeModel> onGenerateDataController() {
        return new MasterListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<MasterTypeModel> controller) {
        return new MasterListAdapter(getActivity(), controller, false, mOnTypeClickListener);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
