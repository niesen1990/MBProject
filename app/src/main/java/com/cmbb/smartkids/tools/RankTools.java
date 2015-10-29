package com.cmbb.smartkids.tools;

import com.cmbb.smartkids.R;

/**
 * Created by N.Sun
 */
public class RankTools {

    /**
     * 处理用户在线毫秒值
     *
     * @param _loginTime
     * @return int[] 当前等级      叶子图片   等级图片  离下一等级还有多少%
     */
    public static long[] gradeDispose(long _loginTime) {

        int[] ye = new int[]{R.drawable.ranktag1, R.drawable.ranktag2, R.drawable.ranktag3, R.drawable.ranktag4};
        int[] lv = new int[]{R.drawable.lv1, R.drawable.lv2, R.drawable.lv3, R.drawable.lv4, R.drawable.lv5};

        long[] grades = new long[4];

        if (0 <= _loginTime && _loginTime < 40) {
            long a = _loginTime / 8;
            grades[0] = 1 + a;
            grades[1] = ye[0];
            switch ((int) a) {
                case 1:
                    grades[2] = lv[0];
                    break;
                case 2:
                    grades[2] = lv[1];
                    break;
                case 3:
                    grades[2] = lv[2];
                    break;
                case 4:
                    grades[2] = lv[3];
                    break;
                case 5:
                    grades[2] = lv[4];
                    break;
            }
            grades[3] = (_loginTime % 8) * 100 / 8;
        }

        if (40 <= _loginTime && _loginTime < 160) {
            long a = (_loginTime - 40) / 24;
            grades[0] = 6 + a;
            grades[1] = ye[1];
            switch ((int) a) {
                case 1:
                    grades[2] = lv[0];
                    break;
                case 2:
                    grades[2] = lv[1];
                    break;
                case 3:
                    grades[2] = lv[2];
                    break;
                case 4:
                    grades[2] = lv[3];
                    break;
                case 5:
                    grades[2] = lv[4];
                    break;
            }
            grades[3] = ((_loginTime - 40) % 24) * 100 / 24;
        }
        if (160 <= _loginTime && _loginTime < 520) {
            long a = (_loginTime - 160) / 72;
            grades[0] = 11 + a;
            grades[1] = ye[2];
            switch ((int) a) {
                case 1:
                    grades[2] = lv[0];
                    break;
                case 2:
                    grades[2] = lv[1];
                    break;
                case 3:
                    grades[2] = lv[2];
                    break;
                case 4:
                    grades[2] = lv[3];
                    break;
                case 5:
                    grades[2] = lv[4];
                    break;
            }
            grades[3] = (_loginTime - 160) % 72 * 100 / 72;
        }
        if (520 <= _loginTime && _loginTime < 1600) {
            long a = (_loginTime - 520) / 216;
            grades[0] = 16 + a;
            grades[1] = ye[3];
            switch ((int) a) {
                case 1:
                    grades[2] = lv[0];
                    break;
                case 2:
                    grades[2] = lv[1];
                    break;
                case 3:
                    grades[2] = lv[2];
                    break;
                case 4:
                    grades[2] = lv[3];
                    break;
                case 5:
                    grades[2] = lv[4];
                    break;
            }
            grades[3] = (_loginTime - 520) % 216 * 100 / 216;
        }
        if (1600 <= _loginTime) {
            grades[0] = 20;
            grades[1] = ye[3];
            grades[2] = lv[5];
            grades[3] = 100;
        }

        return grades;
    }

    /**
     * 处理用户帮助等级
     *
     * @param help
     * @return int[]  积分等级图片下标      离下一级还有多少%
     */
    public static int[] integral(long help) {
        int[] master = new int[]{R.drawable.master1, R.drawable.master2, R.drawable.master3, R.drawable.master4, R.drawable.master5};

        int[] integral = new int[2];

        if (0 <= help && help < 20) {
            integral[0] = master[0];
            integral[1] = (int) (help * 100 / 20);
        }

        if (20 <= help && help < 60) {
            integral[0] = master[1];
            integral[1] = (int) ((help - 20) * 100 / 40);
        }
        if (60 <= help && help < 140) {
            integral[0] = master[2];
            integral[1] = (int) ((help - 60) * 100 / 80);
        }
        if (140 <= help && help < 300) {
            integral[0] = master[3];
            integral[1] = (int) ((help - 140) * 100 / 160);
        }
        if (300 <= help) {
            integral[0] = master[4];
            integral[1] = 100;
        }
        return integral;
    }

    public static int[] master = new int[]{R.drawable.master1, R.drawable.master2, R.drawable.master3, R.drawable.master4, R.drawable.master5};

    public static int getAuthBackground(int auth, int status) {
        int[] imags = new int[]{R.drawable.bac_user_admin, R.drawable.bac_user_xiaobian, R.drawable.bac_user_pu01, R.drawable.bac_user_pu02, R.drawable.bac_user_pu03, R.drawable.bac_user_expert};
        switch (auth) {
            case 1:
                return imags[0];
            case 2:
                return imags[1];
            case 3:
                if (status != -1) {
                    switch (status) {
                        case 1:
                            return imags[2];
                        case 2:
                            return imags[3];
                        case 3:
                            return imags[4];
                        default:
                            return imags[2];
                    }
                }
                break;
            case 4:
                return imags[5];
            case 5:
                return imags[0];
            case 6:
                return imags[0];
            default:
                return -1;
        }
        return -1;
    }

}
