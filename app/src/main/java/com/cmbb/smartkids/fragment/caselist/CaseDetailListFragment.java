package com.cmbb.smartkids.fragment.caselist;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class CaseDetailListFragment extends CommonFragment<CaseDetailListModel> {
    boolean need;
    private CaseTypeModel mCaseTypeModel;


    public CaseDetailListFragment() {
    }

    @SuppressLint("ValidFragment")
    public CaseDetailListFragment(boolean need, CaseTypeModel caseTypeModel) {
        this.need = need;
        this.mCaseTypeModel = caseTypeModel;
    }

    @Override
    protected DataController<CaseDetailListModel> onGenerateDataController() {
        return new CaseDetailListProvider(getActivity(), mCaseTypeModel);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<CaseDetailListModel> controller) {
        return new CaseDetailListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }

}
