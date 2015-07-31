package com.cmbb.smartkids.fragment.homecollection.sameage;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class SameAgeCountModel {
    private String attention;
    private String count;
    private ArrayList<SameAgeModel> homeSameAgeList;

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
    public ArrayList<SameAgeModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<SameAgeModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }

}
