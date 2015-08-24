package com.cmbb.smartkids.fragment.active;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.cmbb.smartkids.R;


/**
 * Custom parent ViewHolder. Any views should be found and set to public variables here to be
 * referenced in your custom ExpandableAdapter later.
 * <p/>
 * Must extend ParentViewHolder
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class CustomParentViewHolder extends ParentViewHolder {

    public TextView dataText;
    public ImageView arrowExpand;
    public TextView tvUnread;

    /**
     * Public constructor for the CustomViewHolder.
     *
     * @param itemView the view of the parent item. Find/modify views using this.
     */
    public CustomParentViewHolder(View itemView) {
        super(itemView);
        dataText = (TextView) itemView.findViewById(R.id.recycler_item_text_parent);
        arrowExpand = (ImageView) itemView.findViewById(R.id.recycler_item_arrow_parent);
        tvUnread = (TextView) itemView.findViewById(R.id.tv_unread);
    }
}
