package com.cmbb.smartkids.fragment.record;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.fragment.message.MessageListAdapter;
import com.cmbb.smartkids.fragment.message.MessageListProvider;
import com.cmbb.smartkids.fragment.message.MessageModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class RecordListFragment extends CommonFragment<RecordModel.ContextEntity> {
    boolean need;


    public RecordListFragment() {
    }

    @SuppressLint("ValidFragment")
    public RecordListFragment(boolean needHeadView) {
        this.need = needHeadView;
    }

    @Override
    protected DataController<RecordModel.ContextEntity> onGenerateDataController() {
        return new RecordListProvider();
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<RecordModel.ContextEntity> controller) {
        return new RecordListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
