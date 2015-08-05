package com.cmbb.smartkids.model.userinfo;

public class ExpertServeBaseModel {

    private String code;
    private ExpertServerCountModel context;

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
    public ExpertServerCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ExpertServerCountModel context) {
        this.context = context;
    }


}