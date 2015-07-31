package com.cmbb.smartkids.fragment.usercenter.wonderful;

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
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/7/1 11:00
 */
public class WonderPublicListViewHolder extends RecyclerView.ViewHolder {

    private final CardView cardview;
    private final ImageView rivHead;
    private final RelativeLayout rlHeaderUp;
    private final TextView tvHeaderUpName;
    private final TextView tvHeaderDownText02;
    private final TextView tvMaster;
    private final TextView tvCenterContent01;
    private final TextView tvCenterContent02;
    private final ImageView psivSmallIcon01;
    private final ImageView psivSmallIcon02;
    private final ImageView psivSmallIcon03;
    private final TextView tvHeaderDownText01;


    private WonderPublicListViewHolder(View view) {
        super(view);
        cardview = (CardView) view.findViewById(R.id.cardview);
        rivHead = (ImageView) view.findViewById(R.id.riv_head);
        rlHeaderUp = (RelativeLayout) view.findViewById(R.id.rl_header_up);
        tvHeaderUpName = (TextView) view.findViewById(R.id.tv_header_up_name);
        tvHeaderDownText02 = (TextView) view.findViewById(R.id.tv_header_down_text02);
        tvMaster = (TextView) view.findViewById(R.id.tv_master);
        tvCenterContent01 = (TextView) view.findViewById(R.id.tv_center_content01);
        tvCenterContent02 = (TextView) view.findViewById(R.id.tv_center_content02);
        psivSmallIcon01 = (ImageView) view.findViewById(R.id.psiv_small_icon01);
        psivSmallIcon02 = (ImageView) view.findViewById(R.id.psiv_small_icon02);
        psivSmallIcon03 = (ImageView) view.findViewById(R.id.psiv_small_icon03);
        tvHeaderDownText01 = (TextView) view.findViewById(R.id.tv_header_down_text01);

    }

    public static WonderPublicListViewHolder create(final Context context, ViewGroup parent) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_home_collection_list_item, parent, false);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //HomeSameAge homeSameAge = (HomeSameAge) v.getTag();
            }
        });
        return new WonderPublicListViewHolder(v);
    }

    public void onBindViewHolder(Context context, final WonderPublicModel entry) {
        // head text
        tvHeaderUpName.setText(entry.getNike());
        if (!TextUtils.isEmpty(entry.getEredarName())) {
            tvMaster.setText(entry.getEredarName() + "达人");
        } else {
            try {
                switch (entry.getAuthority()) {
                    case 1:
                        tvMaster.setText(" 系统管理员");
                        break;
                    case 2:
                        tvMaster.setText(" 小编");
                        break;
                    case 3:
                        tvMaster.setText(" 普通用户");
                        break;
                    case 4:
                        tvMaster.setText(" 专家");
                        break;
                    case 5:
                        tvMaster.setText(" 在线小编");
                        break;
                    default:
                        tvMaster.setText(" 普通用户");
                        break;
                }

            } catch (Exception e) {

            }
        }
        tvHeaderDownText01.setText(entry.getDate());
        tvHeaderDownText02.setText(entry.getRelpys() + "");
        // content
        tvCenterContent01.setText(entry.getTitle());
        String context2 = entry.getContext();
        if (TextUtils.isEmpty(context2)) {
            tvCenterContent02.setVisibility(View.GONE);
        } else {
            tvCenterContent02.setVisibility(View.VISIBLE);
            tvCenterContent02.setText(context2);
        }
        // 显示头像
        String img_head = entry.getUserSmallHeadImg();
        if (!TextUtils.isEmpty(img_head)) {
            GlideTool.loadImage(context, img_head, rivHead, true);
        } else {
            rivHead.setImageResource(R.drawable.ic_loading);
        }
        // 显示图片
        String img_all = entry.getSmallImg();
        if (!TextUtils.isEmpty(img_all)) {
            // 判断图片的个数
            if (img_all.contains("#")) {
                String[] imgs = img_all.split("\\^#\\^");
                for (int i = 0; i < imgs.length; i++) {
                    switch (i) {
                        case 0:
                            psivSmallIcon01.setVisibility(View.VISIBLE);
                            String[] urls1 = imgs[i].split(",");
                            //循环解析是否包含smallImage
                            for (int k = 0; k < urls1.length; k++) {
                                if (urls1[k].contains("smallImage")) {
                                    psivSmallIcon01.setVisibility(View.VISIBLE);
                                    GlideTool.loadImage(context, urls1[k], psivSmallIcon01, false);
                                } else {
                                    if (imgs[i].contains("smallImage")) {
                                    } else {
                                        psivSmallIcon01.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                        case 1:
                            psivSmallIcon02.setVisibility(View.VISIBLE);
                            String[] urls2 = imgs[i].split(",");
                            for (int k = 0; k < urls2.length; k++) {
                                if (urls2[k].contains("smallImage")) {
                                    psivSmallIcon02.setVisibility(View.VISIBLE);
                                    GlideTool.loadImage(context, urls2[k], psivSmallIcon02, false);
                                } else {
                                    if (imgs[i].contains("smallImage")) {
                                    } else {
                                        psivSmallIcon02.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                        case 2:
                            psivSmallIcon03.setVisibility(View.VISIBLE);
                            String[] urls3 = imgs[i].split(",");
                            for (int k = 0; k < urls3.length; k++) {
                                if (urls3[k].contains("smallImage")) {
                                    psivSmallIcon03.setVisibility(View.VISIBLE);
                                    GlideTool.loadImage(context, urls3[k], psivSmallIcon03, false);
                                } else {
                                    if (imgs[i].contains("smallImage")) {
                                    } else {
                                        psivSmallIcon03.setVisibility(View.GONE);
                                    }
                                }
                            }
                            break;
                    }
                }

                // 判断显示图片的数量
                if (imgs.length == 2) {
                    psivSmallIcon01.setVisibility(View.VISIBLE);
                    psivSmallIcon02.setVisibility(View.VISIBLE);
                    psivSmallIcon03.setVisibility(View.INVISIBLE);
                }

            } else {
                psivSmallIcon01.setVisibility(View.VISIBLE);
                psivSmallIcon02.setVisibility(View.VISIBLE);
                psivSmallIcon03.setVisibility(View.VISIBLE);
                String[] urls1 = img_all.split(",");
                for (int i = 0; i < urls1.length; i++) {
                    if (urls1[i].contains("smallImage")) {
                        psivSmallIcon01.setVisibility(View.VISIBLE);
                        GlideTool.loadImage(context, urls1[i], psivSmallIcon01, false);
                    } else {
                        if (img_all.contains("smallImage")) {
                        } else {
                            psivSmallIcon01.setVisibility(View.GONE);
                        }
                    }
                }
            }
        } else {
            psivSmallIcon01.setVisibility(View.GONE);
            psivSmallIcon02.setVisibility(View.GONE);
            psivSmallIcon03.setVisibility(View.GONE);
        }
    }

}
