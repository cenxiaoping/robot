package com.bojun.main.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class RemoteEntity implements Parcelable {
    private String webSocketUrl;

    protected RemoteEntity(Parcel in) {
        webSocketUrl = in.readString();
    }

    public static final Creator<RemoteEntity> CREATOR = new Creator<RemoteEntity>() {
        @Override
        public RemoteEntity createFromParcel(Parcel in) {
            return new RemoteEntity(in);
        }

        @Override
        public RemoteEntity[] newArray(int size) {
            return new RemoteEntity[size];
        }
    };

    public String getWebSocketUrl() {
        return webSocketUrl;
    }

    public void setWebSocketUrl(String webSocketUrl) {
        this.webSocketUrl = webSocketUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(webSocketUrl);
    }
}
