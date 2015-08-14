package com.cmbb.smartkids.fragment.eredar;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class EredarTypeModel implements Parcelable {

    private Integer eredarType;
    private String name;
    private Integer rec = -1;

    public EredarTypeModel(Integer eredarType, String name, Integer rec) {
        this.eredarType = eredarType;
        this.name = name;
        this.rec = rec;
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
     * @return The name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name The name
     */
    public void setName(String name) {
        this.name = name;
    }

    public Integer getRec() {
        return rec;
    }

    public void setRec(Integer rec) {
        this.rec = rec;
    }

    public EredarTypeModel() {
    }



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.eredarType);
        dest.writeString(this.name);
        dest.writeValue(this.rec);
    }

    protected EredarTypeModel(Parcel in) {
        this.eredarType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.rec = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<EredarTypeModel> CREATOR = new Creator<EredarTypeModel>() {
        public EredarTypeModel createFromParcel(Parcel source) {
            return new EredarTypeModel(source);
        }

        public EredarTypeModel[] newArray(int size) {
            return new EredarTypeModel[size];
        }
    };
}
