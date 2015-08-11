package com.cmbb.smartkids.fragment.master;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class MasterDetailModel implements Parcelable {
    private Integer attention;
    private Integer authority;
    private Integer bigImgHeight;
    private Integer bigImgWidth;
    private Integer currentUser;
    private Integer eredarRank;
    private Integer eredarType;
    private Integer id;
    private Integer loginTimes;
    private String nike;
    private Integer smallImgHeight;
    private Integer smallImgWidth;
    private String userBigHeadImg;
    private String userSmallHeadImg;
    private Integer userStatus;
    private Integer eredar;
    private String eredarName;

    /**
     * @return The attention
     */
    public Integer getAttention() {
        return attention;
    }

    /**
     * @param attention The attention
     */
    public void setAttention(Integer attention) {
        this.attention = attention;
    }

    /**
     * @return The attention
     */
    public String getEredarName() {
        return eredarName;
    }

    /**
     * @param eredarName The attention
     */
    public void setEredar(String eredarName) {
        this.eredarName = eredarName;
    }

    /**
     * @return The attention
     */
    public Integer getEredar() {
        return eredar;
    }

    /**
     * @param eredar The attention
     */
    public void setEredar(Integer eredar) {
        this.eredar = eredar;
    }

    /**
     * @return The authority
     */
    public Integer getAuthority() {
        return authority;
    }

    /**
     * @param authority The authority
     */
    public void setAuthority(Integer authority) {
        this.authority = authority;
    }

    /**
     * @return The bigImgHeight
     */
    public Integer getBigImgHeight() {
        return bigImgHeight;
    }

    /**
     * @param bigImgHeight The bigImgHeight
     */
    public void setBigImgHeight(Integer bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    /**
     * @return The bigImgWidth
     */
    public Integer getBigImgWidth() {
        return bigImgWidth;
    }

    /**
     * @param bigImgWidth The bigImgWidth
     */
    public void setBigImgWidth(Integer bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    /**
     * @return The currentUser
     */
    public Integer getCurrentUser() {
        return currentUser;
    }

    /**
     * @param currentUser The currentUser
     */
    public void setCurrentUser(Integer currentUser) {
        this.currentUser = currentUser;
    }

    /**
     * @return The eredarRank
     */
    public Integer getEredarRank() {
        return eredarRank;
    }

    /**
     * @param eredarRank The eredarRank
     */
    public void setEredarRank(Integer eredarRank) {
        this.eredarRank = eredarRank;
    }

    /**
     * @return The eredarType
     */
    public Integer getEredarType() {
        return eredarType;
    }

    /**
     * @param eredarType The eredarType
     */
    public void setEredarType(Integer eredarType) {
        this.eredarType = eredarType;
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The loginTimes
     */
    public Integer getLoginTimes() {
        return loginTimes;
    }

    /**
     * @param loginTimes The loginTimes
     */
    public void setLoginTimes(Integer loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * @return The nike
     */
    public String getNike() {
        return nike;
    }

    /**
     * @param nike The nike
     */
    public void setNike(String nike) {
        this.nike = nike;
    }

    /**
     * @return The smallImgHeight
     */
    public Integer getSmallImgHeight() {
        return smallImgHeight;
    }

    /**
     * @param smallImgHeight The smallImgHeight
     */
    public void setSmallImgHeight(Integer smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    /**
     * @return The smallImgWidth
     */
    public Integer getSmallImgWidth() {
        return smallImgWidth;
    }

    /**
     * @param smallImgWidth The smallImgWidth
     */
    public void setSmallImgWidth(Integer smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    /**
     * @return The userBigHeadImg
     */
    public String getUserBigHeadImg() {
        return userBigHeadImg;
    }

    /**
     * @param userBigHeadImg The userBigHeadImg
     */
    public void setUserBigHeadImg(String userBigHeadImg) {
        this.userBigHeadImg = userBigHeadImg;
    }

    /**
     * @return The userSmallHeadImg
     */
    public String getUserSmallHeadImg() {
        return userSmallHeadImg;
    }

    /**
     * @param userSmallHeadImg The userSmallHeadImg
     */
    public void setUserSmallHeadImg(String userSmallHeadImg) {
        this.userSmallHeadImg = userSmallHeadImg;
    }

    /**
     * @return The userStatus
     */
    public Integer getUserStatus() {
        return userStatus;
    }


    /**
     * @param userStatus The userStatus
     */
    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.attention);
        dest.writeValue(this.authority);
        dest.writeValue(this.bigImgHeight);
        dest.writeValue(this.bigImgWidth);
        dest.writeValue(this.currentUser);
        dest.writeValue(this.eredarRank);
        dest.writeValue(this.eredarType);
        dest.writeValue(this.id);
        dest.writeValue(this.loginTimes);
        dest.writeString(this.nike);
        dest.writeValue(this.smallImgHeight);
        dest.writeValue(this.smallImgWidth);
        dest.writeString(this.userBigHeadImg);
        dest.writeString(this.userSmallHeadImg);
        dest.writeValue(this.userStatus);
        dest.writeValue(this.eredar);
        dest.writeString(this.eredarName);
    }

    public MasterDetailModel() {
    }

    protected MasterDetailModel(Parcel in) {
        this.attention = (Integer) in.readValue(Integer.class.getClassLoader());
        this.authority = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bigImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bigImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.currentUser = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredarRank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredarType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.loginTimes = (Integer) in.readValue(Integer.class.getClassLoader());
        this.nike = in.readString();
        this.smallImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.userBigHeadImg = in.readString();
        this.userSmallHeadImg = in.readString();
        this.userStatus = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredar = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredarName = in.readString();
    }

    public static final Parcelable.Creator<MasterDetailModel> CREATOR = new Parcelable.Creator<MasterDetailModel>() {
        public MasterDetailModel createFromParcel(Parcel source) {
            return new MasterDetailModel(source);
        }

        public MasterDetailModel[] newArray(int size) {
            return new MasterDetailModel[size];
        }
    };
}
