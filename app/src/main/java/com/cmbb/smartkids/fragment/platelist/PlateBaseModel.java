package com.cmbb.smartkids.fragment.platelist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class PlateBaseModel {
    private String code;

    private List<PlateModel> context = new ArrayList<PlateModel>();


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
    public List<PlateModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(List<PlateModel> context) {
        this.context = context;
    }


}
