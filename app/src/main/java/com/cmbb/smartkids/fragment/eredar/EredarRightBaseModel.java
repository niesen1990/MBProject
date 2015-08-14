package com.cmbb.smartkids.fragment.eredar;

import java.util.ArrayList;

public class EredarRightBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<EredarRightModel> context;

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
    public ArrayList<EredarRightModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<EredarRightModel> context) {
        this.context = context;
    }

}