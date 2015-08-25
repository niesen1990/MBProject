package com.cmbb.smartkids.fragment.replay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.ImagePreviewActivity;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.network.api.ApiNetwork;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;


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

    public void onBindViewHolder(final Context context, final PostModel postDetail, final DataController<ReplayModel> mDataController, final ReplayModel entry, final int position) {
        mCardview.setTag(entry);
        mCardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnReplayItemClickListener.onReplayItemClick(v);
            }
        });
        // 未完成
        mCardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                BottomSheet bottomSheet = new BottomSheet.Builder(context).title("操作").sheet(R.menu.menu_replay_de_report).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final ReplayModel replayModel = (ReplayModel) mCardview.getTag();
                        Log.i("ReplayListViewHolder", "ReplayListViewHolder = " + which);
                        switch (which) {
                            case R.id.action_delete:
                                if (1 == replayModel.getIsCurrentUser()) {
                                    ApiNetwork.deleteReplay(postDetail.getType(), postDetail.getPortConnector(), postDetail.getAreaType(), replayModel.getId(), new Callback() {
                                        @Override
                                        public void onFailure(Request request, IOException e) {

                                        }

                                        @Override
                                        public void onResponse(Response response) throws IOException {
                                            if (response.isSuccessful()) {
                                                String result = response.body().string();
                                                Log.i("result", " result = " + result);
                                                if (result.contains("1")) {
                                                    Log.i("remove", "remove2 = " + position);
                                                    mDataController.remove(position);
                                                }
                                            }
                                        }
                                    });

                                } else {
                                    ((MActivity) context).showToast("此贴非本人发布，不可删除");
                                }

                                break;
                            case R.id.action_report:
                                //举报
                                ApiNetwork.addReport(postDetail.getAreaType(), postDetail.getType(), postDetail.getId() + "", replayModel.getId() + "", new Callback() {
                                    @Override
                                    public void onFailure(Request request, IOException e) {
                                        try {
                                            ((MActivity) context).runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    try {
                                                        ((MActivity) context).showToast("举报失败");

                                                    } catch (Exception e1) {

                                                    }
                                                }
                                            });
                                        } catch (Exception e1) {

                                        }
                                    }

                                    @Override
                                    public void onResponse(Response response) throws IOException {
                                        try {
                                            if (response.isSuccessful()) {
                                                String result = response.body().string();
                                                Log.i("result", " result = " + result);
                                                if (result.contains("1")) {
                                                    try {
                                                        ((MActivity) context).runOnUiThread(new Runnable() {
                                                            @Override
                                                            public void run() {
                                                                try {
                                                                    ((MActivity) context).showToast("举报成功");
                                                                } catch (Exception e) {

                                                                }
                                                            }
                                                        });
                                                    } catch (Exception e) {

                                                    }

                                                }
                                            }
                                        } catch (Exception e) {

                                        }
                                    }
                                });
                                break;
                        }
                    }
                }).build();
                bottomSheet.show();
                v.setSelected(true);
                return true;
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
                        mTvHeaderType.setText("萌宝用户");
                        break;
                    case 4:
                        mTvHeaderType.setText("专家");
                        break;
                    case 5:
                        mTvHeaderType.setText("在线小编");
                        break;
                    case 6:
                        mTvHeaderType.setText("萌主");
                        break;
                    case 7:
                        mTvHeaderType.setText("实习萌主");
                        break;
                    default:
                        mTvHeaderType.setText("萌宝用户");
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
                mRivContentReplay.setTag(R.id.riv_content_replay, entry.getBigImg());
                mRivContentReplay.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String imageUrl = (String) v.getTag(R.id.riv_content_replay);
                        ArrayList<String> pagerUrls = new ArrayList<String>();
                        pagerUrls.add(imageUrl);
                        Intent intent = new Intent(context, ImagePreviewActivity.class);
                        intent.putExtra("index", 0);
                        intent.putExtra("data", pagerUrls);
                        context.startActivity(intent);
                    }
                });
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
