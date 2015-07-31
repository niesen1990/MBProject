package com.cmbb.smartkids.fragment.homecollection.wonderful;

/**
 * Created by N.Sun
 */
public class WonderCollectionBaseModel {

    // Context是包含一个数组
    private String code;
    private WonderCollectionCountModel context;

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
    public WonderCollectionCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(WonderCollectionCountModel context) {
        this.context = context;
    }

}
