package com.cmbb.smartkids.fragment.homeplate;

import android.os.Parcel;
import android.os.Parcelable;

public class HomePlateModel implements Parcelable {
    private String bigImg;
    private Integer bigImgHeight;
    private Integer bigImgWidth;
    private String connector;
    private String context;
    private Integer count;
    private Integer id;
    private String plateParentType;
    private String smallImg;
    private Integer smallImgHeight;
    private Integer smallImgWidth;
    private String title;
    private String type;

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
    public Integer getCount() {
        return count;
    }

    /**
     * @param count The count
     */
    public void setCount(Integer count) {
        this.count = count;
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
     * @return The plateParentType
     */
    public String getPlateParentType() {
        return plateParentType;
    }

    /**
     * @param plateParentType The plateParentType
     */
    public void setPlateParentType(String plateParentType) {
        this.plateParentType = plateParentType;
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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bigImg);
        dest.writeValue(this.bigImgHeight);
        dest.writeValue(this.bigImgWidth);
        dest.writeString(this.connector);
        dest.writeString(this.context);
        dest.writeValue(this.count);
        dest.writeValue(this.id);
        dest.writeString(this.plateParentType);
        dest.writeString(this.smallImg);
        dest.writeValue(this.smallImgHeight);
        dest.writeValue(this.smallImgWidth);
        dest.writeString(this.title);
        dest.writeString(this.type);
    }

    public HomePlateModel() {
    }

    protected HomePlateModel(Parcel in) {
        this.bigImg = in.readString();
        this.bigImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.bigImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.connector = in.readString();
        this.context = in.readString();
        this.count = (Integer) in.readValue(Integer.class.getClassLoader());
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.plateParentType = in.readString();
        this.smallImg = in.readString();
        this.smallImgHeight = (Integer) in.readValue(Integer.class.getClassLoader());
        this.smallImgWidth = (Integer) in.readValue(Integer.class.getClassLoader());
        this.title = in.readString();
        this.type = in.readString();
    }

    public static final Parcelable.Creator<HomePlateModel> CREATOR = new Parcelable.Creator<HomePlateModel>() {
        public HomePlateModel createFromParcel(Parcel source) {
            return new HomePlateModel(source);
        }

        public HomePlateModel[] newArray(int size) {
            return new HomePlateModel[size];
        }
    };
}
