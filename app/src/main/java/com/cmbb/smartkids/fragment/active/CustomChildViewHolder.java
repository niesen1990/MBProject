package com.cmbb.smartkids.fragment.active;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.cmbb.smartkids.R;


/**
 * Custom child ViewHolder. Any views should be found and set to public variables here to be
 * referenced in your custom ExpandableAdapter later.
 * <p>
 * Must extend ChildViewHolder
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class CustomChildViewHolder extends ChildViewHolder {


    public final ImageView mIvHead;
    public final TextView mTvConstellation;
    public final ImageView mIvRanktag;
    public final ImageView mIvLv;
    public final TextView mTvContent;

    public final RelativeLayout rvRoot;


    /**
     * Public constructor for the custom child ViewHolder
     *
     * @param itemView the child ViewHolder's view
     */
    public CustomChildViewHolder(View itemView) {
        super(itemView);
        mIvHead = (ImageView) itemView.findViewById(R.id.iv_head);
        mTvConstellation = (TextView) itemView.findViewById(R.id.tv_constellation);
        mIvRanktag = (ImageView) itemView.findViewById(R.id.iv_ranktag);
        mIvLv = (ImageView) itemView.findViewById(R.id.iv_lv);
        mTvContent = (TextView) itemView.findViewById(R.id.tv_content);
        rvRoot = (RelativeLayout) itemView.findViewById(R.id.rv_root);
    }
}