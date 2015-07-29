package com.cmbb.smartkids.activity;

import android.accounts.AccountManager;
import android.accounts.AccountManagerCallback;
import android.accounts.AccountManagerFuture;
import android.accounts.AuthenticatorException;
import android.accounts.OperationCanceledException;
import android.content.ContentProviderOperation;
import android.content.ContentProviderResult;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.log.Log;
import com.cmbb.smartkids.tools.sp.SPCache;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by N.Sun
 */
public class SplashActivity extends MActivity {

    private final static String TAG = SplashActivity.class.getSimpleName();
    private String token;

    @Override
    public int getLayoutId() {
        return R.layout.activity_splash;
    }

    private void initData() {
        token = SPCache.getString(Constants.SharePreference.USER_TOKEN, null);
        if(TextUtils.isEmpty(token)){
            initTask();
        }else{
//            initUserCenterData();
        }
    }

    @Override
    protected void init(Bundle savedInstanceState) {

    }

    /*private void initUserCenterData() {
        Map<String, String> body = new HashMap<>();
        body.put("token", token);
        OkHttp.asyncPost(Constants.User.FINDUSERBASIC_URL, body, TAG, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                if(response.isSuccessful()){
                    if ("1".equals(response.body().string())) {
                        ArrayList<ContentProviderOperation> batch = new ArrayList<ContentProviderOperation>();
                        if (getContentResolver().query(SmartKidContract.UserBasic.TABLE_CONTENT_URI, null, null, null, null).getCount() > 0) {
                            batch.add(ContentProviderOperation.newDelete(SmartKidContract.UserBasic.TABLE_CONTENT_URI).withSelection(" 1 = 1", null).build());
                        }

                        batch.add(ContentProviderOperation.newInsert(SmartKidContract.UserBasic.TABLE_CONTENT_URI)
                                .withValue(SmartKidContract.UserBasic.COLUMN_ATTENTIONCOUNT, data.getContext().getAttentionCount())
                                .withValue(SmartKidContract.UserBasic.COLUMN_AUTHORITY, data.getContext().getAuthority())
                                .withValue(SmartKidContract.UserBasic.COLUMN_BIGIMGHEIGHT, data.getContext().getBigImgHeight())
                                .withValue(SmartKidContract.UserBasic.COLUMN_BIGIMGWIDTH, data.getContext().getBigImgWidth())
                                .withValue(SmartKidContract.UserBasic.COLUMN_EREDAR, data.getContext().getEredar())
                                .withValue(SmartKidContract.UserBasic.COLUMN_EREDARNAME, data.getContext().getEredarName())
                                .withValue(SmartKidContract.UserBasic.COLUMN_EREDARRANK, data.getContext().getEredarRank())
                                .withValue(SmartKidContract.UserBasic.COLUMN_EREDARTYPE, data.getContext().getEredarType())
                                .withValue(SmartKidContract.UserBasic.COLUMN_FLAG, data.getContext().getFlag())
                                .withValue(SmartKidContract.UserBasic.COLUMN_GOID, data.getContext().getGold())
                                .withValue(SmartKidContract.UserBasic.COLUMN_LOGINTIMES, data.getContext().getLoginTimes())
                                .withValue(SmartKidContract.UserBasic.COLUMN_NIKE, data.getContext().getNike())
                                .withValue(SmartKidContract.UserBasic.COLUMN_PUBLISHCOUNT, data.getContext().getPublishCount())
                                .withValue(SmartKidContract.UserBasic.COLUMN_SMALLIMGHEIGHT, data.getContext().getSmallImgHeight())
                                .withValue(SmartKidContract.UserBasic.COLUMN_SMALLIMGWIDTH, data.getContext().getSmallImgWidth())
                                .withValue(SmartKidContract.UserBasic.COLUMN_STORECOUNT, data.getContext().getStoreCount())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERBIGHEADIMG, data.getContext().getUserBigHeadImg())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERID, data.getContext().getUserId())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERPHONE, data.getContext().getUserPhone())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERSMALLHEADIMG, data.getContext().getUserSmallHeadImg())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERSTATUS, data.getContext().getUserStatus())
                                .withValue(SmartKidContract.UserBasic.COLUMN_USERWEIXINHEADIMG, data.getContext().getUserWeiXinHeadImg())
                                .build());

                        try {
                            ContentProviderResult[] results = getContentResolver().applyBatch(SmartKidContract.UserBasic.AUTHORITY, batch);
                            Log.i(TAG, "ContentProviderResult[] = " + results.length);
                        } catch (RemoteException e) {
                            e.printStackTrace();
                        } catch (OperationApplicationException e) {
                            e.printStackTrace();
                        }

                    }

                }else{

                }

            }
        });

    }*/

   /* private void initToken() {
        if (TextUtils.isEmpty(Application.token)) {
            AccountManager accountManager = AccountManager.get(this);
            if (accountManager.getAccountsByType(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE).length > 0) {
                accountManager.getAuthTokenByFeatures(Constants.Auth.SMARTKIDS_ACCOUNT_TYPE, Constants.Auth.AUTHTOKEN_TYPE, new String[0], this, null, null,
                        new AccountManagerCallback<Bundle>() {
                            @Override
                            public void run(AccountManagerFuture<Bundle> future) {
                                try {
                                    String token = future.getResult().getString(AccountManager.KEY_AUTHTOKEN);
                                    if (!TextUtils.isEmpty(token)) {
                                        Application.token = token;
                                        onTokeReceived(Application.token);
                                    }
                                } catch (OperationCanceledException e) {
                                    e.printStackTrace();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                } catch (AuthenticatorException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, null);
            }
        } else {
            initData();
        }

    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    private void initTask() {

        final Intent intentGuide = new Intent(this, GuideActivity.class);
        final Intent intentHome = new Intent(this, HomeActivity.class);
        // 引导界面
        final boolean isFirstInto = SPCache.getBoolean(Constants.SharePreference.IS_FIRST_INTO, true);
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                if (isFirstInto) {
                    startActivity(intentGuide);
                    finish();
                } else {
                    startActivity(intentHome);
                    finish();
                }
            }
        };
        timer.schedule(timerTask, 1500);
    }
}
