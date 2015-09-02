package com.cmbb.smartkids.network.api;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.HomeActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.fragment.caselist.CaseDetailListModel;
import com.cmbb.smartkids.fragment.homeattention.user.UserAttentionBaseModel;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.fragment.replay.PostDetailBaseModel;
import com.cmbb.smartkids.model.photo.PhotoAdd;
import com.cmbb.smartkids.model.userinfo.LoginBaseModel;
import com.cmbb.smartkids.model.userinfo.RongInfoBaseModel;
import com.cmbb.smartkids.model.userinfo.UserInfoBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.rong.RongCloudEvent;
import com.cmbb.smartkids.rong.RongInfoContext;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.UserInfo;

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
                        Log.i("userinfo", "userinfo = " + result);
                        Gson gson = new Gson();
                        UserInfoBaseModel userInfoBaseModel = gson.fromJson(result, UserInfoBaseModel.class);
                        if ("1".equals(userInfoBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Home.USERINFO_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Home.USERINFO_DATA, userInfoBaseModel.getContext());
                            context.sendBroadcast(intent);

                            String rongToken = SPCache.getString(Constants.RongToken, "");
                            Log.i("MEIZU", "rongToken = " + rongToken);
                            if (!TextUtils.isEmpty(rongToken)) {
                                ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
                                if (userInfoBaseModel.getContext().getUserSmallHeadImg().contains("upload")) {

                                    UserInfo info = new UserInfo(rongToken, userInfoBaseModel.getContext().getNike(),
                                            userInfoBaseModel.getContext().getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL_OLD + userInfoBaseModel.getContext().getUserSmallHeadImg()));
                                    userInfos.add(info);
                                } else {
                                    UserInfo info = new UserInfo(rongToken, userInfoBaseModel.getContext().getNike(),
                                            userInfoBaseModel.getContext().getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL + userInfoBaseModel.getContext().getUserSmallHeadImg()));
                                    userInfos.add(info);
                                }
                                RongInfoContext.getInstance().setUserInfos(userInfos);
                            }
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
     * 获取回复顶部详情
     *
     * @param context
     */
    public static void getWonderReplayDetail(final Context context, PostModel mPostModel) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        body.put("userId", "" + mPostModel.getUserId());
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("getWonderReplayDetail", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "FindWonderfulDetails", body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.i("getReplayDetail", "getReplayDetail = " + result);
                try {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        PostDetailBaseModel postDetailBaseModel = gson.fromJson(result, PostDetailBaseModel.class);

                        if ("1".equals(postDetailBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Post.POSTDETAIL_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Post.POSTDETAIL_DATA, postDetailBaseModel.getContext());
                            context.sendBroadcast(intent);
                        }
                    } else {
                        sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                    }
                } catch (Exception e) {
                    Log.i("getReplayDetail", "e = " + e);
                    sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                }
            }
        });
    }

    /**
     * @param context
     * @param caseDetailListModel
     */
    public static void getCaseReplayDetail(final Context context, CaseDetailListModel caseDetailListModel) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", caseDetailListModel.getId() + "");
        body.put("userId", "" + caseDetailListModel.getUserId());
        OkHttp.asyncPost(Constants.Case.FINDBYCASEID_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.i("getReplayDetail", "getReplayDetail = " + result);
                try {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        PostDetailBaseModel postDetailBaseModel = gson.fromJson(result, PostDetailBaseModel.class);

                        if ("1".equals(postDetailBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Post.POSTDETAIL_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Post.POSTDETAIL_DATA, postDetailBaseModel.getContext());
                            context.sendBroadcast(intent);
                        }
                    } else {
                        sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                    }
                } catch (Exception e) {
                    Log.i("getReplayDetail", "e = " + e);
                    sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                }
            }
        });
    }


    /**
     * 获取回复顶部详情
     *
     * @param context
     */
    public static void getAgeCityReplayDetail(final Context context, PostModel mPostModel) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("id", mPostModel.getId() + "");
        body.put("areaType", mPostModel.getAreaType());
        body.put("type", mPostModel.getType());
        body.put("userId", "" + mPostModel.getUserId());
        OkHttp.asyncPost(Constants.BASE_URL + mPostModel.getPortConnector() + "FindStarDetails", body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
            }

            @Override
            public void onResponse(Response response) throws IOException {
                String result = response.body().string();
                Log.i("getReplayDetail", "getReplayDetail = " + result);
                try {
                    if (response.isSuccessful()) {
                        Gson gson = new Gson();
                        PostDetailBaseModel postDetailBaseModel = gson.fromJson(result, PostDetailBaseModel.class);

                        if ("1".equals(postDetailBaseModel.getCode())) {
                            Intent intent = new Intent(Constants.Post.POSTDETAIL_DATA_INTENT);
                            intent.putExtra(Constants.NETWORK_FLAG, true);
                            intent.putExtra(Constants.Post.POSTDETAIL_DATA, postDetailBaseModel.getContext());
                            context.sendBroadcast(intent);
                        }
                    } else {
                        sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                    }
                } catch (Exception e) {
                    Log.i("getReplayDetail", "e = " + e);
                    sendFailureBroadcast(context, Constants.Post.POSTDETAIL_DATA_INTENT);
                }
            }
        });
    }


    /**
     * 获取用户信息
     */
    public static void getUserInfoList() {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.User.FINDATTENTIONUSER_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String result = response.body().string();
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
                    UserAttentionBaseModel data = gson.fromJson(result, UserAttentionBaseModel.class);
                    ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
                    if (data.getContext() != null && data.getContext().size() > 0) {
                        for (int i = 0; i < data.getContext().size(); i++) {
                            if (data.getContext().get(i).getUserSmallHeadImg().contains("upload")) {
                                UserInfo info = new UserInfo(data.getContext().get(i).getAttentionToken(), data.getContext().get(i).getNike(),
                                        data.getContext().get(i).getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL_OLD + data.getContext().get(i).getUserSmallHeadImg()));
                                userInfos.add(info);
                            } else {
                                UserInfo info = new UserInfo(data.getContext().get(i).getAttentionToken(), data.getContext().get(i).getNike(),
                                        data.getContext().get(i).getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL + data.getContext().get(i).getUserSmallHeadImg()));
                                userInfos.add(info);
                            }
                        }
                        RongInfoContext.getInstance().setUserInfos(userInfos);
                    }
                }
            }
        });
    }


    public static void login(String token, final HomeActivity homeActivity) {
        Map<String, String> body = new HashMap<String, String>();
        body.put("token", token);
        OkHttp.asyncPost(Constants.User.LOGINS_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    Gson gson = new Gson();
                    String result = response.body().string();
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
                    LoginBaseModel loginBaseModel = gson.fromJson(result, LoginBaseModel.class);
                    SPCache.putString(Constants.RongToken, loginBaseModel.getContext().getRongyunToken());
                    MApplication.rongToken = loginBaseModel.getContext().getRongyunToken();
                    // 融云登陆
                    RongIM.connect(MApplication.rongToken, new RongIMClient.ConnectCallback() {
                                @Override
                                public void onTokenIncorrect() {
                                    Log.i("MEIZU", "---------onTokenIncorrect userId----------:");
                                }

                                @Override
                                public void onSuccess(String userId) {
                                    Log.i("MEIZU", "---------onSuccess userId----------:" + userId);
                                    homeActivity.btnActive.setClickable(true);
                                    homeActivity.btnAdd.setClickable(true);
                                    homeActivity.btnMaster.setClickable(true);
                                    homeActivity.btnTools.setClickable(true);
                                    // 获取用户信息
                                    RongCloudEvent.getInstance().setOtherListener();
                                }

                                @Override
                                public void onError(RongIMClient.ErrorCode e) {
                                    Log.i("MEIZU", "---------onError ----------:" + e);
                                }
                            }
                    );
                }
            }
        });
    }

    /**
     * 登陆融云服务器
     *
     * @param rongToken String
     */
    public static void loginRongYun(String rongToken) {
        // 融云登陆
        RongIM.connect(rongToken, new RongIMClient.ConnectCallback() {
                    @Override
                    public void onTokenIncorrect() {
                        Log.i("MEIZU", "---------onTokenIncorrect userId----------:");
                    }

                    @Override
                    public void onSuccess(String userId) {
                        Log.i("MEIZU", "---------onSuccess userId----------:" + userId);
                        // 获取用户信息
                        RongCloudEvent.getInstance().setOtherListener();
                    }

                    @Override
                    public void onError(RongIMClient.ErrorCode e) {
                        Log.i("MEIZU", "---------onError ----------:" + e);
                    }
                }
        );
    }


    /**
     * 请求融云用户信息
     *
     * @param token
     */
    public static void getRongUserInfo(final Context mContext, String token, final String userToken) {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("userToken", userToken);
        OkHttp.asyncPost(Constants.BASE_URL + "/user/findUserInfo", body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                if (response.isSuccessful()) {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    RongInfoBaseModel rongInfoBaseModel = gson.fromJson(result, RongInfoBaseModel.class);
                    ArrayList<UserInfo> friendResults = new ArrayList<UserInfo>();
                    if (rongInfoBaseModel.getContext().getUserSmallHeadImg().contains("upload")) {
                        UserInfo info = new UserInfo(userToken, rongInfoBaseModel.getContext().getNike(),
                                rongInfoBaseModel.getContext().getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL_OLD + rongInfoBaseModel.getContext().getUserSmallHeadImg()));
                        friendResults.add(info);
                    } else {
                        UserInfo info = new UserInfo(userToken, rongInfoBaseModel.getContext().getNike(), rongInfoBaseModel.getContext().getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL + rongInfoBaseModel.getContext().getUserSmallHeadImg()));
                        friendResults.add(info);
                    }
                    RongInfoContext.getInstance().setUserInfos(friendResults);
                }
            }
        });
    }


    /**
     * 意见反馈
     *
     * @param suggestion String
     */
    public static void sendSuggestion(String suggestion, Callback callback) {
        Map<String, String> params = new HashMap<>();
        params.put("token", MApplication.token);
        params.put("context", suggestion);
        OkHttp.asyncPost(Constants.User.ADDFEEDBACK_URL, params, callback);
    }

    /**
     * 板块添加关注
     *
     * @param plateId  String
     * @param callback Callback
     */
    public static void addAttentionPlate(String plateId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("code", "plate");
        body.put("plateId", plateId);
        OkHttp.asyncPost(Constants.ADDATTENTION_URL, body, callback);
    }

    /**
     * 板块取消关注
     *
     * @param plateId  String
     * @param callback Callback
     */
    public static void cancelAttentionPlate(String plateId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("code", "plate");
        body.put("plateId", plateId);
        OkHttp.asyncPost(Constants.DELETEATTENTION_URL, body, callback);
    }


    /**
     * 删除Wonder帖子
     *
     * @param type
     * @param connector
     * @param id
     * @param callback
     */
    public static void deleteWonderPost(String type, String connector, String plate, int id, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", type);
        body.put("id", "" + id);
        OkHttp.asyncPost(Constants.BASE_URL + connector + plate, body, callback);
    }

    /**
     * 删除AgeCity帖子
     *
     * @param type
     * @param connector
     * @param id
     * @param callback
     */
    public static void deleteAgeCityPost(String type, String connector, String plate, String areaType, int id, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("areaType", areaType);
        body.put("type", type);
        body.put("id", "" + id);
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("result", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        Log.i("result", "key = " + Constants.BASE_URL + connector + plate);

        OkHttp.asyncPost(Constants.BASE_URL + connector + plate, body, callback);
    }


    /**
     * 删除回复
     *
     * @param type
     * @param connector
     * @param areaType
     * @param id
     * @param callback
     */
    public static void deleteReplay(String type, String connector, String areaType, int id, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("areaType", areaType);
        body.put("type", type);
        body.put("replyId", "" + id);
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("deleteReplay", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.BASE_URL + connector + "DeleteReplys", body, callback);
    }


    /**
     * @param areaType
     * @param type
     * @param publishRelationId
     * @param replysRelationId
     * @param callback
     */
    public static void addReport(String areaType, String type, String publishRelationId, String replysRelationId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("areaType", areaType);
        body.put("type", type);
        body.put("publishRelationId", publishRelationId);
        if (!TextUtils.isEmpty(replysRelationId))
            body.put("replysRelationId", replysRelationId);
        OkHttp.asyncPost(Constants.ADDREPORT_URL, body, callback);
    }


    /**
     * 图片上传
     *
     * @param mPlateModel
     * @param mTitle
     * @param mContext
     * @param photoAdds
     * @param callback
     */
    public static void uploadWonderPost(PlateModel mPlateModel, String mTitle, String mContext, ArrayList<PhotoAdd> photoAdds, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", mPlateModel.getType());
        body.put("title", mTitle);
        body.put("context", mContext);
        body.put("count", photoAdds.size() + "");
        body.put("plateId", mPlateModel.getId() + "");
        for (int i = 0; i < photoAdds.size(); i++) {
            body.put("context" + i, photoAdds.get(i).getPhotoContent());
        }
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("photo", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.BASE_URL + mPlateModel.getConnector() + "AddPublish", body, photoAdds, callback);

    }

    /**
     * @param mPlateModel
     * @param mTitle
     * @param mContext
     * @param photoAdds
     * @param callback
     */
    public static void uploadAgeCityPost(PlateModel mPlateModel, String mTitle, String mContext, String areaType, ArrayList<PhotoAdd> photoAdds, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", mPlateModel.getType());
        body.put("areaType", areaType);
        body.put("title", mTitle);
        body.put("context", mContext);
        body.put("count", photoAdds.size() + "");
        body.put("plateId", mPlateModel.getId() + "");
        for (int i = 0; i < photoAdds.size(); i++) {
            body.put("context" + i, photoAdds.get(i).getPhotoContent());
        }
        for (Map.Entry<String, String> entry : body.entrySet()) {
            Log.i("photo", "key = " + entry.getKey() + " value = " + entry.getValue());
        }
        OkHttp.asyncPost(Constants.BASE_URL + mPlateModel.getConnector() + "AddStar", body, photoAdds, callback);

    }

    /**
     * 添加点赞
     *
     * @param token
     * @param relationId
     * @param areaType
     * @param type
     * @param port
     * @param callback
     */
    public static void addSpot(String token, String relationId, String areaType, String type, String port, Callback callback) {
        if (TextUtils.isEmpty(token)) {
            Log.e("NETWORK", "token == null");
            return;
        }
        if (TextUtils.isEmpty(relationId)) {
            Log.e("NETWORK", "relationId == null");
            return;
        }
        if (TextUtils.isEmpty(areaType)) {
            Log.e("NETWORK", "areaType == null");
            return;
        }
        if (TextUtils.isEmpty(type)) {
            Log.e("NETWORK", "type == null");
            return;
        }
        if (TextUtils.isEmpty(port)) {
            Log.e("NETWORK", "port == null");
            return;
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("relationId", relationId);
        body.put("areaType", areaType);
        body.put("type", type);
        OkHttp.asyncPost(Constants.BASE_URL + port + "AddSpot", body, callback);
    }

    /**
     * 取消点赞
     *
     * @param token
     * @param relationId
     * @param areaType
     * @param type
     * @param port
     * @param callback
     */
    public static void cancelSpot(String token, String relationId, String areaType, String type, String port, Callback callback) {
        if (TextUtils.isEmpty(token)) {
            Log.e("NETWORK", "token == null");
            return;
        }
        if (TextUtils.isEmpty(relationId)) {
            Log.e("NETWORK", "relationId == null");
            return;
        }
        if (TextUtils.isEmpty(areaType)) {
            Log.e("NETWORK", "areaType == null");
            return;
        }
        if (TextUtils.isEmpty(type)) {
            Log.e("NETWORK", "type == null");
            return;
        }
        if (TextUtils.isEmpty(port)) {
            Log.e("NETWORK", "port == null");
            return;
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("relationId", relationId);
        body.put("areaType", areaType);
        body.put("type", type);
        OkHttp.asyncPost(Constants.BASE_URL + port + "DeleteSpot", body, callback);
    }


    /**
     * @param token
     * @param postId
     * @param postType
     * @param callback
     */
    public static void cancelCollection(String token, String postId, String postType, Callback callback) {
        if (TextUtils.isEmpty(token)) {
            Log.i("NETWORK", "token == null");
            return;
        }
        if (TextUtils.isEmpty(postId)) {
            Log.i("NETWORK", "postId == null");
            return;
        }
        if (TextUtils.isEmpty(postType)) {
            Log.i("NETWORK", "postType == null");
            return;
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("postId", postId);
        body.put("postType", postType);
        OkHttp.asyncPost(Constants.DELETESTOREUP_URL, body, callback);
    }


    /**
     * 添加收藏
     *
     * @param token
     * @param postId
     * @param postAreaType
     * @param postType
     * @param callback
     */
    public static void addCollection(String token, String postId, String postAreaType, String postType, Callback callback) {
        if (TextUtils.isEmpty(token)) {
            Log.i("NETWORK", "token == null");
            return;
        }
        if (TextUtils.isEmpty(postId)) {
            Log.i("NETWORK", "postId == null");
            return;
        }
        if (TextUtils.isEmpty(postAreaType)) {
            Log.i("NETWORK", "postAreaType == null");
            return;
        }
        if (TextUtils.isEmpty(postType)) {
            Log.i("NETWORK", "postType == null");
            return;
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("postId", postId);
        body.put("postAreaType", postAreaType);
        body.put("postType", postType);
        OkHttp.asyncPost(Constants.ADDSTOREUP_URL, body, callback);
    }

    // 添加举报
    public static void addReport(String token, String areaType, String type, String publishRelationId, String replysRelationId, Callback callback) {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        body.put("areaType", areaType);
        body.put("type", type);
        body.put("publishRelationId", publishRelationId);
        if (!TextUtils.isEmpty(replysRelationId))
            body.put("replysRelationId", replysRelationId);
        OkHttp.asyncPost(Constants.ADDREPORT_URL, body, callback);
    }


    /**
     * 取消请求
     *
     * @param no
     */
    public static void cancleNetwork(String[] no) {
        String[] ccs = new String[]{"home_list_provider", "eredar_type", "eredar_right", "doctor_type", "doctor_right", "active_message", "active_contact"};

        for (int i = 0; i < ccs.length; i++) {
            for (int j = 0; j < no.length; j++) {
                if (!ccs[i].equals(no[j]))
                    OkHttp.mOkHttpClient.cancel(ccs[i]);
            }
        }
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
