package com.cmbb.smartkids.fragment.caselist;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */

public class CaseTypeBaseModel {
    private String code;
    private ArrayList<CaseTypeModel> context;


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
    public ArrayList<CaseTypeModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<CaseTypeModel> context) {
        this.context = context;
    }
}
