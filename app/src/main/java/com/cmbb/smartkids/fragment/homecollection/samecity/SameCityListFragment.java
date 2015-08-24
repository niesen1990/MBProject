package com.cmbb.smartkids.fragment.homecollection.samecity;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SameCityListFragment extends CommonFragment<PostModel> {
    boolean need;


    public SameCityListFragment() {
    }

    @SuppressLint("ValidFragment")
    public SameCityListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<PostModel> onGenerateDataController() {
        return new SameCityListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PostModel> controller) {
        return new SameCityListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
