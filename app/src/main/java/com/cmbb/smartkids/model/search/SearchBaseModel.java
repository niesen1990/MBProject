package com.cmbb.smartkids.model.search;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class SearchBaseModel {

    private String code;
    private ArrayList<SearchModel> context;


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
    public ArrayList<SearchModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<SearchModel> context) {
        this.context = context;
    }
}
