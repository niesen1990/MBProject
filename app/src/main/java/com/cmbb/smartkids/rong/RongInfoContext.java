package com.cmbb.smartkids.rong;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.text.TextUtils;

import java.util.ArrayList;

import io.rong.imlib.model.UserInfo;


public class RongInfoContext {

    private static RongInfoContext mDemoContext;
    public Context mContext;
    private SharedPreferences mPreferences;

    private static ArrayList<UserInfo> mUserInfos = new ArrayList<>();


    public static RongInfoContext getInstance() {

        if (mDemoContext == null) {
            mDemoContext = new RongInfoContext();
        }
        return mDemoContext;
    }

    private RongInfoContext() {
    }

    private RongInfoContext(Context context) {
        mContext = context;
        mDemoContext = this;
        //http初始化 用于登录、注册使用
        mPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void init(Context context) {
        mDemoContext = new RongInfoContext(context);
    }

    public SharedPreferences getSharedPreferences() {
        return mPreferences;
    }

    public static ArrayList<UserInfo> getUserInfos() {
        return mUserInfos;
    }

    public static void setUserInfos(ArrayList<UserInfo> userInfos) {

        if (userInfos == null || userInfos.size() <= 0) {
            return;
        } else {
            RongInfoContext.mUserInfos.addAll(userInfos);
        }
    }

    /**
     * 通过userid 查找 UserInfo，查找的是本地的数据库
     *
     * @param userId
     * @return
     */
    public UserInfo getUserInfoById(String userId) {

        if (userId == null)
            return null;

        UserInfo userInfoReturn = null;
        if (!TextUtils.isEmpty(userId) && mUserInfos != null) {
            for (UserInfo userInfo : mUserInfos) {
                if (userId.equals(userInfo.getUserId())) {
                    userInfoReturn = userInfo;
                    break;
                }
            }
        }
        return userInfoReturn;
    }

    /**
     * 通过userid 获得username
     *
     * @param userId
     * @return
     */
    public String getUserNameByUserId(String userId) {
        UserInfo userInfoReturn = null;
        if (!TextUtils.isEmpty(userId) && mUserInfos != null) {
            for (UserInfo userInfo : mUserInfos) {

                if (userId.equals(userInfo.getUserId())) {
                    userInfoReturn = userInfo;
                    break;
                }
            }
        }
        // 通过网络获取
        if (null == userInfoReturn) {
            return null;
        }
        return userInfoReturn.getName();
    }


}
