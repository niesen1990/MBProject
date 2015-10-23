package com.cmbb.smartkids.activity.user;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.model.userinfo.UserInfoDetailModel;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.tools.log.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UserInfoActivity extends MActivity {

    private RelativeLayout rlRoot;
    private CardView cardview;
    private RelativeLayout rlHeadBac;
    private ImageView civHead;
    private TextView tvNick;
    private TextView tvRank;
    private TextView tvGold;
    private EditText etNick;
    private Spinner spinnerStatus;


    private UserInfoDetailModel userInfoDetailModel;

    private int status;
    private static final String[] mStatus = {"备孕中", "怀孕中", "已生育"};

    // Userinfo Receiver
    BroadcastReceiver userInfoReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            hideWaitDialog();
            if (intent.getBooleanExtra(Constants.NETWORK_FLAG, false)) {
                if (intent.getParcelableExtra(Constants.Home.USERINFO_DATA) instanceof Parcelable) {
                    userInfoDetailModel = intent.getParcelableExtra(Constants.Home.USERINFO_DATA);
                    applyData(userInfoDetailModel);
                } else {
                    showToast(intent.getStringExtra(Constants.Home.USERINFO_DATA));

                }
            } else {
                showToast(intent.getStringExtra(Constants.NETWORK_FAILURE));
            }
        }
    };

    private void assignViews() {
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        rlHeadBac = (RelativeLayout) findViewById(R.id.rl_head_bac);
        civHead = (ImageView) findViewById(R.id.civ_head);
        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(UserInfoActivity.this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        tvNick = (TextView) findViewById(R.id.tv_nick);
        tvRank = (TextView) findViewById(R.id.tv_rank);
        tvRank.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, UserLVActivity.class);
                startActivity(intent);
            }
        });
        tvGold = (TextView) findViewById(R.id.tv_gold);
        tvGold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserInfoActivity.this, UserGlodActivity.class);
                startActivity(intent);
            }
        });
        etNick = (EditText) findViewById(R.id.et_nick);
        cardview = (CardView) findViewById(R.id.cardview);

        spinnerStatus = (Spinner) findViewById(R.id.spinner_status);
        ArrayAdapter<String> ad = new ArrayAdapter<String>(this, R.layout.layout_text_one, mStatus);
        ad.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerStatus.setAdapter(ad);
        spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                status = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_user_info;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
        initData();
    }

    private void initData() {
        userInfoDetailModel = getIntent().getParcelableExtra("user");
        if (null == userInfoDetailModel) {
            showWaitDialog();
            ApiNetwork.getUserInfo(this);
        } else {
            applyData(userInfoDetailModel);
        }
    }

    /**
     * 数据显示
     */
    private void applyData(UserInfoDetailModel userInfoDetailModel) {
        tvNick.setText(userInfoDetailModel.getNike());
        spinnerStatus.setSelection(userInfoDetailModel.getUserStatus() - 1, true);
        tvGold.setText(userInfoDetailModel.getGold() + "");
        etNick.setText(userInfoDetailModel.getNike());
        PicassoTool.loadImage(this, userInfoDetailModel.getUserSmallHeadImg(), civHead, true);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(userInfoReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter2 = new IntentFilter(Constants.Home.USERINFO_DATA_INTENT);
        registerReceiver(userInfoReceiver, intentFilter2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_user_info, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_submit) {
            showWaitDialog();
            Map<String, String> body = new HashMap<>();
            body.put("token", MApplication.token);
            if (!etNick.getText().toString().equals(userInfoDetailModel.getNike())) {
                if (etNick.getText().toString().getBytes().length > 21) {
                    hideWaitDialog();
                    showToast("昵称设置过长");
                    return true;
                }
                body.put("nike", etNick.getText().toString());
            }
            Log.i("userinfo", "userinfo1 = ");
            body.put("userStatus", status + "");
            File file = null;
            if (imgs.size() > 0) {
                file = new File(imgs.get(0));
            }
            ApiNetwork.editUserInfo(this, body, file);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public final static int REQUEST_CODE = 1;
    private ArrayList<String> imgs = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
            if (data != null) {
                imgs = data.getStringArrayListExtra(PhotoPickerActivity.KEY_SELECTED_PHOTOS);
                Log.i("image", "image = " + imgs.get(0));
                if (imgs.size() == 0) return;
                PicassoTool.loadImage(this, imgs.get(0), civHead, true);
            }
        }
    }
}
