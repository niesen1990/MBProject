package com.cmbb.smartkids.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;
import android.util.SparseArray;

import com.cmbb.smartkids.db.DBHelper;
import com.cmbb.smartkids.db.MBContract;
import com.cmbb.smartkids.tools.log.Log;


/**
 * Created by N.Sun
 */
public class ProviderUserAccount extends ContentProvider {

    private static final String TAG = ProviderUserAccount.class.getSimpleName();

    private DBHelper dbHelper;

    private static final UriMatcher sUriMatcher;
    private static final SparseArray<String> sMimeTypes;

    static {
        sUriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        sMimeTypes = new SparseArray<String>();
        sUriMatcher.addURI(MBContract.UserAccount.AUTHORITY, MBContract.UserAccount.TABLE_NAME, 1);
        sUriMatcher.addURI(MBContract.UserAccount.AUTHORITY, MBContract.UserAccount.TABLE_NAME + "/#", 2);

        sMimeTypes.put(1, "vnd.android.cursor.dir/vnd." + MBContract.UserAccount.AUTHORITY + "." + MBContract.UserAccount.TABLE_NAME);
        sMimeTypes.put(2, "vnd.android.cursor.item/vnd." + MBContract.UserAccount.AUTHORITY + "." + MBContract.UserAccount.TABLE_NAME);
    }

    public ProviderUserAccount() {

    }

    @Override
    public boolean onCreate() {

        Log.i(TAG, "UserAccount Provider");
        dbHelper = new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(MBContract.UserAccount.TABLE_NAME);
        switch (sUriMatcher.match(uri)) {
            case 1:
                break;

            case 2:
                queryBuilder.appendWhere(MBContract.UserAccount.ROW_ID + " = " + uri.getLastPathSegment());
                break;
        }

        Cursor cursor = queryBuilder.query(db, projection, selection, selectionArgs, null, null, sortOrder);
        cursor.setNotificationUri(getContext().getContentResolver(), uri);
        return cursor;

    }

    @Override
    public String getType(Uri uri) {
        return sMimeTypes.get(sUriMatcher.match(uri));
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        switch (sUriMatcher.match(uri)) {
            case 1:
                long id = db.insert(MBContract.UserAccount.TABLE_NAME, null, values);
                if (-1 != id) {
                    getContext().getContentResolver().notifyChange(uri, null);
                    return Uri.withAppendedPath(uri, Long.toString(id));
                } else {
                    throw new SQLiteException("Insert error:" + uri);
                }
        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowsDeleted = 0;
        switch (sUriMatcher.match(uri)) {
            case 1:
                rowsDeleted = db.delete(MBContract.UserAccount.TABLE_NAME, selection, selectionArgs);
                break;

            case 2:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowsDeleted = db.delete(MBContract.UserAccount.TABLE_NAME, MBContract.UserAccount.ROW_ID + " = " + id, null);
                } else {
                    rowsDeleted = db.delete(MBContract.UserAccount.TABLE_NAME, MBContract.UserAccount.ROW_ID + " = " + id + " and " + selection, null);
                }
                break;
        }

        getContext().getContentResolver().notifyChange(uri, null);
        return rowsDeleted;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int rowUpdated = 0;
        switch (sUriMatcher.match(uri)) {
            case 1:
                rowUpdated = db.update(MBContract.UserAccount.TABLE_NAME, values, selection, selectionArgs);
                break;

            case 2:
                String id = uri.getLastPathSegment();
                if (TextUtils.isEmpty(selection)) {
                    rowUpdated = db.update(MBContract.UserAccount.TABLE_NAME, values, MBContract.UserAccount.ROW_ID + " = " + id, null);
                } else {
                    rowUpdated = db.update(MBContract.UserAccount.TABLE_NAME, values, MBContract.UserAccount.ROW_ID + " = " + id + " and " + selection, null);
                }
                break;
        }
        getContext().getContentResolver().notifyChange(uri, null);
        return rowUpdated;
    }
}
