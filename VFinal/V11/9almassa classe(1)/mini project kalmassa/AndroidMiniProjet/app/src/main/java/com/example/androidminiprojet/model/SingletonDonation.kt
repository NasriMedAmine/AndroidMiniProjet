package com.example.androidminiprojet.model

object SingletonDonation {
    private lateinit var item_view_titre : String
    private lateinit var item_view_typeDonation : Set<String>
    private lateinit var item_view_description : String

    public final fun setTitre(titre : String){
        this.item_view_titre = titre
    }
    public final fun getTitre():String{
        return this.item_view_titre
    }

    public final fun setTypeDonation(typeDonation : Set<String>){
        this.item_view_typeDonation = typeDonation
    }
    public final fun getTypeDonation():Set<String>{
        return this.item_view_typeDonation
    }

    public final fun setDescri(descri : String){
        this.item_view_description = descri
    }
    public final fun getDescri():String{
        return this.item_view_description
    }


}