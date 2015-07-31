package com.cmbb.smartkids.fragment.homepublish.samecity;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SameCityPublishListFragment extends CommonFragment<SameCityPublishModel> {
    boolean need;


    public SameCityPublishListFragment() {
    }

    public SameCityPublishListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<SameCityPublishModel> onGenerateDataController() {
        return new SameCityPublishListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<SameCityPublishModel> controller) {
        return new SameCityPublishListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
