package com.cmbb.smartkids.activity.tools;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.overlayutil.PoiOverlay;
import com.baidu.mapapi.search.core.CityInfo;
import com.baidu.mapapi.search.core.PoiInfo;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchOption;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.cmbb.smartkids.R;
import com.cmbb.smartkids.base.MActivity;

/**
 * Created by javon on 2015/7/30.
 */
public class ToolsMapActivity extends MActivity implements BDLocationListener, OnGetPoiSearchResultListener {
    private MapView mv;
    private LinearLayout llBottom;
    private TextView tvDel, tvHospital, tvKursaal, tvHotel;
    // 定位相关
    LocationClient mLocClient;
    private LatLng pt = null;
    //搜索周边
    private PoiSearch mPoiSearch = null;
    //地图相关
    private BaiduMap mBaiduMap;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_tools_map;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initView();
        initData();
        addListener();
    }

    private void initView() {
        mv = (MapView) findViewById(R.id.bmapView);
        mBaiduMap = mv.getMap();
        llBottom = (LinearLayout) findViewById(R.id.ll_map_bottom);
        tvDel = (TextView) findViewById(R.id.tv_map_del);
        tvHospital = (TextView) findViewById(R.id.tv_map_hospital);
        tvKursaal = (TextView) findViewById(R.id.tv_map_kursaal);
        tvHotel = (TextView) findViewById(R.id.tv_map_hotel);
    }

    private void initData() {
        // 开启定位图层
        mBaiduMap.setMyLocationEnabled(true);
        // 设置定位图层的配置（定位模式，是否允许方向信息，用户自定义定位图标）
        BitmapDescriptor mCurrentMarker = BitmapDescriptorFactory
                .fromResource(R.drawable.icon_geo);
        MyLocationConfiguration config = new MyLocationConfiguration(MyLocationConfiguration.LocationMode.NORMAL, true, mCurrentMarker);
        mBaiduMap.setMyLocationConfigeration(config);
        // 初始化搜索模块，注册搜索事件监听
        mPoiSearch = PoiSearch.newInstance();
        // 定位初始化
        mLocClient = new LocationClient(this);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(2000);
        mLocClient.setLocOption(option);
        mLocClient.start();

    }

    private void addListener() {
        tvDel.setOnClickListener(this);
        tvHospital.setOnClickListener(this);
        tvHotel.setOnClickListener(this);
        tvKursaal.setOnClickListener(this);
        //定位监听器
        mLocClient.registerLocationListener(this);
        //搜索监听
        mPoiSearch.setOnGetPoiSearchResultListener(this);
    }

    //百度地图监听事件
    //定位SDK回调
    @Override
    public void onReceiveLocation(BDLocation bdLocation) {
        if (bdLocation == null || mBaiduMap == null)
            return;
        pt = new LatLng(bdLocation.getLatitude(), bdLocation.getLongitude());
        MyLocationData locData = new MyLocationData.Builder()
                .accuracy(bdLocation.getRadius())
                        // 此处设置开发者获取到的方向信息，顺时针0-360
                .direction(100).latitude(bdLocation.getLatitude())
                .longitude(bdLocation.getLongitude()).build();
        if (mBaiduMap != null) {
            mBaiduMap.setMyLocationData(locData);
        }
        //        if (isFirstLoc) {
        //            isFirstLoc = false;
        LatLng ll = new LatLng(bdLocation.getLatitude(),
                bdLocation.getLongitude());
        MapStatusUpdate u = MapStatusUpdateFactory.newLatLng(ll);
        mBaiduMap.animateMapStatus(u);
        //        }

    }

    @Override
    public void onGetPoiResult(PoiResult poiResult) {
        if (poiResult == null
                || poiResult.error == SearchResult.ERRORNO.RESULT_NOT_FOUND) {
            showToast("未找到结果");
            return;
        }
        if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
            mBaiduMap.clear();
            PoiOverlay overlay = new MyPoiOverlay(mBaiduMap);
            mBaiduMap.setOnMarkerClickListener(overlay);
            overlay.setData(poiResult);
            overlay.addToMap();
            overlay.zoomToSpan();
            return;
        }
        if (poiResult.error == SearchResult.ERRORNO.AMBIGUOUS_KEYWORD) {
            // 当输入关键字在本市没有找到，但在其他城市找到时，返回包含该关键字信息的城市列表
            String strInfo = "在";
            for (CityInfo cityInfo : poiResult.getSuggestCityList()) {
                strInfo += cityInfo.city;
                strInfo += ",";
            }
            strInfo += "找到结果";
            showToast(strInfo);
        }

    }

    @Override
    public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {
        if (poiDetailResult.error == SearchResult.ERRORNO.NO_ERROR) {
            showToast(poiDetailResult.getName() + ": " + poiDetailResult.getAddress());
        }
    }


    //=====================================================================================================================


    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.tv_map_del:
                llBottom.setVisibility(View.GONE);
                break;
            case R.id.tv_map_hospital:
                startSearch("医院");
                break;
            case R.id.tv_map_hotel:
                startSearch("酒店");
                break;
            case R.id.tv_map_kursaal:
                startSearch("游乐场");
                break;
        }
    }

    /**
     * 影响搜索按钮点击事件
     *
     * @param search
     */
    public void startSearch(String search) {
        PoiNearbySearchOption option = new PoiNearbySearchOption();
        option.keyword(search);
        option.location(pt);
        option.radius(2000);
        mPoiSearch.searchNearby(option);
    }


    @Override
    protected void onDestroy() {
        // 退出时销毁定位
        mLocClient.stop();
        //退出销毁Poi检索
        mPoiSearch.destroy();
        mv.onDestroy();
        mBaiduMap = null;
        super.onDestroy();
    }

    private class MyPoiOverlay extends PoiOverlay {

        public MyPoiOverlay(BaiduMap baiduMap) {
            super(baiduMap);
        }

        @Override
        public boolean onPoiClick(int index) {
            super.onPoiClick(index);
            PoiInfo poi = getPoiResult().getAllPoi().get(index);
            // if (poi.hasCaterDetails) {
            mPoiSearch.searchPoiDetail((new PoiDetailSearchOption())
                    .poiUid(poi.uid));
            // }
            return true;
        }
    }

}
