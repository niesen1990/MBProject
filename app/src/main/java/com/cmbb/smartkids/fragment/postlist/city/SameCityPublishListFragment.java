package com.cmbb.smartkids.fragment.postlist.city;

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
public class SameCityPublishListFragment extends CommonFragment<PostModel> {
    boolean need;
    int userId;

    public SameCityPublishListFragment(boolean need, int userId) {
        this.need = need;
        this.userId = userId;
    }

    public SameCityPublishListFragment() {
    }


    @Override
    protected DataController<PostModel> onGenerateDataController() {
        return new SameCityPublishListProvider(userId);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PostModel> controller) {
        return new SameCityPublishListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
