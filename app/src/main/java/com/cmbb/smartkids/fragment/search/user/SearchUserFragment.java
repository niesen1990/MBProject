package com.cmbb.smartkids.fragment.search.user;

import android.annotation.SuppressLint;
import android.support.v7.widget.RecyclerView;

import com.cmbb.smartkids.base.CommonFragment;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.model.search.SearchModel;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:54
 */
public class SearchUserFragment extends CommonFragment<SearchModel> {
    boolean need;
    String content;


    public SearchUserFragment() {
    }

    @SuppressLint("ValidFragment")
    public SearchUserFragment(boolean need, String content) {
        this.need = need;
        this.content = content;
    }

    @Override
    protected DataController<SearchModel> onGenerateDataController() {
        return new SearchUserListProvider(getActivity(), content);
    }

    @Override
    protected RecyclerView.Adapter onGenerateAdapter(DataController<SearchModel> controller) {
        return new SearchUserListAdapter(getActivity(), controller, false);
    }

    @Override
    protected boolean enableRefresh() {
        return false;
    }


}
