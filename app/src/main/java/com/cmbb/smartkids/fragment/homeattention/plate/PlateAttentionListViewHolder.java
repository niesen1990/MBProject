package com.cmbb.smartkids.fragment.homeattention.plate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.post.PostAgeListActivity;
import com.cmbb.smartkids.activity.post.PostCityListActivity;
import com.cmbb.smartkids.activity.post.PostWonderListActivity;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.tools.picasso.PicassoTool;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class PlateAttentionListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivHead;
    private final TextView tvConstellation;
    private final TextView tvPost;
    private final TextView tvContent;
    private final CardView mCardView;


    private PlateAttentionListViewHolder(View view) {
        super(view);
        mCardView = (CardView) view.findViewById(R.id.cardview);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        tvConstellation = (TextView) view.findViewById(R.id.tv_constellation);
        tvPost = (TextView) view.findViewById(R.id.tv_post);
        tvContent = (TextView) view.findViewById(R.id.tv_content);

    }

    public static PlateAttentionListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_content_list_plate, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeSameAge homeSameAge = (HomeSameAge) v.getTag();
            }
        });
        return new PlateAttentionListViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final PlateAttentionModel entry) {
        mCardView.setTag(entry);
        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlateAttentionModel plateAttentionModel = (PlateAttentionModel) v.getTag();
                PlateModel plateModel = new PlateModel();
                plateModel.setId(plateAttentionModel.getId());
                plateModel.setBigImg(plateAttentionModel.getBigImg());
                plateModel.setConnector(plateAttentionModel.getConnector());
                plateModel.setContext(plateAttentionModel.getContext());
                plateModel.setCount(plateAttentionModel.getCount());
                plateModel.setPlateParentType(plateAttentionModel.getPlateParentType());
                plateModel.setSmallImg(plateAttentionModel.getSmallImg());
                plateModel.setTitle(plateAttentionModel.getTitle());
                plateModel.setType(plateAttentionModel.getType());
                Intent intent = null;
                if (plateModel.getPlateParentType().equals("WONDERFUL")) {
                    intent = new Intent(context, PostWonderListActivity.class);
                } else if (plateModel.getPlateParentType().equals("AGEBREAKET")) {
                    intent = new Intent(context, PostAgeListActivity.class);
                } else if (plateModel.getPlateParentType().equals("LOCAL")) {
                    intent = new Intent(context, PostCityListActivity.class);
                }
                intent.putExtra("model", plateModel);
                context.startActivity(intent);
            }
        });
        tvConstellation.setText(entry.getTitle());
        tvPost.setText(entry.getCount() + "");
        tvContent.setText(entry.getContext());
        PicassoTool.loadImage(context, entry.getSmallImg(), ivHead, true);
    }

}
