package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.cmbb.smartkids.model.photo.PhotoAdd;

import java.util.ArrayList;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/7 上午11:30
 */
public class PhotoAddAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;

    private ArrayList<PhotoAdd> photoContent = new ArrayList<>();

    public ArrayList<PhotoAdd> getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(ArrayList<PhotoAdd> photoContent) {
        this.photoContent = photoContent;
    }


    public void setPhotoUrls(ArrayList<String> photoUrls) {
        if (null == photoUrls || photoUrls.size() == 0) {
            return;
        } else {
            photoContent.clear();
            for (int i = 0; i < photoUrls.size(); i++) {
                photoContent.add(new PhotoAdd(photoUrls.get(i), ""));
            }
        }
    }


    public PhotoAddAdapter(Context context, ArrayList<PhotoAdd> data) {
        mContext = context;
        this.photoContent = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return PostAddViewHolder.create(mContext, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((PostAddViewHolder) holder).onBindViewHolder(mContext, photoContent, position);
    }

    @Override
    public int getItemCount() {
        return photoContent.size();
    }
}
