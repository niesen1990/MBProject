package com.cmbb.smartkids.model.userinfo;

public class UserInfoBaseModel {

    private String code;
    private UserInfoDetailModel context;

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
    public UserInfoDetailModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(UserInfoDetailModel context) {
        this.context = context;
    }

}