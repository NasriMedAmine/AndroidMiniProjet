package com.example.androidminiprojet

import android.content.Intent
import android.os.Bundle
import android.os.Looper
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.network.RequestClass.RequestPostLoginWithToken
import com.example.androidminiprojet.utils.FileManagementSecret
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Response
import okhttp3.ResponseBody
import org.json.JSONObject
import java.util.logging.Handler
import javax.security.auth.callback.Callback


class LoginActivity : AppCompatActivity() {

    private lateinit var textView : TextView
    private lateinit var btnLogin : Button
    private lateinit var ForgetPW : TextView
    private lateinit var TextUnderline1 : TextView
    private lateinit var TextUnderline2 : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_login)
        val myLoginText = findViewById<TextView>(R.id.inputUsername)
        val myPasswordText = findViewById<TextView>(R.id.InputPassword)
        textView = findViewById(R.id.Btn_Signup)
        btnLogin= findViewById(R.id.BtnLogin)
        ForgetPW = findViewById(R.id.forgetPW)
        TextUnderline1 = findViewById(R.id.terme)
        TextUnderline2 = findViewById(R.id.policy)

        val content : SpannableString = SpannableString("Terms of Use")
        val content2 : SpannableString = SpannableString("Privacy Policy")
        content.setSpan(UnderlineSpan(),0, content.length,0)
        content2.setSpan(UnderlineSpan(),0, content.length,0)
        TextUnderline1.setText(content)
        TextUnderline2.setText(content2)





        //=========================================================================================================================
        CoroutineScope(Dispatchers.IO).launch {
            if (FileManagementSecret.checkTokenFileForPostLogin(this@LoginActivity.applicationContext)) {
                Log.i("loginWithToken","tao aaraft eli file mawjoud")

                val myApi2: IAPINet = MySingApi.getApiClient()!!

                val tokenPost = RequestPostLoginWithToken(
                    myToken = FileManagementSecret.getToken()
                )
                val resRes2 = myApi2.getLoginWithToken(tokenPost)
                if (resRes2.getSucces() == "true"){
                    val intentToMain2 = Intent (this@LoginActivity, MainActivity::class.java )
                    startActivity(intentToMain2)

                }
                Log.i("loginWithToken","tao aaraft token shiha jetni m response")

            }
        }
        //=========================================================================================================================

        textView.setOnClickListener{
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

        ForgetPW.setOnClickListener{
            val intent = Intent(this, PasswordForgotActivity::class.java)
            startActivity(intent)
        }

        btnLogin.setOnClickListener{

            val login = myLoginText.text.toString()
            val pass = myPasswordText.text.toString()

            Log.i("gson","1")

            // mochkla f intent maa CoroutineScope
            CoroutineScope(Dispatchers.IO).launch{
                val myApi : IAPINet = MySingApi.getApiClient()!!
                val resRes = myApi.getLogin(login,pass)


                //methode jdidaaa mtaa retrofit b jsonbuilder
                ///-----------------------------------------------------------------------------------------------------------------------------------------
                //          val bodyRespString = resRes.body()?.string()
//                val myObj: JSONObject = JSONObject(bodyRespString)
//                Log.i("test","1")
//                Log.i("test",myObj.toString())
//                Log.i("test",myObj.get("message") as String)
//                Log.i("test",myObj.get("succes") as String)
//
//                val user : JSONObject = JSONObject(myObj.get("user").toString())
//                Log.i("test",user.get("name") as String)
                //-------------------------------------------------------------------------------------------------------------------------------------------


                if (resRes.getSucces() == "true"){
                    Log.i("login","tao dkhalt login shih")
                    android.os.Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@LoginActivity, "Login valide", Toast.LENGTH_SHORT)
                            .show()
                    }
                    Log.i("login","hedha User.name")
                    Log.i("token" , resRes.getToken())
                    FileManagementSecret.setToken(resRes.getToken())
                    FileManagementSecret.SaveFileToken(resRes.getToken(),this@LoginActivity.applicationContext)

                    Log.i("token","saye file saved token")




                    val intentToMain = Intent (this@LoginActivity, MainActivity::class.java )
                    startActivity(intentToMain)
                }




                else{
                    Log.i("login","tao dkhalt login ghalet")
                    android.os.Handler(Looper.getMainLooper()).post {
                        Toast.makeText(this@LoginActivity,"Login or Password invalide", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

    }
}