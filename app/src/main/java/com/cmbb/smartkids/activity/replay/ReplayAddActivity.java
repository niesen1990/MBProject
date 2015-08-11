package com.cmbb.smartkids.activity.replay;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.photopicker.PhotoPickerActivity;
import com.cmbb.smartkids.photopicker.utils.PhotoPickerIntent;
import com.cmbb.smartkids.tools.glide.GlideTool;

import java.util.ArrayList;

public class ReplayAddActivity extends MActivity {

    private CardView cardview;
    private ImageView ivAddPic;
    private TextInputLayout textInputLayout;
    private EditText etContentPic;
    private TextView btnSend;

    private void assignViews() {
        cardview = (CardView) findViewById(R.id.cardview);
        ivAddPic = (ImageView) findViewById(R.id.iv_add_pic);
        ivAddPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhotoPickerIntent intent = new PhotoPickerIntent(ReplayAddActivity.this);
                intent.setPhotoCount(1);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        textInputLayout = (TextInputLayout) findViewById(R.id.text_input_layout);
        etContentPic = (EditText) findViewById(R.id.et_content_pic);
        btnSend = (TextView) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_replay_add;
    }


    @Override
    protected void init(Bundle savedInstanceState) {
        assignViews();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_replay_add, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
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
                if (imgs.size() == 0) return;
                GlideTool.loadImage(this, imgs.get(0), ivAddPic, false);
            }
        }
    }
}
