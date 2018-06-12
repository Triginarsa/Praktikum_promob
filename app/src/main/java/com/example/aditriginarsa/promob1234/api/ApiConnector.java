package com.example.aditriginarsa.promob1234.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConnector {
    public static final String BASE_URL = "http://192.168.43.50:8000/";
    public static Retrofit retrofit;

    public static Retrofit getApiPegawai(){
        if(retrofit== null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }
}
