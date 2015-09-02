package com.cmbb.smartkids.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.fragment.active.CustomChildViewHolder;
import com.cmbb.smartkids.fragment.active.CustomParentViewHolder;
import com.cmbb.smartkids.fragment.homeattention.user.UserAttentionModel;
import com.cmbb.smartkids.model.active.CustomParentObject;
import com.cmbb.smartkids.rong.RongInfoContext;
import com.cmbb.smartkids.tools.picasso.PicassoTool;
import com.cmbb.smartkids.tools.log.Log;

import java.util.List;

import io.rong.imkit.RongIM;

/**
 * An example custom implementation of the ExpandableRecyclerAdapter.
 *
 * @author Ryan Brooks
 * @version 1.0
 * @since 5/27/2015
 */
public class ActiveExpandableAdapter extends ExpandableRecyclerAdapter<CustomParentViewHolder, CustomChildViewHolder> {
    private final String TAG = this.getClass().getSimpleName();

    private Context mContext;

    private LayoutInflater mInflater;

    /**
     * Public primary constructor.
     *
     * @param context        for inflating views
     * @param parentItemList the list of parent items to be displayed in the RecyclerView
     */
    public ActiveExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Public secondary constructor. This constructor adds the ability to add a custom triggering
     * view when the adapter is created without having to set it later. This is here for demo
     * purposes.
     *
     * @param context               for inflating views
     * @param parentItemList        the list of parent items to be displayed in the RecyclerView
     * @param customClickableViewId the id of the view that triggers the expansion
     */
    public ActiveExpandableAdapter(Context context, List<ParentObject> parentItemList, int customClickableViewId) {
        super(context, parentItemList, customClickableViewId);
        mInflater = LayoutInflater.from(context);
    }

    /**
     * Public secondary constructor. This constructor adds the ability to add a custom triggering
     * view and a custom animation duration when the adapter is created without having to set them
     * later. This is here for demo purposes.
     *
     * @param context               for inflating views
     * @param parentItemList        the list of parent items to be displayed in the RecyclerView
     * @param customClickableViewId the id of the view that triggers the expansion
     * @param animationDuration     the duration (in ms) of the rotation animation
     */
    public ActiveExpandableAdapter(Context context, List<ParentObject> parentItemList, int customClickableViewId, long animationDuration) {
        super(context, parentItemList, customClickableViewId, animationDuration);
        mInflater = LayoutInflater.from(context);
    }

    /**
     * OnCreateViewHolder implementation for parent items. The desired ParentViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @Override
    public CustomParentViewHolder onCreateParentViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.recycler_item_layout_parent, parent, false);
        return new CustomParentViewHolder(view);
    }

    /**
     * OnCreateViewHolder implementation for child items. The desired ChildViewHolder should
     * be inflated here
     *
     * @param parent for inflating the View
     * @return the user's custom parent ViewHolder that must extend ParentViewHolder
     */
    @Override
    public CustomChildViewHolder onCreateChildViewHolder(ViewGroup parent) {
        View view = mInflater.inflate(R.layout.recycler_item_layout_child, parent, false);
        return new CustomChildViewHolder(view);
    }

    /**
     * OnBindViewHolder implementation for parent items. Any data or view modifications of the
     * parent view should be performed here.
     *
     * @param parentViewHolder the ViewHolder of the parent item created in OnCreateParentViewHolder
     * @param position         the position in the RecyclerView of the item
     */
    @Override
    public void onBindParentViewHolder(CustomParentViewHolder parentViewHolder, int position, Object parentObject) {
        CustomParentObject customParentObject = (CustomParentObject) parentObject;
        parentViewHolder.dataText.setText(customParentObject.getParentText());
        if (customParentObject.getUnRead() == 0) {
            parentViewHolder.tvUnread.setText("");
            parentViewHolder.tvUnread.setVisibility(View.INVISIBLE);
        } else {
            parentViewHolder.tvUnread.setText(customParentObject.getUnRead() + "");
            parentViewHolder.tvUnread.setVisibility(View.VISIBLE);
        }
    }

    /**
     * OnBindViewHolder implementation for child items. Any data or view modifications of the
     * child view should be performed here.
     *
     * @param childViewHolder the ViewHolder of the child item created in OnCreateChildViewHolder
     * @param position        the position in the RecyclerView of the item
     */
    @Override
    public void onBindChildViewHolder(CustomChildViewHolder childViewHolder, int position, Object childObject) {
        UserAttentionModel userAttentionModel = (UserAttentionModel) childObject;
        PicassoTool.loadImage(mContext, userAttentionModel.getUserSmallHeadImg(), childViewHolder.mIvHead, true);

        if (!TextUtils.isEmpty(userAttentionModel.getEredarName())) {
            childViewHolder.mTvContent.setText(userAttentionModel.getEredarName() + "达人");
        } else {
            childViewHolder.mTvContent.setText(userAttentionModel.getContentLast());
        }
        childViewHolder.mTvConstellation.setText(userAttentionModel.getNike());
        if (userAttentionModel.getUnRead() > 0) {
            childViewHolder.ivUnread.setText(userAttentionModel.getUnRead() + "");
            childViewHolder.ivUnread.setVisibility(View.VISIBLE);
        } else {
            childViewHolder.ivUnread.setVisibility(View.INVISIBLE);
        }

        if (TextUtils.isEmpty(userAttentionModel.getTime())) {
            childViewHolder.tvTime.setVisibility(View.INVISIBLE);
        } else {
            childViewHolder.tvTime.setVisibility(View.VISIBLE);
            childViewHolder.tvTime.setText(userAttentionModel.getTime());
        }

        childViewHolder.rvRoot.setTag(userAttentionModel);
        childViewHolder.rvRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserAttentionModel userModel = (UserAttentionModel) v.getTag();
                if (RongIM.getInstance() != null && RongInfoContext.getInstance() != null) {
                    Log.d("tokenRong", "rong token = " + userModel.getAttentionToken());
                    Log.d("tokenRong", "rong name = " + userModel.getNike());
                    if (userModel.isServer()) {
                        RongIM.getInstance().startCustomerServiceChat(mContext, userModel.getAttentionToken(), userModel.getNike());
                    } else {
                        RongIM.getInstance().startPrivateChat(mContext, userModel.getAttentionToken(), userModel.getNike());
                    }
                }
            }
        });
    }
}