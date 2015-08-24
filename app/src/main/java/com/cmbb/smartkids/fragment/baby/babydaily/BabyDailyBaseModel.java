package com.cmbb.smartkids.fragment.baby.babydaily;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by N.Sun
 */
public class BabyDailyBaseModel {
    private String code;
    private List<BabyDailyModel> context = new ArrayList<BabyDailyModel>();

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
    public List<BabyDailyModel> getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(List<BabyDailyModel> context) {
        this.context = context;
    }

}
