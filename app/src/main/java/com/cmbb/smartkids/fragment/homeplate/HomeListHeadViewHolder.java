package com.cmbb.smartkids.fragment.homeplate;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.user.UserActivity;
import com.cmbb.smartkids.fragment.platelist.PlateModel;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.tools.glide.GlideTool;

import java.util.List;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/29 下午2:37
 */
public class HomeListHeadViewHolder extends RecyclerView.ViewHolder {

    public final LinearLayout linearlayout_master;

    private HomeListHeadViewHolder(View view) {
        super(view);
        linearlayout_master = (LinearLayout) view.findViewById(R.id.linearlayout_master);
    }

    public static HomeListHeadViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_list_headview, parent, false);
        return new HomeListHeadViewHolder(v);
    }

    public void onBindViewHolder(Context context) {
        // 静态数据bind
    }

    // 动态数据bind
    public void onLoadFinishedHeadBindViewHolder(final Context context, DataController<PlateModel> mDataController) {
        linearlayout_master.removeAllViews();
        try {
            List<HomeEredarModel> listEredar = ((HomeListProvider) mDataController).baseData.getContext().getEredarList();
            for (int i = 0; i < listEredar.size(); i++) {
                final View view = LayoutInflater.from(context).inflate(R.layout.activity_home_list_headview_item, null, false);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.setMargins(23, 12, 23, 12);

            /*TextView tvName = (TextView) view.findViewById(R.id.tv_name);
            tvName.setText(listEredar.get(i).getNike());*/
                TextView tvMaster = (TextView) view.findViewById(R.id.tv_master);
                tvMaster.setText(listEredar.get(i).getEredarName() + "达人");
                GlideTool.loadImage(context, listEredar.get(i).getUserSmallHeadImg(), (ImageView) view.findViewById(R.id.civ_head), true);
                view.setLayoutParams(params);
                view.setTag(listEredar.get(i));
                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, UserActivity.class);
                        intent.putExtra("user", (HomeEredarModel) (view.getTag()));
                        context.startActivity(intent);
                    }
                });
                linearlayout_master.addView(view);
            }
        } catch (NullPointerException e) {

        }

    }


}
