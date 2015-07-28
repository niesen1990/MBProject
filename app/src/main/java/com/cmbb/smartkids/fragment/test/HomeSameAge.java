package com.cmbb.smartkids.fragment.test;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;

public class HomeSameAge implements Parcelable {

    private String bigImg;
    private int bigImgHeight;
    private int bigImgWidth;
    private String connector;
    private String context;
    private int count;

    private int id;
    private String smallImg;
    private int smallImgHeight;
    private int smallImgWidth;
    private String title;
    private String type;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();



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
    public Object getBigImgHeight() {
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
    public Object getBigImgWidth() {
        return bigImgWidth;
    }

    /**
     * @param bigImgWidth The bigImgWidth
     */
    public void setBigImgWidth(int bigImgWidth) {
        this.bigImgWidth = bigImgWidth;
    }

    /**
     * @return The connector
     */
    public String getConnector() {
        return connector;
    }

    /**
     * @param connector The connector
     */
    public void setConnector(String connector) {
        this.connector = connector;
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
     * @return The count
     */
    public Object getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * @return The id
     */
    public Object getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(int id) {
        this.id = id;
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
    public Object getSmallImgHeight() {
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
    public Object getSmallImgWidth() {
        return smallImgWidth;
    }

    /**
     * @param smallImgWidth The smallImgWidth
     */
    public void setSmallImgWidth(int smallImgWidth) {
        this.smallImgWidth = smallImgWidth;
    }

    /**
     * @return The title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title The title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return The type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type The type
     */
    public void setType(String type) {
        this.type = type;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        return "HomeSameAge{" +
                "bigImg='" + bigImg + '\'' +
                ", bigImgHeight=" + bigImgHeight +
                ", bigImgWidth=" + bigImgWidth +
                ", connector='" + connector + '\'' +
                ", context='" + context + '\'' +
                ", count=" + count +
                ", id=" + id +
                ", smallImg='" + smallImg + '\'' +
                ", smallImgHeight=" + smallImgHeight +
                ", smallImgWidth=" + smallImgWidth +
                ", title='" + title + '\'' +
                ", type='" + type + '\'' +
                ", additionalProperties=" + additionalProperties +
                '}';
    }

    public HomeSameAge() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bigImg);
        dest.writeInt(this.bigImgHeight);
        dest.writeInt(this.bigImgWidth);
        dest.writeString(this.connector);
        dest.writeString(this.context);
        dest.writeInt(this.count);
        dest.writeInt(this.id);
        dest.writeString(this.smallImg);
        dest.writeInt(this.smallImgHeight);
        dest.writeInt(this.smallImgWidth);
        dest.writeString(this.title);
        dest.writeString(this.type);
    }

    protected HomeSameAge(Parcel in) {
        this.bigImg = in.readString();
        this.bigImgHeight = in.readInt();
        this.bigImgWidth = in.readInt();
        this.connector = in.readString();
        this.context = in.readString();
        this.count = in.readInt();
        this.id = in.readInt();
        this.smallImg = in.readString();
        this.smallImgHeight = in.readInt();
        this.smallImgWidth = in.readInt();
        this.title = in.readString();
        this.type = in.readString();
    }

    public static final Creator<HomeSameAge> CREATOR = new Creator<HomeSameAge>() {
        public HomeSameAge createFromParcel(Parcel source) {
            return new HomeSameAge(source);
        }

        public HomeSameAge[] newArray(int size) {
            return new HomeSameAge[size];
        }
    };
}