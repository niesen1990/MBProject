package com.cmbb.smartkids.fragment.postlist.city;

/**
 * Created by N.Sun
 */
public class SameCityPublishBaseModel {

    // Context是包含一个数组
    private String code;
    private SameCityPublishCountModel context;

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
    public SameCityPublishCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(SameCityPublishCountModel context) {
        this.context = context;
    }

}
