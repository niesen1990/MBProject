package com.cmbb.smartkids.model.userinfo;

public class LoginBaseModel {

    private String code;
    private LoginModel context;

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
    public LoginModel getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(LoginModel context) {
        this.context = context;
    }

}