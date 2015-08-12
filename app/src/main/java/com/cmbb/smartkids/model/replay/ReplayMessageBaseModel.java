package com.cmbb.smartkids.model.replay;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class ReplayMessageBaseModel {

    private String code;
    private ArrayList<ReplayMessageModel> context;

    /**
     * @return The code
     */
    public String getCode() {
        return code;
    }

    /**
     * @param code The code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * @return The context
     */
    public ArrayList<ReplayMessageModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<ReplayMessageModel> context) {
        this.context = context;
    }

}
