package com.cmbb.smartkids.fragment.baby.babydaily;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.baby.babylist.BabyListModel;
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
public class BabyDailyProvider extends DataController<BabyDailyModel> {

    private Context mContext;

    private BabyListModel mBabyListModel;

    public BabyDailyProvider(Context context, BabyListModel babyRelationId) {
        mContext = context;
        this.mBabyListModel = babyRelationId;
    }


    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("babyRelationId", mBabyListModel.getBabyId() + "");
        OkHttp.asyncPost(Constants.Baby.FINDBYBABYGROWING_URL, body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("babyRelationId", mBabyListModel.getBabyId() + "");
        OkHttp.asyncPost(Constants.Baby.FINDBYBABYGROWING_URL, body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("babyRelationId", mBabyListModel.getBabyId() + "");
        OkHttp.asyncPost(Constants.Baby.FINDBYBABYGROWING_URL, body, callback);
    }

    @Override
    public List<BabyDailyModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            BabyDailyBaseModel data = gson.fromJson(result, BabyDailyBaseModel.class);
            List<BabyDailyModel> plateModels = data.getContext();
            return plateModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<BabyDailyModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }
}
