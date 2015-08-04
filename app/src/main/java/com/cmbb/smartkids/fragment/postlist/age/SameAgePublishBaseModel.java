package com.cmbb.smartkids.fragment.postlist.age;

/**
 * Created by N.Sun
 */
public class SameAgePublishBaseModel {

    // Context是包含一个数组
    private String code;
    private SameAgePublishCountModel context;

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
    public SameAgePublishCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(SameAgePublishCountModel context) {
        this.context = context;
    }

}
