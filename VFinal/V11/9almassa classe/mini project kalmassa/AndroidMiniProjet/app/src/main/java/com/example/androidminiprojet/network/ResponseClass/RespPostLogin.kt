package com.example.androidminiprojet.network.ResponseClass

import android.annotation.SuppressLint
import android.util.Log
import com.example.androidminiprojet.network.RequestClass.UserRequest
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("NotConstructor")
class RespPostLogin {





    @SerializedName("succes")
    @Expose
    private lateinit var mysucces: String




    @SerializedName("message")
    @Expose
    private lateinit var mymessage: String



    @SerializedName("token")
    @Expose
    private lateinit var myToken: String




    @SerializedName("user")
    @Expose
    private lateinit var myUserResp: UserRes


    constructor(mysucces: String, message: String, myToken: String, myUserResp: UserRes) {
        this.mysucces = mysucces
        this.mymessage = message
        this.myToken = myToken
        this.myUserResp = myUserResp
    }

    fun setSucces(succes : String){
        this.mysucces = succes
    }
    fun setUser(myUserResp : UserRes) {this.myUserResp = myUserResp}

    fun getSucces(): String {
        return this.mysucces
    }
    fun getUser(): UserRes {
        return this.myUserResp
    }

    fun getToken(): String {
        return this.myToken
    }

    fun getMessage(): String {
        return this.mymessage
    }

    fun RespPostLogin(mysucces: String, message: String, myToken: String, myUserResp: UserRes) {
        this.mysucces = mysucces
        this.mymessage = message
        this.myToken = myToken
        this.myUserResp = myUserResp
    }
}






//     fun getemail(): String? {
//         return email
//     }
//
//     fun setemail(email: String?) {
//         this.email = email
//     }
//
