package com.example.androidminiprojet.utils

import android.R
import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.File


object FileManagementSecret {

    lateinit var token : String
    var tokenText : String = ""

    fun SaveFileToken(token : String,context : Context){
        val file = File(context.filesDir.absoluteFile, "token.txt")
        context.openFileOutput("token.txt", Context.MODE_PRIVATE).use {
            it.write(token.toByteArray())
        }
    }

    @JvmName("setToken1")
    public final fun setToken(token: String){
        this.token = token
    }

    @JvmName("getToken1")
    public final fun getToken() : String {
        return this.token
    }


    public final fun checkTokenFileForPostLogin(context : Context) : Boolean{
        val file2 = File(context.filesDir.absoluteFile, "token.txt")
        Log.i("token","tao f FileManagement  w f checkTokenFileForPostLogin ")
        if (file2.exists()){
            Log.i("token","tao dkhalt l file exists")
            file2.forEachLine { this.tokenText += it }
            this.setToken(tokenText)
            Log.i("token","tao aamalt appel l string token w hatitha myToken")
            Log.i("token","hedhi token hazitha men file")
            Log.i("token", tokenText)

            return true
        }

        return false


    }




    //=============================================================================
    // In a list of lines
//    private fun readFileAsLinesUsingReadLines(fileName: String): List<String>
//            = File(fileName).readLines()
//    // As a sole string
//    private fun readFileDirectlyAsText(fileName: String): String
//            = File(fileName).readText(Charsets.UTF_8)
//    // Line by line
//    private fun readFileLineByLineUsingForEachLine(fileName: String)
//            = File(fileName).forEachLine { println(it) }


    //========================================================================



}

