package com.cmbb.smartkids.fragment.homepublish.wonderful;

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
public class WonderPublicListFragment extends CommonFragment<PostModel> {
    boolean need;


    public WonderPublicListFragment() {
    }

    @SuppressLint("ValidFragment")
    public WonderPublicListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<PostModel> onGenerateDataController() {
        return new WonderPublicListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PostModel> controller) {
        return new WonderPublicListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
