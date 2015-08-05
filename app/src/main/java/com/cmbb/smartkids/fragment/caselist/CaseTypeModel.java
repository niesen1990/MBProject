package com.cmbb.smartkids.fragment.caselist;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class CaseTypeModel implements Parcelable {

    private String name;
    private String context;
    private int type;
    private int status;

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.name);
        dest.writeString(this.context);
        dest.writeInt(this.type);
        dest.writeInt(this.status);
    }

    public CaseTypeModel() {
    }

    protected CaseTypeModel(Parcel in) {
        this.name = in.readString();
        this.context = in.readString();
        this.type = in.readInt();
        this.status = in.readInt();
    }

    public static final Parcelable.Creator<CaseTypeModel> CREATOR = new Parcelable.Creator<CaseTypeModel>() {
        public CaseTypeModel createFromParcel(Parcel source) {
            return new CaseTypeModel(source);
        }

        public CaseTypeModel[] newArray(int size) {
            return new CaseTypeModel[size];
        }
    };
}

