package com.cmbb.smartkids.photopicker.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cmbb.smartkids.photopicker.R;
import com.cmbb.smartkids.photopicker.entity.PhotoDirectory;

import java.util.ArrayList;
import java.util.List;

/**
 * 项目名称：MengBao
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/21 上午11:12
 */
public class PopupDirectoryListAdapter extends BaseAdapter {

    private Context context;

    private List<PhotoDirectory> directories = new ArrayList<>();

    private LayoutInflater mLayoutInflater;


    public PopupDirectoryListAdapter(Context context, List<PhotoDirectory> directories) {
        this.context = context;
        this.directories = directories;
        mLayoutInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return directories.size();
    }


    @Override
    public PhotoDirectory getItem(int position) {
        return directories.get(position);
    }


    @Override
    public long getItemId(int position) {
        return directories.get(position).hashCode();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.mpicker_item_directory, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.bindData(directories.get(position));

        return convertView;
    }


    private class ViewHolder {

        public ImageView ivCover;
        public TextView tvName;

        public ViewHolder(View rootView) {
            ivCover = (ImageView) rootView.findViewById(R.id.iv_dir_cover);
            tvName = (TextView) rootView.findViewById(R.id.tv_dir_name);
        }

        public void bindData(PhotoDirectory directory) {
            Glide.with(context)
                    .load(directory.getCoverPath())
                    .thumbnail(0.1f)
                    .into(ivCover);

            //Picasso.with(context).load(directory.getCoverPath()).into(ivCover);

            /*Picasso.with(context).load(new File(directory.getCoverPath())).fit()
                    .into(ivCover);*/
            tvName.setText(directory.getName());
        }
    }
}
