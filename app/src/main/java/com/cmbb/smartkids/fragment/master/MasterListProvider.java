package com.cmbb.smartkids.fragment.master;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.network.OkHttp;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:56
 */
public class MasterListProvider extends DataController<MasterTypeModel> {

    private Context mContext;

    public MasterListProvider(Context context) {
        mContext = context;
    }


    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", 1 + "");
        OkHttp.asyncPost(Constants.Master.EREDARFINDTYPE_URL, body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {

    }

    @Override
    public void doMore(Callback callback) {

    }

    @Override
    public List<MasterTypeModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            EredarTypeBaseModel data = gson.fromJson(result, EredarTypeBaseModel.class);
            ArrayList<MasterTypeModel> masterTypeModels = new ArrayList<>();
            masterTypeModels.add(new MasterTypeModel("推荐", 0, 1));
            masterTypeModels.add(new MasterTypeModel("关注", 0, 0));
            masterTypeModels.addAll(data.getContext());
            return masterTypeModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<MasterTypeModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
