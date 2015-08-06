package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.replay.ReplayAgeCityActivity;
import com.cmbb.smartkids.activity.replay.ReplayWonderActivity;
import com.cmbb.smartkids.fragment.homeplate.HomeBannerModel;
import com.cmbb.smartkids.fragment.postlist.PostModel;
import com.cmbb.smartkids.tools.glide.GlideTool;
import com.cmbb.smartkids.widget.autoscroll.RecyclingPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午8:09
 */
public class HomeAutoScrollBannerAdapter extends RecyclingPagerAdapter {

    private List<HomeBannerModel> data = new ArrayList<HomeBannerModel>();
    private Context mContext;

    public HomeAutoScrollBannerAdapter(Context context) {
        mContext = context;
    }

    public void setData(List data) {
        if (null != data && data.size() > 0) {
            this.data.clear();
            this.data.addAll(data);
        }
        notifyDataSetChanged();
    }


    @Override
    public View getView(final int position, View view, final ViewGroup container) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            LayoutInflater inflater = (LayoutInflater) container.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.activity_home_banner_item, container, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        }
        holder.iv.setTag(R.id.img, data.get(position));
        GlideTool.loadImage(mContext, data.get(position).getBigImg(), holder.iv, false);
        holder.iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HomeBannerModel homeBannerModel = (HomeBannerModel) v.getTag(R.id.img);
                if (homeBannerModel.getPortConnector().contains("wonder")) {
                    PostModel postModel = new PostModel();
                    postModel.setEredarName(homeBannerModel.getEredarName());
                    postModel.setEredar(homeBannerModel.getEredar());
                    postModel.setUserSmallHeadImg(homeBannerModel.getUserSmallHeadImg());
                    postModel.setNike(homeBannerModel.getNike());
                    postModel.setAreaId(homeBannerModel.getId());
                    postModel.setAreaType(homeBannerModel.getAreaType());
                    postModel.setType(homeBannerModel.getType());
                    postModel.setId(homeBannerModel.getId());
                    postModel.setUserId(homeBannerModel.getUserId());
                    postModel.setPortConnector(homeBannerModel.getPortConnector());
                    postModel.setStore(homeBannerModel.getStore());
                    postModel.setLoginTimes(homeBannerModel.getLoginTimes());
                    postModel.setAuthority(homeBannerModel.getAuthority());
                    Intent intent = new Intent(mContext, ReplayWonderActivity.class);
                    intent.putExtra("model", postModel);
                    mContext.startActivity(intent);
                } else {
                    PostModel postModel = new PostModel();
                    postModel.setEredarName(homeBannerModel.getEredarName());
                    postModel.setEredar(homeBannerModel.getEredar());
                    postModel.setUserSmallHeadImg(homeBannerModel.getUserSmallHeadImg());
                    postModel.setNike(homeBannerModel.getNike());
                    postModel.setAreaId(homeBannerModel.getId());
                    postModel.setAreaType(homeBannerModel.getAreaType());
                    postModel.setType(homeBannerModel.getType());
                    postModel.setId(homeBannerModel.getId());
                    postModel.setUserId(homeBannerModel.getUserId());
                    postModel.setPortConnector(homeBannerModel.getPortConnector());
                    postModel.setStore(homeBannerModel.getStore());
                    postModel.setLoginTimes(homeBannerModel.getLoginTimes());
                    postModel.setAuthority(homeBannerModel.getAuthority());
                    Intent intent = new Intent(mContext, ReplayAgeCityActivity.class);
                    intent.putExtra("model", postModel);
                    mContext.startActivity(intent);
                }

            }
        });
        return view;
    }

    static final class ViewHolder {
        public ImageView iv;

        public ViewHolder(View view) {
            iv = (ImageView) view.findViewById(R.id.img);
        }
    }

    @Override
    public int getCount() {
        return data.size();
    }
}
