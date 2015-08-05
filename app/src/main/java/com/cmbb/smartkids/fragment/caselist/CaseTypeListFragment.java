package com.cmbb.smartkids.fragment.caselist;

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
public class CaseTypeListFragment extends CommonFragment<CaseTypeModel> {
    boolean need;
    CaseTypeListViewHolder.OnCaseTypeClickListener mOnCaseTypeClickListener;


    public CaseTypeListFragment() {
    }

    @SuppressLint("ValidFragment")
    public CaseTypeListFragment(boolean need, CaseTypeListViewHolder.OnCaseTypeClickListener mOnCaseTypeClickListener) {
        this.need = need;
        this.mOnCaseTypeClickListener = mOnCaseTypeClickListener;
    }

    @Override
    protected DataController<CaseTypeModel> onGenerateDataController() {
        return new CaseTypeListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<CaseTypeModel> controller) {
        return new CaseTpyeListAdapter(getActivity(), controller, false, mOnCaseTypeClickListener);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
