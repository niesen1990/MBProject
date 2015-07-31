package com.cmbb.smartkids.fragment.homecollection.sameage;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SameAgeListFragment extends CommonFragment<SameAgeModel> {
    boolean need;


    public SameAgeListFragment() {
    }

    public SameAgeListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<SameAgeModel> onGenerateDataController() {
        return new SameAgeListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<SameAgeModel> controller) {
        return new SameAgeListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
