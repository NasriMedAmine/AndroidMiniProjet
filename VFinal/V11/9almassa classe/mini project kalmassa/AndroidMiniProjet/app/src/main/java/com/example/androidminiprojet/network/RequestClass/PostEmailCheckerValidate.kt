package com.example.androidminiprojet.network.RequestClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PostEmailCheckerValidate {












    @SerializedName("code")
    @Expose
    private lateinit var mycode: String






    @SerializedName("email")
    @Expose
    private lateinit var myemail: String

    constructor(
        myemail: String,
        mycode: String

    ) {

        this.mycode = mycode
        this.myemail = myemail
    }



    fun getEmail(): String {
        return myemail
    }

    fun setEmail(color: String) {
        this.myemail = color
    }


    fun getmycode(): String {
            return mycode
        }

        fun setmycode(color: String) {
            this.mycode = color
        }









}