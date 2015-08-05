package com.cmbb.smartkids.model.userinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class RongInfoModel implements Parcelable {

    private String nike;
    private String userBigHeadImg;
    private String userSmallHeadImg;

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getUserBigHeadImg() {
        return userBigHeadImg;
    }

    public void setUserBigHeadImg(String userBigHeadImg) {
        this.userBigHeadImg = userBigHeadImg;
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
        dest.writeString(this.nike);
        dest.writeString(this.userBigHeadImg);
        dest.writeString(this.userSmallHeadImg);
    }

    public RongInfoModel() {
    }

    protected RongInfoModel(Parcel in) {
        this.nike = in.readString();
        this.userBigHeadImg = in.readString();
        this.userSmallHeadImg = in.readString();
    }

    public static final Parcelable.Creator<RongInfoModel> CREATOR = new Parcelable.Creator<RongInfoModel>() {
        public RongInfoModel createFromParcel(Parcel source) {
            return new RongInfoModel(source);
        }

        public RongInfoModel[] newArray(int size) {
            return new RongInfoModel[size];
        }
    };
}