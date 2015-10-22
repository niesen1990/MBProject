package com.cmbb.smartkids.tools.picasso;

import android.content.Context;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.tools.TDevice;
import com.cmbb.smartkids.widget.CircleTransform;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午7:56
 */
public class PicassoTool {

    /**
     * @param context Context
     * @param url     图片地址
     * @param view    ImageView
     * @param circle  是否裁圆
     */
    public static void loadImage(Context context, String url, ImageView view, boolean circle) {
        Log.i("imageUrl", "imageUrl = " + url);
        if (url.contains("storage") || url.contains("sdcard") || url.contains("mnt") || url.contains(Environment.getExternalStorageDirectory().getPath())) {
            if (circle) {
                Picasso.with(context).load(new File(url)).fit().transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(new File(url)).fit().error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
            return;
        }

        if (url.contains("upload")) {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL_OLD + url;
            if (circle) {
                Picasso.with(context).load(httpUrl).fit().transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(httpUrl).fit().error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
        } else {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL + url;
            if (circle) {
                Picasso.with(context).load(httpUrl).fit().transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(httpUrl).fit().centerInside().error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
        }
    }

    public static String getImageUrl(String url) {
        if (url.contains("storage") || url.contains("sdcard") || url.contains("mnt") || url.contains(Environment.getExternalStorageDirectory().getPath())) {
            return url;
        }
        if (url.contains("upload")) {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL_OLD + url;
            return httpUrl;
        } else {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL + url;
            return httpUrl;
        }
    }

    public static void loadImageWithSize(Context context, String url, ImageView view, int width, int height, boolean circle) {
        int cacheHeight = (int) TDevice.getScreenWidth() * height / width;
        Log.i("loadImageWithSize", "getScreenWidth = " + TDevice.getScreenWidth());
        Log.i("loadImageWithSize", "height = " + height);
        Log.i("loadImageWithSize", "width = " + width);
        Log.i("loadImageWithSize", "cacheHeight = " + cacheHeight);
        if (url.contains("storage") || url.contains("sdcard") || url.contains("mnt")) {
            if (circle) {
                Picasso.with(context).load(new File(url)).fit().transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(new File(url)).fit().error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
            return;
        }

        if (url.contains("upload")) {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL_OLD + url;
            if (circle) {
                Picasso.with(context).load(httpUrl).resize((int) TDevice.getScreenWidth(), cacheHeight).transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(httpUrl).resize((int) TDevice.getScreenWidth(), cacheHeight).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            }
        } else {
            String httpUrl = url.contains("http") ? url : Constants.BASE_IMAGE_URL + url;
            if (circle) {
                Picasso.with(context).load(httpUrl).resize((int) TDevice.getScreenWidth(), cacheHeight).transform(new CircleTransform()).error(R.drawable.ic_loadfail).placeholder(R.drawable.ic_loading).into(view);
            } else {
                Picasso.with(context).load(httpUrl).resize((int) TDevice.getScreenWidth(), cacheHeight).error(R.drawable.ic_loadfail).into(view);
            }
        }
    }
}
