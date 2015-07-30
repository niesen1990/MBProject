package com.cmbb.smartkids.fragment.test;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cmbb.smartkids.R;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class EntryListViewHolder extends RecyclerView.ViewHolder {


    public final ImageView thumb;
    public final TextView title, content, time;
    public final View parent;
    public final CardView cardview;


    private EntryListViewHolder(View itemView) {
        super(itemView);
        thumb = (ImageView) itemView.findViewById(R.id.thumb);
        title = (TextView) itemView.findViewById(R.id.title);
        content = (TextView) itemView.findViewById(R.id.content);
        time = (TextView) itemView.findViewById(R.id.time);
        cardview = (CardView) itemView.findViewById(R.id.cardview);
        parent = itemView;

    }

    public static EntryListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.test_entry_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeSameAge homeSameAge = (HomeSameAge) v.getTag();
            }
        });
        return new EntryListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final HomeSameAge entry) {
        parent.setTag(entry);
        title.setText(entry.getTitle());
        content.setText(entry.getContext());
        time.setText(entry.getSmallImgWidth() + entry.getConnector());
        Glide.with(context).load("http://mengbaopai.smart-kids.com/image" + entry.getBigImg()).into(thumb);
    }

    public void onBindViewHolder(Context context) {
        parent.setTag("dd");
        title.setText("meizu");
        content.setText("meizu fa bu hui ");
        time.setText("you are good");
        Glide.with(context).load("http://pic2.ooopic.com/01/26/61/83bOOOPIC72.jpg").into(thumb);
    }
}
