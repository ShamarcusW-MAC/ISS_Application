
package com.example.iss_application.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Response {

    @SerializedName("duration")
    private Long mDuration;
    @SerializedName("risetime")
    private Long mRisetime;

    public Long getDuration() {
        return mDuration;
    }

    public void setDuration(Long duration) {
        mDuration = duration;
    }

    public Long getRisetime() {
        return mRisetime;
    }

    public void setRisetime(Long risetime) {
        mRisetime = risetime;
    }

}
