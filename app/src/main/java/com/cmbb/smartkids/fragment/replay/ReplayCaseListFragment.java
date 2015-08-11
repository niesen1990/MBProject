package com.cmbb.smartkids.fragment.replay;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
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

    LinearLayout mHeadView;

    public ReplayCaseListFragment() {
    }

    @SuppressLint("ValidFragment")
    public ReplayCaseListFragment(boolean needHeadView, CaseDetailListModel caseDetailListModel, LinearLayout headView) {
        super();
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
        return new ReplayListAdapter(getActivity(), controller, need, mHeadView);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
