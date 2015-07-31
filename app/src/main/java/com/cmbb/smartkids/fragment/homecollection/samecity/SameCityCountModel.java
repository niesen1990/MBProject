package com.cmbb.smartkids.fragment.homecollection.samecity;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class SameCityCountModel {
    private String attention;
    private String count;
    private ArrayList<SameCityModel> homeSameAgeList;

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
    public ArrayList<SameCityModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<SameCityModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }

}
