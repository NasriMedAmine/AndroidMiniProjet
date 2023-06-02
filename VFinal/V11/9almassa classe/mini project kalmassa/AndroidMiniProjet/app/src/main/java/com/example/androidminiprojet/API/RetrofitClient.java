package com.example.androidminiprojet.API;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    private static String Base_Url ="http://192.168.43.118:9090";
    private static RetrofitClient mInstance;
    private Retrofit retrofit;

    private RetrofitClient(){
        retrofit = new Retrofit.Builder().baseUrl(Base_Url).addConverterFactory(GsonConverterFactory.create()).build();

    }

    public static  synchronized  RetrofitClient getInstance(){
        if(mInstance==null){
            mInstance= new RetrofitClient();
        }
        return mInstance;

    }

    public api getApi(){
        return retrofit.create(api.class);
    }




}
