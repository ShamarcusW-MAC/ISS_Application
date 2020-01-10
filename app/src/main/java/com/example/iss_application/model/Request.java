
package com.example.iss_application.model;


import com.google.gson.annotations.SerializedName;
@SuppressWarnings("unused")
public class Request {

    @SerializedName("altitude")
    private Long mAltitude;
    @SerializedName("datetime")
    private Long mDatetime;
    @SerializedName("latitude")
    private Double mLatitude;
    @SerializedName("longitude")
    private Double mLongitude;
    @SerializedName("passes")
    private Long mPasses;

    public Long getAltitude() {
        return mAltitude;
    }

    public void setAltitude(Long altitude) {
        mAltitude = altitude;
    }

    public Long getDatetime() {
        return mDatetime;
    }

    public void setDatetime(Long datetime) {
        mDatetime = datetime;
    }

    public Double getLatitude() {
        return mLatitude;
    }

    public void setLatitude(Double latitude) {
        mLatitude = latitude;
    }

    public Double getLongitude() {
        return mLongitude;
    }

    public void setLongitude(Double longitude) {
        mLongitude = longitude;
    }

    public Long getPasses() {
        return mPasses;
    }

    public void setPasses(Long passes) {
        mPasses = passes;
    }

}
