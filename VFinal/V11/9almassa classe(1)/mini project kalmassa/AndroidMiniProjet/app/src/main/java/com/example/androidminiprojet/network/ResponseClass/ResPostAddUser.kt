package com.example.androidminiprojet.network.ResponseClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResPostAddUser {

    @SerializedName("message")
    @Expose
    private lateinit var mymessage: String



    @SerializedName("succes")
    @Expose
    private lateinit var mysucces: String

    constructor(mymessage: String, mysucces: String) {
        this.mymessage = mymessage
        this.mysucces = mysucces
    }


    fun setMessage(message : String){this.mymessage = message}
    fun getMessage() : String {return this.mymessage}


    fun setmysucces(message : String){this.mysucces = message}
    fun getmysucces() : String {return this.mysucces}

}