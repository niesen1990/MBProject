package com.cmbb.smartkids.fragment.guid;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.login.LoginActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MFragment;
import com.cmbb.smartkids.tools.sp.SPCache;

/**
 * 项目名称：SmartKids
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：2015/5/13 16:15
 * 修改人：N.Sun
 * 修改时间：2015/5/13 16:15
 * 修改备注：
 */
public class FragmentGuideThree extends MFragment {
    private static final String TAG = FragmentGuideThree.class.getSimpleName();
    TextView tv_next;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_guide_last, container, false);
        ImageView iv_guide_bac = (ImageView) rootView.findViewById(R.id.iv_guide_bac);
        iv_guide_bac.setImageResource(R.drawable.guide_bac_03);
        tv_next = (TextView) rootView.findViewById(R.id.tv_next);
        tv_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //标识第一次进入程序
                SPCache.putBoolean(Constants.SharePreference.IS_FIRST_INTO, false);
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });
        return rootView;
    }
}
