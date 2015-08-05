package com.cmbb.smartkids.fragment.message;

import java.util.ArrayList;

public class MessageListBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<MessageModel> context;

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
    public ArrayList<MessageModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<MessageModel> context) {
        this.context = context;
    }

}