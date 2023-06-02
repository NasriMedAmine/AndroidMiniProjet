package com.example.androidminiprojet

import android.R.attr.text
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.children
import androidx.core.view.marginStart
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.RecyclerView.AdapterMessageChat
import com.example.androidminiprojet.RecyclerView.AdpaterAllUsers
import com.example.androidminiprojet.RecyclerView.AllUsersRVData
import com.example.androidminiprojet.RecyclerView.itemMessageChat
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.utils.FileManagementSecret
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import com.example.androidminiprojet.utils.SocketHandler
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject


class ChatAcActivity : AppCompatActivity() {
    val listMessage : ArrayList<itemMessageChat> = ArrayList<itemMessageChat>()



    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat_ac)
        //getEmailUser()
        val linearLayout : LinearLayout = findViewById(R.id.idLinearPlaceTextChatMessage)
        val myMessageChate = findViewById<TextView>(R.id.inputChatMessage)

        val nameUserToChat : TextView = findViewById(R.id.idnameUserToChat)
        val firstnameUserToChat : TextView = findViewById(R.id.idFirstnameUserToChat)
        val online : TextView = findViewById(R.id.idOnline)
        val offline : TextView = findViewById(R.id.idOffline)
        val RV_Message : RecyclerView = findViewById(R.id.RV_Message)


        nameUserToChat.text = SingletonDataInMyApp.getnameUserProfileCibleRecherche()
        firstnameUserToChat.text = SingletonDataInMyApp.getfirstNameUserProfileCibleRecherche()
        offline.visibility =  View.INVISIBLE
        online.visibility =  View.INVISIBLE

        //socket chat
//-------------------------------------------------------------------------------------------------------------------------------------------------------
        Log.i("socket","3")
        SocketHandler.setSocket()
        Log.i("socket","4")
        SocketHandler.establishConnection()
        Log.i("socket","5")
        val mSocket = SocketHandler.getSocket()
        mSocket.emit("whoIam", FileManagementSecret.getToken())

        mSocket.emit("isThatProfileConn",SingletonDataInMyApp.getemailToUserChat(),SingletonDataInMyApp.getnameUserProfileCibleRecherche())



        mSocket.on("isThatProfileConnRes") { args ->
            if (args[0] != null) {
                Log.i("socket",args.toString())
                val isConn = args[0] as String
                Log.i("socket",isConn)

                runOnUiThread {
                    if(isConn == "yes"){
                        online.visibility =  View.VISIBLE
                    }else if(isConn == "no"){
                        offline.visibility =  View.VISIBLE

                    }
                }
            }
        }


        mSocket.on("messageFromServerToMobile") { args ->
            Log.i("socket",args.toString())

            if (args[0] != null) {
                val message = args[0] as String
                runOnUiThread {
                    Log.i("socket","message")
                    Log.i("socket",message)


                    RV_Message.adapter = null


                    val messageLabel = itemMessageChat(message,true)
                    this.listMessage.add(messageLabel)
                    RV_Message.layoutManager = LinearLayoutManager(this@ChatAcActivity)

                    val adapterFinal = AdapterMessageChat(
                        this.listMessage,
                        this@ChatAcActivity
                    )

                    RV_Message.adapter = adapterFinal
                    RV_Message.setItemAnimator(SlideInUpAnimator())




//
//                    var textV2 = TextView(this@ChatAcActivity)
//
//                    textV2.text = message
//                    textV2.textSize =28f
//                    textV2.height = 100
//
//                    textV2.setBackgroundColor(R.color.purple_200)
//
//                    linearLayout.addView(textV2)

                }









//
//
//                //hedhi habit naamel thread w dima ichouf ekher element
//                runOnUiThread({
//                    while (true){
//                        if (RV_Message.isActivated){
//                            RV_Message.children.last().findViewById<TextView>(R.id.idSendMessageChat).setTextColor(R.color.black)
//
//                        }
//                    }
//
//
//                })

            }
        }



//-------------------------------------------------------------------------------------------------------------------------------------------------------


        //btn Chat send
        val btnChatSend : ImageView = findViewById(R.id.btnChatSendMessage)
        btnChatSend.setOnClickListener {
            val mSocket = SocketHandler.getSocket()
            mSocket.emit("messageFromMobileToServer",myMessageChate.text.toString(),FileManagementSecret.getToken(),SingletonDataInMyApp.getnameUserProfileCibleRecherche())
            myMessageChate?.text == null
        }







    }

    private fun getEmailUser() {

        CoroutineScope(Dispatchers.IO).launch {
            val myApi: IAPINet = MySingApi.getApiClient()!!
            val resRes = myApi.getUserByNameAndFirstname(
                name = SingletonDataInMyApp.nameUserProfileCibleRecherche,
                firstname = SingletonDataInMyApp.firstNameUserProfileCibleRecherche
            )
            if(resRes.isSuccessful){
                val bodyRespString = resRes.body()?.string()
                val myObj: JSONObject = JSONObject(bodyRespString)
                SingletonDataInMyApp.setemailToUserChat(myObj.get("email") as String)
            }

        }
    }
}