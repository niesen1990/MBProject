package com.cmbb.smartkids.fragment.baby.babylist;

import android.content.Context;
import android.content.DialogInterface;
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
import com.cmbb.smartkids.activity.baby.MBabyDailyActivity;
import com.cmbb.smartkids.activity.post.PlateListActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MActivity;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.mengbottomsheets.BottomSheet;
import com.cmbb.smartkids.mengrecyclerview.actions.DataController;
import com.cmbb.smartkids.network.OkHttp;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.tools.log.Log;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class BabyListViewHolder extends RecyclerView.ViewHolder {

    private CardView cardview;
    private RelativeLayout rl01;
    private ImageView civHead;
    private TextView tvNick;
    private TextView activityBabyTvNick;
    private TextView tvBirthday;
    private RelativeLayout rl02;
    private TextView tv02DailyLeft;
    private TextView tvDailyValue;
    private TextView tv02DailyRight;
    private RelativeLayout rl03;
    private TextView tv02FriendLeft;
    private TextView tvFriendValue;
    private TextView tv02FriendRight;


    private BabyListViewHolder(View view) {
        super(view);
        cardview = (CardView) view.findViewById(R.id.cardview);
        rl01 = (RelativeLayout) view.findViewById(R.id.rl_01);
        civHead = (ImageView) view.findViewById(R.id.civ_head);
        tvNick = (TextView) view.findViewById(R.id.tv_nick);
        activityBabyTvNick = (TextView) view.findViewById(R.id.activity_baby_tv_nick);
        tvBirthday = (TextView) view.findViewById(R.id.tv_birthday);
        rl02 = (RelativeLayout) view.findViewById(R.id.rl_02);
        tv02DailyLeft = (TextView) view.findViewById(R.id.tv02_daily_left);
        tvDailyValue = (TextView) view.findViewById(R.id.tv_daily_value);
        tv02DailyRight = (TextView) view.findViewById(R.id.tv02_daily_right);
        rl03 = (RelativeLayout) view.findViewById(R.id.rl_03);
        tv02FriendLeft = (TextView) view.findViewById(R.id.tv02_friend_left);
        tvFriendValue = (TextView) view.findViewById(R.id.tv_friend_value);
        tv02FriendRight = (TextView) view.findViewById(R.id.tv02_friend_right);
    }

    public static BabyListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_baby_list_item, parent, false);
        return new BabyListViewHolder(v);
    }

    public void onBindViewHolder(final Context context, final DataController<BabyListModel> dataController, final BabyListModel entry, final int position) {
        cardview.setTag(entry);
        cardview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(final View v) {
                BottomSheet bottomSheet = new BottomSheet.Builder(context).title("操作").sheet(R.menu.menu_post_delete_list).listener(new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BabyListModel babyListModel = (BabyListModel) v.getTag();
                        Map<String, String> body = new HashMap<>();
                        body.put("token", MApplication.token);
                        body.put("id", babyListModel.getBabyId() + "");
                        OkHttp.asyncPost(Constants.Baby.DELETEBABY_URL, body, new Callback() {
                            @Override
                            public void onFailure(Request request, IOException e) {
                                try {
                                    ((MActivity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            ((MActivity) context).showToast("请检查网络");
                                        }
                                    });
                                } catch (Exception e1) {
                                }

                            }

                            @Override
                            public void onResponse(final Response response) throws IOException {
                                try {
                                    ((MActivity) context).runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            try {
                                                String result = response.body().string();
                                                Log.i("result", "result " + result);
                                                if (result.contains("1")) {
                                                    ((MActivity) context).showToast("删除成功");
                                                    dataController.remove(position);
                                                }
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }

                                        }
                                    });
                                } catch (Exception e1) {
                                }
                            }
                        });
                    }
                }).build();
                bottomSheet.show();
                return false;
            }
        });
        PicassoTool.loadImage(context, entry.getBabySmallHeadImg(), civHead, true);
        tvNick.setText(entry.getBabyNick());
        tvBirthday.setText(entry.getBrithday());
        tvDailyValue.setText(entry.getGrowingCount() + "");
        tvFriendValue.setText(1 + "");

        rl02.setTag(entry);
        rl02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, MBabyDailyActivity.class);
                intent.putExtra("model", (BabyListModel) (rl02.getTag()));
                context.startActivity(intent);
            }
        });
        // 同城
        rl03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlateListActivity.class);
                context.startActivity(intent);
            }
        });
    }
}
