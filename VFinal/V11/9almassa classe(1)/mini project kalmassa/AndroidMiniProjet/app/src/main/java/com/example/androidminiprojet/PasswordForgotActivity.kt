package com.example.androidminiprojet

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
import com.example.androidminiprojet.network.ResponseClass.ResGetForgotPassword
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PasswordForgotActivity : AppCompatActivity() {

    private lateinit var emailforgot : TextView
    private lateinit var btnemailforgot : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_forgot)

        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        emailforgot = findViewById(R.id.inputForgotPassword)

        btnemailforgot = findViewById(R.id.btnforgot)

        btnemailforgot.setOnClickListener{

            val emailforgotText = emailforgot.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val myApi: IAPINet = MySingApi.getApiClient()!!
                val resultRes : ResGetForgotPassword = myApi.passwordForgot(emailforgotText)

                if(resultRes.getMessage() == "forgotEmail"){
                    if(resultRes.getsucces() == "true"){

                        Log.i("forgotEmail","saye email tebaath code")
                        Log.i("forgotEmail","----------")
                        Log.i("forgotEmail","----------")


                        val bundle = Bundle()
                        bundle.putString("message", resultRes.getMessage())
                        bundle.putString("email", resultRes.getemail())
                        bundle.putString("name", resultRes.getName())
                        //bundle.putString("username", resultRes.getusername())
                        val fragInfo = codeValidePasswordFragment()
                        fragInfo.arguments = bundle

                        supportFragmentManager.beginTransaction().add(com.example.androidminiprojet.R.id.fragmentContainerViewResetPasswordHere, fragInfo).commit()


                    }
                    else{

                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(this@PasswordForgotActivity,"email does not exist",
                                Toast.LENGTH_SHORT).show()
                        }

                        Log.i("forgotEmail","mail tebaathech code")
                        Log.i("forgotEmail","----------")
                        Log.i("forgotEmail","----------")


                    }
                }
                else if(resultRes.getMessage() == "syntax invalide"){
                    Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@PasswordForgotActivity,"syntax email invalide invalide",
                            Toast.LENGTH_SHORT).show()
                    }

                }


            }

        }


    }
}