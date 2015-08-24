package com.cmbb.smartkids.rong;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

import java.util.Locale;

import io.rong.imkit.RongIM;
import io.rong.imkit.common.RongConst;
import io.rong.imkit.fragment.ConversationFragment;
import io.rong.imkit.fragment.ConversationListFragment;
import io.rong.imkit.fragment.SubConversationListFragment;
import io.rong.imkit.fragment.UriFragment;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.Discussion;

/**
 * B项目名称：SmartKids
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/6/1 15:56
 * 修改人：N.Sun
 * 修改时间：2015/6/1 15:56
 * 修改备注：
 */
public class RongActivity extends MActivity implements Handler.Callback {

    private static final String TAG = RongActivity.class.getSimpleName();

    @Override
    public int getLayoutId() {
        return R.layout.activity_rong_chat;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
    }


    /**
     * 对方id
     */
    private String targetId;
    /**
     * 刚刚创建完讨论组后获得讨论组的targetIds
     */
    private String targetIds;
    /**
     * 讨论组id
     */
    private String mDiscussionId;
    /**
     * 会话类型
     */
    private Conversation.ConversationType mConversationType;
    private Handler mHandler;

    protected void initView() {
        // 设置聊天昵称

        mHandler = new Handler(this);
        Intent intent = getIntent();
        /*try {
            TextView mTitle = (TextView) getToolbar().findViewById(R.id.toolbar_title);
            mTitle.setText(intent.getData().getQueryParameter("title"));
            getSupportActionBar().setDisplayShowTitleEnabled(false);
        } catch (NullPointerException e) {

        }*/


        //push或通知过来
        if (intent != null && intent.getData() != null && intent.getData().getScheme().equals("rong") && intent.getData().getQueryParameter("push") != null) {
            //通过intent.getData().getQueryParameter("push") 为true，判断是否是push消息
            if (RongInfoContext.getInstance() != null && intent.getData().getQueryParameter("push").equals("true")) {
                Log.e(TAG, "0518---test-push --" + intent.getData());
                if (RongInfoContext.getInstance() != null) {
                    String token = RongInfoContext.getInstance().getSharedPreferences().getString("rongyunToken", "defult");
                    reconnect(token);
                }
            } else {
                enterFragment(intent);
            }
        } else if (intent != null) {
            //程序切到后台，收到消息后点击进入,会执行这里
            Log.i("MEIZU", "intent != null");
            Log.e(TAG, "0518---test-activity--" + intent.getData());
            enterFragment(intent);
        }
    }

