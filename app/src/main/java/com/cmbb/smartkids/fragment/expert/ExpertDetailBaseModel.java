package com.cmbb.smartkids.fragment.expert;

import java.util.ArrayList;

public class ExpertDetailBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<ExpertDetailModel> context;

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
    public ArrayList<ExpertDetailModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<ExpertDetailModel> context) {
        this.context = context;
    }
}