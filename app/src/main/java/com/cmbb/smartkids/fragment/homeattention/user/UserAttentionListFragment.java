package com.cmbb.smartkids.fragment.homeattention.user;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class UserAttentionListFragment extends CommonFragment<UserAttentionModel> {
    boolean need;


    public UserAttentionListFragment() {
    }

    public UserAttentionListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<UserAttentionModel> onGenerateDataController() {
        return new UserAttentionListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<UserAttentionModel> controller) {
        return new UserAttentionListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
