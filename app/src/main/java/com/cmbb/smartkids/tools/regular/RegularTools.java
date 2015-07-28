package com.cmbb.smartkids.tools.regular;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/28 上午11:49
 */
public class RegularTools {

    /**
     * 手机号码正则表达式
     *
     * @param phone String
     * @return true : 号码正确， false :号码错误
     */
    public static boolean isMobileNo(String phone) {
        String match = "^((13|15|18|17|14)\\d{9})|147\\d{8}$";
        Pattern pattern = Pattern.compile(match);
        Matcher matcher = pattern.matcher(phone);
        return matcher.matches();
    }
}
