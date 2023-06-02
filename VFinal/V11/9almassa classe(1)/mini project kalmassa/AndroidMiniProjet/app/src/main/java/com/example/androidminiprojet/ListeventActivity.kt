package com.example.androidminiprojet


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.Adaptor.MyAdaptor
import com.example.androidminiprojet.model.Event


import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ListeventActivity : AppCompatActivity() {

    private lateinit var callDelete : Call<Void>
    private lateinit var call : Call<List<Event>>
    private lateinit var myAdaptor: MyAdaptor
    private lateinit var linearLayoutManager : LinearLayoutManager
    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.listevent)


        recyclerView = findViewById(R.id.recycle_view_user)
        recyclerView.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = linearLayoutManager

        getdata()

    }


    private fun getdata() {
        call = RetrofitClient.getInstance().api.findAllEvent()
        call.enqueue(object : Callback<List<Event>?> {
            override fun onResponse(call: Call<List<Event>?>, response: Response<List<Event>?>) {
                val responseBody = response.body()!!
                myAdaptor = MyAdaptor(baseContext,responseBody)
                myAdaptor.notifyDataSetChanged()
                recyclerView.adapter = myAdaptor

            }

            override fun onFailure(call: Call<List<Event>?>, t: Throwable) {
                Toast.makeText(this@ListeventActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
