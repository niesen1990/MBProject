package com.cmbb.smartkids.fragment.homepublish.sameage;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class SameAgePublishCountModel {
    private String attention;
    private String count;
    private ArrayList<SameAgePublishModel> homeSameAgeList;

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
    public ArrayList<SameAgePublishModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<SameAgePublishModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }

}
