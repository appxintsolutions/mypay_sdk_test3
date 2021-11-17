package com.mypay.mypay.networks;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String LIVE_BASE_URL= "https://mypay.pk/api/";
    public static final String LOCAL_BASE_URL = "";

    public static Retrofit retrofit = null;

    public static Retrofit getRetrofit(){

        if(retrofit==null){

            retrofit = new Retrofit.Builder().baseUrl(LIVE_BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}