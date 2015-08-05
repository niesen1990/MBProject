package com.cmbb.smartkids.fragment.postlist.wonder;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.log.Log;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class WonderPublicListFragment extends CommonFragment<PostModel> {
    boolean need;
    int userId;


    public WonderPublicListFragment() {
    }

    @SuppressLint("ValidFragment")
    public WonderPublicListFragment(boolean need, int userId) {
        super();
        this.need = need;
        this.userId = userId;
    }

    @Override
    protected DataController<PostModel> onGenerateDataController() {
        return new WonderPublicListProvider(userId);

    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PostModel> controller) {
        Log.i("adapter", " generate");
        return new PostListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
