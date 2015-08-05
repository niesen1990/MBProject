package com.cmbb.smartkids.model.userinfo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class ExpertServerCountModel {

    private List<ExpertServeModel> expertList = new ArrayList<ExpertServeModel>();
    private List<ExpertServeModel> serveList = new ArrayList<ExpertServeModel>();

    /**
     * @return The attention
     */
    public List<ExpertServeModel> getExpertList() {
        return expertList;
    }

    /**
     * @param attention The attention
     */
    public void setExpertList(List<ExpertServeModel> attention) {
        this.expertList = attention;
    }

    /**
     * @return The babyCount
     */
    public List<ExpertServeModel> getServeList() {
        return serveList;
    }

    /**
     * @param babyCount The babyCount
     */
    public void setServeList(List<ExpertServeModel> babyCount) {
        this.serveList = babyCount;
    }

}
