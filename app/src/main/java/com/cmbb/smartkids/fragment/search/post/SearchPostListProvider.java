package com.cmbb.smartkids.fragment.search.post;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.model.search.SearchBaseModel;
import com.cmbb.smartkids.model.search.SearchModel;
import com.cmbb.smartkids.network.OkHttp;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:56
 */
public class SearchPostListProvider extends DataController<SearchModel> {

    private Context mContext;
    private String content;

    public SearchPostListProvider(Context context, String content) {
        mContext = context;
        this.content = content;
    }

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", 2 + "");
        body.put("name", content);
        OkHttp.asyncPost(Constants.Search.SEARCH_URL, body, content, callback);
    }

    @Override
    public void doRefresh(Callback callback) {

    }

    @Override
    public void doMore(Callback callback) {

    }

    @Override
    public List<SearchModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            SearchBaseModel data = gson.fromJson(result, SearchBaseModel.class);
            return data.getContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<SearchModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
