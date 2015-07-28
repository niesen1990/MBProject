package com.cmbb.smartkids.fragment.test;

import android.text.TextUtils;
import android.util.Log;

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
public class EntryListProvider extends DataController<HomeSameAge> {

    @Override
    public void doInitialize(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("areaType", "WONDERFUL");
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost("http://mengbaopai.smart-kids.com/iosAndroid" + "/plate/findByAreaTypeFromPlate", body, callback);
    }

    @Override
    public void doRefresh(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("areaType", "WONDERFUL");
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost("http://mengbaopai.smart-kids.com/iosAndroid" + "/plate/findByAreaTypeFromPlate", body, callback);
    }

    @Override
    public void doMore(Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("areaType", "WONDERFUL");
        body.put("token", "083cbf5c89a44c01a2fe92f9b81baaf5");
        OkHttp.asyncPost("http://mengbaopai.smart-kids.com/iosAndroid" + "/plate/findByAreaTypeFromPlate", body, callback);
    }

    @Override
    public List<HomeSameAge> doParser(Response response) {
        try {
            String result = response.body().string();
            Log.i("response", "response = " + result);
            if (TextUtils.isEmpty(result)) {
                return null;
            }
            Gson gson = new Gson();
            ResponseListModel data = gson.fromJson(result, ResponseListModel.class);
            return data.getContext();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("doParser", "doParser err = " + e.toString());
            return null;
        }
    }

    @Override
    public void doSave(List<HomeSameAge> data) {
        try {

        } catch (Exception e) {
            Log.e("doSave", "doSave err = " + e.toString());
        }
    }
}
