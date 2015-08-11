package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.model.photo.PhotoAdd;
import com.cmbb.smartkids.tools.glide.GlideTool;

import java.util.ArrayList;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/6 下午7:06
 */
public class PostAddViewHolder extends RecyclerView.ViewHolder {


    private final ImageView ivAddPic;
    private final ImageView ivAddDel;
    private final FrameLayout flRoot;
    private final EditText etContentPic;


    public PostAddViewHolder(View itemView) {
        super(itemView);
        ivAddPic = (ImageView) itemView.findViewById(R.id.iv_add_pic);
        ivAddDel = (ImageView) itemView.findViewById(R.id.iv_add_del);
        flRoot = (FrameLayout) itemView.findViewById(R.id.fl_root);
        etContentPic = (EditText) itemView.findViewById(R.id.et_content_pic);
    }

    public static PostAddViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_post_add_item, parent, false);

        return new PostAddViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final ArrayList<PhotoAdd> photoContents, final int position) {
        flRoot.setTag(position);
        GlideTool.loadImage(context, photoContents.get(position).getPhotoUrl(), ivAddPic, false);
        etContentPic.setTag(position);
        etContentPic.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                //将editText中改变的值设置的HashMap中
                photoContents.get(position).setPhotoContent(s.toString());
            }
        });
    }


}
