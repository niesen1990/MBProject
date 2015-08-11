package com.cmbb.smartkids.fragment.master;

import java.util.ArrayList;

public class EredarDetailBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<MasterDetailModel> context;

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
    public ArrayList<MasterDetailModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<MasterDetailModel> context) {
        this.context = context;
    }

}