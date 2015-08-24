package com.cmbb.smartkids.fragment.baby.babydaily;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.baby.babylist.BabyListModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class BabyDailyFragment extends CommonFragment<BabyDailyModel> {
    boolean need;
    BabyListModel mBabyListModel;


    public BabyDailyFragment() {
    }

    @SuppressLint("ValidFragment")
    public BabyDailyFragment(boolean needHeadView, BabyListModel mBabyListModel) {
        this.need = needHeadView;
        this.mBabyListModel = mBabyListModel;
    }

    @Override
    protected DataController<BabyDailyModel> onGenerateDataController() {
        return new BabyDailyProvider(getActivity(), mBabyListModel);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<BabyDailyModel> controller) {
        return new BabyDailyAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
