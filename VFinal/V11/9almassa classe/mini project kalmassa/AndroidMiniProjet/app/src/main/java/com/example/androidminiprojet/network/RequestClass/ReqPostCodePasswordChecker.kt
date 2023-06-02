package com.example.androidminiprojet.network.RequestClass

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ReqPostCodePasswordChecker {




    @SerializedName("code")
    @Expose
    private lateinit var code: String

    constructor(code: String) {
        this.code = code
    }


    fun getCode() : String {return this.code}
    fun setCode(code : String){this.code = code}

}