package com.cmbb.smartkids.fragment.replay;

import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
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
public class ReplayListProvider extends DataController<ReplayModel> {


    PostModel mPostModel;

    private int sort = 2;
    private int id = -1;

    public ReplayListProvider(PostModel postModel) {
        this.mPostModel = postModel;
    }

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        body.put("sort", sort + "");

        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("map", "key = " + entry.getKey() + " value = " + entry.getValue());
        }

        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "FindReplys", body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        id = -1;
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        body.put("sort", sort + "");
        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "FindReplys", body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        body.put("sort", sort + "");
        if (-1 != id) {
            body.put("replyId", id + "");
            body.put("upDown", 2 + "");
        }

        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "FindReplys", body, callback);
    }

    @Override
    public List<ReplayModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("responseReplay", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            ReplayBaseModel data = gson.fromJson(result, ReplayBaseModel.class);
            Log.i("list", "list = " + data.getContext().size());
            try {
                id = data.getContext().get(data.getContext().size() - 1).getId();
            } catch (Exception e) {

            }
            return data.getContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<ReplayModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
