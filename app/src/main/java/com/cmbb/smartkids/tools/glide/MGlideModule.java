package com.cmbb.smartkids.tools.glide;

import android.content.Context;

import com.bumptech.glide.Glide;
import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory;
import com.bumptech.glide.load.engine.cache.LruResourceCache;
import com.bumptech.glide.module.GlideModule;
import com.cmbb.smartkids.tools.log.Log;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/21 下午3:31
 */
public class MGlideModule implements GlideModule {
    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        Log.i("GlideModule", "GlideModule applyOptions");
        builder.setDiskCache(new InternalCacheDiskCacheFactory(context, 1024 * 1024 * 50));
        builder.setMemoryCache(new LruResourceCache(1024 * 1024 * 5));
        //builder.setBitmapPool(new LruBitmapPool(sizeInBytes));
        builder.setDecodeFormat(DecodeFormat.PREFER_RGB_565);
    }

    @Override
    public void registerComponents(Context context, Glide glide) {

    }
}
