package com.cmbb.smartkids.adapter;

import android.app.Activity;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.tools.glide.GlideTool;

import java.util.ArrayList;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 19:02
 */
public class ImagePreviewAdapter extends PagerAdapter {
    private ArrayList<String> data;
    private Activity activity;

    public ImagePreviewAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<String> data) {
        if (data != null) {
            this.data = data;
        } else {
            this.data = new ArrayList<>();
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        ImageView img = new ImageView(container.getContext());
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(-1, -1);
        img.setLayoutParams(params);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String imgUrl = data.get(position);
        GlideTool.loadImage(container.getContext(), imgUrl, img, false);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
        container.addView(img);
        return img;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeViewAt(position);
    }


}
