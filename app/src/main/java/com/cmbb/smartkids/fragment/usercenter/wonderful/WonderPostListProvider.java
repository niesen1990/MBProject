package com.cmbb.smartkids.fragment.usercenter.wonderful;

import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.homeplate.HomePlateModel;
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
public class WonderPostListProvider extends DataController<WonderPublicModel> {


    HomePlateModel mHomePlateModel;

    public WonderPostListProvider( HomePlateModel homePlateModel) {
        this.mHomePlateModel = homePlateModel;
    }

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("plateId", mHomePlateModel.getId() + "");
        body.put("areaType", "WONDERFUL");
        /*if (!TextUtils.isEmpty(postId)) {
            body.put("id", postId);
            body.put("upDown", 2 + "");
        }*/
        body.put("type", mHomePlateModel.getType());
        OkHttp.asyncPost(Constants.BASE_URL + mHomePlateModel.getConnector() + "FindPublish", body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("plateId", mHomePlateModel.getId() + "");
        body.put("areaType", "WONDERFUL");
        /*if (!TextUtils.isEmpty(postId)) {
            body.put("id", postId);
            body.put("upDown", 2 + "");
        }*/
        body.put("type", mHomePlateModel.getType());
        OkHttp.asyncPost(Constants.BASE_URL + mHomePlateModel.getConnector() + "FindPublish", body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("plateId", mHomePlateModel.getId() + "");
        body.put("areaType", "WONDERFUL");
        /*if (!TextUtils.isEmpty(postId)) {
            body.put("id", postId);
            body.put("upDown", 2 + "");
        }*/
        body.put("type", mHomePlateModel.getType());
        OkHttp.asyncPost(Constants.BASE_URL + mHomePlateModel.getConnector() + "FindPublish", body, callback);
    }

    @Override
    public List<WonderPublicModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            WonderPublicBaseModel data = gson.fromJson(result, WonderPublicBaseModel.class);
            Log.i("list", "list = " + data.getContext().getHomeSameAgeList().size());
            return data.getContext().getHomeSameAgeList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<WonderPublicModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
