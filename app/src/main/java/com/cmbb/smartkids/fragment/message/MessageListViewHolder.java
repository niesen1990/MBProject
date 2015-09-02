package com.cmbb.smartkids.fragment.message;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.replay.ReplayAgeCityActivity;
import com.cmbb.smartkids.activity.replay.ReplayWonderActivity;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.tools.RankTools;
import com.cmbb.smartkids.tools.picasso.PicassoTool;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class MessageListViewHolder extends RecyclerView.ViewHolder {

    private CardView mCardview;
    private RelativeLayout mRlHeaderUp;
    private ImageView mRivHead;
    private TextView mTvHeaderUpName;
    private ImageView mIvHeaderUpIcon1;
    private ImageView mIvHeaderUpIcon2;
    private TextView mTvMaster;
    private TextView mTvCenterContent01;
    private TextView mTvCenterContent02;
    private TextView mTvHeaderDownText01;


    private MessageListViewHolder(View view) {
        super(view);
        mCardview = (CardView) view.findViewById(R.id.cardview);
        mRlHeaderUp = (RelativeLayout) view.findViewById(R.id.rl_header_up);
        mRivHead = (ImageView) view.findViewById(R.id.riv_head);
        mTvHeaderUpName = (TextView) view.findViewById(R.id.tv_header_up_name);
        mIvHeaderUpIcon1 = (ImageView) view.findViewById(R.id.iv_header_up_icon1);
        mIvHeaderUpIcon2 = (ImageView) view.findViewById(R.id.iv_header_up_icon2);
        mTvMaster = (TextView) view.findViewById(R.id.tv_master);
        mTvCenterContent01 = (TextView) view.findViewById(R.id.tv_center_content01);
        mTvCenterContent02 = (TextView) view.findViewById(R.id.tv_center_content02);
        mTvHeaderDownText01 = (TextView) view.findViewById(R.id.tv_header_down_text01);
    }

    public static MessageListViewHolder create(final Context mContext, ViewGroup parent) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_active_message_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MessageModel entry = (MessageModel) v.getTag();
                if (entry.getPortName().contains("wonder")) {
                    PostModel postModel = new PostModel();
                    postModel.setEredarName(entry.getEredarName());
                    postModel.setEredar(entry.getEredar());
                    postModel.setUserSmallHeadImg(entry.getUserSmallHeadImg());
                    postModel.setNike(entry.getNike());
                    postModel.setAreaId(entry.getId());
                    postModel.setAreaType(entry.getAreaType());
                    postModel.setType(entry.getPlateType());
                    postModel.setId(entry.getId());
                    postModel.setUserId(entry.getUserId());
                    postModel.setPortConnector(entry.getPortName());
                    postModel.setLoginTimes(entry.getLoginTimes());
                    postModel.setAuthority(entry.getAuthority());
                    Intent intent = new Intent(mContext, ReplayWonderActivity.class);
                    intent.putExtra("model", postModel);
                    mContext.startActivity(intent);
                } else {
                    PostModel postModel = new PostModel();
                    postModel.setEredarName(entry.getEredarName());
                    postModel.setEredar(entry.getEredar());
                    postModel.setUserSmallHeadImg(entry.getUserSmallHeadImg());
                    postModel.setNike(entry.getNike());
                    postModel.setAreaId(entry.getId());
                    postModel.setAreaType(entry.getAreaType());
                    postModel.setType(entry.getPlateType());
                    postModel.setId(entry.getId());
                    postModel.setUserId(entry.getUserId());
                    postModel.setPortConnector(entry.getPortName());
                    postModel.setLoginTimes(entry.getLoginTimes());
                    postModel.setAuthority(entry.getAuthority());
                    Intent intent = new Intent(mContext, ReplayAgeCityActivity.class);
                    intent.putExtra("model", postModel);
                    mContext.startActivity(intent);
                }
            }
        });
        return new MessageListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final MessageModel entry) {
        mCardview.setTag(entry);
        mTvCenterContent01.setText(entry.getReplyContext());
        mTvHeaderUpName.setText(entry.getNike());
        mTvHeaderDownText01.setText(entry.getDate());
        if (1 == entry.getEredar()) {
            mTvMaster.setText(entry.getEredarName());
        } else {
            try {
                switch (entry.getAuthority()) {
                    case 1:
                        mTvMaster.setText("系统管理员");
                        break;
                    case 2:
                        mTvMaster.setText("小编");
                        break;
                    case 3:
                        mTvMaster.setText("萌宝用户");
                        break;
                    case 4:
                        mTvMaster.setText("专家");
                        break;
                    case 5:
                        mTvMaster.setText("在线小编");
                        break;
                    case 6:
                        mTvMaster.setText("萌主");
                        break;
                    case 7:
                        mTvMaster.setText("实习萌主");
                        break;
                    default:
                        mTvMaster.setText("萌宝用户");
                        break;
                }

            } catch (Exception e) {

            }
        }

        // 设置我的主题
        mTvCenterContent02.setText("回复我的主题：" + entry.getTitle());
        // 加载头像
        PicassoTool.loadImage(context, entry.getUserSmallHeadImg(), mRivHead, true);
        long[] ranks = RankTools.gradeDispose(entry.getLoginTimes());
        mIvHeaderUpIcon1.setImageResource((int) ranks[1]);
        mIvHeaderUpIcon2.setImageResource((int) ranks[2]);
    }

}
