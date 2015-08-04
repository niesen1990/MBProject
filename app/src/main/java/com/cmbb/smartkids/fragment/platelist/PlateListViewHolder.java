package com.cmbb.smartkids.fragment.platelist;

import android.content.Context;
import android.content.Intent;
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
import com.cmbb.smartkids.tools.glide.GlideTool;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class PlateListViewHolder extends RecyclerView.ViewHolder {

    private final ImageView ivHead;
    private final TextView tvConstellation;
    private final TextView tvPost;
    private final TextView tvContent;
    private final View parent;


    private PlateListViewHolder(View view) {
        super(view);
        ivHead = (ImageView) view.findViewById(R.id.iv_head);
        tvConstellation = (TextView) view.findViewById(R.id.tv_constellation);
        tvPost = (TextView) view.findViewById(R.id.tv_post);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        parent = view;
    }

    public static PlateListViewHolder create(final Context context, ViewGroup parent, final boolean isCity) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_content_list_plate, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlateModel plateModel = (PlateModel) v.getTag();
                Intent intent = null;
                if (plateModel.getConnector().contains("wonder")) {
                    intent = new Intent(context, PostWonderListActivity.class);
                } else if (plateModel.getConnector().contains("star") && !isCity) {
                    intent = new Intent(context, PostAgeListActivity.class);
                } else if (plateModel.getConnector().contains("star") && isCity) {
                    intent = new Intent(context, PostCityListActivity.class);
                }
                intent.putExtra("model", plateModel);
                context.startActivity(intent);
            }
        });
        return new PlateListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final PlateModel entry) {
        parent.setTag(entry);
        tvConstellation.setText(entry.getTitle());
        tvPost.setText(entry.getCount() + "");
        tvContent.setText(entry.getContext());
        GlideTool.loadImage(context, entry.getSmallImg(), ivHead, false);
    }

}
