package com.example.androidminiprojet

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.model.SingletonDonation
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class UpdateDonationFragment : Fragment() {

    private lateinit var thiscontext :Context
    private lateinit var callupdate : Call<ResponseBody>

    private lateinit var clothUp : CheckBox
    private lateinit var moneyUp : CheckBox
    private lateinit var foodUp : CheckBox
    private lateinit var otherUp : CheckBox
    private lateinit var descriptionUp : EditText
    private lateinit var titre : EditText
    private lateinit var UpdateBtN : Button
    private lateinit var retourListe : Button

    private lateinit var typeDonationUp : Set<String>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_update_donation, container, false)

        clothUp = rootview.findViewById(R.id.cbclothesss)
        moneyUp = rootview.findViewById(R.id.cbargentt)
        foodUp = rootview.findViewById(R.id.cbfooddd)
        otherUp = rootview.findViewById(R.id.cbotherrr)
        titre = rootview.findViewById(R.id.ETtitreUpdate)
        descriptionUp = rootview.findViewById(R.id.editMultiLine)
        UpdateBtN = rootview.findViewById(R.id.btnUpDonation123)
        retourListe = rootview.findViewById(R.id.btnListDonationAfterUpdate)

        titre.setText(SingletonDonation.getTitre())
        descriptionUp.setText(SingletonDonation.getDescri())

        retourListe.setOnClickListener {
            Navigation.findNavController(rootview).navigate(R.id.action_updateDonationFragment_to_listeDonationFragment)
        }

        UpdateBtN.setOnClickListener {
            updatedataDonation()
        }



        thiscontext = container!!.context
        return rootview
    }

    private fun updatedataDonation() {
        val titre : String = titre.text.toString().trim()
        val description : String = descriptionUp.text.toString().trim()
        typeDonationUp = mutableSetOf()
        //***************************************************************************
//        if(SingletonDonation.getTypeDonation().contains("vetement")){
//            (typeDonationUp as MutableSet<String>).add("vetement")
//        }
//        if(SingletonDonation.getTypeDonation().contains("argent")){
//            (typeDonationUp as MutableSet<String>).add("argent")
//        }
//        if(SingletonDonation.getTypeDonation().contains("aliment")){
//            (typeDonationUp as MutableSet<String>).add("aliment")
//        }
//        if (SingletonDonation.getTypeDonation().contains("autre")){
//            (typeDonationUp as MutableSet<String>).add("autre")
//        }
        //***************************************************************************
        if(clothUp.isChecked){
            (typeDonationUp as MutableSet<String>).add("Clothes")
        }
        if(moneyUp.isChecked){
            (typeDonationUp as MutableSet<String>).add("Money")
        }
        if(foodUp.isChecked){
            (typeDonationUp as MutableSet<String>).add("Food")
        }
        if(otherUp.isChecked){
            (typeDonationUp as MutableSet<String>).add("Other")
        }
        if(typeDonationUp.isEmpty() || typeDonationUp.size<=0){
            Toast.makeText(thiscontext,"selection of type donation is required", Toast.LENGTH_SHORT).show()
        }
        else if (description == null || description == "" || description.length<5){
            Toast.makeText(thiscontext,"Check description", Toast.LENGTH_SHORT).show()
        }else{
            callupdate = RetrofitClient.getInstance().api.updatedonation(SingletonDonation.getTitre(),titre,typeDonationUp,description)
            callupdate.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Toast.makeText(thiscontext,"Donation Updated", Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.listeDonationFragment)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(thiscontext,t.message, Toast.LENGTH_SHORT).show()
                }
            })
        }

    }


}