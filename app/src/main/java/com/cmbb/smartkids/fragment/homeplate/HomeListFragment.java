package com.cmbb.smartkids.fragment.homeplate;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class HomeListFragment extends CommonFragment<PlateModel> {
    boolean need;


    public HomeListFragment() {
    }

    public HomeListFragment(boolean needHeadView) {
        this.need = needHeadView;
    }

    @Override
    protected DataController<PlateModel> onGenerateDataController() {
        return new HomeListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PlateModel> controller) {
        return new HomeListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
