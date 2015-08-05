package com.cmbb.smartkids.model.userinfo;

import android.os.Parcel;
import android.os.Parcelable;

public class LoginModel implements Parcelable {

    private String presentation;
    private String token;
    private String rongyunToken;
    private int userStatus;

    public String getRongyunToken() {
        return rongyunToken;
    }

    public void setRongyunToken(String rongyunToken) {
        this.rongyunToken = rongyunToken;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    /**
     * @return The presentation
     */
    public String getPresentation() {
        return presentation;
    }

    /**
     * @param presentation The presentation
     */
    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    /**
     * @return The token
     */
    public String getToken() {
        return token;
    }

    /**
     * @param token The token
     */
    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.presentation);
        dest.writeString(this.token);
        dest.writeString(this.rongyunToken);
        dest.writeInt(this.userStatus);
    }

    public LoginModel() {
    }

    protected LoginModel(Parcel in) {
        this.presentation = in.readString();
        this.token = in.readString();
        this.rongyunToken = in.readString();
        this.userStatus = in.readInt();
    }

    public static final Parcelable.Creator<LoginModel> CREATOR = new Parcelable.Creator<LoginModel>() {
        public LoginModel createFromParcel(Parcel source) {
            return new LoginModel(source);
        }

        public LoginModel[] newArray(int size) {
            return new LoginModel[size];
        }
    };
}