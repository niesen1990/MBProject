package com.cmbb.smartkids.fragment.usercenter.sameage;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SameAgePublishListFragment extends CommonFragment<SameAgePublishModel> {
    boolean need;
    int userId;

    public SameAgePublishListFragment(boolean need, int userId) {
        this.need = need;
        this.userId = userId;
    }

    public SameAgePublishListFragment() {
    }


    @Override
    protected DataController<SameAgePublishModel> onGenerateDataController() {
        return new SameAgePublishListProvider(userId);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<SameAgePublishModel> controller) {
        return new SameAgePublishListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
