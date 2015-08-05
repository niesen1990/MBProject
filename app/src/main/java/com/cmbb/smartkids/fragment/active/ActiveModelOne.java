package com.cmbb.smartkids.fragment.active;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/4/24.
 */
public class ActiveModelOne implements Parcelable {
    private String abstracting;

    private String authorityName;

    private String rongyunServiceId;

    private int authorityRelationId;

    private String beGoodAt;

    private int bigImgHeight;

    private int bigImgWidth;

    private int loginTimes;

    private String realName;

    private int smallImgHeight;

    private int smallImgWidth;

    private String token;

    private String userBigHeadImg;

    private int userId;

    private String userSmallHeadImg;

    public String getRongyunServiceId() {
        return rongyunServiceId;
    }

    public void setRongyunServiceId(String rongyunServiceId) {
        this.rongyunServiceId = rongyunServiceId;
    }


    public String getAbstracting() {
        return abstracting;
    }

    public void setAbstracting(String abstracting) {
        this.abstracting = abstracting;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public int getAuthorityRelationId() {
        return authorityRelationId;
    }

    public void setAuthorityRelationId(int authorityRelationId) {
        this.authorityRelationId = authorityRelationId;
    }

    public String getBeGoodAt() {
        return beGoodAt;
    }

    public void setBeGoodAt(String beGoodAt) {
        this.beGoodAt = beGoodAt;
    }

    public int getBigImgHeight() {
        return bigImgHeight;
    }

    public void setBigImgHeight(int bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    public int getBigImgWidth() {
        return bigImgWidth;
    }

    public void setBigImgWidth(int bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    public int getLoginTimes() {
        return loginTimes;
    }

    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public int getSmallImgHeight() {
        return smallImgHeight;
    }

    public void setSmallImgHeight(int smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    public int getSmallImgWidth() {
        return smallImgWidth;
    }

    public void setSmallImgWidth(int smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserBigHeadImg() {
        return userBigHeadImg;
    }

    public void setUserBigHeadImg(String userBigHeadImg) {
        this.userBigHeadImg = userBigHeadImg;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserSmallHeadImg() {
        return userSmallHeadImg;
    }

    public void setUserSmallHeadImg(String userSmallHeadImg) {
        this.userSmallHeadImg = userSmallHeadImg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.abstracting);
        dest.writeString(this.authorityName);
        dest.writeString(this.rongyunServiceId);
        dest.writeInt(this.authorityRelationId);
        dest.writeString(this.beGoodAt);
        dest.writeInt(this.bigImgHeight);
        dest.writeInt(this.bigImgWidth);
        dest.writeInt(this.loginTimes);
        dest.writeString(this.realName);
        dest.writeInt(this.smallImgHeight);
        dest.writeInt(this.smallImgWidth);
        dest.writeString(this.token);
        dest.writeString(this.userBigHeadImg);
        dest.writeInt(this.userId);
        dest.writeString(this.userSmallHeadImg);
    }

    public ActiveModelOne() {
    }

    protected ActiveModelOne(Parcel in) {
        this.abstracting = in.readString();
        this.authorityName = in.readString();
        this.rongyunServiceId = in.readString();
        this.authorityRelationId = in.readInt();
        this.beGoodAt = in.readString();
        this.bigImgHeight = in.readInt();
        this.bigImgWidth = in.readInt();
        this.loginTimes = in.readInt();
        this.realName = in.readString();
        this.smallImgHeight = in.readInt();
        this.smallImgWidth = in.readInt();
        this.token = in.readString();
        this.userBigHeadImg = in.readString();
        this.userId = in.readInt();
        this.userSmallHeadImg = in.readString();
    }

    public static final Parcelable.Creator<ActiveModelOne> CREATOR = new Parcelable.Creator<ActiveModelOne>() {
        public ActiveModelOne createFromParcel(Parcel source) {
            return new ActiveModelOne(source);
        }

        public ActiveModelOne[] newArray(int size) {
            return new ActiveModelOne[size];
        }
    };
}
