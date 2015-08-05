package com.cmbb.smartkids.fragment.message;

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
public class MessageListFragment extends CommonFragment<MessageModel> {
    boolean need;


    public MessageListFragment() {
    }

    @SuppressLint("ValidFragment")
    public MessageListFragment(boolean needHeadView) {
        this.need = needHeadView;
    }

    @Override
    protected DataController<MessageModel> onGenerateDataController() {
        return new MessageListProvider(getActivity());
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<MessageModel> controller) {
        return new MessageListAdapter(getActivity(), controller, need);
    }

    @Override
    protected boolean enableRefresh() {
        return true;
    }


}
