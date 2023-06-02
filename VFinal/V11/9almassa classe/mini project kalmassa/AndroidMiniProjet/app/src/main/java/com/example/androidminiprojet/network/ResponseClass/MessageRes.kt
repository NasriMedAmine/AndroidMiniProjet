package ir.almasapps.kotlinretrofitcrud.Model

import android.annotation.SuppressLint
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@SuppressLint("NotConstructor")

class MessageRes {





    // eli f west @SerializedName() lezem nafs cle f west json eli jeni men back
    @SerializedName("message")
    @Expose
    private lateinit var mymessage: String


    @SerializedName("succes")
    @Expose
    private lateinit var succes: String


    constructor( mymessage: String, succes: String) {

        this.mymessage = mymessage
        this.succes = succes


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