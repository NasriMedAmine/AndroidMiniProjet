package com.example.androidminiprojet.Adaptor

import android.content.Context
import android.content.Intent

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.ListeventActivity
import com.example.androidminiprojet.R
import com.example.androidminiprojet.UpdateEventFragment
import com.example.androidminiprojet.model.Event
import com.example.androidminiprojet.model.SingletonEvent
import com.example.androidminiprojet.updateEventActivity

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyAdaptor(
    val context: Context?, val eventlist: List<Event>) : RecyclerView.Adapter<MyAdaptor.ViewHolder>(){
    private lateinit var callDelete : Call<Void>
    private lateinit var callGetOne : Call<Event>



    class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){


        var name_event : TextView
        var lieu_event : TextView
        var prix_event : TextView
        var date_event : TextView
        var description_event : TextView
        var deleteEvent : ImageView = itemView.findViewById<ImageView>(R.id.oneEventdeleteBtn)
        var updateEvent : ImageView = itemView.findViewById<ImageView>(R.id.updateBtn)



        init {
            name_event  = itemView.findViewById<TextView>(R.id.name_event)
            lieu_event  = itemView.findViewById<TextView>(R.id.lieu_event)
            prix_event  = itemView.findViewById<TextView>(R.id.prix_event)
            date_event  = itemView.findViewById<TextView>(R.id.date_event)
            description_event  = itemView.findViewById<TextView>(R.id.description_event)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        var  itemView = LayoutInflater.from(context).inflate(R.layout.one_item_recycle_view,parent,false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val event : Event = eventlist[position]
        holder.name_event.text = event.name.toString()
        holder.lieu_event.text = event.lieu.toString()
        holder.prix_event.text = event.prix.toString()
        holder.date_event.text = event.date_event.toString()
        holder.description_event.text = event.description.toString()

        holder.updateEvent.setOnClickListener {
            SingletonEvent.setName(name = holder.name_event.text.toString() )
            SingletonEvent.setLieu(lieu = holder.lieu_event.text.toString() )
            SingletonEvent.setPrix(prix = holder.prix_event.text.toString() )
            SingletonEvent.setDate(date = holder.date_event.text.toString() )
            SingletonEvent.setDescription(descri = holder.description_event.text.toString() )
            Navigation.findNavController(holder.itemView).navigate(R.id.action_listeEventFragment_to_updateEventFragment2)
        }
        holder.deleteEvent.setOnClickListener {

            callDelete = RetrofitClient.getInstance().api.deleteEventByName(holder.name_event.text.toString())
            callDelete.enqueue(object : Callback<Void?> {
                override fun onResponse(call: Call<Void?>, response: Response<Void?>) {
                    if(response.isSuccessful){
                        Navigation.findNavController(holder.itemView).navigate(R.id.action_listeEventFragment_self)
                    } }
                override fun onFailure(call: Call<Void?>, t: Throwable) {

                }
            })
        }
    }



    override fun getItemCount(): Int {
        return eventlist.size
    }


}