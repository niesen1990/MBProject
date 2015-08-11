package com.cmbb.smartkids.fragment.master;

import java.util.ArrayList;

public class EredarTypeBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<MasterTypeModel> context;

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
    public ArrayList<MasterTypeModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<MasterTypeModel> context) {
        this.context = context;
    }

}