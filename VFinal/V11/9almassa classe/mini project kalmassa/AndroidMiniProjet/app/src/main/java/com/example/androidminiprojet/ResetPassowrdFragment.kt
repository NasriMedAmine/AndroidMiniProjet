package com.example.androidminiprojet

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.network.RequestClass.ReqPostCodePasswordChecker
import com.example.androidminiprojet.network.RequestClass.putForgotPassword
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ResetPassowrdFragment : Fragment() {

    private lateinit var thiscontext : Context
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment



        val view = inflater.inflate(R.layout.fragment_reset_passowrd, container, false)
        val inputNewPassword3Text = view.findViewById<TextView>(R.id.inputNewPassword3)
        val inputNewPassword4text = view.findViewById<TextView>(R.id.inputNewPassword4)
        val btnNewPassword2btn = view.findViewById<Button>(R.id.btnNewPassword2)


        val Pass1 : String = inputNewPassword3Text.text.toString().trim()
        val Pass2 : String = inputNewPassword4text.text.toString().trim()
        if (Pass1 != Pass2){
            Toast.makeText(thiscontext,"Passwords should be the same", Toast.LENGTH_SHORT).show()
        }else{

        }


        btnNewPassword2btn.setOnClickListener({
            val Pass1 : String = inputNewPassword3Text.text.toString().trim()
            val Pass2 : String = inputNewPassword4text.text.toString().trim()
            if (Pass1 != Pass2){
                Toast.makeText(thiscontext,"Passwords should be the same", Toast.LENGTH_SHORT).show()
            }else{

                CoroutineScope(Dispatchers.IO).launch {

                    val putForgotPassword = putForgotPassword(email = SingletonDataInMyApp.getmyEmailUser(), password = inputNewPassword3Text.text.toString() )

                    val myApi: IAPINet = MySingApi.getApiClient()!!
                    val resRes = myApi.putpasswordForgot(putForgotPassword)

                    if (resRes.getMessage() == "updatePasswordByIdAndTokenService") {
                        if (resRes.getsucces() == "true") {
                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(
                                    context,
                                    "code password reset  valide",
                                    Toast.LENGTH_SHORT
                                )
                                    .show()
                            }
                            val intentToMAin2 = Intent (context, LoginActivity::class.java )
                            startActivity(intentToMAin2)

                        } else {

                            Handler(Looper.getMainLooper()).post {
                                Toast.makeText(
                                    context,
                                    "code password reset invalide",
                                    Toast.LENGTH_SHORT
                                ).show()

                            }

                        }
                    }
            }






            }

        })








        thiscontext = container!!.context
        return view
    }


}