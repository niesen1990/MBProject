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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:56
 */
public class MasterListDetailProvider extends DataController<MasterDetailModel> {

    private Context mContext;
    private MasterTypeModel mMasterTypeModel;

    public MasterListDetailProvider(Context context, MasterTypeModel mMasterTypeModel) {
        mContext = context;
        this.mMasterTypeModel = mMasterTypeModel;
    }


    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        Log.i("master", "master = " + mMasterTypeModel);
        if (mMasterTypeModel.getRec() == -1) {
            body.put("token", MApplication.token);
            body.put("type", mMasterTypeModel.getEredarType() + "");
            OkHttp.asyncPost(Constants.Master.EREDARFINDBYTYPEUSER_URL, body, callback);
        } else {
            body.put("token", MApplication.token);
            body.put("recommend", mMasterTypeModel.getRec() + "");
            OkHttp.asyncPost(Constants.Master.EREDARFINDATTENTIONUSER_URL, body, callback);
        }
    }

    @Override
    public void doRefresh(Callback callback) {

    }

    @Override
    public void doMore(Callback callback) {

    }

    @Override
    public List<MasterDetailModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            EredarDetailBaseModel data = gson.fromJson(result, EredarDetailBaseModel.class);
            return data.getContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<MasterDetailModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }
}
