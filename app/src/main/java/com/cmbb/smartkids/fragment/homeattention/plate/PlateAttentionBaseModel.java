package com.cmbb.smartkids.fragment.homeattention.plate;

import java.util.ArrayList;

public class PlateAttentionBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<PlateAttentionModel> context;

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
    public ArrayList<PlateAttentionModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<PlateAttentionModel> context) {
        this.context = context;
    }


}