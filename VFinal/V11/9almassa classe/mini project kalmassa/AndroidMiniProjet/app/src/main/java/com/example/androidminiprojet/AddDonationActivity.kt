package com.example.androidminiprojet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import com.example.androidminiprojet.API.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDonationActivity : AppCompatActivity() {


    private lateinit var resetB : Button
    private lateinit var submitB : Button
    private lateinit var clothes : CheckBox
    private lateinit var money : CheckBox
    private lateinit var food : CheckBox
    private lateinit var other : CheckBox
    private lateinit var description : EditText
    private lateinit var call : Call<ResponseBody>
    private lateinit var typedonation : Set<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_donation)

        resetB = findViewById(R.id.btnReset)
        submitB = findViewById(R.id.btnSubmit)
        clothes = findViewById(R.id.cbvetement)
        money = findViewById(R.id.cbmoney)
        food = findViewById(R.id.cbaliment)
        other = findViewById(R.id.cbautre)
        description = findViewById(R.id.editTextTextMultiLine2)

//        submitB.setOnClickListener {
//            addDonation()
//        }



    }

//    private fun addDonation() {
//        val descrption :String = description.text.toString().trim()
//        typedonation = mutableSetOf()
//        if(clothes.isChecked){
//             (typedonation as MutableSet<String>).add("vetement")
//        }
//        if(money.isChecked){
//            (typedonation as MutableSet<String>).add("argent")
//        }
//        if(food.isChecked){
//            (typedonation as MutableSet<String>).add("aliment")
//        }
//        if(other.isChecked){
//            (typedonation as MutableSet<String>).add("autre")
//        }
//
//        call = RetrofitClient.getInstance().api.addonation(typedonation,descrption)
//        call.enqueue(object : Callback<ResponseBody?> {
//            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
//                val body :String = response.body()!!.string()
//                Toast.makeText(this@AddDonationActivity,"Donation Added", Toast.LENGTH_SHORT).show()
//
//            }
//            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
//                Toast.makeText(this@AddDonationActivity,t.message, Toast.LENGTH_SHORT).show()
//            }
//        })
//
//
//    }
}