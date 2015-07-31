package com.cmbb.smartkids.fragment.homecollection.wonderful;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class WonderCollectionCountModel {
    private String attention;
    private String count;
    private ArrayList<WonderCollectionModel> homeSameAgeList;

    /**
     * @return The presentation
     */
    public String getAttention() {
        return attention;
    }

    /**
     * @param presentation The presentation
     */
    public void setAttention(String presentation) {
        this.attention = presentation;
    }

    /**
     * @return The token
     */
    public String getCount() {
        return count;
    }

    /**
     * @param token The token
     */
    public void setCount(String token) {
        this.count = token;
    }

    /**
     * @return The HomeSameAge
     */
    public ArrayList<WonderCollectionModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<WonderCollectionModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }
}
