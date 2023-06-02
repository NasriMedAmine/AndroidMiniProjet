package com.example.androidminiprojet.network.RequestClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RequestPostLoginWithToken {

    @SerializedName("token")
    @Expose
    lateinit var myToken: String


    constructor(myToken: String) {
        this.myToken = myToken
    }

    fun setToken(token : String){this.myToken = token}
    fun getToken() : String{return this.myToken}

}