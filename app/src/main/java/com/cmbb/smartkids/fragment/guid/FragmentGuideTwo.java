package com.cmbb.smartkids.fragment.guid;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MFragment;

/**
 * 项目名称：SmartKids
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/5/13 16:15
 * 修改人：N.Sun
 * 修改时间：2015/5/13 16:15
 * 修改备注：
 */
public class FragmentGuideTwo extends MFragment {
    private static final String TAG = FragmentGuideTwo.class.getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guide_image, container, false);
        ImageView iv_guide_bac = (ImageView) rootView.findViewById(R.id.iv_guide_bac);
        iv_guide_bac.setImageResource(R.drawable.guide_bac_02);
        return rootView;
    }
}
