package com.cmbb.smartkids.fragment.usercenter.wonderful;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.homeplate.HomePlateModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.log.Log;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class WonderPostListFragment extends CommonFragment<WonderPublicModel> {
    boolean need;
    HomePlateModel mHomePlateModel;

    public WonderPostListFragment() {
    }

    public WonderPostListFragment(boolean needHeadView, HomePlateModel homePlateModel) {
        super();
        this.need = needHeadView;
        this.mHomePlateModel = homePlateModel;

    }

    @Override
    protected DataController<WonderPublicModel> onGenerateDataController() {
        return new WonderPostListProvider(mHomePlateModel);

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
