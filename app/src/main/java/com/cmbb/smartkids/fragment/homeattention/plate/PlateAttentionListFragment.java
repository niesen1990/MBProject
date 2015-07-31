package com.cmbb.smartkids.fragment.homeattention.plate;

import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class PlateAttentionListFragment extends CommonFragment<PlateAttentionModel> {
    boolean need;


    public PlateAttentionListFragment() {
    }

    public PlateAttentionListFragment(boolean need) {
        this.need = need;
    }

    @Override
    protected DataController<PlateAttentionModel> onGenerateDataController() {
        return new PlateAttentionListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<PlateAttentionModel> controller) {
        return new PlateAttentionListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
