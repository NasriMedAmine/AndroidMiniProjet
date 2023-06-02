package com.example.androidminiprojet


import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.Adaptor.MyAdaptor
import com.example.androidminiprojet.model.Event
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeEventFragment : Fragment() {

    var thiscontext : Context? = null

    private lateinit var call: Call<List<Event>>
    private lateinit var myAdaptor: MyAdaptor
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView




    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_liste_event, container, false)

        recyclerView = rootview.findViewById(R.id.rv_event_fragMent)
        recyclerView.hasFixedSize()
        linearLayoutManager = LinearLayoutManager(thiscontext)
        recyclerView.layoutManager = linearLayoutManager

        getDataEvent()

        thiscontext = container!!.context
        return rootview
    }

    private fun getDataEvent() {
        call = RetrofitClient.getInstance().api.findAllEvent()
        call.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(call: Call<List<Event>?>, response: Response<List<Event>?>) {
                val responseBody = response.body()!!
                myAdaptor = MyAdaptor(context,responseBody)
                myAdaptor.notifyDataSetChanged()
                recyclerView.adapter = myAdaptor

            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                Toast.makeText(thiscontext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}