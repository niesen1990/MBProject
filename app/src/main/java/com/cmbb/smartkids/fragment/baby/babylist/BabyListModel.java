package com.cmbb.smartkids.fragment.baby.babylist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2015/4/21.
 */
public class BabyListModel implements Parcelable {
    private Integer ageCount;
    private String babyBigHeadImg;
    private Integer babyId;
    private String babyNick;
    private Integer babySex;
    private String babySmallHeadImg;
    private Integer bigImgHeight;
    private Integer bigImgWidth;
    private String brithday;
    private Integer growingCount;
    private Integer smallImgHeight;
    private Integer smallImgWidth;
    private String weixinImg;

    public Integer getAgeCount() {
        return ageCount;
    }

    public void setAgeCount(Integer ageCount) {
        this.ageCount = ageCount;
    }

    public String getBabyBigHeadImg() {
        return babyBigHeadImg;
    }

    public void setBabyBigHeadImg(String babyBigHeadImg) {
        this.babyBigHeadImg = babyBigHeadImg;
    }

    public Integer getBabyId() {
        return babyId;
    }

    public void setBabyId(Integer babyId) {
        this.babyId = babyId;
    }

    public String getBabyNick() {
        return babyNick;
    }

    public void setBabyNick(String babyNick) {
        this.babyNick = babyNick;
    }

    public Integer getBabySex() {
        return babySex;
    }

    public void setBabySex(Integer babySex) {
        this.babySex = babySex;
    }

    public String getBabySmallHeadImg() {
        return babySmallHeadImg;
    }

    public void setBabySmallHeadImg(String babySmallHeadImg) {
        this.babySmallHeadImg = babySmallHeadImg;
    }

    public Integer getBigImgHeight() {
        return bigImgHeight;
    }

    public void setBigImgHeight(Integer bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    public Integer getBigImgWidth() {
        return bigImgWidth;
    }

    public void setBigImgWidth(Integer bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    public String getBrithday() {
        return brithday;
    }

    public void setBrithday(String brithday) {
        this.brithday = brithday;
    }

    public Integer getGrowingCount() {
        return growingCount;
    }

    public void setGrowingCount(Integer growingCount) {
        this.growingCount = growingCount;
    }

    public Integer getSmallImgHeight() {
        return smallImgHeight;
    }

    public void setSmallImgHeight(Integer smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    public Integer getSmallImgWidth() {
        return smallImgWidth;
    }

    public void setSmallImgWidth(Integer smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
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
        dest.writeValue(this.ageCount);
        dest.writeString(this.babyBigHeadImg);
        dest.writeValue(this.babyId);
        dest.writeString(this.babyNick);
        dest.writeValue(this.babySex);
        dest.writeString(this.babySmallHeadImg);
        dest.writeValue(this.bigImgHeight);
        dest.writeValue(this.bigImgWidth);
        dest.writeString(this.brithday);
        dest.writeValue(this.growingCount);
        dest.writeValue(this.smallImgHeight);
        dest.writeValue(this.smallImgWidth);
        dest.writeString(this.weixinImg);
    }

    public BabyListModel() {
    }

    protected BabyListModel(Parcel in) {
        this.ageCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.babyBigHeadImg = in.readString();
        this.babyId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.babyNick = in.readString();
        this.babySex = (Integer) in.readValue(Integer.class.getClassLoader());
        this.babySmallHeadImg = in.readString();
        this.bigImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bigImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.brithday = in.readString();
        this.growingCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.weixinImg = in.readString();
    }

    public static final Parcelable.Creator<BabyListModel> CREATOR = new Parcelable.Creator<BabyListModel>() {
        public BabyListModel createFromParcel(Parcel source) {
            return new BabyListModel(source);
        }

        public BabyListModel[] newArray(int size) {
            return new BabyListModel[size];
        }
    };
}
