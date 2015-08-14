package com.cmbb.smartkids.fragment.replay;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.glide.GlideTool;


/**
 * 项目名称：MengBao
 * 类描述：绑定PostModel
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class ReplayListViewHolder extends RecyclerView.ViewHolder {

    private final CardView mCardview;
    private final ImageView mRivHead;
    private final RelativeLayout mRlContent;
    private final RelativeLayout mRlHeaderUp;
    private final TextView mTvNick;
    private final TextView mTvHeaderType;
    private final TextView mTvContentReplay;
    private final ImageView mRivContentReplay;
    private final TextView mTvHeaderFloor;
    private final TextView mTvHeaderTime;
    private final RelativeLayout mRlReplayAgain;
    private final RelativeLayout mRlHeaderOther;
    private final ImageView mRivOtherHead;
    private final TextView mTvNameOther;
    private final TextView mTvHeaderFloorOther;
    private final TextView mTvContentReplayOther;

    private OnReplayItemClickListener mOnReplayItemClickListener;


    private ReplayListViewHolder(View view, OnReplayItemClickListener onReplayItemClickListener) {
        super(view);
        mCardview = (CardView) view.findViewById(R.id.cardview);
        mRivHead = (ImageView) view.findViewById(R.id.riv_head);
        mRlContent = (RelativeLayout) view.findViewById(R.id.rl_content);
        mRlHeaderUp = (RelativeLayout) view.findViewById(R.id.rl_header_up);
        mTvNick = (TextView) view.findViewById(R.id.tv_nick);
        mTvHeaderType = (TextView) view.findViewById(R.id.tv_header_type);
        mTvContentReplay = (TextView) view.findViewById(R.id.tv_content_replay);
        mRivContentReplay = (ImageView) view.findViewById(R.id.riv_content_replay);
        mTvHeaderFloor = (TextView) view.findViewById(R.id.tv_header_floor);
        mTvHeaderTime = (TextView) view.findViewById(R.id.tv_header_time);
        mRlReplayAgain = (RelativeLayout) view.findViewById(R.id.rl_replay_again);
        mRlHeaderOther = (RelativeLayout) view.findViewById(R.id.rl_header_other);
        mRivOtherHead = (ImageView) view.findViewById(R.id.riv_other_head);
        mTvNameOther = (TextView) view.findViewById(R.id.tv_name_other);
        mTvHeaderFloorOther = (TextView) view.findViewById(R.id.tv_header_floor_other);
        mTvContentReplayOther = (TextView) view.findViewById(R.id.tv_content_replay_other);

        this.mOnReplayItemClickListener = onReplayItemClickListener;
    }

    public static ReplayListViewHolder create(final Context context, ViewGroup parent, OnReplayItemClickListener onReplayItemClickListener) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_replay_list_item, parent, false);
        return new ReplayListViewHolder(v, onReplayItemClickListener);
    }

    public void onBindViewHolder(Context context, final ReplayModel entry) {
        mCardview.setTag(entry);
        mCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnReplayItemClickListener.onReplayItemClick(v);
            }
        });
        // head text
        mTvNick.setText(entry.getNike());
        if (!TextUtils.isEmpty(entry.getEredarName())) {
            mTvHeaderType.setText(entry.getEredarName() + "达人");
        } else {
            try {
                switch (entry.getAuthority()) {
                    case 1:
                        mTvHeaderType.setText("系统管理员");
                        break;
                    case 2:
                        mTvHeaderType.setText("小编");
                        break;
                    case 3:
                        mTvHeaderType.setText("普通用户");
                        break;
                    case 4:
                        mTvHeaderType.setText("专家");
                        break;
                    case 5:
                        mTvHeaderType.setText("在线小编");
                        break;
                    default:
                        mTvHeaderType.setText("普通用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
        mTvContentReplay.setText(entry.getContext());
        mTvHeaderFloor.setText(entry.getFloor() + "楼");
        mTvHeaderTime.setText(entry.getDate());
        GlideTool.loadImage(context, entry.getUserSmallHeadImg(), mRivHead, true);
        // 删除UI
        if (1 == entry.getDeleteTag()) {
            mTvContentReplay.setText("此回复已经删除");
            mRivContentReplay.setVisibility(View.GONE);
        } else { // 未删除
            mTvContentReplay.setText(entry.getContext());
            if (TextUtils.isEmpty(entry.getBigImg())) {
                mRivContentReplay.setVisibility(View.GONE);
            } else {
                mRivContentReplay.setVisibility(View.VISIBLE);
                GlideTool.loadImage(context, entry.getBigImg().split(",")[0], mRivContentReplay, false);
            }
        }
        // other
        if (!TextUtils.isEmpty(entry.getOtherNike())) {
            // other 未删除
            mRlReplayAgain.setVisibility(View.VISIBLE);
            if (1 == entry.getOtherDeleteTag()) {
                mTvNameOther.setText(entry.getOtherNike() + "");
                mTvHeaderFloorOther.setText(entry.getOtherFloor() + "楼");
                mTvContentReplayOther.setText("此回复已经删除");
            } else {
                mTvNameOther.setText(entry.getOtherNike() + "");
                mTvHeaderFloorOther.setText(entry.getOtherFloor() + "楼");
                mTvContentReplayOther.setText(entry.getOtherContext());
                GlideTool.loadImage(context, entry.getOtherUserSmallHeadImg().split(",")[0], mRivOtherHead, true);
            }
        } else {
            mRlReplayAgain.setVisibility(View.GONE);
        }
    }

    public interface OnReplayItemClickListener {
        void onReplayItemClick(View view);
    }
}
