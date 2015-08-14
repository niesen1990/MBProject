package com.cmbb.smartkids.fragment.doctor;

import java.util.ArrayList;

public class DoctorRightBaseModel {

    // Context是一个数组

    private String code;
    private ArrayList<DoctorRightModel> context;

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
    public ArrayList<DoctorRightModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(ArrayList<DoctorRightModel> context) {
        this.context = context;
    }
}