package com.cmbb.smartkids.fragment.usercenter.wonderful;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.log.Log;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class WonderPublicListFragment extends CommonFragment<WonderPublicModel> {
    boolean need;
    int userId;


    public WonderPublicListFragment() {
    }

    public WonderPublicListFragment(boolean need, int userId) {
        super();
        this.need = need;
        this.userId = userId;
    }

    @Override
    protected DataController<WonderPublicModel> onGenerateDataController() {
        return new WonderPublicListProvider(userId);

    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<WonderPublicModel> controller) {
        Log.i("adapter", " generate");
        return new WonderPublicListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
