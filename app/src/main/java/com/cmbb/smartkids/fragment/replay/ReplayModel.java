package com.cmbb.smartkids.fragment.replay;

import android.os.Parcel;
import android.os.Parcelable;

public class ReplayModel implements Parcelable {

    private int authority;
    private String bigImg;
    private int bigImgHeight;
    private int bigImgWidth;
    private String context;
    private String date;
    private int deleteTag;
    private int floor;
    private int id;
    private int isCurrentUser;
    private String nike;
    private int loginTimes;
    private String otherContext;
    private int otherDeleteTag;
    private int otherFloor;
    private String otherNike;
    private int otherUserId;
    private String otherUserSmallHeadImg;
    private int otherUserSmallImgHeight;
    private int otherUserSmallImgWidth;
    private int replys;
    private int replysParentId;
    private String smallImg;
    private int smallImgHeight;
    private int smallImgWidth;
    private int userId;
    private String userSmallHeadImg;
    private int userSmallImgHeight;
    private int userSmallImgWidth;
    private String weixinImg;

    private Integer eredar;
    private String eredarName;
    private Integer eredarRank;
    private Integer eredarType;

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
    public int getAuthority() {
        return authority;
    }

    /**
     * @param authority The authority
     */
    public void setAuthority(int authority) {
        this.authority = authority;
    }

    /**
     * @return The bigImg
     */
    public String getBigImg() {
        return bigImg;
    }

