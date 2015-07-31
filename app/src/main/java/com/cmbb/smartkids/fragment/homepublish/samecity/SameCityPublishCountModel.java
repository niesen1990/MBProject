package com.cmbb.smartkids.fragment.homepublish.samecity;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class SameCityPublishCountModel {
    private String attention;
    private String count;
    private ArrayList<SameCityPublishModel> homeSameAgeList;

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
    public ArrayList<SameCityPublishModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<SameCityPublishModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }

}
