package com.cmbb.smartkids.fragment.homecollection.samecity;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SameCityListFragment extends CommonFragment<SameCityModel> {
    boolean need;


    public SameCityListFragment() {
    }

    public SameCityListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<SameCityModel> onGenerateDataController() {
        return new SameCityListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<SameCityModel> controller) {
        return new SameCityListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
