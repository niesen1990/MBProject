package com.cmbb.smartkids.fragment.homeplate;

import com.cmbb.smartkids.fragment.platelist.PlateModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class HomePageListModel {
    private List<HomeEredarModel> eredarList = new ArrayList<HomeEredarModel>();
    private List<HomeBannerModel> homeImgList = new ArrayList<HomeBannerModel>();
    private List<PlateModel> plateList = new ArrayList<PlateModel>();

    /**
     * @return The eredarList
     */
    public List<HomeEredarModel> getEredarList() {
        return eredarList;
    }

    /**
     * @param eredarList The eredarList
     */
    public void setEredarList(List<HomeEredarModel> eredarList) {
        this.eredarList = eredarList;
    }

    /**
     * @return The homeImgList
     */
    public List<HomeBannerModel> getHomeImgList() {
        return homeImgList;
    }

    /**
     * @param homeImgList The homeImgList
     */
    public void setHomeImgList(List<HomeBannerModel> homeImgList) {
        this.homeImgList = homeImgList;
    }

    /**
     * @return The plateList
     */
    public List<PlateModel> getPlateList() {
        return plateList;
    }

    /**
     * @param plateList The plateList
     */
    public void setPlateList(List<PlateModel> plateList) {
        this.plateList = plateList;
    }


}
