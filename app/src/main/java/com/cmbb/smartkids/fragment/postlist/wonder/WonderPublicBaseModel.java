package com.cmbb.smartkids.fragment.postlist.wonder;

/**
 * Created by N.Sun
 */
public class WonderPublicBaseModel {

    // Context是包含一个数组
    private String code;
    private WonderPublicCountModel context;

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
    public WonderPublicCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(WonderPublicCountModel context) {
        this.context = context;
    }

}
