package com.example.androidminiprojet.network.ResponseClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ResGetForgotPassword {




    @SerializedName("name")
    @Expose
    private lateinit var name: String


    @SerializedName("username")
    @Expose
    private lateinit var username: String

    @SerializedName("email")
    @Expose
    private lateinit var email: String

    // eli f west @SerializedName() lezem nafs cle f west json eli jeni men back
    @SerializedName("message")
    @Expose
    private lateinit var mymessage: String


    @SerializedName("succes")
    @Expose
    private lateinit var succes: String

    constructor(name: String, password: String, email: String, mymessage: String, succes: String) {
        this.name = name
        this.username = password
        this.email = email
        this.mymessage = mymessage
        this.succes = succes
    }


    fun getemail(): String? {
        return email
    }

    fun setemail(email: String) {
        this.email = email
    }


    fun getName(): String? {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getusername(): String {
        return username
    }

    fun setusername(message: String) {
        this.username = message
    }














    fun getMessage(): String {
        return mymessage
    }

    fun setMessage(name: String) {
        this.mymessage = name
    }

    fun getsucces(): String {
        return succes
    }

    fun setsucces(note: String) {
        this.succes = note
    }










    fun MessageRes( mymessage: String, succes: String) {

        this.mymessage = mymessage
        this.succes = succes


    }



}