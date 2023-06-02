package com.example.androidminiprojet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.Adaptor.MyAdaptorDonation
import com.example.androidminiprojet.model.Donation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListedonationActivity : AppCompatActivity() {

    private lateinit var call : Call<List<Donation>>
    private lateinit var myAdaptorDonation: MyAdaptorDonation
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listedonation)

        recyclerView = findViewById(R.id.recycle_view_donation)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager


        getDataDonation()
    }

    private fun getDataDonation() {
        call = RetrofitClient.getInstance().api.findAllDonation()
        call.enqueue(object : Callback<List<Donation>?> {
            override fun onResponse(
                call: Call<List<Donation>?>,
                response: Response<List<Donation>?>
            ) {
                val responseBody = response.body()!!
                myAdaptorDonation = MyAdaptorDonation(baseContext,responseBody)
                myAdaptorDonation.notifyDataSetChanged()
                recyclerView.adapter=myAdaptorDonation

            }

            override fun onFailure(call: Call<List<Donation>?>, t: Throwable) {
                Toast.makeText(this@ListedonationActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}