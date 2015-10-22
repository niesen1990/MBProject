package com.cmbb.smartkids.fragment.record;

import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
import com.cmbb.smartkids.fragment.replay.ReplayBaseModel;
import com.cmbb.smartkids.fragment.replay.ReplayModel;
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
public class RecordListProvider extends DataController<RecordModel.ContextEntity> {
    private int id = -1;

    String url = Constants.BASE_URL + "/gold/goldFindRecord";

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("map", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(url, body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        id = -1;
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(url, body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        if (-1 != id) {
            body.put("id", id + "");
            body.put("upDown", 2 + "");
        }
        OkHttp.asyncPost(url, body, callback);
    }

    @Override
    public List<RecordModel.ContextEntity> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("responseReplay", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            RecordModel data = gson.fromJson(result, RecordModel.class);
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
    public void doSave(List<RecordModel.ContextEntity> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