    /**
     * 收到push消息后做重连，重新连接融云
     *
     * @param token
     */
    private void reconnect(String token) {

        showWaitDialog();

        try {
            Log.e(TAG, "----reconnect----try--111111:");
            RongIM.connect(token, new RongIMClient.ConnectCallback() {

                @Override
                public void onTokenIncorrect() {
                    Log.e(TAG, "----token－－－－－onTokenIncorrect------:");
                }

                @Override
                public void onSuccess(String userId) {
                    Log.e(TAG, "----token－－－－－onSuccess------:");
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            Intent intent = getIntent();
                            if (intent != null) {
                                enterFragment(intent);
                            }
                        }
                    });
                }

                @Override
                public void onError(RongIMClient.ErrorCode e) {
                    Log.e(TAG, "----token－－－－－onError------:");
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                        }
                    });
                }
            });
            Log.e(TAG, "----reconnect----try--222222:");
        } catch (Exception e) {
            Log.e(TAG, "----reconnect----catch--:");
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    hideWaitDialog();
                }
            });
            e.printStackTrace();
        }

    }

    /**
     * 消息分发，选择跳转到哪个fragment
     *
     * @param intent
     */
    private void enterFragment(Intent intent) {
        try {
            getSupportActionBar().setTitle(RongInfoContext.getInstance().getUserNameByUserId(targetId));

        } catch (Exception e) {

        }
        String tag = null;
        if (intent != null) {
            Fragment fragment = null;

            if (intent.getExtras() != null && intent.getExtras().containsKey(RongConst.EXTRA.CONTENT)) {
                String fragmentName = intent.getExtras().getString(RongConst.EXTRA.CONTENT);
                fragment = Fragment.instantiate(this, fragmentName);
            } else if (intent.getData() != null) {
                if (intent.getData().getPathSegments().get(0).equals("conversation")) {
                    tag = "conversation";
                    if (intent.getData().getLastPathSegment().equals("system")) {
                        //注释掉的代码为不加输入框的聊天页面（此处作为示例）
                        //                        String fragmentName = MessageListFragment.class.getCanonicalName();
                        //                        fragment = Fragment.instantiate(this, fragmentName);
                        /*startActivity(new Intent(RongMainActivity.this, DeNewFriendListActivity.class));
                        finish();
                        List<Conversation> conversations = RongIM.getInstance().getRongClient().getConversationList(Conversation.ConversationType.SYSTEM);
                        for (int i = 0; i < conversations.size(); i++) {
                            RongIM.getInstance().getRongClient().clearMessagesUnreadStatus(Conversation.ConversationType.SYSTEM, conversations.get(i).getSenderUserId());
                        }*/
                    } else {
                        String fragmentName = ConversationFragment.class.getCanonicalName();
                        fragment = Fragment.instantiate(this, fragmentName);
                    }
                } else if (intent.getData().getLastPathSegment().equals("conversationlist")) {
                    tag = "conversationlist ";
                    String fragmentName = ConversationListFragment.class.getCanonicalName();
                    fragment = Fragment.instantiate(this, fragmentName);
                } else if (intent.getData().getLastPathSegment().equals("subconversationlist")) {
                    tag = "subconversationlist";
                    String fragmentName = SubConversationListFragment.class.getCanonicalName();
                    fragment = Fragment.instantiate(this, fragmentName);
                } else if (intent.getData().getPathSegments().get(0).equals("friend")) {
                    /*tag = "friend";
                    String fragmentName = DeFriendMultiChoiceFragment.class.getCanonicalName();
                    fragment = Fragment.instantiate(this, fragmentName);
                    ActionBar actionBar = getSupportActionBar();
                    actionBar.hide();//隐藏ActionBar*/
                }
                targetId = intent.getData().getQueryParameter("targetId");
                targetIds = intent.getData().getQueryParameter("targetIds");
                mDiscussionId = intent.getData().getQueryParameter("discussionId");
                if (targetId != null) {
                    mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
                } else if (targetIds != null)
                    mConversationType = Conversation.ConversationType.valueOf(intent.getData().getLastPathSegment().toUpperCase(Locale.getDefault()));
            }

            Log.i("MEIZU", "meizu tag = " + tag);
            if (fragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.add(R.id.de_content, fragment, tag);
                transaction.addToBackStack(null).commitAllowingStateLoss();
            }
        }
    }

    protected void initData() {
        if (mConversationType != null) {
            if (mConversationType.toString().equals("PRIVATE")) {
                Log.i("MEIZU", "meizu tag = " + "private");
                if (RongInfoContext.getInstance() != null) {

                }
                getSupportActionBar().setTitle(RongInfoContext.getInstance().getUserNameByUserId(targetId));
            } else if (mConversationType.toString().equals("GROUP")) {
                if (RongInfoContext.getInstance() != null) {
                    getSupportActionBar().setTitle(RongInfoContext.getInstance().getUserNameByUserId(targetId));
                }
            } else if (mConversationType.toString().equals("DISCUSSION")) {
                if (targetId != null) {
                    RongIM.getInstance().getRongIMClient().getDiscussion(targetId, new RongIMClient.ResultCallback<Discussion>() {
                        @Override
                        public void onSuccess(Discussion discussion) {
                            getSupportActionBar().setTitle(discussion.getName());
                        }

                        @Override
                        public void onError(RongIMClient.ErrorCode e) {
                            if (e.equals(RongIMClient.ErrorCode.NOT_IN_DISCUSSION)) {
                                getSupportActionBar().setTitle("不在讨论组中");
                                supportInvalidateOptionsMenu();
                            }

                        }
                    });
                } else if (targetIds != null) {
                    setDiscussionName(targetIds);
                } else {
                    //getSupportActionBar().setTitle("讨论组");
                }
            } else if (mConversationType.toString().equals("SYSTEM")) {
                //getSupportActionBar().setTitle("系统会话类型");
            } else if (mConversationType.toString().equals("CHATROOM")) {
                //getSupportActionBar().setTitle("聊天室");
            } else if (mConversationType.toString().equals("CUSTOMER_SERVICE")) {
                //getSupportActionBar().setTitle("客服");
                getSupportActionBar().setTitle(RongInfoContext.getInstance().getUserNameByUserId(targetId));
            }

        }

    }

    /**
     * set discussion name
     *
     * @param targetIds
     */
    private void setDiscussionName(String targetIds) {
        StringBuilder sb = new StringBuilder();
        //getSupportActionBar().setTitle(targetIds);
        String[] ids = targetIds.split(",");
        if (RongInfoContext.getInstance() != null) {
            for (int i = 0; i < ids.length; i++) {
                RongInfoContext.getInstance().getUserNameByUserId(ids[i]);
                sb.append(RongInfoContext.getInstance().getUserNameByUserId(ids[i]));
                sb.append(",");
            }
            sb.append(RongInfoContext.getInstance().getSharedPreferences().getString("DEMO_USER_NAME", "0.0"));
        }

        //getSupportActionBar().setTitle(sb);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        setIntent(intent);

        String tag = null;
        Fragment fragment = null;

        if (intent.getExtras() != null && intent.getExtras().containsKey(RongConst.EXTRA.CONTENT)) {
            String fragmentName = intent.getExtras().getString(RongConst.EXTRA.CONTENT);
            fragment = Fragment.instantiate(this, fragmentName);
        } else if (intent.getData() != null) {

            if (intent.getData().getPathSegments().get(0).equals("conversation")) {
                tag = "conversation";
                fragment = getSupportFragmentManager().findFragmentByTag(tag);
                if (fragment != null)
                    return;
                String fragmentName = ConversationFragment.class.getCanonicalName();
                fragment = Fragment.instantiate(this, fragmentName);
            } else if (intent.getData().getLastPathSegment().equals("conversationlist")) {
                tag = "conversationlist";
                String fragmentName = ConversationListFragment.class.getCanonicalName();
                fragment = Fragment.instantiate(this, fragmentName);
            } else if (intent.getData().getLastPathSegment().equals("subconversationlist")) {
                tag = "subconversationlist";
                String fragmentName = SubConversationListFragment.class.getCanonicalName();
                fragment = Fragment.instantiate(this, fragmentName);

            }
        }

        if (fragment != null) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.de_content, fragment, tag);
            transaction.addToBackStack(null).commitAllowingStateLoss();
        }
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            super.onBackPressed();
            this.finish();
        } else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        //inflater.inflate(R.menu.de_conversation_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.icon:
                if (mConversationType == null) {
                    return false;
                }
                if (mConversationType == Conversation.ConversationType.PUBLIC_SERVICE
                        || mConversationType == Conversation.ConversationType.APP_PUBLIC_SERVICE) {
                    RongIM.getInstance().startPublicServiceProfile(this, mConversationType, targetId);
                } else {
                    //通过targetId 和 会话类型 打开指定的设置页面
                    if (!TextUtils.isEmpty(targetId)) {
                        Uri uri = Uri.parse("demo://" + getApplicationInfo().packageName).buildUpon().appendPath("conversationSetting")
                                .appendPath(mConversationType.getName()).appendQueryParameter("targetId", targetId).build();
                        Intent intent = new Intent(Intent.ACTION_VIEW);
                        intent.setData(uri);
                        startActivity(intent);
                        //当你刚刚创建完讨论组以后获得的是 targetIds
                    } else if (!TextUtils.isEmpty(targetIds)) {
                        UriFragment fragment = (UriFragment) getSupportFragmentManager().getFragments().get(0);
                        fragment.getUri();
                        //得到讨论组的 targetId
                        targetId = fragment.getUri().getQueryParameter("targetId");

                        if (!TextUtils.isEmpty(targetId)) {
                            Uri uri = Uri.parse("demo://" + getApplicationInfo().packageName).buildUpon().appendPath("conversationSetting")
                                    .appendPath(mConversationType.getName()).appendQueryParameter("targetId", targetId).build();
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(uri);
                            startActivity(intent);
                        } else {
                            //WinToast.toast(DemoActivity.this, "讨论组尚未创建成功");
                        }
                    }
                }
                break;

            case android.R.id.home:
                finish();

                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean handleMessage(Message msg) {
        return false;
    }

}
