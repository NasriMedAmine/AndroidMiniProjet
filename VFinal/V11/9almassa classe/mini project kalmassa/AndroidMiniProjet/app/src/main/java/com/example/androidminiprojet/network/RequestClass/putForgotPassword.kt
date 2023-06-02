package com.example.androidminiprojet.network.RequestClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class putForgotPassword {


    @SerializedName("newcode")
    @Expose
    private var newcode: String

    @SerializedName("email")
    @Expose
    private var email: String

    constructor(password: String, email: String) {

        this.newcode = password
        this.email = email
    }


    fun getemail(): String? {
        return this.email
    }

    fun setemail(email: String) {
        this.email = email
    }


    fun getpassword(): String {
        return this.newcode
    }

    fun setpassword(message: String) {
        this.newcode
    }
}