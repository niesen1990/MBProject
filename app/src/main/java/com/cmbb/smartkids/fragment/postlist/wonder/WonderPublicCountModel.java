package com.cmbb.smartkids.fragment.postlist.wonder;

import android.os.Parcel;
import android.os.Parcelable;

import com.cmbb.smartkids.fragment.postlist.PostModel;

import java.util.ArrayList;

/**
 * Created by N.Sun
 */
public class WonderPublicCountModel implements Parcelable {
    private String attention;
    private String count;
    private ArrayList<PostModel> list;

    /**
     * @return The presentation
     */
    public String getAttention() {
        return attention;
    }

    /**
     * @param presentation The presentation
     */
    public void setAttention(String presentation) {
        this.attention = presentation;
    }

    /**
     * @return The token
     */
    public String getCount() {
        return count;
    }

    /**
     * @param token The token
     */
    public void setCount(String token) {
        this.count = token;
    }

    /**
     * @return The HomeSameAge
     */
    public ArrayList<PostModel> getHomeSameAgeList() {
        return list;
    }

    /**
     * @param HomeSameAge The HomeSameAge
     */
    public void setHomeSameAgeList(ArrayList<PostModel> HomeSameAge) {
        this.list = HomeSameAge;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.attention);
        dest.writeString(this.count);
        dest.writeTypedList(list);
    }

    public WonderPublicCountModel() {
    }

    protected WonderPublicCountModel(Parcel in) {
        this.attention = in.readString();
        this.count = in.readString();
        this.list = in.createTypedArrayList(PostModel.CREATOR);
    }

    public static final Parcelable.Creator<WonderPublicCountModel> CREATOR = new Parcelable.Creator<WonderPublicCountModel>() {
        public WonderPublicCountModel createFromParcel(Parcel source) {
            return new WonderPublicCountModel(source);
        }

        public WonderPublicCountModel[] newArray(int size) {
            return new WonderPublicCountModel[size];
        }
    };
}
