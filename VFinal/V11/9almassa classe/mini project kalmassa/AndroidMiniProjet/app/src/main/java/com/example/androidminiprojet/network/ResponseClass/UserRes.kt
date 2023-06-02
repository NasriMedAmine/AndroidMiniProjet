package com.example.androidminiprojet.network.ResponseClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class UserRes {


    @SerializedName("name")
    @Expose
    private var name: String


    @SerializedName("password")
    @Expose
    private var password: String

    @SerializedName("email")
    @Expose
    private var email: String

    constructor(name: String, password: String, email: String) {
        this.name = name
        this.password = password
        this.email = email
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

    fun getpassword(): String {
        return password
    }

    fun setpassword(message: String) {
        this.password = message
    }
}