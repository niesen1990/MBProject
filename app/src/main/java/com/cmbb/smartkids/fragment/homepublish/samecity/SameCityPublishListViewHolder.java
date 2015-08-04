package com.cmbb.smartkids.fragment.homepublish.samecity;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.fragment.postlist.PostModel;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class SameCityPublishListViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardview;
    private final ImageView rivHead;
    private final RelativeLayout rlHeaderUp;
    private final TextView tvHeaderUpName;
    private final TextView tvHeaderDownText02;
    private final TextView tvMaster;
    private final TextView tvCenterContent01;
    private final TextView tvCenterContent02;
    private final ImageView psivSmallIcon01;
    private final ImageView psivSmallIcon02;
    private final ImageView psivSmallIcon03;
    private final TextView tvHeaderDownText01;


    private SameCityPublishListViewHolder(View view) {
        super(view);
        cardview = (CardView) view.findViewById(R.id.cardview);
        rivHead = (ImageView) view.findViewById(R.id.riv_head);
        rlHeaderUp = (RelativeLayout) view.findViewById(R.id.rl_header_up);
        tvHeaderUpName = (TextView) view.findViewById(R.id.tv_header_up_name);
        tvHeaderDownText02 = (TextView) view.findViewById(R.id.tv_header_down_text02);
        tvMaster = (TextView) view.findViewById(R.id.tv_master);
        tvCenterContent01 = (TextView) view.findViewById(R.id.tv_center_content01);
        tvCenterContent02 = (TextView) view.findViewById(R.id.tv_center_content02);
        psivSmallIcon01 = (ImageView) view.findViewById(R.id.psiv_small_icon01);
        psivSmallIcon02 = (ImageView) view.findViewById(R.id.psiv_small_icon02);
        psivSmallIcon03 = (ImageView) view.findViewById(R.id.psiv_small_icon03);
        tvHeaderDownText01 = (TextView) view.findViewById(R.id.tv_header_down_text01);

    }

    public static SameCityPublishListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_collection_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeSameAge homeSameAge = (HomeSameAge) v.getTag();
            }
        });
        return new SameCityPublishListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final PostModel entry) {
        //GlideTool.loadImage(context, entry.getSmallImg(), ivHead, false);
    }

}
