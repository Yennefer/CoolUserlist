package com.yennefer.cooluserlist.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.yennefer.cooluserlist.R;
import com.yennefer.cooluserlist.StringUtils;

/**
 * Created by Yennefer on 01.07.2016.
 * Класс для хранения данных о пользователе
 */
public class User implements Parcelable {

    private UserName name = new UserName();
    private UserPhoto picture = new UserPhoto();
    private UserAddress location = new UserAddress();
    private String email, phone, nat;

    protected User(Parcel in) {
        name = in.readParcelable(UserName.class.getClassLoader());
        picture = in.readParcelable(UserPhoto.class.getClassLoader());
        location = in.readParcelable(UserAddress.class.getClassLoader());
        email = in.readString();
        phone = in.readString();
        nat = in.readString();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getFirstName() {
        return StringUtils.leftToRightText(name.getFirstName());
    }

    public String getLastName() {
        return StringUtils.leftToRightText(name.getLastName());
    }

    public String getLargePhoto() {
        return picture.getLarge();
    }

    public String getMediumPhoto() {
        return picture.getMedium();
    }

    public String getStreet() {
        return StringUtils.leftToRightText(location.getStreet());
    }

    public String getCity() {
        return StringUtils.leftToRightText(location.getCity());
    }

    public String getState() {
        return StringUtils.leftToRightText(location.getState());
    }

    public String getPostcode() {
        return StringUtils.leftToRightText(location.getPostcode());
    }

    public String getEmail() {
        return StringUtils.leftToRightText(email);
    }

    public String getNationality() {
        return nat;
    }

    public String getPhone() {
        return phone;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(name, flags);
        dest.writeParcelable(picture, flags);
        dest.writeParcelable(location, flags);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(nat);
    }
}
