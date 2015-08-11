package com.cmbb.smartkids.fragment.master;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.tools.log.Log;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterFragment extends Fragment implements MasterTypeListViewHolder.OnTypeClickListener {

    private FrameLayout lvRight;
    private FrameLayout lvLeft;

    public MasterFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_master, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.i("master", "master = onViewCreated");
        lvRight = (FrameLayout) view.findViewById(R.id.lv_case_right);
        lvLeft = (FrameLayout) view.findViewById(R.id.lv_case_left);

    }

    @Override
    public void onResume() {
        super.onResume();
        getRetainInstance();
        Log.e("master", "master = onresume");
        Fragment fragmentLeft = new MasterTypeListFragment(false, this);
        getChildFragmentManager().beginTransaction().replace(R.id.lv_case_left, fragmentLeft).commit();
//        MasterTypeModel masterTypeModel = new MasterTypeModel("推荐", 0, 1);
        getChildFragmentManager().beginTransaction().replace(R.id.lv_case_right, new MasterDetailListFragment()).commit();
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.e("master", "master = onpause");
    }

    @Override
    public void onTypeClick(View view) {
        MasterTypeModel masterTypeModel = (MasterTypeModel) view.getTag();
        getChildFragmentManager().beginTransaction().replace(R.id.lv_case_right, new MasterDetailListFragment(false, masterTypeModel)).commit();
    }
}
