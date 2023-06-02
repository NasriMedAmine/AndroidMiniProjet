package com.example.androidminiprojet.RecyclerView

import androidx.recyclerview.widget.RecyclerView


import android.content.Context
import android.content.Intent
//import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat.startActivity
//import androidx.core.content.ContextCompat.startActivity
import com.example.androidminiprojet.ChatAcActivity
import com.example.androidminiprojet.PickImageForProfileActivity
import com.example.androidminiprojet.ProfileChercheActivity
import com.example.androidminiprojet.R
import com.example.androidminiprojet.utils.SingletonDataInMyApp

//import androidx.recyclerview.widget.RecyclerView



class AdpaterAllUsers (private val mList2: ArrayList<AllUsersRVData> ,val context : Context?) : RecyclerView.Adapter<AdpaterAllUsers.MyViewHolderTP6>() {

    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolderTP6 {
        Log.i("recycleview", "1")
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.rv_allusers, parent, false)
        return MyViewHolderTP6(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: MyViewHolderTP6, position: Int) {
        val ItemsViewModel2 = mList2[position]



        holder.nom.text = ItemsViewModel2.lastNamee
        holder.firstName.text = ItemsViewModel2.firstName
        holder.nameImage.setImageResource(ItemsViewModel2.nameImage)



        holder.cardView.setOnClickListener({
            SingletonDataInMyApp.setnameUserProfileCibleRecherche(holder.nom.text.toString())
            SingletonDataInMyApp.setmyNfirstNameUserProfileCibleRecherche(holder.firstName.text.toString())



            val intentToMAin = Intent (this.context, ProfileChercheActivity::class.java )
            startActivity(this.context!!,intentToMAin,null)
        })


        holder.checkProfile.setOnClickListener({
            Log.i("checkProfile" , "checkProfile")
            Log.i("checkProfile" , holder.nom.text.toString())


            SingletonDataInMyApp.setnameUserProfileCibleRecherche(holder.nom.text.toString())
            SingletonDataInMyApp.setmyNfirstNameUserProfileCibleRecherche(holder.firstName.text.toString())



            val intentToMAin = Intent (this.context, ProfileChercheActivity::class.java )
            startActivity(this.context!!,intentToMAin,null)




        })


    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        Log.i("recycleview" , "tao site mtaa list f Adapter = "+ mList2.size.toString()  )
        return mList2.size
    }

    // Holds the views for adding it to image and text
    class MyViewHolderTP6(ItemView: View) : RecyclerView.ViewHolder(ItemView) {


        val nom = itemView.findViewById<TextView>(R.id.idLastNameRV)
        val firstName = itemView.findViewById<TextView>(R.id.idFirstNameRV)
        val nameImage = itemView.findViewById<ImageView>(R.id.idImageRV)
        val checkProfile = itemView.findViewById<TextView>(R.id.idCheckThisProfile)
        val cardView = itemView.findViewById<CardView>(R.id.idCardView)











    }
}