package com.cmbb.smartkids.tools.glide;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.widget.CircleImage.CircleImageTransform;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午7:56
 */
public class GlideTool {

    /**
     * @param context Context
     * @param url     图片地址
     * @param view    ImageView
     * @param circle  是否裁圆
     */
    public static void loadImage(Context context, String url, ImageView view, boolean circle) {
        if (url.contains("upload")) {
            if (circle) {
                Glide.with(context).load(Constants.BASE_IMAGE_URL_OLD + url).transform(new CircleImageTransform(context)).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Glide.with(context).load(Constants.BASE_IMAGE_URL_OLD + url).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
        } else {
            if (circle) {
                Glide.with(context).load(Constants.BASE_IMAGE_URL + url).transform(new CircleImageTransform(context)).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Glide.with(context).load(Constants.BASE_IMAGE_URL + url).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
        }
    }
}
