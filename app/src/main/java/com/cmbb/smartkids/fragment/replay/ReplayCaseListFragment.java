package com.cmbb.smartkids.fragment.replay;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class ReplayCaseListFragment extends CommonFragment<ReplayModel> {
    boolean need;
    CaseDetailListModel mCaseDetailListModel;
    PostDetail mPostDetail;

    private ReplayListViewHolder.OnReplayItemClickListener mOnReplayItemClickListener;

    LinearLayout mHeadView;

    public ReplayCaseListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ReplayCaseListFragment(boolean needHeadView, PostDetail postDetail, CaseDetailListModel caseDetailListModel, LinearLayout headView, ReplayListViewHolder.OnReplayItemClickListener onReplayItemClickListener) {
        super();
        this.mPostDetail = postDetail;
        this.mOnReplayItemClickListener = onReplayItemClickListener;
        this.need = needHeadView;
        this.mCaseDetailListModel = caseDetailListModel;
        this.mHeadView = headView;

    }

    @Override
    protected DataController<ReplayModel> onGenerateDataController() {
        return new ReplayCaseListProvider(mCaseDetailListModel);

    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<ReplayModel> controller) {
        PostModel postModel = new PostModel();
        postModel.setUserSmallHeadImg(mCaseDetailListModel.getUserSmallHeadImg());
        postModel.setNike(mCaseDetailListModel.getNike());
        postModel.setAreaId(mCaseDetailListModel.getId());
        postModel.setType(mCaseDetailListModel.getType() + "");
        postModel.setId(mCaseDetailListModel.getId());
        postModel.setUserId(mCaseDetailListModel.getUserId());
        postModel.setStore(mCaseDetailListModel.getStore());
        postModel.setAuthority(mCaseDetailListModel.getAuthority());
        return new ReplayListAdapter(getActivity(), postModel, controller, need, mHeadView, mOnReplayItemClickListener);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
