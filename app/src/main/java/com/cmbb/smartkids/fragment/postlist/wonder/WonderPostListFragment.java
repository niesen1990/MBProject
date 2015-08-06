package com.cmbb.smartkids.fragment.postlist.wonder;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.log.Log;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class WonderPostListFragment extends CommonFragment<PostModel> {
    boolean need;
    PlateModel mPlateModel;
    Context mContext;

    public WonderPostListFragment() {
    }

    @SuppressLint("ValidFragment")
    public WonderPostListFragment(Context context, boolean needHeadView, PlateModel postModel) {
        super();
        this.mContext = context;
        this.need = needHeadView;
        this.mPlateModel = postModel;

    }

    @Override
    protected DataController<PostModel> onGenerateDataController() {
        return new WonderPostListProvider(mContext, mPlateModel);

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
