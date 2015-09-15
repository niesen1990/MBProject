package com.cmbb.smartkids.fragment.replay;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

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
public class ReplayListFragment extends CommonFragment<ReplayModel> {
    boolean need;
    PostModel mPostModel;
    PostDetail mPostDetail;
    public ReplayListProvider mReplayListProvider;
    public ReplayListAdapter mReplayListAdapter;
    int sort;

    ReplayListViewHolder.OnReplayItemClickListener mOnReplayItemClickListener;
    ReplayListViewHolder.OnReplayClickListener mOnReplayClickListener;

    LinearLayout mHeadView;

    public ReplayListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ReplayListFragment(boolean needHeadView, PostDetail postDetail, PostModel postModel, LinearLayout headView, int sort, ReplayListViewHolder.OnReplayItemClickListener onReplayItemClickListener, ReplayListViewHolder.OnReplayClickListener onReplayClickListener) {
        super();
        this.mPostDetail = postDetail;
        this.sort = sort;
        this.mOnReplayItemClickListener = onReplayItemClickListener;
        this.mOnReplayClickListener = onReplayClickListener;
        this.need = needHeadView;
        this.mPostModel = postModel;
        this.mHeadView = headView;
    }

    @Override
    protected DataController<ReplayModel> onGenerateDataController() {
        mReplayListProvider = new ReplayListProvider(mPostModel, sort);
        return mReplayListProvider;
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<ReplayModel> controller) {
        Log.i("adapter", " generate");
        mReplayListAdapter = new ReplayListAdapter(getActivity(), mPostModel, controller, need, mHeadView, mOnReplayItemClickListener, mOnReplayClickListener);
        return mReplayListAdapter;
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
