package com.yennefer.cooluserlist.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Yennefer on 01.07.2016.
 * Интерфейс для обращения к randomuser API.
 */
public interface IRestAPI {

    @GET("http://api.randomuser.me/")
    Call<UsersData> getRandomUsers(@Query("results") int usersCount);
}
