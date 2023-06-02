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
import com.example.androidminiprojet.Adaptor.MyAdaptorDonation
import com.example.androidminiprojet.model.Donation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class ListeDonationFragment : Fragment() {


    var thiscontext : Context? = null

    private lateinit var call: Call<List<Donation>>
    private lateinit var myAdaptorDonation: MyAdaptorDonation
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_liste_donation, container, false)

        recyclerView = rootview.findViewById(R.id.rv_donation_fragment)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(thiscontext)
        recyclerView.layoutManager = linearLayoutManager


        getDonationData()

        thiscontext = container!!.context
        return rootview
    }

    private fun getDonationData() {
        call = RetrofitClient.getInstance().api.findAllDonation()
        call.enqueue(object : Callback<List<Donation>?> {
            override fun onResponse(
                call: Call<List<Donation>?>,
                response: Response<List<Donation>?>
            ) {
                val responseBody = response.body()!!
                myAdaptorDonation = MyAdaptorDonation(context,responseBody)
                myAdaptorDonation.notifyDataSetChanged()
                recyclerView.adapter=myAdaptorDonation

            }

            override fun onFailure(call: Call<List<Donation>?>, t: Throwable) {
                Toast.makeText(thiscontext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }


}