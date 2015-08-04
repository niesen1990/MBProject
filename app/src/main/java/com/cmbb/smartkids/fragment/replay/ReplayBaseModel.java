package com.cmbb.smartkids.fragment.replay;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class ReplayBaseModel {

    private String code;
    private ArrayList<ReplayModel> context;

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
    public ArrayList<ReplayModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<ReplayModel> context) {
        this.context = context;
    }

}
