package com.cmbb.smartkids.fragment.active;

public class ActiveBaseModel {

    private String code;
    private ActiveCountModel context;

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
    public ActiveCountModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ActiveCountModel context) {
        this.context = context;
    }

}