package com.example.androidminiprojet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.network.RequestClass.ReqPostCodePasswordChecker
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CodeValidePassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_code_valide_password)


        val InputCodeValideText = findViewById<TextView>(R.id.inputCodeForgotPassword)
        val btnforgotCodeValide = findViewById<Button>(R.id.btnforgotCodeValide)


        btnforgotCodeValide.setOnClickListener({
            val inputCodeValideTextString = InputCodeValideText.text.toString()

            CoroutineScope(Dispatchers.IO).launch {
                val postCodePasswordChecker =
                    ReqPostCodePasswordChecker(code = inputCodeValideTextString)
                val myApi: IAPINet = MySingApi.getApiClient()!!
                val resRes = myApi.postpasswordForgot(postCodePasswordChecker)
                if (resRes.getMessage() == "isCodeValidePasswordReset") {
                    if (resRes.getsucces() == "true") {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                this@CodeValidePassword,
                                "code password reset  valide",
                                Toast.LENGTH_SHORT
                            )
                                .show()
                            val fragInfo = ResetPassowrdFragment()
                            supportFragmentManager.beginTransaction().add(R.id.fragmentContainerViewResetPassword, fragInfo).commit()

                        }

                    } else {

                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(
                                this@CodeValidePassword,
                                "code password reset invalide",
                                Toast.LENGTH_SHORT
                            ).show()

                        }

                    }
                }
            }
        })







    }
}