    /**
     * @param bigImg The bigImg
     */
    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
    }

    /**
     * @return The bigImgHeight
     */
    public int getBigImgHeight() {
        return bigImgHeight;
    }

    /**
     * @param bigImgHeight The bigImgHeight
     */
    public void setBigImgHeight(int bigImgHeight) {
        this.bigImgHeight = bigImgHeight;
    }

    /**
     * @return The bigImgWidth
     */
    public int getBigImgWidth() {
        return bigImgWidth;
    }

    /**
     * @param bigImgWidth The bigImgWidth
     */
    public void setBigImgWidth(int bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    /**
     * @return The context
     */
    public String getContext() {
        return context;
    }

    /**
     * @param context The context
     */
    public void setContext(String context) {
        this.context = context;
    }

    /**
     * @return The date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(String date) {
        this.date = date;
    }

    /**
     * @return The deleteTag
     */
    public int getDeleteTag() {
        return deleteTag;
    }

    /**
     * @param deleteTag The deleteTag
     */
    public void setDeleteTag(int deleteTag) {
        this.deleteTag = deleteTag;
    }

    /**
     * @return The floor
     */
    public int getFloor() {
        return floor;
    }

    /**
     * @param floor The floor
     */
    public void setFloor(int floor) {
        this.floor = floor;
    }

    /**
     * @return The id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return The isCurrentUser
     */
    public int getIsCurrentUser() {
        return isCurrentUser;
    }

    /**
     * @param isCurrentUser The isCurrentUser
     */
    public void setIsCurrentUser(int isCurrentUser) {
        this.isCurrentUser = isCurrentUser;
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
     * @return The loginTimes
     */
    public int getLoginTimes() {
        return loginTimes;
    }

    /**
     * @param loginTimes The loginTimes
     */
    public void setLoginTimes(int loginTimes) {
        this.loginTimes = loginTimes;
    }

    /**
     * @return The otherContext
     */
    public String getOtherContext() {
        return otherContext;
    }

    /**
     * @param otherContext The otherContext
     */
    public void setOtherContext(String otherContext) {
        this.otherContext = otherContext;
    }

    /**
     * @return The otherDeleteTag
     */
    public int getOtherDeleteTag() {
        return otherDeleteTag;
    }

    /**
     * @param otherDeleteTag The otherDeleteTag
     */
    public void setOtherDeleteTag(int otherDeleteTag) {
        this.otherDeleteTag = otherDeleteTag;
    }

    /**
     * @return The otherFloor
     */
    public int getOtherFloor() {
        return otherFloor;
    }

    /**
     * @param otherFloor The otherFloor
     */
    public void setOtherFloor(int otherFloor) {
        this.otherFloor = otherFloor;
    }

    /**
     * @return The otherNike
     */
    public String getOtherNike() {
        return otherNike;
    }

    /**
     * @param otherNike The otherNike
     */
    public void setOtherNike(String otherNike) {
        this.otherNike = otherNike;
    }

    /**
     * @return The otherUserId
     */
    public int getOtherUserId() {
        return otherUserId;
    }

    /**
     * @param otherUserId The otherUserId
     */
    public void setOtherUserId(int otherUserId) {
        this.otherUserId = otherUserId;
    }

    /**
     * @return The otherUserSmallHeadImg
     */
    public String getOtherUserSmallHeadImg() {
        return otherUserSmallHeadImg;
    }

    /**
     * @param otherUserSmallHeadImg The otherUserSmallHeadImg
     */
    public void setOtherUserSmallHeadImg(String otherUserSmallHeadImg) {
        this.otherUserSmallHeadImg = otherUserSmallHeadImg;
    }

    /**
     * @return The otherUserSmallImgHeight
     */
    public int getOtherUserSmallImgHeight() {
        return otherUserSmallImgHeight;
    }

    /**
     * @param otherUserSmallImgHeight The otherUserSmallImgHeight
     */
    public void setOtherUserSmallImgHeight(int otherUserSmallImgHeight) {
        this.otherUserSmallImgHeight = otherUserSmallImgHeight;
    }

    /**
     * @return The otherUserSmallImgWidth
     */
    public int getOtherUserSmallImgWidth() {
        return otherUserSmallImgWidth;
    }

    /**
     * @param otherUserSmallImgWidth The otherUserSmallImgWidth
     */
    public void setOtherUserSmallImgWidth(int otherUserSmallImgWidth) {
        this.otherUserSmallImgWidth = otherUserSmallImgWidth;
    }

    /**
     * @return The replys
     */
    public int getReplys() {
        return replys;
    }

    /**
     * @param replys The replys
     */
    public void setReplys(int replys) {
        this.replys = replys;
    }

    /**
     * @return The replysParentId
     */
    public int getReplysParentId() {
        return replysParentId;
    }

    /**
     * @param replysParentId The replysParentId
     */
    public void setReplysParentId(int replysParentId) {
        this.replysParentId = replysParentId;
    }

    /**
     * @return The smallImg
     */
    public String getSmallImg() {
        return smallImg;
    }

    /**
     * @param smallImg The smallImg
     */
    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
    }

    /**
     * @return The smallImgHeight
     */
    public int getSmallImgHeight() {
        return smallImgHeight;
    }

    /**
     * @param smallImgHeight The smallImgHeight
     */
    public void setSmallImgHeight(int smallImgHeight) {
        this.smallImgHeight = smallImgHeight;
    }

    /**
     * @return The smallImgWidth
     */
    public int getSmallImgWidth() {
        return smallImgWidth;
    }

    /**
     * @param smallImgWidth The smallImgWidth
     */
    public void setSmallImgWidth(int smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    /**
     * @return The userId
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId The userId
     */
    public void setUserId(int userId) {
        this.userId = userId;
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
     * @return The userSmallImgHeight
     */
    public int getUserSmallImgHeight() {
        return userSmallImgHeight;
    }

    /**
     * @param userSmallImgHeight The userSmallImgHeight
     */
    public void setUserSmallImgHeight(int userSmallImgHeight) {
        this.userSmallImgHeight = userSmallImgHeight;
    }

    /**
     * @return The userSmallImgWidth
     */
    public int getUserSmallImgWidth() {
        return userSmallImgWidth;
    }

    /**
     * @param userSmallImgWidth The userSmallImgWidth
     */
    public void setUserSmallImgWidth(int userSmallImgWidth) {
        this.userSmallImgWidth = userSmallImgWidth;
    }

    /**
     * @return The weixinImg
     */
    public String getWeixinImg() {
        return weixinImg;
    }

    /**
     * @param weixinImg The weixinImg
     */
    public void setWeixinImg(String weixinImg) {
        this.weixinImg = weixinImg;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.authority);
        dest.writeString(this.bigImg);
        dest.writeInt(this.bigImgHeight);
        dest.writeInt(this.bigImgWidth);
        dest.writeString(this.context);
        dest.writeString(this.date);
        dest.writeInt(this.deleteTag);
        dest.writeInt(this.floor);
        dest.writeInt(this.id);
        dest.writeInt(this.isCurrentUser);
        dest.writeString(this.nike);
        dest.writeInt(this.loginTimes);
        dest.writeString(this.otherContext);
        dest.writeInt(this.otherDeleteTag);
        dest.writeInt(this.otherFloor);
        dest.writeString(this.otherNike);
        dest.writeInt(this.otherUserId);
        dest.writeString(this.otherUserSmallHeadImg);
        dest.writeInt(this.otherUserSmallImgHeight);
        dest.writeInt(this.otherUserSmallImgWidth);
        dest.writeInt(this.replys);
        dest.writeInt(this.replysParentId);
        dest.writeString(this.smallImg);
        dest.writeInt(this.smallImgHeight);
        dest.writeInt(this.smallImgWidth);
        dest.writeInt(this.userId);
        dest.writeString(this.userSmallHeadImg);
        dest.writeInt(this.userSmallImgHeight);
        dest.writeInt(this.userSmallImgWidth);
        dest.writeString(this.weixinImg);
        dest.writeValue(this.eredar);
        dest.writeString(this.eredarName);
        dest.writeValue(this.eredarRank);
        dest.writeValue(this.eredarType);
    }

    public ReplayModel() {
    }

    protected ReplayModel(Parcel in) {
        this.authority = in.readInt();
        this.bigImg = in.readString();
        this.bigImgHeight = in.readInt();
        this.bigImgWidth = in.readInt();
        this.context = in.readString();
        this.date = in.readString();
        this.deleteTag = in.readInt();
        this.floor = in.readInt();
        this.id = in.readInt();
        this.isCurrentUser = in.readInt();
        this.nike = in.readString();
        this.loginTimes = in.readInt();
        this.otherContext = in.readString();
        this.otherDeleteTag = in.readInt();
        this.otherFloor = in.readInt();
        this.otherNike = in.readString();
        this.otherUserId = in.readInt();
        this.otherUserSmallHeadImg = in.readString();
        this.otherUserSmallImgHeight = in.readInt();
        this.otherUserSmallImgWidth = in.readInt();
        this.replys = in.readInt();
        this.replysParentId = in.readInt();
        this.smallImg = in.readString();
        this.smallImgHeight = in.readInt();
        this.smallImgWidth = in.readInt();
        this.userId = in.readInt();
        this.userSmallHeadImg = in.readString();
        this.userSmallImgHeight = in.readInt();
        this.userSmallImgWidth = in.readInt();
        this.weixinImg = in.readString();
        this.eredar = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredarName = in.readString();
        this.eredarRank = (Integer) in.readValue(Integer.class.getClassLoader());
        this.eredarType = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Parcelable.Creator<ReplayModel> CREATOR = new Parcelable.Creator<ReplayModel>() {
        public ReplayModel createFromParcel(Parcel source) {
            return new ReplayModel(source);
        }

        public ReplayModel[] newArray(int size) {
            return new ReplayModel[size];
        }
    };
}