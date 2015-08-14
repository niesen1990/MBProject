package com.cmbb.smartkids.fragment.postlist.wonder;

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
public class WonderPublicListProvider extends DataController<PostModel> {

    int userId;
    int id = -1;

    public WonderPublicListProvider(int userId) {
        this.userId = userId;
    }

    @Override
    public void doInitialize(Callback callback) {
        id = -1;
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("userId", userId + "");
        OkHttp.asyncPost(Constants.User.WONDERFULPUBLISH_URL, body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        id = -1;
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("userId", userId + "");
        OkHttp.asyncPost(Constants.User.WONDERFULPUBLISH_URL, body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("userId", userId + "");
        if (id != -1) {
            body.put("id", id + "");
            body.put("upDown", 2 + "");
        }
        OkHttp.asyncPost(Constants.User.WONDERFULPUBLISH_URL, body, callback);
    }

    @Override
    public List<PostModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            WonderPublicBaseModel data = gson.fromJson(result, WonderPublicBaseModel.class);
            Log.i("list", "list = " + data.getContext().getHomeSameAgeList().size());
            try {
                id = data.getContext().getHomeSameAgeList().get(data.getContext().getHomeSameAgeList().size() - 1).getId();
            } catch (Exception e) {

            }
            return data.getContext().getHomeSameAgeList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<PostModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
