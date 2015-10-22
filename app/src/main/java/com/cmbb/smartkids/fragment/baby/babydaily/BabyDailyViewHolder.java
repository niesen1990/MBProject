package com.cmbb.smartkids.fragment.baby.babydaily;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.activity.ImagePreviewDailyActivity;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.adapter.ImagePreviewDailyAdapter;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class BabyDailyViewHolder extends RecyclerView.ViewHolder {

    private RelativeLayout timeAxleRl;
    private TextView tvTime;
    private TextView tv_time2;
    private ImageView timeAxleIv1;
    private TextView timeAxleTv1;
    private TextView tvYear;
    private SimpleDraweeView riv01;
    private SimpleDraweeView riv02;
    private SimpleDraweeView riv03;


    private BabyDailyViewHolder(View view) {
        super(view);
        timeAxleRl = (RelativeLayout) view.findViewById(R.id.time_axle_rl);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tv_time2 = (TextView) view.findViewById(R.id.tv_time2);
        timeAxleIv1 = (ImageView) view.findViewById(R.id.time_axle_iv1);
        timeAxleTv1 = (TextView) view.findViewById(R.id.time_axle_tv1);
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        riv01 = (SimpleDraweeView) view.findViewById(R.id.riv_01);
        riv02 = (SimpleDraweeView) view.findViewById(R.id.riv_02);
        riv03 = (SimpleDraweeView) view.findViewById(R.id.riv_03);
    }

    public static BabyDailyViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_baby_daily_item, parent, false);
        return new BabyDailyViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final DataController<BabyDailyModel> dataController, final BabyDailyModel entry, final int position) {

        // 图片加载
        String imgUrl = entry.getSmallImg();
        String content = entry.getContext();
        if (!TextUtils.isEmpty(imgUrl)) {
            String[] imgUrls = imgUrl.split("\\^#\\^");
            String[] contents = content.split("\\^#\\^");

            ArrayList<ImagePreviewDailyAdapter.DailyModel> dailyModels = new ArrayList<>();
            for (int i = 0; i < imgUrls.length; i++) {
                ImagePreviewDailyAdapter.DailyModel dailyModel = new ImagePreviewDailyAdapter.DailyModel();
                dailyModel.setContent(contents[i]);
                dailyModel.setUrl(imgUrls[i].split(",")[1]);
                dailyModels.add(dailyModel);
            }

            for (int i = 0; i < imgUrls.length; i++) {
                switch (i) {
                    case 0:
                        riv01.setVisibility(View.VISIBLE);
                        String[] urls1 = imgUrls[i].split(",");
                        riv01.setAspectRatio(Float.parseFloat(urls1[2]) / Float.parseFloat(urls1[3]));
                        riv01.setImageURI(Uri.parse(PicassoTool.getImageUrl(urls1[1])));
                        riv01.setTag(dailyModels);
                        riv01.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<ImagePreviewDailyAdapter.DailyModel> datas = (ArrayList<ImagePreviewDailyAdapter.DailyModel>) v.getTag();
                                Intent intent = new Intent(v.getContext(), ImagePreviewDailyActivity.class);
                                intent.putExtra("index", 0);
                                intent.putExtra("data", datas);
                                v.getContext().startActivity(intent);
                            }
                        });
                        break;
                    case 1:
                        riv02.setVisibility(View.VISIBLE);
                        String[] urls2 = imgUrls[i].split(",");
                        //PicassoTool.loadImage(context, urls2[1], riv02, false);
                        riv02.setAspectRatio(Float.parseFloat(urls2[2]) / Float.parseFloat(urls2[3]));
                        riv02.setImageURI(Uri.parse(PicassoTool.getImageUrl(urls2[1])));
                        riv02.setTag(dailyModels);
                        riv02.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<ImagePreviewDailyAdapter.DailyModel> datas = (ArrayList<ImagePreviewDailyAdapter.DailyModel>) v.getTag();
                                Intent intent = new Intent(v.getContext(), ImagePreviewDailyActivity.class);
                                intent.putExtra("index", 1);
                                intent.putExtra("data", datas);
                                v.getContext().startActivity(intent);
                            }
                        });
                        break;
                    case 2:
                        riv03.setVisibility(View.VISIBLE);
                        String[] urls3 = imgUrls[i].split(",");
                        //PicassoTool.loadImage(context, urls3[1], riv03, false);
                        riv03.setAspectRatio(Float.parseFloat(urls3[2]) / Float.parseFloat(urls3[3]));
                        riv03.setImageURI(Uri.parse(PicassoTool.getImageUrl(urls3[1])));
                        riv03.setTag(dailyModels);
                        riv03.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                ArrayList<ImagePreviewDailyAdapter.DailyModel> datas = (ArrayList<ImagePreviewDailyAdapter.DailyModel>) v.getTag();
                                Intent intent = new Intent(v.getContext(), ImagePreviewDailyActivity.class);
                                intent.putExtra("index", 2);
                                intent.putExtra("data", datas);
                                v.getContext().startActivity(intent);
                            }
                        });
                        break;
                }
            }
        }

        String time = entry.getDate();
        try {
            String[] times = time.split(" ");
            tvTime.setText(times[0]);
            tv_time2.setText(times[1]);

        } catch (Exception e) {

        }
    }
}
