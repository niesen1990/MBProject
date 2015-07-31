package com.cmbb.smartkids.fragment.homeplate;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.cmbb.smartkids.base.Constants;
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
public class HomeListProvider extends DataController<HomePlateModel> {

    private Context mContext;

    public HomeListProvider(Context context) {
        mContext = context;
    }

    public HomePageBaseModel baseData;

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost(Constants.Home.FINDHOMEPAGE_URL, body, callback);
    }

    @Override
    public List<HomePlateModel> doParser(Response response) {
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
            return data.getContext().getPlateList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void doSave(List<HomePlateModel> data) {
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