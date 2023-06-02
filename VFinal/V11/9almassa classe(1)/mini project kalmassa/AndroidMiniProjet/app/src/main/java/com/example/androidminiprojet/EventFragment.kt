package com.example.androidminiprojet

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.androidminiprojet.API.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class EventFragment : Fragment() {
    lateinit var thiscontext : Context


    private  lateinit var nametext : EditText
    private  lateinit var lieutext : EditText
    private  lateinit var prixtext : EditText
    private  lateinit var datep : EditText
    private  lateinit var descriptiontext : EditText
    private lateinit var resetB : Button
    private lateinit var submitB : Button
    private lateinit var ListeEventB : Button
    private lateinit var call : Call<ResponseBody>


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var rootview = inflater.inflate(R.layout.fragment_event, container, false)

        datep = rootview.findViewById(R.id.updatepicker)
        nametext = rootview.findViewById(R.id.editTextTextPersonName)
        lieutext = rootview.findViewById(R.id.editTextTextPersonName2)
        prixtext = rootview.findViewById(R.id.editTextTextPersonName3)
        descriptiontext = rootview.findViewById(R.id.editTextTextMultiLine)
        resetB = rootview.findViewById(R.id.btnResetevent)
        submitB = rootview.findViewById(R.id.btnSubmitevent)
        ListeEventB= rootview.findViewById(R.id.ListeEventBTN)



        datep.setOnClickListener{
            setDate()
        }

        resetB.setOnClickListener {
            resetForm()
        }

        submitB.setOnClickListener {
            addEvent()

        }

        ListeEventB.setOnClickListener {
            findNavController().navigate(R.id.listeEventFragment)

        }

        thiscontext = container!!.context

        return rootview
    }

    private fun setDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(thiscontext, DatePickerDialog.OnDateSetListener{ view, year, monthofYear, dayofMonth->
            val returnDate = "${monthofYear + 1} $dayofMonth $year"
            val date = StringHelper.parseDate(
                "MM dd yyyy",
                "MM/dd/yyyy",
                returnDate
            )
            datep.setText(StringHelper.parseDate("MM/dd/yyyy","MM/dd/yyyy", date))
            datep.error = null
        },year,month,day
        )
        dpd.show()
    }

    private fun resetForm(){
        nametext?.text= null
        datep?.text = null
        lieutext?.text = null
        prixtext?.text = null
        datep?.text = null
        descriptiontext?.text = null
    }

    private fun addEvent(){
        val name :String = nametext.text.toString().trim()
        val lieu :String = lieutext.text.toString().trim()
        val prix :String = prixtext.text.toString().trim()
        val date_event :String = datep.text.toString().trim()
        val description :String = descriptiontext.text.toString().trim()

        if(name == null || name == "" || name.length<5){
            Toast.makeText(thiscontext,"Check name",Toast.LENGTH_SHORT).show()
        }
        else if (lieu == null || lieu =="" || lieu.length<5){
            Toast.makeText(thiscontext,"Check lieu",Toast.LENGTH_SHORT).show()
        }
        else if (prix == null || prix == ""){
            Toast.makeText(thiscontext,"Check prix",Toast.LENGTH_SHORT).show()
        }
        else if (date_event == null || date_event ==""){
            Toast.makeText(thiscontext,"Check date",Toast.LENGTH_SHORT).show()
        }
        else if (description == null || description=="" || description.length<5){
            Toast.makeText(thiscontext,"Check description",Toast.LENGTH_SHORT).show()
        }else{
            call = RetrofitClient.getInstance().api.addevent(name, lieu, prix, date_event, description)

            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    val body :String = response.body()!!.string()
                    Toast.makeText(thiscontext,"Event Added",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.listeEventFragment)

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(thiscontext,t.message,Toast.LENGTH_SHORT).show()
                }
            })
        }


    }


}