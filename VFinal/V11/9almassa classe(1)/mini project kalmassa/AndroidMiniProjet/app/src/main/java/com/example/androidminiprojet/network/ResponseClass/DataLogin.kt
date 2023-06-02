package com.example.androidminiprojet.network.ResponseClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DataLogin(
    @SerializedName("succes")
    @Expose
    val mysucces: String,

    @SerializedName("message")
    @Expose
    val mymessage: String


){


}
