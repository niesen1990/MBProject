package com.cmbb.smartkids.fragment.caselist;

import java.util.ArrayList;

public class CaseDetailListBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<CaseDetailListModel> context;

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
    public ArrayList<CaseDetailListModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<CaseDetailListModel> context) {
        this.context = context;
    }


}