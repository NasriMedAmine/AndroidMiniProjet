package com.example.androidminiprojet.RecyclerView

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.R

class AdapterMessageChat (private val mList2: ArrayList<itemMessageChat> ,val context : Context?) : RecyclerView.Adapter<AdapterMessageChat.MyViewMessage>() {

    class MyViewMessage(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val messageChat = itemView.findViewById<TextView>(R.id.tv_send_message)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMessage {
        Log.i("recycleview", "1")
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_send_message_layout, parent, false)
        return AdapterMessageChat.MyViewMessage(view)
    }

    override fun onBindViewHolder(holder: MyViewMessage, position: Int) {
        val itemsViewModel2 = mList2[position]
        holder.messageChat.text = itemsViewModel2.messageChat



    }

    override fun getItemCount(): Int {
        return mList2.size
    }
}