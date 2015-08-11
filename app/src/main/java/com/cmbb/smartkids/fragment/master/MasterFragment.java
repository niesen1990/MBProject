package com.cmbb.smartkids.fragment.master;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.cmbb.smartkids.R;

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
        lvRight = (FrameLayout) view.findViewById(R.id.lv_case_right);
        lvLeft = (FrameLayout) view.findViewById(R.id.lv_case_left);
        Fragment fragmentLeft = new MasterTypeListFragment(false, this);
        getChildFragmentManager().beginTransaction().add(R.id.lv_case_left, fragmentLeft).commit();
        MasterTypeModel masterTypeModel = new MasterTypeModel("推荐", 0, 1);
        getChildFragmentManager().beginTransaction().add(R.id.lv_case_right, new MasterDetailListFragment(false, masterTypeModel)).commit();
    }

    @Override
    public void onTypeClick(View view) {
        MasterTypeModel masterTypeModel = (MasterTypeModel) view.getTag();
        getChildFragmentManager().beginTransaction().replace(R.id.lv_case_right, new MasterDetailListFragment(false, masterTypeModel)).commit();
    }
}
