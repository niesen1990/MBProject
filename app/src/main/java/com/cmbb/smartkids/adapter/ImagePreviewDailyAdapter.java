package com.cmbb.smartkids.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.widget.ZoomImageView;

import java.util.ArrayList;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：javon
 * 创建时间：2015/8/11 19:02
 */
public class ImagePreviewDailyAdapter extends PagerAdapter {
    private ArrayList<DailyModel> data;
    private Activity activity;

    public ImagePreviewDailyAdapter(Activity activity) {
        this.activity = activity;
    }

    public void setData(ArrayList<DailyModel> data) {
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
        View rootView = LayoutInflater.from(container.getContext()).inflate(R.layout.image_preview_daily_item, container);
        /*final ZoomImageView img = (ZoomImageView) rootView.findViewById(R.id.ziv_daily);
        img.setScaleType(ImageView.ScaleType.FIT_CENTER);
        String imgUrl = data.get(position).getUrl();
        Log.e("ZoomImageView", "ZoomImageView = " + data.get(position).toString());
        PicassoTool.loadImage(container.getContext(), imgUrl, img, false);
        img.setOnPhotoTapListener(new ZoomImageView.OnPhotoTapListener() {
            @Override
            public void onPhotoTap(View view, float x, float y) {
                activity.finish();
            }
        });*/

        Log.e("ZoomImageView", "ZoomImageView = " + data.get(position).toString());
        final TextView tvContent = (TextView) rootView.findViewById(R.id.tv_content);
        if (TextUtils.isEmpty(data.get(position).getContent())) {
            tvContent.setText("");
            tvContent.setVisibility(View.GONE);
        } else {
            tvContent.setText(data.get(position).getContent());
            tvContent.setVisibility(View.VISIBLE);
        }
        return rootView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

            /*
             * Recycle the old bitmap to free up memory straight away
             */
        /*try {
            final ImageView imageView = (ImageView) object;
            final Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            imageView.setImageBitmap(null);
            bitmap.recycle();
        } catch (Exception e) {
        }*/
    }

    public static class DailyModel implements Parcelable {
        String url;
        String content;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        @Override
        public String toString() {
            return "DailyModel{" +
                    "url='" + url + '\'' +
                    ", content='" + content + '\'' +
                    '}';
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.url);
            dest.writeString(this.content);
        }

        public DailyModel() {
        }

        protected DailyModel(Parcel in) {
            this.url = in.readString();
            this.content = in.readString();
        }

        public static final Parcelable.Creator<DailyModel> CREATOR = new Parcelable.Creator<DailyModel>() {
            public DailyModel createFromParcel(Parcel source) {
                return new DailyModel(source);
            }

            public DailyModel[] newArray(int size) {
                return new DailyModel[size];
            }
        };
    }


}
