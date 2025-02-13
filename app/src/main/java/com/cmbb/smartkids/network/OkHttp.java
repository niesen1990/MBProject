package com.cmbb.smartkids.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.model.photo.PhotoAdd;
import com.cmbb.smartkids.tools.ImageUtils;
import com.cmbb.smartkids.tools.log.Log;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.Cache;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.Headers;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.MultipartBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by N.Sun
 */
public class OkHttp {
    private static final String TAG = "OkHttp";
    public static final OkHttpClient mOkHttpClient = new OkHttpClient();
    private static int cacheSize = 30 * 1024 * 1024; // 30 MiB

    private static final MediaType MEDIA_TYPE_PNG = MediaType.parse("image/png");

    // timeout
    static {
        mOkHttpClient.setConnectTimeout(60, TimeUnit.SECONDS);
        mOkHttpClient.setWriteTimeout(60, TimeUnit.SECONDS);
        mOkHttpClient.setReadTimeout(60, TimeUnit.SECONDS);
        //mOkHttpClient.networkInterceptors().add(new StethoInterceptor());
        mOkHttpClient.setCookieHandler(new CookieManager(new PersistentCookieStore(MApplication.getContext()), CookiePolicy.ACCEPT_ALL));
        mOkHttpClient.setCache(new Cache(MApplication.getContext().getExternalCacheDir(), cacheSize));
        //fresco
        ImagePipelineConfig config = OkHttpImagePipelineConfigFactory
                .newBuilder(MApplication.getContext(), mOkHttpClient)
                .build();
        Fresco.initialize(MApplication.getContext(), config);
    }


    /**
     * 不使用异步线程
     *
     * @param request
     * @return
     * @throws IOException
     */
    private static Response execute(Request request) throws IOException {
        return mOkHttpClient.newCall(request).execute();
    }

    /**
     * 开启异步线程访问网络
     *
     * @param request
     * @param responseCallback
     */
    private static void enqueue(Request request, Callback responseCallback) {

        mOkHttpClient.newCall(request).enqueue(responseCallback);
    }

    /**
     * 同步Get(一般不使用)
     *
     * @param url
     * @return String
     */
    public static String syncGet(String url) throws IOException {
        Request request = new Request.Builder()
                .url(url)
                .build();
        Response response = execute(request);
        if (!response.isSuccessful())
            throw new IOException("Unexpected code " + response);
        Headers responseHeaders = response.headers();
        for (int i = 0; i < responseHeaders.size(); i++) {
            Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
        }
        Log.i(TAG, "cache response:    " + response.cacheResponse());
        Log.i(TAG, "network response:  " + response.networkResponse());
        return response.body().string();
    }

    /**
     * 异步get
     *
     * @param url
     * @param callback
     * @return
     */
    public static void asyncGet(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        enqueue(request, callback);
    }

    // post without file
    public static void asyncPost(String url, Map<String, String> body, Callback callback) {


        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (TextUtils.isEmpty(body.get(key))) {
                return;
            }
            formEncodingBuilder.add(key, body.get(key));
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }

    // post without file with tag
    public static void asyncPost(String url, Map<String, String> body, String tag, Callback callback) {
        FormEncodingBuilder formEncodingBuilder = new FormEncodingBuilder();
        for (String key : body.keySet()) {
            if (TextUtils.isEmpty(body.get(key))) {
                return;
            }
            formEncodingBuilder.add(key, body.get(key));
        }
        RequestBody formBody = formEncodingBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .tag(tag)
                .build();
        enqueue(request, callback);
    }

    // post with file
    public static void asyncPost(String url, Map<String, String> body, File file, Callback callback) {

        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);

        for (String key : body.keySet()) {
            multipartBuilder.addFormDataPart(key, body.get(key));
        }
        //        Date date = new Date();
        if (file != null && file.exists()) {
            multipartBuilder.addFormDataPart("image", "image", RequestBody.create(MEDIA_TYPE_PNG, getSmallBitmap(file.getPath())));

        }
        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }

    // post with files
    public static void asyncPost(String url, Map<String, String> body, ArrayList<PhotoAdd> files, Callback callback) {
        MultipartBuilder multipartBuilder = new MultipartBuilder();
        multipartBuilder.type(MultipartBuilder.MIXED);
        for (String key : body.keySet()) {
            multipartBuilder.addFormDataPart(key, body.get(key));
        }

        if (files != null && files.size() > 0) {
            for (int i = 0; i < files.size(); i++) {
                multipartBuilder.addFormDataPart("image" + i, "image" + i, RequestBody.create(MEDIA_TYPE_PNG, getSmallBitmap(files.get(i).getPhotoUrl())));
                Log.i(TAG, "add picture addres = " + files.get(i) + "image" + (i + 1));
            }
        }

        RequestBody formBody = multipartBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
        enqueue(request, callback);
    }


    /*test*/
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

    public static void asyncPost(String url, Callback callback) {
        RequestBody body = RequestBody.create(JSON, "{\n" +
                "  \"cmd\": \"smart/register\",\n" +
                "  \"parameters\": {\n" +
                "    \"loginAccount\" : \"15901718791\"\n" +
                "  }\n" +
                "}");
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        enqueue(request, callback);
    }

    // 根据路径获得图片并压缩，返回bitmap用于显示
    public synchronized static byte[] getSmallBitmap(String filePath) {
        ByteArrayOutputStream baos = null;
        Bitmap bitmap = null;
        Bitmap bitmapCache = null;
        byte[] bytes = null;
        try {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(filePath, options);
            options.inSampleSize = calculateInSampleSize(options, 320, 540);
            options.inJustDecodeBounds = false;
            bitmapCache = BitmapFactory.decodeFile(filePath, options);
            if (bitmapCache == null) {
                return null;
            }
            bitmap = ImageUtils.rotaingImageView(ImageUtils.readPictureDegree(filePath), bitmapCache);
            baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {

        } finally {
            if (baos != null) try {
                baos.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (baos != null) try {
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }

    //计算图片的缩放值
    public static int calculateInSampleSize(BitmapFactory.Options options, int reqWidth, int reqHeight) {
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

}
