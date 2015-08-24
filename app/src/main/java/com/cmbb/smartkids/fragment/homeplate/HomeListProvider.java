package com.cmbb.smartkids.fragment.homeplate;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
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
public class HomeListProvider extends DataController<PlateModel> {

    private Context mContext;

    public HomeListProvider(Context context) {
        mContext = context;
    }

    public HomePageBaseModel baseData;

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, "home_list_provider", callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, "home_list_provider", callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, "home_list_provider", callback);
    }

    @Override
    public List<PlateModel> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            HomePageBaseModel data = gson.fromJson(result, HomePageBaseModel.class);
            baseData = data;
            sendDataToBanner(data);
            List<PlateModel> plateModels = data.getContext().getPlateList();
            return plateModels;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<PlateModel> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }

    private void sendDataToBanner(HomePageBaseModel data) {
        Intent intent = new Intent(Constants.Home.BANNER_DATA_INTENT);
        intent.putParcelableArrayListExtra(Constants.Home.BANNER_DATA, (ArrayList<HomeBannerModel>) data.getContext().getHomeImgList());
        mContext.sendBroadcast(intent);
    }
}
