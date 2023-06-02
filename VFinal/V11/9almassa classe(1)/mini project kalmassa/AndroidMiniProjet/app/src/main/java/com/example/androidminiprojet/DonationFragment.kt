package com.example.androidminiprojet

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidminiprojet.API.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response




class DonationFragment : Fragment() {


    lateinit var thiscontext : Context

    private lateinit var resetB :Button
    private lateinit var submitB : Button
    private lateinit var clothes : CheckBox
    private lateinit var money : CheckBox
    private lateinit var food : CheckBox
    private lateinit var other : CheckBox
    private lateinit var titre : EditText
    private lateinit var description : EditText
    private lateinit var call : Call<ResponseBody>
    private lateinit var typedonation : Set<String>
    private lateinit var listdonationfrag : Button


    private fun resetForm() {
        clothes?.isChecked = false
        money?.isChecked = false
        food?.isChecked = false
        other?.isChecked = false
        description?.text = null
        titre?.text = null
    }


    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview  : View = inflater.inflate(R.layout.fragment_donation, container, false)

        submitB = rootview.findViewById(R.id.Submitbtn)
        resetB = rootview.findViewById(R.id.Resetbtn)
        clothes = rootview.findViewById(R.id.cbvetement)
        money = rootview.findViewById(R.id.cbargent)
        food = rootview.findViewById(R.id.cbfood)
        other = rootview.findViewById(R.id.cbother)
        titre = rootview.findViewById(R.id.ETtitrenew)
        description = rootview.findViewById((R.id.editTextTextMultiLine2))
        listdonationfrag = rootview.findViewById(R.id.ListeDonationBTN)

        thiscontext = container!!.context

        listdonationfrag.setOnClickListener{
            findNavController().navigate(R.id.listeDonationFragment)
        }

        resetB!!.setOnClickListener{
            resetForm()
        }
        submitB!!.setOnClickListener {
            addDonation()
        }


        return rootview
    }

    private fun addDonation() {
        val titre : String = titre.text.toString().trim()
        val descrption :String = description.text.toString().trim()
        typedonation = mutableSetOf()

        if(clothes.isChecked){
            (typedonation as MutableSet<String>).add("Clothes")
        }
        if(money.isChecked){
            (typedonation as MutableSet<String>).add("Money")
        }
        if(food.isChecked){
            (typedonation as MutableSet<String>).add("Food")
        }
        if(other.isChecked){
            (typedonation as MutableSet<String>).add("Other")
        }

        if (typedonation.isEmpty() || typedonation.size<=0){
            Toast.makeText(thiscontext,"selection of type donation is required", Toast.LENGTH_SHORT).show()
        }
        else if (titre == null || titre == "" || titre.length<5){
            Toast.makeText(thiscontext,"Check titre", Toast.LENGTH_SHORT).show()
        }
        else if(descrption ==null || descrption ==""){
            Toast.makeText(thiscontext,"Check description", Toast.LENGTH_SHORT).show()
        }else{
            call = RetrofitClient.getInstance().api.addonation(titre,typedonation,descrption)
            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    val body :String = response.body()!!.string()
                    Toast.makeText(thiscontext,"Donation Added", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.listeDonationFragment)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(thiscontext,t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

}