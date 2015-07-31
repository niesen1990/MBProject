package com.cmbb.smartkids.fragment.homecollection.samecity;

/**
 * Created by N.Sun
 */
public class SameCityBaseModel {

    // Context是包含一个数组
    private String code;
    private SameCityCountModel context;

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
    public SameCityCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(SameCityCountModel context) {
        this.context = context;
    }

}
