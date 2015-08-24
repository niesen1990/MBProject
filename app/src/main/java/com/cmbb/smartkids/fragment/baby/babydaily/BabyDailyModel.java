package com.cmbb.smartkids.fragment.baby.babydaily;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class BabyDailyModel implements Parcelable {
    private String bigImg;
    private String context;
    private String date;
    private Integer id;
    private String smallImg;
    private String weixinImg;

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    public String getWeixinImg() {
        return weixinImg;
    }

    public void setWeixinImg(String weixinImg) {
        this.weixinImg = weixinImg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bigImg);
        dest.writeString(this.context);
        dest.writeString(this.date);
        dest.writeValue(this.id);
        dest.writeString(this.smallImg);
        dest.writeString(this.weixinImg);
    }

    public BabyDailyModel() {
    }

    protected BabyDailyModel(Parcel in) {
        this.bigImg = in.readString();
        this.context = in.readString();
        this.date = in.readString();
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImg = in.readString();
        this.weixinImg = in.readString();
    }

    public static final Parcelable.Creator<BabyDailyModel> CREATOR = new Parcelable.Creator<BabyDailyModel>() {
        public BabyDailyModel createFromParcel(Parcel source) {
            return new BabyDailyModel(source);
        }

        public BabyDailyModel[] newArray(int size) {
            return new BabyDailyModel[size];
        }
    };
}
