package com.cmbb.smartkids.mengrecyclerview.holders;

import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cmbb.smartkids.mengrecyclerview.R;

public class FooterViewHolder extends RecyclerView.ViewHolder {
    public final ContentLoadingProgressBar progressBar;
    public final TextView tips;

    public FooterViewHolder(View itemView) {
        super(itemView);
        progressBar = (ContentLoadingProgressBar) itemView.findViewById(R.id.loading_more_progress);
        tips = (TextView) itemView.findViewById(R.id.loading_more_tips);
    }

    public void onBindViewHolder(boolean isEnd, boolean moreFlag) {
        if (isEnd || moreFlag) {
            tips.setText(R.string.footer_end_tips);
            tips.setVisibility(View.VISIBLE);
            progressBar.hide();
        } else {
            Log.i("isEnd", "is End = " + isEnd);
            tips.setVisibility(View.GONE);
            progressBar.setVisibility(View.VISIBLE);
            progressBar.show();
        }
    }
}
