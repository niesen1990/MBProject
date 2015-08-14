package com.cmbb.smartkids.model.eredar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/13 下午5:06
 */
public class AddEredarModel implements Parcelable {
    private String presentation;
    private String rongyunToken;
    private String token;

    public String getPresentation() {
        return presentation;
    }

    public void setPresentation(String presentation) {
        this.presentation = presentation;
    }

    public String getRongyunToken() {
        return rongyunToken;
    }

    public void setRongyunToken(String rongyunToken) {
        this.rongyunToken = rongyunToken;
    }

    public String getToken() {
        return token;
    }

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
        dest.writeString(this.rongyunToken);
        dest.writeString(this.token);
    }

    public AddEredarModel() {
    }

    protected AddEredarModel(Parcel in) {
        this.presentation = in.readString();
        this.rongyunToken = in.readString();
        this.token = in.readString();
    }

    public static final Parcelable.Creator<AddEredarModel> CREATOR = new Parcelable.Creator<AddEredarModel>() {
        public AddEredarModel createFromParcel(Parcel source) {
            return new AddEredarModel(source);
        }

        public AddEredarModel[] newArray(int size) {
            return new AddEredarModel[size];
        }
    };
}
