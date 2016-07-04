package com.yennefer.cooluserlist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yennefer on 01.07.2016.
 * Класс для хранения ссылок на фото пользователя
 */
public class UserPhoto implements Parcelable {

    private String large, medium, thumbnail;

    public UserPhoto() {
    }

    protected UserPhoto(Parcel in) {
        large = in.readString();
        medium = in.readString();
        thumbnail = in.readString();
    }

    public String getLarge() {
        return large;
    }

    public String getMedium() {
        return medium;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public static final Creator<UserPhoto> CREATOR = new Creator<UserPhoto>() {
        @Override
        public UserPhoto createFromParcel(Parcel in) {
            return new UserPhoto(in);
        }

        @Override
        public UserPhoto[] newArray(int size) {
            return new UserPhoto[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(large);
        dest.writeString(medium);
        dest.writeString(thumbnail);
    }
}
