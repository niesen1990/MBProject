package com.cmbb.smartkids.fragment.caselist;

import android.os.Parcel;
import android.os.Parcelable;

public class CaseDetailListModel implements Parcelable {

    private int attent;
    private int authority;
    private String bigImg;
    private int bigImgHeight;
    private int bigImgWidth;
    private String context;
    private String date;
    private int id;
    private String name;
    private String nike;
    private String question;
    private int replys;
    private String smallImg;
    private int smallImgHeight;
    private int smallImgWidth;
    private int store;
    private String title;
    private int type;
    private String userBigHeadImg;
    private int userId;
    private String userSmallHeadImg;
    private int userStatus;

    public int getAttent() {
        return attent;
    }

    public void setAttent(int attent) {
        this.attent = attent;
    }

    public int getAuthority() {
        return authority;
    }

    public void setAuthority(int authority) {
        this.authority = authority;
    }

    public String getBigImg() {
        return bigImg;
    }

    public void setBigImg(String bigImg) {
        this.bigImg = bigImg;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNike() {
        return nike;
    }

    public void setNike(String nike) {
        this.nike = nike;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getReplys() {
        return replys;
    }

    public void setReplys(int replys) {
        this.replys = replys;
    }

    public String getSmallImg() {
        return smallImg;
    }

    public void setSmallImg(String smallImg) {
        this.smallImg = smallImg;
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

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.attent);
        dest.writeInt(this.authority);
        dest.writeString(this.bigImg);
        dest.writeInt(this.bigImgHeight);
        dest.writeInt(this.bigImgWidth);
        dest.writeString(this.context);
        dest.writeString(this.date);
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.nike);
        dest.writeString(this.question);
        dest.writeInt(this.replys);
        dest.writeString(this.smallImg);
        dest.writeInt(this.smallImgHeight);
        dest.writeInt(this.smallImgWidth);
        dest.writeInt(this.store);
        dest.writeString(this.title);
        dest.writeInt(this.type);
        dest.writeString(this.userBigHeadImg);
        dest.writeInt(this.userId);
        dest.writeString(this.userSmallHeadImg);
        dest.writeInt(this.userStatus);
    }

    public CaseDetailListModel() {
    }

    protected CaseDetailListModel(Parcel in) {
        this.attent = in.readInt();
        this.authority = in.readInt();
        this.bigImg = in.readString();
        this.bigImgHeight = in.readInt();
        this.bigImgWidth = in.readInt();
        this.context = in.readString();
        this.date = in.readString();
        this.id = in.readInt();
        this.name = in.readString();
        this.nike = in.readString();
        this.question = in.readString();
        this.replys = in.readInt();
        this.smallImg = in.readString();
        this.smallImgHeight = in.readInt();
        this.smallImgWidth = in.readInt();
        this.store = in.readInt();
        this.title = in.readString();
        this.type = in.readInt();
        this.userBigHeadImg = in.readString();
        this.userId = in.readInt();
        this.userSmallHeadImg = in.readString();
        this.userStatus = in.readInt();
    }

    public static final Parcelable.Creator<CaseDetailListModel> CREATOR = new Parcelable.Creator<CaseDetailListModel>() {
        public CaseDetailListModel createFromParcel(Parcel source) {
            return new CaseDetailListModel(source);
        }

        public CaseDetailListModel[] newArray(int size) {
            return new CaseDetailListModel[size];
        }
    };
}