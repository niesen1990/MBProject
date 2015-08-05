package com.cmbb.smartkids.fragment.active;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class ActiveCountModel {

    private List<ActiveModelOne> expertList = new ArrayList<ActiveModelOne>();
    private List<ActiveModelOne> serveList = new ArrayList<ActiveModelOne>();

    /**
     * @return The attention
     */
    public List<ActiveModelOne> getExpertList() {
        return expertList;
    }

    /**
     * @param attention The attention
     */
    public void setExpertList(List<ActiveModelOne> attention) {
        this.expertList = attention;
    }

    /**
     * @return The babyCount
     */
    public List<ActiveModelOne> getServeList() {
        return serveList;
    }

    /**
     * @param babyCount The babyCount
     */
    public void setServeList(List<ActiveModelOne> babyCount) {
        this.serveList = babyCount;
    }

}
