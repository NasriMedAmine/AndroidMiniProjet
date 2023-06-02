package com.example.androidminiprojet

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.TextView
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ActivEmailAfterRegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_activ_email_after_register)

        val btnYesEmailCheckerTextView : TextView = findViewById(R.id.btnYesEmailChecker)
        val btnNoEmailCheckerTextView : TextView = findViewById(R.id.btnNEmailChecker)



        btnYesEmailCheckerTextView.setOnClickListener({



            CoroutineScope(Dispatchers.IO).launch{
                val myApi : IAPINet = MySingApi.getApiClient()!!
                val resRes  = myApi.getemailValide(SingletonDataInMyApp.getmyEmailUser())
                if (resRes.getMessage() == "sendCodeToEmailValidation"){
                    if (resRes.getsucces() == "true") {
                        val fragInfo = YesEmailCheckerFragment()
                        supportFragmentManager.beginTransaction().add(com.example.androidminiprojet.R.id.fragmentContainerViewEmailChecker, fragInfo).commit()

                    }
                }

            }


        })

        btnNoEmailCheckerTextView.setOnClickListener({

            val intentToMAin = Intent (this@ActivEmailAfterRegisterActivity, LoginActivity::class.java )
            startActivity(intentToMAin)
        })

    }
}