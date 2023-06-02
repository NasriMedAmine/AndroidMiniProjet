package com.example.androidminiprojet.utils

import kotlin.properties.Delegates

object SingletonDataInMyApp {

    lateinit var myNameUser : String
    lateinit var myEmailUser : String
    lateinit var myUsernameUser : String


    lateinit var nameUserProfileCibleRecherche : String
    lateinit var firstNameUserProfileCibleRecherche : String


    var isListUserFromServerDone : Boolean = false
    public final fun setisListUserFromServerDone(input  : Boolean){
        this.isListUserFromServerDone = input
    }

    public final fun getisListUserFromServerDone() : Boolean {
        return this.isListUserFromServerDone
    }



    lateinit var emailToUserChat : String


    public final fun setemailToUserChat(input  : String){
        this.emailToUserChat = input
    }

    public final fun getemailToUserChat() : String {
        return this.emailToUserChat
    }





    public final fun setnameUserProfileCibleRecherche(input  : String){
        this.nameUserProfileCibleRecherche = input
    }

    public final fun getnameUserProfileCibleRecherche() : String {
        return this.nameUserProfileCibleRecherche
    }


    public final fun setmyNfirstNameUserProfileCibleRecherche(input  : String){
        this.firstNameUserProfileCibleRecherche = input
    }

    public final fun getfirstNameUserProfileCibleRecherche() : String {
        return this.firstNameUserProfileCibleRecherche
    }














    public final fun setmyNameUser(input  : String){
        this.myNameUser = input
    }

    public final fun getmyNameUser() : String {
        return this.myNameUser
    }

    public final fun setmyEmailUser(input  : String){
        this.myEmailUser = input
    }

    public final fun getmyEmailUser() : String {
        return this.myEmailUser
    }


    public final fun setmyUsernameUser(input  : String){
        this.myUsernameUser = input
    }

    public final fun getmyUsernameUser() : String {
        return this.myUsernameUser
    }
}