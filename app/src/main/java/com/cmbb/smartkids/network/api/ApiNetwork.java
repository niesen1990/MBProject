package com.cmbb.smartkids.network.api;

import android.content.Context;
import android.content.Intent;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.model.userinfo.UserInfoBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/30 上午9:45
 */
public class ApiNetwork {

    /**
     * 获取用户信息
     *
     * @param context
     */
    public static void getUserInfo(final Context context) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.User.FINDUSERBASIC_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailureBroadcast(context, Constants.Home.USERINFO_DATA_INTENT);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        UserInfoBaseModel userInfoBaseModel = gson.fromJson(result, UserInfoBaseModel.class);
                        if ("1".equals(userInfoBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Home.USERINFO_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Home.USERINFO_DATA, userInfoBaseModel.getContext());
                            context.sendBroadcast(intent);
                        }
                    }
                } catch (Exception e) {
                    Log.i("userinfo", "e");
                    sendFailureBroadcast(context, Constants.Home.USERINFO_DATA_INTENT);
                }
            }
        });
    }

    /**
     * 修改用户信息
     *
     * @param context
     */
    public static void editUserInfo(final Context context, Map<String, String> body, File file) {
        OkHttp.asyncPost(Constants.User.UPDATEUSERBASIC_URL, body, file, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailureBroadcast(context, Constants.Home.USERINFO_DATA_INTENT);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Gson gson = new Gson();
                        UserInfoBaseModel userInfoBaseModel = gson.fromJson(result, UserInfoBaseModel.class);
                        if ("1".equals(userInfoBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Home.USERINFO_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Home.USERINFO_DATA, userInfoBaseModel.getContext());
                            context.sendBroadcast(intent);
                        }
                    }
                } catch (Exception e) {
                    Log.i("userinfo", "e");
                    sendFailureBroadcast(context, Constants.Home.USERINFO_DATA_INTENT);
                }
            }
        });
    }


    /*
        * /attention/addAttention
        * 添加关注
        * */
    public static void addUserAttention(String token, String userId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("code", "user");
        body.put("userId", userId);
        OkHttp.asyncPost(Constants.ADDATTENTION_URL, body, callback);
    }

    /*
        * /attention/addAttention
        * 取消关注
        * */
    public static void CancelUserAttention(String token, String userId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("code", "user");
        body.put("userId", userId);
        OkHttp.asyncPost(Constants.DELETEATTENTION_URL, body, callback);
    }

    /**
     * 发送网络错误
     *
     * @param context Context
     */
    public static void sendFailureBroadcast(Context context, String intentflag) {
        Intent intent = new Intent(intentflag);
        intent.putExtra(Constants.NETWORK_FLAG, false);
        intent.putExtra(Constants.NETWORK_FAILURE, context.getResources().getString(R.string.service_error));
        context.sendBroadcast(intent);
    }


}
