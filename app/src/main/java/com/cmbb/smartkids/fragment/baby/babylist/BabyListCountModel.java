package com.cmbb.smartkids.fragment.baby.babylist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class BabyListCountModel {

    private Integer attention;
    private Integer babyCount;
    private List<BabyListModel> list = new ArrayList<BabyListModel>();

    /**
     * @return The attention
     */
    public Integer getAttention() {
        return attention;
    }

    /**
     * @param attention The attention
     */
    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    /**
     * @return The babyCount
     */
    public Integer getBabyCount() {
        return babyCount;
    }

    /**
     * @param babyCount The babyCount
     */
    public void setBabyCount(Integer babyCount) {
        this.babyCount = babyCount;
    }

    /**
     * @return The list
     */
    public List<BabyListModel> getList() {
        return list;
    }

    /**
     * @param list The list
     */
    public void setList(List<BabyListModel> list) {
        this.list = list;
    }

}
