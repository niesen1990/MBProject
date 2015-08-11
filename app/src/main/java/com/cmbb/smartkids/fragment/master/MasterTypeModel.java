package com.cmbb.smartkids.fragment.master;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by N.Sun
 */
public class MasterTypeModel implements Parcelable {

    private Integer eredarType;
    private String name;
    private Integer rec = -1;

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

    public MasterTypeModel() {
    }

    public MasterTypeModel(String name, int type, int rec) {
        setName(name);
        setEredarType(type);
        setRec(rec);

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

    protected MasterTypeModel(Parcel in) {
        this.eredarType = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.rec = (Integer) in.readValue(Integer.class.getClassLoader());
    }

    public static final Creator<MasterTypeModel> CREATOR = new Creator<MasterTypeModel>() {
        public MasterTypeModel createFromParcel(Parcel source) {
            return new MasterTypeModel(source);
        }

        public MasterTypeModel[] newArray(int size) {
            return new MasterTypeModel[size];
        }
    };
}
