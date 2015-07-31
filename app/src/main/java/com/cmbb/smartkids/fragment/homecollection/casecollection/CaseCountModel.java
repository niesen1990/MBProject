package com.cmbb.smartkids.fragment.homecollection.casecollection;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class CaseCountModel {
    private String attention;
    private String count;
    private ArrayList<CaseModel> homeSameAgeList;

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
    public ArrayList<CaseModel> getHomeSameAgeList() {
        return homeSameAgeList;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<CaseModel> HomeSameAge) {
        this.homeSameAgeList = HomeSameAge;
    }

}
