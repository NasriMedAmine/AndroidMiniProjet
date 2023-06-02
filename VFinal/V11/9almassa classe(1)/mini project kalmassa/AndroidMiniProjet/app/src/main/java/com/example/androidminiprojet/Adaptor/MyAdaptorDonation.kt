package com.example.androidminiprojet.Adaptor

import android.content.Context
import android.content.Intent
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.ListedonationActivity
import com.example.androidminiprojet.R
import com.example.androidminiprojet.model.Donation
import com.example.androidminiprojet.model.SingletonDonation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdaptorDonation(val context: Context?, val donationList: List<Donation>) : RecyclerView.Adapter<MyAdaptorDonation.ViewHolder>() {
    private lateinit var callDelete : Call<Void>
    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){

        var titre : TextView
        var typedonation : TextView
        var description : TextView
        var deleteDonation : ImageView = itemView.findViewById(R.id.onedonationdeleteBtn)
        var updateDonation : ImageView = itemView.findViewById(R.id.updateBtndonation)

        init {
            titre = itemView.findViewById(R.id.titreItemView)
            typedonation = itemView.findViewById(R.id.typedonation_donation)
            description = itemView.findViewById(R.id.description_donation)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var itemView = LayoutInflater.from(context).inflate(R.layout.one_donation_recycle_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.titre.text = donationList[position].titre.toString()
        holder.typedonation.text = donationList[position].typedonation.toString()
        holder.description.text = donationList[position].description.toString()
        holder.updateDonation.setOnClickListener{
            SingletonDonation.setTitre(titre = holder.titre.text.toString())
            SingletonDonation.setDescri(descri = holder.description.text.toString())
            Navigation.findNavController(holder.itemView).navigate(R.id.action_listeDonationFragment_to_updateDonationFragment)
        }
        holder.deleteDonation.setOnClickListener {
            callDelete = RetrofitClient.getInstance().api.deleteDonationByTitre(holder.titre.text.toString())
            callDelete.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if(response.isSuccessful){
                        Navigation.findNavController(holder.itemView).navigate(R.id.action_listeDonationFragment_self)
                    }
                }
                override fun onFailure(call: Call<Void?>, t: Throwable) {
                    //Toast.makeText(this@ListeventActivity, t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    override fun getItemCount(): Int {
        return donationList.size
    }
}