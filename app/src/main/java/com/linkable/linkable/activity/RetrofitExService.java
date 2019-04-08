package com.linkable.linkable.activity;

import com.linkable.linkable.Data;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface RetrofitExService {
    @GET("book/{id}/")
    Call<Data> gettitle(@Path("id")String id);
    @GET("book/")
    Call<List<Data>> index();
    @GET("user/{id}")
    Call<List<Data>> recommend(@Path("id")String id);
}