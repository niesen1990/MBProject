package com.cmbb.smartkids.fragment.platelist;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class PlateAgeListFragment extends CommonFragment<PlateModel> {
    boolean need;


    public PlateAgeListFragment() {
    }

    public PlateAgeListFragment(boolean needHeadView) {
        this.need = needHeadView;
    }

    @Override
    protected DataController<PlateModel> onGenerateDataController() {
        return new PlateAgeListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PlateModel> controller) {
        return new PlateWonderListAdapter(getActivity(), controller, need, false);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
