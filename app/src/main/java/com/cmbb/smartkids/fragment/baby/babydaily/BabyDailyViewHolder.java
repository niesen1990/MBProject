package com.cmbb.smartkids.fragment.baby.babydaily;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.glide.GlideTool;


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
    private ImageView riv01;
    private ImageView riv02;
    private ImageView riv03;


    private BabyDailyViewHolder(View view) {
        super(view);
        timeAxleRl = (RelativeLayout) view.findViewById(R.id.time_axle_rl);
        tvTime = (TextView) view.findViewById(R.id.tv_time);
        tv_time2 = (TextView) view.findViewById(R.id.tv_time2);
        timeAxleIv1 = (ImageView) view.findViewById(R.id.time_axle_iv1);
        timeAxleTv1 = (TextView) view.findViewById(R.id.time_axle_tv1);
        tvYear = (TextView) view.findViewById(R.id.tv_year);
        riv01 = (ImageView) view.findViewById(R.id.riv_01);
        riv02 = (ImageView) view.findViewById(R.id.riv_02);
        riv03 = (ImageView) view.findViewById(R.id.riv_03);
    }

    public static BabyDailyViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_baby_daily_item, parent, false);
        return new BabyDailyViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final DataController<BabyDailyModel> dataController, final BabyDailyModel entry, final int position) {

        // 图片加载
        String imgUrl = entry.getSmallImg();
        if (!TextUtils.isEmpty(imgUrl)) {
            String[] imgUrls = imgUrl.split("\\^#\\^");
            for (int i = 0; i < imgUrls.length; i++) {
                switch (i) {
                    case 0:
                        riv01.setVisibility(View.VISIBLE);
                        String[] urls1 = imgUrls[i].split(",");
                        GlideTool.loadImage(context, urls1[1], riv01, false);
                        break;
                    case 1:
                        riv02.setVisibility(View.VISIBLE);
                        String[] urls2 = imgUrls[i].split(",");
                        GlideTool.loadImage(context, urls2[1], riv02, false);
                        break;
                    case 2:
                        riv03.setVisibility(View.VISIBLE);
                        String[] urls3 = imgUrls[i].split(",");
                        GlideTool.loadImage(context, urls3[1], riv03, false);
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
