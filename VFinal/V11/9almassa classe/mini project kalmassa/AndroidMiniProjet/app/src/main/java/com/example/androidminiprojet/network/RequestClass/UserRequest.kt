package com.example.androidminiprojet.network.RequestClass

import android.annotation.SuppressLint
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("NotConstructor")

class UserRequest {



















    @SerializedName("username")
    @Expose
    lateinit var mysername: String


    @SerializedName("tel")
    @Expose
    lateinit var myTel: Number


    @SerializedName("firstname")
    @Expose
    lateinit var myfirstname: String




    fun getmyfirstname(): String {
        return this.myfirstname
    }

    fun setmyfirstname(name: String) {
        this.myfirstname = name
    }


    fun getmysername(): String {
        return this.mysername
    }

    fun setmysername(name: String) {
        this.mysername = name
    }


    fun getmyTel(): Number {
        return this.myTel
    }

    fun setmyTel(name: Number) {
        this.myTel = name
    }












    @SerializedName("name")
    @Expose
    lateinit var myname: String

//    @SerializedName("username")
//    @Expose
//    private lateinit var myusername: String



    @SerializedName("password")
    @Expose
    private lateinit var mypassword: String


    @SerializedName("email")
    @Expose
    private lateinit var myemail: String

    constructor(
        mysername: String,
        myTel: Number,
        myfirstname: String,
        myname: String,
        mypassword: String,
        myemail: String
    ) {
        this.mysername = mysername
        this.myTel = myTel
        this.myfirstname = myfirstname
        this.myname = myname
        this.mypassword = mypassword
        this.myemail = myemail
    }


    fun getName(): String {
        return myname
    }

    fun setNAme(name: String) {
        this.myname = name
    }

    fun getPassword(): String {
        return mypassword
    }

    fun setPassword(note: String) {
        this.mypassword = note
    }

    fun getEmail(): String {
        return myemail
    }

    fun setEmail(color: String) {
        this.myemail = color
    }










    fun User( name: String, password: String, email: String) {

        this.myname = name
        this.mypassword = password
        this.myemail = email

    }


}