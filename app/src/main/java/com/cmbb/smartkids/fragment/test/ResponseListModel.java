package com.cmbb.smartkids.fragment.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResponseListModel {

    // Context是一个数组

    private String code;
    private ArrayList<HomeSameAge> context;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

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
    public ArrayList<HomeSameAge> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<HomeSameAge> context) {
        this.context = context;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "ResponseModel{" +
                "code='" + code + '\'' +
                ", context=" + context +
                ", additionalProperties=" + additionalProperties +
                '}';
    }
}