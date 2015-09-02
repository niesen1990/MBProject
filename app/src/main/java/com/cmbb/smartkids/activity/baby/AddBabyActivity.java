package com.cmbb.smartkids.activity.baby;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.tools.log.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class AddBabyActivity extends MActivity {

    private RelativeLayout rlRoot;
    private RelativeLayout rlHeadBac;
    private ImageView civHead;
    private CardView cardview;
    private EditText etNick;
    private TextView tvBirthday;
    private Spinner spinnerGender;
    int gender;
    String birthday;
    private static final String[] mCountries = {"小王子", "小公主"};

    @Override
    protected int getLayoutId() {
        return R.layout.activity_add_baby;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
    }

    private void assignViews() {
        rlRoot = (RelativeLayout) findViewById(R.id.rl_root);
        rlHeadBac = (RelativeLayout) findViewById(R.id.rl_head_bac);
        civHead = (ImageView) findViewById(R.id.civ_head);
        civHead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(AddBabyActivity.this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        cardview = (CardView) findViewById(R.id.cardview);
        etNick = (EditText) findViewById(R.id.et_nick);
        tvBirthday = (TextView) findViewById(R.id.tv_birthday);
        tvBirthday.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar myCalendar = Calendar.getInstance(Locale.CHINA);
                new DatePickerDialog(AddBabyActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        monthOfYear = monthOfYear + 1;
                        String monthOfYears;
                        if (monthOfYear < 10) {
                            monthOfYears = "0" + monthOfYear;
                        } else {
                            monthOfYears = monthOfYear + "";
                        }
                        birthday = year + "-" + monthOfYears + "-" + dayOfMonth;
                        tvBirthday.setText(birthday);
                    }
                }, myCalendar.get(Calendar.YEAR), myCalendar.get(Calendar.MONTH), myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
        spinnerGender = (Spinner) findViewById(R.id.spinner_gender);
        ArrayAdapter<String> ad = new ArrayAdapter<>(this, R.layout.layout_text_two, mCountries);
        ad.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spinnerGender.setAdapter(ad);
        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                gender = position + 1;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_add_baby, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_submit) {
            attemptAdd();
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

    private void attemptAdd() {
        String nick = etNick.getText().toString();
        if (TextUtils.isEmpty(birthday) || TextUtils.isEmpty(nick) || gender == 0) {
            Toast.makeText(AddBabyActivity.this, "宝宝信息不完整哟！", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat format = new SimpleDateFormat("yy-MM-dd");
        long date = new Date().getTime();
        long birthdaytime = 0;
        try {
            birthdaytime = format.parse(birthday).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (birthdaytime > date) {
            showToast("宝宝还未出生！请检查宝宝生日！");
            return;
        }
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("brithday", birthday);
        body.put("babyNick", nick);
        body.put("babySex", gender + "");
        File file = null;
        if (imgs.size() > 0) {
            file = new File(imgs.get(0));
        }
        showWaitDialog("宝宝创建中...");
        OkHttp.asyncPost(Constants.Baby.ADDORUPDATEBABY_URL, body, file, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                try {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            hideWaitDialog();
                            showToast(getResources().getString(R.string.rc_network_error));
                        }
                    });
                } catch (Exception e1) {

                }
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String result = response.body().string();
                        if (result.contains("1")) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    hideWaitDialog();
                                    showToast("宝宝创建成功");
                                    finish();
                                }
                            });

                        }
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                hideWaitDialog();
                                showToast(getResources().getString(R.string.rc_network_error));
                            }
                        });
                    }
                } catch (Exception e) {

                }
            }
        });

    }

}
