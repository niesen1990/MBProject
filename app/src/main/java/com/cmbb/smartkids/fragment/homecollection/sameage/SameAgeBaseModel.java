package com.cmbb.smartkids.fragment.homecollection.sameage;

/**
 * Created by N.Sun
 */
public class SameAgeBaseModel {

    // Context是包含一个数组
    private String code;
    private SameAgeCountModel context;

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
    public SameAgeCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(SameAgeCountModel context) {
        this.context = context;
    }

}
