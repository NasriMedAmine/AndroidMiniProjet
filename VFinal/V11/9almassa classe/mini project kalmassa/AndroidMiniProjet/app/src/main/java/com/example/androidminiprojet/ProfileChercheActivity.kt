package com.example.androidminiprojet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import com.google.gson.JsonObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray
import org.json.JSONObject

class ProfileChercheActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_profile_cherche)


        val nameText : TextView = findViewById(R.id.idNameProfileRecherche)
        val firstnameText : TextView = findViewById(R.id.idFirstNameProfileCherche)
        val emailText : TextView = findViewById(R.id.idEmailProfileCherche)



        val goChat : Button = findViewById(R.id.idGoChat)


        goChat.setOnClickListener({

            val intentChat = Intent (this, ChatAcActivity::class.java )
            startActivity(intentChat)
        })


        CoroutineScope(Dispatchers.IO).launch {
            val myApi: IAPINet = MySingApi.getApiClient()!!
            val resRes = myApi.getUserByNameAndFirstname(
                name = SingletonDataInMyApp.nameUserProfileCibleRecherche,
                firstname = SingletonDataInMyApp.firstNameUserProfileCibleRecherche
            )
            if(resRes.isSuccessful){
                val bodyRespString = resRes.body()?.string()

                val myObj: JSONObject = JSONObject(bodyRespString)
                Log.i("test", "1")
                Log.i("test", myObj.toString())
                Log.i("test", myObj.length().toString())

                SingletonDataInMyApp.setemailToUserChat(myObj.get("email") as String)

                nameText.text = myObj.get("name") as String
                firstnameText.text = myObj.get("firstname") as String
                emailText.text = myObj.get("email") as String



            }
        }

    }
}