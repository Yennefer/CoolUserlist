package com.yennefer.cooluserlist.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Yennefer on 01.07.2016.
 * Класс для хранения данных об адресе пользователя
 */
public class UserAddress implements Parcelable {

    private String street, city, state, postcode;

    public UserAddress() {
    }

    protected UserAddress(Parcel in) {
        street = in.readString();
        city = in.readString();
        state = in.readString();
        postcode = in.readString();
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getPostcode() {
        return postcode;
    }

    public static final Creator<UserAddress> CREATOR = new Creator<UserAddress>() {
        @Override
        public UserAddress createFromParcel(Parcel in) {
            return new UserAddress(in);
        }

        @Override
        public UserAddress[] newArray(int size) {
            return new UserAddress[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(street);
        dest.writeString(city);
        dest.writeString(state);
        dest.writeString(postcode);
    }

}
