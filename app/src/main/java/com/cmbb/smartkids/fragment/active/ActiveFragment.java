package com.cmbb.smartkids.fragment.active;


import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ClickListeners.ExpandCollapseListener;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.ActiveExpandableAdapter;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.base.MFragment;
import com.cmbb.smartkids.fragment.homeattention.user.UserAttentionBaseModel;
import com.cmbb.smartkids.fragment.homeattention.user.UserAttentionModel;
import com.cmbb.smartkids.model.active.CustomParentObject;
import com.cmbb.smartkids.model.userinfo.ExpertServeBaseModel;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.rong.RongInfoContext;
import com.cmbb.smartkids.tools.log.Log;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;

/**
 * A simple {@link Fragment} subclass.
 */
public class ActiveFragment extends MFragment implements ExpandCollapseListener {

    RecyclerView mRecyclerView;
    private ActiveExpandableAdapter mActiveExpandableAdapter;

    ArrayList<ParentObject> parentObjectList = new ArrayList<>();

    LocalBroadcastManager mLocalBroadcastManager;

    public ActiveFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mLocalBroadcastManager = LocalBroadcastManager.getInstance(getActivity());
        mLocalBroadcastManager.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                initConversationList();
            }
        }, new IntentFilter("com.cmbb.smartkids.rong.message"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_active, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpParentData();
        mRecyclerView = (RecyclerView) view.findViewById(R.id.vertical_recyclerview_sample);
        mActiveExpandableAdapter = new ActiveExpandableAdapter(getActivity(), parentObjectList);
        mActiveExpandableAdapter.addExpandCollapseListener(ActiveFragment.this);
        mRecyclerView.setAdapter(mActiveExpandableAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        initAttentionData();
        initXiaoBianList();
    }

    @Override
    public void onResume() {
        super.onResume();
        initConversationList();
    }

    CustomParentObject customParentObject1;
    CustomParentObject customParentObject2;
    CustomParentObject customParentObject3;

    private void setUpParentData() {

        if (parentObjectList.size() > 0) parentObjectList.clear();
        //萌宝在线
        customParentObject1 = new CustomParentObject();
        //customParentObject1.setChildObjectList(mUserAttentionModels);
        customParentObject1.setParentText("萌宝在线");
        parentObjectList.add(customParentObject1);
        //我的联系人
        customParentObject2 = new CustomParentObject();
        //customParentObject2.setChildObjectList(mUserAttentionModels);
        customParentObject2.setParentText("我的联系人");
        parentObjectList.add(customParentObject2);
        //最近联系人
        customParentObject3 = new CustomParentObject();
        //customParentObject3.setChildObjectList(mUserAttentionModels);
        customParentObject3.setParentText("最近联系人");
        parentObjectList.add(customParentObject3);
    }


    @Override
    public void onRecyclerViewItemExpanded(int position) {

    }

    @Override
    public void onRecyclerViewItemCollapsed(int position) {

    }

    /**
     * 获取联系人列表
     */
    private void initAttentionData() {
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.User.FINDATTENTIONUSER_URL, body, "active_contact", new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                try {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                hideWaitDialog();

                            } catch (Exception e1) {

                            }
                        }
                    });
                } catch (Exception e1) {

                }

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String result = response.body().string();
                    Gson gson = new Gson();
                    UserAttentionBaseModel data = gson.fromJson(result, UserAttentionBaseModel.class);
                    final ArrayList<UserAttentionModel> mUserAttentionModels = data.getContext();
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            ((CustomParentObject) parentObjectList.get(1)).setChildObjectList(mUserAttentionModels);
                            mActiveExpandableAdapter.setData(parentObjectList);
                        }
                    });
                } catch (Exception e) {

                }
            }
        });
    }

    /**
     * 初始化回话列表
     */
    private void initConversationList() {
        int unReadAll = 0;
        List<Conversation> conversations = RongIM.getInstance().getRongIMClient().getConversationList(Conversation.ConversationType.PRIVATE);
        if (conversations == null) {
            return;
        }

        List<UserAttentionModel> itemsChild = new ArrayList<UserAttentionModel>();
        for (int i = 0; i < conversations.size(); i++) {
            // 判断userInfo是否存在
            if (null == RongInfoContext.getInstance().getUserNameByUserId(conversations.get(i).getTargetId())) {
                // 用户不存在万络获取
                ApiNetwork.getRongUserInfo(getActivity(), MApplication.token, conversations.get(i).getTargetId());
                continue;
            }
            UserAttentionModel childItem = new UserAttentionModel();
            childItem.setAttentionToken(conversations.get(i).getTargetId());
            childItem.setNike(RongInfoContext.getInstance().getUserNameByUserId(conversations.get(i).getTargetId()));
            childItem.setUserSmallHeadImg(RongInfoContext.getInstance().getUserInfoById(conversations.get(i).getTargetId()).getPortraitUri().toString());
            childItem.setUnRead(conversations.get(i).getUnreadMessageCount());
            unReadAll = unReadAll + conversations.get(i).getUnreadMessageCount();
            String time = getMessageDate(conversations.get(i).getReceivedTime());
            childItem.setTime(time);
            String s = new String(conversations.get(i).getLatestMessage().encode());
            if (TextUtils.isEmpty(s)) {
                childItem.setContentLast("");
            } else {
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    String message = jsonObject.getString("content");
                    childItem.setContentLast(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            itemsChild.add(childItem);
        }
        customParentObject3.setUnRead(unReadAll);
        ((CustomParentObject) parentObjectList.get(2)).setChildObjectList(itemsChild);
        ((CustomParentObject) parentObjectList.get(2)).setParentText("最近联系人");
        mActiveExpandableAdapter.setData(parentObjectList);

        // 发送给主页
        Intent in = new Intent();
        in.setAction("com.cmbb.smartkids.rong.message_count");
        in.putExtra("count", unReadAll);
        mLocalBroadcastManager.sendBroadcast(in);
    }

    /**
     * 获取小编信息
     */
    public void initXiaoBianList() {
        Map<String, String> body = new HashMap<String, String>();
        body.put("token", MApplication.token);
        OkHttp.asyncPost(Constants.Acitive.EXPERTFINDEXPERTCHAT_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {

            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        Log.i("MEIZU", " xiao bian = " + result);
                        Gson gson = new Gson();
                        ExpertServeBaseModel expertServeBaseModel = gson.fromJson(result, ExpertServeBaseModel.class);
                        final List<UserAttentionModel> itemsChild = new ArrayList<UserAttentionModel>();
                        for (int i = 0; i < expertServeBaseModel.getContext().getServeList().size(); i++) {
                            UserAttentionModel childItem = new UserAttentionModel();
                            childItem.setAttentionToken(expertServeBaseModel.getContext().getServeList().get(i).getRongyunServiceId());
                            childItem.setEredarName(expertServeBaseModel.getContext().getServeList().get(i).getAuthorityName());
                            childItem.setNike(expertServeBaseModel.getContext().getServeList().get(i).getRealName());
                            childItem.setUserSmallHeadImg(expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg());
                            childItem.setIsServer(true);
                            itemsChild.add(childItem);

                            ArrayList<UserInfo> userInfos = new ArrayList<UserInfo>();
                            if (expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg().contains("upload")) {
                                UserInfo info = new UserInfo(expertServeBaseModel.getContext().getServeList().get(i).getRongyunServiceId(), expertServeBaseModel.getContext().getServeList().get(i).getRealName(),
                                        expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL_OLD + expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg()));
                                userInfos.add(info);
                            } else {
                                UserInfo info = new UserInfo(expertServeBaseModel.getContext().getServeList().get(i).getRongyunServiceId(), expertServeBaseModel.getContext().getServeList().get(i).getRealName(),
                                        expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg() == null ? null : Uri.parse(Constants.BASE_IMAGE_URL + expertServeBaseModel.getContext().getServeList().get(i).getUserSmallHeadImg()));
                                userInfos.add(info);
                            }
                            RongInfoContext.getInstance().setUserInfos(userInfos);
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                ((CustomParentObject) parentObjectList.get(0)).setChildObjectList(itemsChild);
                                mActiveExpandableAdapter.setData(parentObjectList);

                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        });
    }

    /**
     * 获得消息的时间处理
     */
    private String getMessageDate(Long aLong) {
        String s;
        SimpleDateFormat sFormat;
        Date dalong = new Date(aLong);
        Date date = new Date();
        SimpleDateFormat Format = new SimpleDateFormat("dd");
        String s1 = Format.format(dalong);
        String s2 = Format.format(date);
        int ints1 = Integer.parseInt(s1) - 1;
        long dateLong = date.getTime();
        long a = dateLong - aLong;
        int tian = (int) a / 1000 / 60 / 60 / 24;
        switch (tian) {
            case 0:
                if (s2.equals(s1)) {
                    sFormat = new SimpleDateFormat("HH:mm:ss");
                    s = sFormat.format(dalong);
                    return s;
                }
            case 1:
                sFormat = new SimpleDateFormat("HH:mm:ss");
                s = "昨天 " + sFormat.format(dalong);
                if (s2.equals(ints1 + "")) {
                    return s;
                }
                break;
        }
        if (tian > 1) {
            sFormat = new SimpleDateFormat("yy:MM:dd HH:mm");
            Date date3 = new Date(aLong);
            s = sFormat.format(date3);
            return s;
        }

        return "";
    }
}
