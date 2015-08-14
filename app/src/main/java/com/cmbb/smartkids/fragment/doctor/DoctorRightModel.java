package com.cmbb.smartkids.fragment.doctor;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class DoctorRightModel implements Parcelable {
    private String abstracting;
    private String address;
    private Integer attention;
    private String beGoodAt;
    private Integer bigImgHeight;
    private Integer bigImgWidth;
    private Integer caseCount;
    private Integer chatCount;
    private Integer city;
    private Integer currentUser;
    private Integer eredar;
    private Integer helpCount;
    private String name;
    private Integer online;
    private String realName;
    private Integer smallImgHeight;
    private Integer smallImgWidth;
    private Integer type;
    private String userBigHeadImg;
    private Integer userId;
    private String userSmallHeadImg;

    public Integer getAttention() {
        return attention;
    }

    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    public String getAbstracting() {
        return abstracting;
    }

    public void setAbstracting(String abstracting) {
        this.abstracting = abstracting;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBeGoodAt() {
        return beGoodAt;
    }

    public void setBeGoodAt(String beGoodAt) {
        this.beGoodAt = beGoodAt;
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

    public Integer getCaseCount() {
        return caseCount;
    }

    public void setCaseCount(Integer caseCount) {
        this.caseCount = caseCount;
    }

    public Integer getChatCount() {
        return chatCount;
    }

    public void setChatCount(Integer chatCount) {
        this.chatCount = chatCount;
    }

    public Integer getCity() {
        return city;
    }

    public void setCity(Integer city) {
        this.city = city;
    }

    public Integer getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Integer currentUser) {
        this.currentUser = currentUser;
    }

    public Integer getEredar() {
        return eredar;
    }

    public void setEredar(Integer eredar) {
        this.eredar = eredar;
    }

    public Integer getHelpCount() {
        return helpCount;
    }

    public void setHelpCount(Integer helpCount) {
        this.helpCount = helpCount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getOnline() {
        return online;
    }

    public void setOnline(Integer online) {
        this.online = online;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserBigHeadImg() {
        return userBigHeadImg;
    }

    public void setUserBigHeadImg(String userBigHeadImg) {
        this.userBigHeadImg = userBigHeadImg;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
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
        dest.writeString(this.address);
        dest.writeValue(this.attention);
        dest.writeString(this.beGoodAt);
        dest.writeValue(this.bigImgHeight);
        dest.writeValue(this.bigImgWidth);
        dest.writeValue(this.caseCount);
        dest.writeValue(this.chatCount);
        dest.writeValue(this.city);
        dest.writeValue(this.currentUser);
        dest.writeValue(this.eredar);
        dest.writeValue(this.helpCount);
        dest.writeString(this.name);
        dest.writeValue(this.online);
        dest.writeString(this.realName);
        dest.writeValue(this.smallImgHeight);
        dest.writeValue(this.smallImgWidth);
        dest.writeValue(this.type);
        dest.writeString(this.userBigHeadImg);
        dest.writeValue(this.userId);
        dest.writeString(this.userSmallHeadImg);
    }

    public DoctorRightModel() {
    }

    protected DoctorRightModel(Parcel in) {
        this.abstracting = in.readString();
        this.address = in.readString();
        this.attention = (Integer) in.readValue(Integer.class.getClassLoader());
        this.beGoodAt = in.readString();
        this.bigImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bigImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.caseCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.chatCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.city = (Integer) in.readValue(Integer.class.getClassLoader());
        this.currentUser = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredar = (Integer) in.readValue(Integer.class.getClassLoader());
        this.helpCount = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.online = (Integer) in.readValue(Integer.class.getClassLoader());
        this.realName = in.readString();
        this.smallImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.type = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userBigHeadImg = in.readString();
        this.userId = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userSmallHeadImg = in.readString();
    }

    public static final Creator<DoctorRightModel> CREATOR = new Creator<DoctorRightModel>() {
        public DoctorRightModel createFromParcel(Parcel source) {
            return new DoctorRightModel(source);
        }

        public DoctorRightModel[] newArray(int size) {
            return new DoctorRightModel[size];
        }
    };
}
