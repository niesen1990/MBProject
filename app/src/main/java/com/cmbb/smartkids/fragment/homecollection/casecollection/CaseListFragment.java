package com.cmbb.smartkids.fragment.homecollection.casecollection;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class CaseListFragment extends CommonFragment<CaseModel> {
    boolean need;


    public CaseListFragment() {
    }

    public CaseListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<CaseModel> onGenerateDataController() {
        return new CaseListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<CaseModel> controller) {
        return new CaseListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
