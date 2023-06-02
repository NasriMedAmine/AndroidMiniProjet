package com.example.androidminiprojet

import android.app.DatePickerDialog

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.androidminiprojet.API.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Calendar

class AddEventActivity : AppCompatActivity() {
    private lateinit var datepicker :EditText
    private lateinit var textname : EditText
    private lateinit var textprix : EditText
    private lateinit var textlieu : EditText
    private lateinit var textdescription: EditText
    private lateinit var sbtn : Button
    private lateinit var call : Call<ResponseBody>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_add_event)

        datepicker = findViewById(R.id.updatepicker)
        textname = findViewById(R.id.editTextTextPersonName)
        textprix = findViewById(R.id.editTextTextPersonName3)
        textlieu = findViewById(R.id.editTextTextPersonName2)
        textdescription= findViewById(R.id.editTextTextMultiLine)
        sbtn = findViewById(R.id.btnSubmitevent)


        datepicker.setOnClickListener {
            setDate()
        }

        sbtn.setOnClickListener {
            addEvent()

        }

    }

    private fun addEvent(){
        val name :String = textname.text.toString().trim()
        val lieu :String = textlieu.text.toString().trim()
        val prix :String = textprix.text.toString().trim()
        val date_event :String = datepicker.text.toString().trim()
        val description :String = textdescription.text.toString().trim()

        if(name == null){
            Toast.makeText(this@AddEventActivity,"name is Required",Toast.LENGTH_SHORT).show()
        }else if (lieu == null){
            Toast.makeText(this@AddEventActivity,"lieu is Required",Toast.LENGTH_SHORT).show()
        }else if (prix == null){
            Toast.makeText(this@AddEventActivity,"lieu is Required",Toast.LENGTH_SHORT).show()
        }else if (date_event == null){
            Toast.makeText(this@AddEventActivity,"lieu is Required",Toast.LENGTH_SHORT).show()
        }else if (description == null){
            Toast.makeText(this@AddEventActivity,"lieu is Required",Toast.LENGTH_SHORT).show()
        }else{
            call = RetrofitClient.getInstance().api.addevent(name, lieu, prix, date_event, description)

            call.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    val body :String = response.body()!!.string()
                    Toast.makeText(this@AddEventActivity,body,Toast.LENGTH_SHORT).show()

                }

                override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                    Toast.makeText(this@AddEventActivity,t.message,Toast.LENGTH_SHORT).show()
                }
            })
        }


    }



    private fun setDate(){
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view,year,monthofYear,dayofMonth->
            val returnDate = "${monthofYear + 1} $dayofMonth $year"
            val date = StringHelper.parseDate(
                "MM dd yyyy",
                "MM/dd/yyyy",
                returnDate
            )
            datepicker.setText(StringHelper.parseDate("MM/dd/yyyy","MM/dd/yyyy", date))
            datepicker.error = null
        },year,month,day
        )
        dpd.show()
    }
}


