package com.cmbb.smartkids.fragment.homecollection.casecollection;

/**
 * Created by N.Sun
 */
public class CaseBaseModel {

    // Context是包含一个数组
    private String code;
    private CaseCountModel context;

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
    public CaseCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(CaseCountModel context) {
        this.context = context;
    }

}
