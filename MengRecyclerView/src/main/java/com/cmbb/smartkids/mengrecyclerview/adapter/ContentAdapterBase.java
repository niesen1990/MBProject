package com.cmbb.smartkids.mengrecyclerview.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.cmbb.smartkids.mengrecyclerview.R;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.mengrecyclerview.holders.FooterViewHolder;
import com.cmbb.smartkids.mengrecyclerview.interfaces.AdapterRespondent;
import com.cmbb.smartkids.mengrecyclerview.interfaces.SimpleUIRespondent;

import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 10:34
 */
public abstract class ContentAdapterBase<T> extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    protected Context mContext;
    protected LayoutInflater mInflater;
    protected DataController<T> mDataController;
    protected View mLoadingMoreView;

    //HeadViewHolder
    protected RecyclerView.ViewHolder headViewHolder;

    protected boolean needHeadView;

    protected boolean moreFlag;

    public boolean isMoreFlag() {
        return moreFlag;
    }

    public void setMoreFlag(boolean moreFlag) {
        this.moreFlag = moreFlag;
    }

    public enum CommonFeature {
        HEADER,
        COMMON,
        FOOTER
    }

    public ContentAdapterBase(Context mContext, final DataController<T> mDataController) {
        this.mContext = mContext;
        this.mDataController = mDataController;
        this.mInflater = LayoutInflater.from(mContext);
        //添加响应（Adapter, UI）
        this.mDataController.addAdapterRespondent(new AdapterRespondent() {
            @Override
            public void onDataChanged() {
                notifyDataSetChanged();
            }

            @Override
            public void onDataInsert(int position) {
                //notifyDataSetChanged();
                notifyItemChanged(position);
            }

            @Override
            public void onDataRemove(int position) {
                notifyItemRemoved(position);
            }
        });
        this.mDataController.addUIRespondent(new DataObserver());
    }

    @Override
    public int getItemViewType(int position) {
        if (needHeader() && position == 0) {
            return CommonFeature.HEADER.ordinal();
        }

        if (position == getItemCount() - 1) {
            return CommonFeature.FOOTER.ordinal();
        }

        return CommonFeature.COMMON.ordinal();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == CommonFeature.FOOTER.ordinal()) {
            mLoadingMoreView = mInflater.inflate(R.layout.recyclerview_footer_mengrecycler, parent, false);
            return new FooterViewHolder(mLoadingMoreView);
        } else if (viewType == CommonFeature.HEADER.ordinal()) {
            headViewHolder = onCreateCustomHeaderHolder(parent);
            return headViewHolder;
        } else {
            return onCreateCustomContentHolder(parent, viewType);
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (needHeader() && position == 0) {
            onBindCustomHeaderHolder(holder);
            return;
        }

        if (needHeader()) {
            position -= 1;
        }

        if (holder instanceof FooterViewHolder) {
            FooterViewHolder footerViewHolder = (FooterViewHolder) holder;
            if (mDataController.isEnd() || moreFlag) {
                footerViewHolder.onBindViewHolder(mDataController.isEnd(), moreFlag);
            } else {
                footerViewHolder.onBindViewHolder(mDataController.isEnd(), moreFlag);
                if (!mDataController.moreBusy) {
                    mDataController.more();
                }
            }
        } else {
            onBindCustomViewHolder(holder, position);
        }
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        // 入口
        mDataController.initialize();
    }

    @Override
    public int getItemCount() {

        if (mDataController.getSize() == 0) {
            if (needHeader()) {
                return 1;
            } else {
                return 0;
            }
        }
        if (needHeader()) {
            return mDataController.getSize() + 2;
        }

        return mDataController.getSize() + 1;
    }

    protected boolean needHeader() {
        return this.needHeadView;
    }

    public void setNeedHeadView(boolean needHeadView) {
        this.needHeadView = needHeadView;
    }

    protected void onBindCustomHeaderHolder(RecyclerView.ViewHolder holder) {

    }

    protected void onLoadFinishedHeadBind(RecyclerView.ViewHolder holder) {

    }

    protected RecyclerView.ViewHolder onCreateCustomHeaderHolder(ViewGroup parent) {
        return null;
    }

    protected abstract RecyclerView.ViewHolder onCreateCustomContentHolder(ViewGroup parent, int viewType);

    protected abstract void onBindCustomViewHolder(RecyclerView.ViewHolder holder, int position);

    /**
     * UI 响应
     */
    private class DataObserver extends SimpleUIRespondent<T> {

        @Override
        public void onInitializeDone(Exception e, List<T> data) {
            super.onInitializeDone(e, data);
            if (needHeader()) {
                onLoadFinishedHeadBind(headViewHolder);
            }
        }

        @Override
        public void onLoadMoreDone(Exception e, List data) {
            if (e != null && mLoadingMoreView != null) {
                final Button refresh = (Button) mLoadingMoreView.findViewById(R.id.loading_more_retry);
                final TextView tips = (TextView) mLoadingMoreView.findViewById(R.id.loading_more_tips);
                final ProgressBar progressBar = (ProgressBar) mLoadingMoreView.findViewById(R.id.loading_more_progress);
                progressBar.setVisibility(View.INVISIBLE);
                refresh.setVisibility(View.VISIBLE);
                tips.setVisibility(View.VISIBLE);
                refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        refresh.setVisibility(View.INVISIBLE);
                        tips.setVisibility(View.INVISIBLE);
                        progressBar.setVisibility(View.VISIBLE);
                        mDataController.more();
                    }
                });
            }
        }
    }
}
