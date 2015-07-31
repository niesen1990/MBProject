package com.cmbb.smartkids.fragment.homeattention.user;

import java.util.ArrayList;

public class UserAttentionBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<UserAttentionModel> context;

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
    public ArrayList<UserAttentionModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<UserAttentionModel> context) {
        this.context = context;
    }

}