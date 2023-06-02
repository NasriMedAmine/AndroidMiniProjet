package com.example.androidminiprojet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.network.RequestClass.UserRequest
import com.example.androidminiprojet.network.ResponseClass.ResPostAddUser
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import ir.almasapps.kotlinretrofitcrud.Model.MessageRes
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_register)






        val Input_Register_Name : TextView = findViewById(R.id.Input_Register_Name)
        val Input_Register_Username : TextView = findViewById(R.id.Input_Register_Username)
        val Input_Register_Email : TextView = findViewById(R.id.Input_Register_Email)
        val Input_Register_Pass : TextView = findViewById(R.id.Input_Register_Pass)
        val Input_Register_Firstname : TextView = findViewById(R.id.Input_Register_FirstName)
        val Input_Register_Tel : TextView = findViewById(R.id.Input_Register_TelNum)


        val myButton : Button = findViewById(R.id.buttonRegister)


        myButton.setOnClickListener{

            val Input_Register_Name1 = Input_Register_Name.text.toString()
            val Input_Register_Username1 = Input_Register_Username.text.toString()
            val Input_Register_Email1 = Input_Register_Email.text.toString()
            val Input_Register_Pass1 = Input_Register_Pass.text.toString()
            val Input_Register_Firstname1 = Input_Register_Firstname.text.toString()
            val Input_Register_Tel1 = Input_Register_Tel.text.toString()



            CoroutineScope(Dispatchers.IO).launch{

                val user : UserRequest = UserRequest(
                    myname = Input_Register_Name1,
                    mysername = Input_Register_Username1,
                    mypassword = Input_Register_Pass1,
                    myemail = Input_Register_Email1,
                    myfirstname = Input_Register_Firstname1,
                    myTel = Input_Register_Tel1.toInt()
                )
                val myApi : IAPINet = MySingApi.getApiClient()!!
                val messageRes : ResPostAddUser = myApi.addUser(user)
                if(messageRes.getMessage() == "ajout user"){
                    if (messageRes.getmysucces() == "true"){
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@RegisterActivity,"user is created ", Toast.LENGTH_SHORT).show()

                        }

                        Log.i("ajout","ajout user succes")


                        SingletonDataInMyApp.setmyEmailUser(Input_Register_Email1)
                        val intentToActivEmailAfterRegisterActivity = Intent (this@RegisterActivity, ActivEmailAfterRegisterActivity::class.java )
                        startActivity(intentToActivEmailAfterRegisterActivity)

                    }
                    else{
                        Log.i("ajout","ajout user failed")
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@RegisterActivity,"user is not created ", Toast.LENGTH_SHORT).show()

                        }


                    }
                }
                else if( messageRes.getMessage() == "ajout user catch"){

                    Log.i("ajout","user is not created catch")

                }


            }

        }



    }
}