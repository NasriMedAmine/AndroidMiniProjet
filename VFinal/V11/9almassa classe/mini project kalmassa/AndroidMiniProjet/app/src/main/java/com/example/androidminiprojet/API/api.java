package com.example.androidminiprojet.API;


import com.example.androidminiprojet.model.Donation;
import com.example.androidminiprojet.model.Event;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Query;

public interface api {



    @GET("/event")
    Call<List<Event>> findAllEvent();

    @GET("/event/geteventbyname")
    Call<Event> findOneEvent(@Query("name")String name);

    @FormUrlEncoded
    @POST("/event/")
    Call<ResponseBody> addevent(

            @Field("name")String name,
            @Field("lieu")String lieu,
            @Field("prix")String prix,
            @Field("date_event")String date_event,
            @Field("description")String description
    );

    @DELETE("/event/delEventbyname")
    Call<Void>deleteEventByName(@Query("name")String name);

    @FormUrlEncoded
    @PUT("/event/upEventbyname")
    Call<ResponseBody> updateevent(
            @Field("nameOld")String nameOld,
            @Field("name")String name,
            @Field("lieu")String lieu,
            @Field("prix")String prix,
            @Field("date_event")String date_event,
            @Field("description")String description
    );


    @GET("/donation")
    Call<List<Donation>> findAllDonation();

    @GET("/donation/getDonationByTitre")
    Call<Donation> findOneDonation(@Query("titre") String titre);

    @FormUrlEncoded
    @POST("/donation/")
    Call<ResponseBody> addonation(
            @Field("titre") String titre,
            @Field("typedonation") Set<String> typedonation,
            @Field("description")String description
    );

    @DELETE("/donation/delDonationbytitre")
    Call<Void>deleteDonationByTitre(@Query("titre") String titre);

    @FormUrlEncoded
    @PUT("/donation/upDonationByTitre")
    Call<ResponseBody> updatedonation(
            @Field("titreOld") String titreOld,
            @Field("titre") String titre,
            @Field("typedonation") Set<String> typedonation,
            @Field("description")String description
    );
}
