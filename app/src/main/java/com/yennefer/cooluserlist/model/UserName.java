package com.yennefer.cooluserlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.yennefer.cooluserlist.StringUtils;

/**
 * Created by Yennefer on 01.07.2016.
 * Класс для хранения данных об имени пользователя
 */
public class UserName implements Parcelable {

    private String first, last;

    public UserName() {
    }

    protected UserName(Parcel in) {
        first = in.readString();
        last = in.readString();
    }

    public String getFirstName() {
        return StringUtils.fromCapitalLetter(first);
    }

    public String getLastName() {
        return StringUtils.fromCapitalLetter(last);
    }

    public static final Creator<UserName> CREATOR = new Creator<UserName>() {
        @Override
        public UserName createFromParcel(Parcel in) {
            return new UserName(in);
        }

        @Override
        public UserName[] newArray(int size) {
            return new UserName[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(first);
        dest.writeString(last);
    }
}
