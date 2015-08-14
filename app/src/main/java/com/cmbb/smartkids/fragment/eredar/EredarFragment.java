package com.cmbb.smartkids.fragment.eredar;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.cmbb.smartkids.R;
import com.cmbb.smartkids.activity.master.MasterAddActivity;
import com.cmbb.smartkids.base.Constants;
import com.cmbb.smartkids.base.MApplication;
import com.cmbb.smartkids.base.MFragment;
import com.cmbb.smartkids.network.OkHttp;
import com.google.gson.Gson;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class EredarFragment extends MFragment implements EredarLeftViewHolder.OnTypeClickListener {

    private RecyclerView recyclerviewLeft;
    private RecyclerView recyclerviewRight;
    private ContentLoadingProgressBar loadingBar;

    private EredarLeftAdapter mEredarLeftAdapter;
    private EredarRightAdapter mEredarRightAdapter;


    private List<EredarRightModel> mEredarRightModels = new ArrayList<>();
    private List<EredarTypeModel> mEredarTypeModels = new ArrayList<>();


    public EredarFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        mEredarLeftAdapter = new EredarLeftAdapter(getActivity(), this, mEredarTypeModels);
        mEredarRightAdapter = new EredarRightAdapter(getActivity(), mEredarRightModels);
        initData();
    }

    private void initData() {
        // Left
        Map<String, String> body = new HashMap<>();
        body.put("token", MApplication.token);
        body.put("type", 1 + "");
        OkHttp.asyncPost(Constants.Master.EREDARFINDTYPE_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.hide();
                        showToast(getString(R.string.meng_list_network_err));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.hide();
                    }
                });
                if (response.isSuccessful()) {
                    try {
                        String result = response.body().string();
                        if (TextUtils.isEmpty(result)) {
                            return;
                        }
                        Gson gson = new Gson();
                        EredarLeftBaseModel data = gson.fromJson(result, EredarLeftBaseModel.class);
                        final ArrayList<EredarTypeModel> masterTypeModels = new ArrayList<>();
                        masterTypeModels.add(new EredarTypeModel(0, "推荐", 1));
                        masterTypeModels.add(new EredarTypeModel(0, "关注", 0));
                        masterTypeModels.addAll(data.getContext());
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mEredarLeftAdapter.updateData(masterTypeModels);
                            }
                        });
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        // right
        Map<String, String> bodyRight = new HashMap<>();
        bodyRight.put("token", MApplication.token);
        bodyRight.put("recommend", 1 + "");
        OkHttp.asyncPost(Constants.Master.EREDARFINDATTENTIONUSER_URL, body, new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.hide();
                        showToast(getString(R.string.meng_list_network_err));
                    }
                });
            }

            @Override
            public void onResponse(Response response) throws IOException {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        loadingBar.hide();
                    }
                });

                if (response.isSuccessful()) {
                    String result = response.body().string();
                    if (TextUtils.isEmpty(result)) {
                        return;
                    }
                    Gson gson = new Gson();
                    final EredarRightBaseModel data = gson.fromJson(result, EredarRightBaseModel.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mEredarRightAdapter.updateData(data.getContext());
                        }
                    });
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_eredar, container, false);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_master_add_master, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_add_master:
                Intent intent = new Intent(getActivity(), MasterAddActivity.class);
                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerviewLeft = (RecyclerView) view.findViewById(R.id.recyclerview_left);
        recyclerviewRight = (RecyclerView) view.findViewById(R.id.recyclerview_right);
        recyclerviewLeft.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewLeft.setItemAnimator(new DefaultItemAnimator());
        recyclerviewLeft.setAdapter(mEredarLeftAdapter);
        recyclerviewRight.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerviewRight.setItemAnimator(new DefaultItemAnimator());
        recyclerviewRight.setAdapter(mEredarRightAdapter);
        loadingBar = (ContentLoadingProgressBar) view.findViewById(R.id.loading_bar);
        loadingBar.setVisibility(View.VISIBLE);
        loadingBar.show();
    }

    Callback callbackRight = new Callback() {
        @Override
        public void onFailure(Request request, IOException e) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    showToast(getString(R.string.meng_list_network_err));
                }
            });
        }

        @Override
        public void onResponse(Response response) throws IOException {
            if (response.isSuccessful()) {
                String result = response.body().string();
                if (TextUtils.isEmpty(result)) {
                    return;
                }
                Gson gson = new Gson();
                final EredarRightBaseModel data = gson.fromJson(result, EredarRightBaseModel.class);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mEredarRightAdapter.updateData(data.getContext());
                    }
                });
            }
        }
    };


    @Override
    public void onTypeClick(View view) {
        EredarTypeModel eredarTypeModel = (EredarTypeModel) view.getTag();
        Map<String, String> body = new HashMap<>();
        if (eredarTypeModel.getRec() == -1) {
            body.put("token", MApplication.token);
            body.put("type", eredarTypeModel.getEredarType() + "");
            OkHttp.asyncPost(Constants.Master.EREDARFINDBYTYPEUSER_URL, body, callbackRight);

        } else {
            body.put("token", MApplication.token);
            body.put("recommend", eredarTypeModel.getRec() + "");
            OkHttp.asyncPost(Constants.Master.EREDARFINDATTENTIONUSER_URL, body, callbackRight);
        }
    }
}
