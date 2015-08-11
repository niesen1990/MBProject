package com.cmbb.smartkids.model.photo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 项目名称：MBProject
 * 类描述：
 * 创建人：N.Sun
 * 创建时间：15/8/7 下午12:38
 */
public class PhotoAdd implements Parcelable {
    private String photoUrl;
    private String photoContent;

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getPhotoContent() {
        return photoContent;
    }

    public void setPhotoContent(String photoContent) {
        this.photoContent = photoContent;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.photoUrl);
        dest.writeString(this.photoContent);
    }

    public PhotoAdd() {
    }

    public PhotoAdd(String photoUrl, String photoContent) {
        this.photoUrl = photoUrl;
        this.photoContent = photoContent;
    }

    protected PhotoAdd(Parcel in) {
        this.photoUrl = in.readString();
        this.photoContent = in.readString();
    }

    public static final Parcelable.Creator<PhotoAdd> CREATOR = new Parcelable.Creator<PhotoAdd>() {
        public PhotoAdd createFromParcel(Parcel source) {
            return new PhotoAdd(source);
        }

        public PhotoAdd[] newArray(int size) {
            return new PhotoAdd[size];
        }
    };

    @Override
    public String toString() {
        return "PhotoAdd{" +
                "photoUrl='" + photoUrl + '\'' +
                ", photoContent='" + photoContent + '\'' +
                '}';
    }
}
