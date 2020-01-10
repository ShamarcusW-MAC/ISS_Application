
package com.example.iss_application.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings("unused")
public class Location {

    @SerializedName("iss_position")
    private IssPosition mIssPosition;
    @SerializedName("message")
    private String mMessage;
    @SerializedName("timestamp")
    private Long mTimestamp;

    public IssPosition getIssPosition() {
        return mIssPosition;
    }

    public void setIssPosition(IssPosition issPosition) {
        mIssPosition = issPosition;
    }

    public String getMessage() {
        return mMessage;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

    public Long getTimestamp() {
        return mTimestamp;
    }

    public void setTimestamp(Long timestamp) {
        mTimestamp = timestamp;
    }

}
