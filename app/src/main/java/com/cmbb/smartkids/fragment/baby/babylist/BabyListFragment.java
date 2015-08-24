package com.cmbb.smartkids.fragment.baby.babylist;

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
public class BabyListFragment extends CommonFragment<BabyListModel> {
    boolean need;


    public BabyListFragment() {
    }

    @SuppressLint("ValidFragment")
    public BabyListFragment(boolean needHeadView) {
        this.need = needHeadView;
    }

    @Override
    protected DataController<BabyListModel> onGenerateDataController() {
        return new BabyListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<BabyListModel> controller) {
        return new BabyListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
