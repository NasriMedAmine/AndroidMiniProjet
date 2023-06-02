package com.example.androidminiprojet

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.androidminiprojet.API.RetrofitClient
import com.example.androidminiprojet.model.Event
import com.example.androidminiprojet.model.SingletonEvent
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class UpdateEventFragment : Fragment() {

    private lateinit var thiscontext : Context
    private lateinit var callUpdate :Call<ResponseBody>

    private lateinit var nameUpdateEvent : EditText
    private lateinit var lieuUpdateEvent : EditText
    private lateinit var prixUpdateEvent : EditText
    private lateinit var dateUpdatePicker : EditText
    private lateinit var descriptionUpdateEvent : EditText
    private lateinit var updateEvntB : Button
    private lateinit var retourListeDonation : Button

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootview = inflater.inflate(R.layout.fragment_update_event, container, false)

        nameUpdateEvent = rootview.findViewById(R.id.upnameEvent)
        lieuUpdateEvent = rootview.findViewById(R.id.uplieuEvent)
        prixUpdateEvent = rootview.findViewById(R.id.upprixEvent)
        dateUpdatePicker = rootview.findViewById(R.id.updateDateEvent)
        descriptionUpdateEvent = rootview.findViewById(R.id.updescriptionEvent)
        updateEvntB = rootview.findViewById(R.id.btnUpdateEvent)
        retourListeDonation = rootview.findViewById(R.id.btnListAfterUpdate)



        nameUpdateEvent.setText(SingletonEvent.getName())
        lieuUpdateEvent.setText(SingletonEvent.getLieu())
        prixUpdateEvent.setText(SingletonEvent.getPrix())
        dateUpdatePicker.setText(SingletonEvent.getDate())
        descriptionUpdateEvent.setText(SingletonEvent.getDescription())



        updateEvntB.setOnClickListener {
            updateDataEvent()
        }
        retourListeDonation.setOnClickListener {
            Navigation.findNavController(rootview).navigate(R.id.action_updateEventFragment_to_listeEventFragment)
        }

        dateUpdatePicker.setOnClickListener{
            setDate()
        }


        thiscontext = container!!.context
        return rootview
    }

    private fun updateDataEvent() {
        val nameUpdated : String = nameUpdateEvent.text.toString().trim()
        val lieuUpdated : String = lieuUpdateEvent.text.toString().trim()
        val prixUpdated : String = prixUpdateEvent.text.toString().trim()
        val DateUpdated : String = dateUpdatePicker.text.toString().trim()
        val descripUpdated : String = descriptionUpdateEvent.text.toString().trim()

        if(nameUpdated == null || nameUpdated == "" || nameUpdated.length<5){
            Toast.makeText(thiscontext,"Check name", Toast.LENGTH_SHORT).show()
        }
        else if(lieuUpdated == null || lieuUpdated == "" || lieuUpdated.length<5){
            Toast.makeText(thiscontext,"Check lieu",Toast.LENGTH_SHORT).show()
        }
        else if(prixUpdated ==null || prixUpdated ==""){
            Toast.makeText(thiscontext,"Check prix",Toast.LENGTH_SHORT).show()
        }else if (DateUpdated == null || DateUpdated == "" ){
            Toast.makeText(thiscontext,"Check date",Toast.LENGTH_SHORT).show()
        }else if(descripUpdated == null || descripUpdated=="" || descripUpdated.length<5){
            Toast.makeText(thiscontext,"Check description",Toast.LENGTH_SHORT).show()
        }else{
            callUpdate = RetrofitClient.getInstance().api.updateevent(
                SingletonEvent.getName(),nameUpdated,lieuUpdated,prixUpdated,DateUpdated,descripUpdated)
            callUpdate.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(
                    call: Call<ResponseBody?>,
                    response: Response<ResponseBody?>
                ) {
                    Toast.makeText(thiscontext,"Event Updated",Toast.LENGTH_SHORT).show()
                    findNavController().navigate(R.id.listeEventFragment)
                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(thiscontext,t.message,Toast.LENGTH_SHORT).show()
                }
            })

        }




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
            dateUpdatePicker.setText(StringHelper.parseDate("MM/dd/yyyy","MM/dd/yyyy", date))
            dateUpdatePicker.error = null
        },year,month,day
        )
        dpd.show()
    }

}