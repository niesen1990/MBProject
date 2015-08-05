package com.cmbb.smartkids.fragment.caselist;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.network.OkHttp;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:56
 */
public class CaseTypeListProvider extends DataController<CaseTypeModel> {

    private Context mContext;

    public CaseTypeListProvider(Context context) {
        mContext = context;
    }


    @Override
    public void doInitialize(Callback callback) {
        OkHttp.asyncGet(Constants.Case.FINDCASETYPE_URL, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        //OkHttp.asyncGet(Constants.Case.FINDCASETYPE_URL, callback);
    }

    @Override
    public void doMore(Callback callback) {
        //OkHttp.asyncGet(Constants.Case.FINDCASETYPE_URL, callback);
    }

    @Override
    public List<CaseTypeModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            CaseTypeBaseModel data = gson.fromJson(result, CaseTypeBaseModel.class);
            return data.getContext();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<CaseTypeModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

}
