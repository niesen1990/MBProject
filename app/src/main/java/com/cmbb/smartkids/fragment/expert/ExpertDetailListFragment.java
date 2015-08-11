package com.cmbb.smartkids.fragment.expert;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.master.MasterTypeModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class ExpertDetailListFragment extends CommonFragment<ExpertDetailModel> {
    boolean need;
    MasterTypeModel mMasterTypeModel;

    public ExpertDetailListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ExpertDetailListFragment(boolean need, MasterTypeModel mMasterTypeModel) {
        this.need = need;
        this.mMasterTypeModel = mMasterTypeModel;
    }

    @Override
    protected DataController<ExpertDetailModel> onGenerateDataController() {
        return new ExpertListDetailProvider(getActivity(), mMasterTypeModel);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<ExpertDetailModel> controller) {
        return new ExpertDetailListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
