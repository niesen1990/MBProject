package com.cmbb.smartkids.fragment.test;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class EntryListFragment extends CommonFragment<HomeSameAge> {
    boolean need;


    public EntryListFragment() {
    }

    public EntryListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<HomeSameAge> onGenerateDataController() {
        return new EntryListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<HomeSameAge> controller) {
        return new EntryListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
