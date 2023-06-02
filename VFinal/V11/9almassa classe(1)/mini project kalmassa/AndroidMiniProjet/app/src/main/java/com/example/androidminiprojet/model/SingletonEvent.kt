package com.example.androidminiprojet.model

object SingletonEvent {
    private lateinit var item_view_name : String
    private lateinit var item_view_lieu : String
    private lateinit var item_view_prix : String
    private lateinit var item_view_date_event : String
    private lateinit var item_view_description : String

    public final fun setName(name : String){
        this.item_view_name = name
    }
    public final fun getName():String{
        return this.item_view_name
    }

    public final fun setLieu(lieu : String){
        this.item_view_lieu = lieu
    }
    public final fun getLieu():String{
        return this.item_view_lieu
    }

    public final fun setPrix(prix : String){
        this.item_view_prix = prix
    }
    public final fun getPrix():String{
        return this.item_view_prix
    }

    public final fun setDate(date : String){
        this.item_view_date_event = date
    }
    public final fun getDate():String{
        return this.item_view_date_event
    }

    public final fun setDescription(descri : String){
        this.item_view_description = descri
    }
    public final fun getDescription():String{
        return this.item_view_description
    }



}