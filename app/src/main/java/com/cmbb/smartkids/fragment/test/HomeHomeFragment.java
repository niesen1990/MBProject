package com.cmbb.smartkids.fragment.test;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MFragment;

/**
 * A simple {@link MFragment} subclass.
 */
public class HomeHomeFragment extends MFragment {

    private RecyclerView recyclerviewContent;

    public HomeHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_home, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerviewContent = (RecyclerView) view.findViewById(R.id.rv_master);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerviewContent.setLayoutManager(manager);
        recyclerviewContent.setItemAnimator(new DefaultItemAnimator());
        recyclerviewContent.setAdapter(new RecyclerViewHeadAdapter());
        FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.container_fragment, new EntryListFragment());
        fragmentTransaction.commit();
    }

    class RecyclerViewHeadAdapter extends RecyclerView.Adapter<ViewHolder> {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_home_list_headview_item, parent, false);
            return new ViewHolder(v);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.tvName.setText("MEIZU " + position);
            holder.tvMaster.setText("XIAMI " + position);
        }

        @Override
        public int getItemCount() {
            return 20;
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvMaster;

        public ViewHolder(View itemView) {
            super(itemView);
            tvMaster = (TextView) itemView.findViewById(R.id.tv_master);
        }
    }
}
