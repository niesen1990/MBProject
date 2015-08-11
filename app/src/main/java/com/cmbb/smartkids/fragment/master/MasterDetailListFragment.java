package com.cmbb.smartkids.fragment.master;

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
public class MasterDetailListFragment extends CommonFragment<MasterDetailModel> {
    boolean need;
    MasterTypeModel mMasterTypeModel;

    public MasterDetailListFragment() {
    }

    @SuppressLint("ValidFragment")
    public MasterDetailListFragment(boolean need, MasterTypeModel mMasterTypeModel) {
        this.need = need;
        this.mMasterTypeModel = mMasterTypeModel;
    }

    @Override
    protected DataController<MasterDetailModel> onGenerateDataController() {
        return new MasterListDetailProvider(getActivity(), mMasterTypeModel);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<MasterDetailModel> controller) {
        return new MasterDetailListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
