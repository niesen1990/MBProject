package com.cmbb.smartkids.fragment.homecollection.wonderful;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class WonderCollectionListFragment extends CommonFragment<WonderCollectionModel> {
    boolean need;


    public WonderCollectionListFragment() {
    }

    public WonderCollectionListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<WonderCollectionModel> onGenerateDataController() {
        return new WonderCollectionListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<WonderCollectionModel> controller) {
        return new WonderCollectionListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
