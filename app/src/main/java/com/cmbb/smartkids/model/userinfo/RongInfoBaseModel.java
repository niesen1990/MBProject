package com.cmbb.smartkids.model.userinfo;

public class RongInfoBaseModel {

    private String code;
    private RongInfoModel context;

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
    public RongInfoModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(RongInfoModel context) {
        this.context = context;
    }

}