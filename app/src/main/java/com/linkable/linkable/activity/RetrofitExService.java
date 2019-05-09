package com.linkable.linkable.activity;

import com.linkable.linkable.Data;
import com.linkable.linkable.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitExService {
    @GET("book/{id}")
    Call<Data> book(//@Header("Authorization") String token,
                    @Path("id")int id);
    @FormUrlEncoded
    @POST("book/{id}")
    Call<Data> addMyBook(@Header("Authorization") String token,@Path("id")int id,@Field("id") int pk);

    @GET("best")
    Call<List<Data>> best();

    @GET("user/{id}")
    Call<List<Data>> recommend(@Path("id")String id);

    @FormUrlEncoded
    @POST("login")
    Call<User> postFirst(@FieldMap HashMap<String, Object> parameters);




}