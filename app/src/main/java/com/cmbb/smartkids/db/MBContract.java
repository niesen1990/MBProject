package com.cmbb.smartkids.db;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/7/28 上午11:58
 */
public final class MBContract {
    private MBContract() {

    }

    private static final String TEXT_TYPE = " TEXT,";
    private static final String INT_TYPE = " INTEGER,";
    public static final String DATABASE_NAME = " SmartKids.db";

    public final static class UserAccount implements BaseColumns {

        private UserAccount() {

        }

        // scheme
        public static final String SCHEME = "content";

        // Authority
        public static final String AUTHORITY = "com.cmbb.smartkids.useraccount";

        // content uri
        public static final Uri CONTENT_URI = Uri.parse(SCHEME + "://" + AUTHORITY);

        // multiple rows
        public static final String MIME_TYPE_ROWS = "vnd.android.cursor.dir/vnd.com.cmbb" +
                ".smartkids.useraccount";

        // single row
        public static final String MIME_TYPE_SINGLE_ROWS = "vnd.android.cursor.item/vnd.com.cmbb" +
                ".smartkids.useraccount";

        // primary key column name
        public static final String ROW_ID = BaseColumns._ID;

        // table name
        public static final String TABLE_NAME = "UserAccount";

        // table content URI
        public static final Uri TABLE_CONTENT_URI = Uri.withAppendedPath(CONTENT_URI, TABLE_NAME);

        public static final String COLUMN_ATTENTIONCOUNT = "attentionCount";
        public static final String COLUMN_AUTHORITY = "authority";
        public static final String COLUMN_BIGIMGHEIGHT = "bigImgHeight";
        public static final String COLUMN_BIGIMGWIDTH = "bigImgWidth";
        public static final String COLUMN_EREDAR = "eredar";
        public static final String COLUMN_EREDARNAME = "eredarName";
        public static final String COLUMN_EREDARRANK = "eredarRank";
        public static final String COLUMN_EREDARTYPE = "eredarType";
        public static final String COLUMN_FLAG = "flag";
        public static final String COLUMN_GOID = "gold";
        public static final String COLUMN_LOGINTIMES = "loginTimes";
        public static final String COLUMN_NIKE = "nike";
        public static final String COLUMN_PUBLISHCOUNT = "publishCount";
        public static final String COLUMN_SMALLIMGHEIGHT = "smallImgHeight";
        public static final String COLUMN_SMALLIMGWIDTH = "smallImgWidth";
        public static final String COLUMN_STORECOUNT = "storeCount";
        public static final String COLUMN_USERBIGHEADIMG = "userBigHeadImg";
        public static final String COLUMN_USERID = "userId";
        public static final String COLUMN_USERPHONE = "userPhone";
        public static final String COLUMN_USERSMALLHEADIMG = "userSmallHeadImg";
        public static final String COLUMN_USERSTATUS = "userStatus";
        public static final String COLUMN_USERWEIXINHEADIMG = "userWeiXinHeadImg";

        public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + ROW_ID + " INTEGER PRIMARY KEY," +
                COLUMN_ATTENTIONCOUNT + INT_TYPE +
                COLUMN_AUTHORITY + INT_TYPE +
                COLUMN_BIGIMGHEIGHT + INT_TYPE +
                COLUMN_BIGIMGWIDTH + INT_TYPE +
                COLUMN_EREDAR + INT_TYPE +
                COLUMN_EREDARNAME + TEXT_TYPE +
                COLUMN_EREDARRANK + INT_TYPE +
                COLUMN_EREDARTYPE + INT_TYPE +
                COLUMN_FLAG + TEXT_TYPE +
                COLUMN_GOID + INT_TYPE +
                COLUMN_LOGINTIMES + TEXT_TYPE +
                COLUMN_NIKE + TEXT_TYPE +
                COLUMN_PUBLISHCOUNT + INT_TYPE +
                COLUMN_SMALLIMGHEIGHT + INT_TYPE +
                COLUMN_SMALLIMGWIDTH + INT_TYPE +
                COLUMN_STORECOUNT + INT_TYPE +
                COLUMN_USERBIGHEADIMG + TEXT_TYPE +
                COLUMN_USERID + INT_TYPE +
                COLUMN_USERPHONE + TEXT_TYPE +
                COLUMN_USERSMALLHEADIMG + TEXT_TYPE +
                COLUMN_USERSTATUS + INT_TYPE +
                COLUMN_USERWEIXINHEADIMG + " TEXT" + ");";

        public static final String DELETE_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}

