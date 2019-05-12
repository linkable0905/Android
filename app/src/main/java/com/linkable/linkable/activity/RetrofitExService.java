package com.linkable.linkable.activity;

import com.linkable.linkable.Data;
import com.linkable.linkable.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.DELETE;
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
    //책하나가져오기
    @GET("book")
    Call<Data> book(@Header("Authorization") String token, @Query("node") int node);

    //책추가하기
    @POST("book")
    Call<Data> addMyBook(@Header("Authorization") String token, @Query("node") int node);

    @DELETE("book")
    Call<Data> deleteMyBook(@Header("Authorization") String token, @Query("node") int node);

    //베스트셀러 다 가져오기
    @GET("best")
    Call<List<Data>> best();

    //베스트 5개 가져오기
    @GET("best5")
    Call<List<Data>> best5();

    //검색
    @GET("search")
    Call<List<Data>> search(@Query("pattern")String pattern);

    //추천책 가져오기
    @GET("rank")
    Call<List<Data>> recommend(@Header("Authorization") String token);

    //유저정보 가져오기
    @GET("user/{id}")
    Call<List<Data>> user(@Path("id")String id);

    //유저의 내 책목록 가져오기
    @GET("mybook")
    Call<List<Data>> mybook(@Header("Authorization") String token);

    //로그인하기
    @FormUrlEncoded
    @POST("login")
    Call<User> postFirst(@FieldMap HashMap<String, Object> parameters);

    //회원가입하기
    @FormUrlEncoded
    @POST("register")
    Call<User> register(@FieldMap HashMap<String, Object> parameters);

}