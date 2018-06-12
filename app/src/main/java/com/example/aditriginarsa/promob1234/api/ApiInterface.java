package com.example.aditriginarsa.promob1234.api;


import com.example.aditriginarsa.promob1234.model.PegawaiList;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiInterface {
    @GET("pegawai")
    Call<List<PegawaiList>> getPegawai();

    @FormUrlEncoded
    @POST("pegawai")
    Call<JSONObject> register(@Field("nama") String nama,
                              @Field("email") String email,
                              @Field("jk") String jk,
                              @Field("hobby") String hobby,
                              @Field("serius") String serius);

    @DELETE("pegawai/{id}")
    Call<PegawaiList> getDelete(@Path("id") String id);

    @FormUrlEncoded
    @PUT(("pegawai/{id}"))
    Call<JSONObject> getUpdate(@Path("id") String id,
                               @Field("nama") String nama,
                               @Field("email") String email,
                               @Field("jk") String jk,
                               @Field("hobby") String hobby,
                               @Field("serius") String serius);
}
