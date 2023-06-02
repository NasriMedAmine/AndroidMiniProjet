package com.example.androidminiprojet

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.androidminiprojet.MainActivity
import com.example.androidminiprojet.R
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import com.example.androidminiprojet.network.ResponseClass.ResGetForgotPassword
import com.example.androidminiprojet.utils.SingletonDataInMyApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


/**
 * A simple [Fragment] subclass.
 * Use the [codeValidePasswordFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class codeValidePasswordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }



//        val bundle = this.arguments
//        val myEmail = bundle!!.getString("email")
//        val myName = bundle!!.getString("name")
//        val myUsername= bundle!!.getString("username")
//
//        val  nameText = requireView().findViewById<TextView>(R.id.idnameFrcodePassword) as TextView
//        val  usernameText = requireView().findViewById<TextView>(R.id.idusernameFrcodePassword) as TextView
//        val  emailText = requireView().findViewById<TextView>(R.id.idemailFrcodePassword) as TextView
//
//        nameText.text = myName
//        usernameText.text = myUsername
//        emailText.text = myEmail


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val bundle = this.arguments
        val myEmail = bundle!!.getString("email")
        val myName = bundle!!.getString("name")

        if (myEmail != null) {
            Log.i("bundle",myEmail)
        }
        else{
            Log.i("bundle","myEmail = null")

        }

        val view =  inflater.inflate(R.layout.fragment_code_valide_password, container, false)


        val  nameText = view.findViewById<TextView>(R.id.idnameFrcodePassword)
        val  emailText = view.findViewById<TextView>(R.id.idemailFrcodePassword)
        //java.lang.IllegalStateException: Fragment codeValidePasswordFragment{b4d0d57} (0afe6633-bccc-4e20-8b2f-e09505c7b9de id=0x7f0a0221) did not return a View from onCreateView() or this was called before onCreateView().
        Log.i("bundle","")

        nameText.text = myName
        emailText.text = myEmail
        Log.i("bundle","")
        nameText.setText(myName)


        val  btnYes2 = view.findViewById<Button>(R.id.idbtinCode)
        btnYes2.setOnClickListener({
            val myApi : IAPINet = MySingApi.getApiClient()!!
            CoroutineScope(Dispatchers.IO).launch{


                //-------------------------------
                //kont aamel haka
                //val resRes  = myApi.sendCodeToEmail(myEmail)
                val resRes : ResGetForgotPassword? = myEmail?.let { it1 -> myApi.sendCodeToEmail(it1) }
                //---------------------------------------------------------------

                Log.i("response",resRes.toString())
                if (resRes?.getMessage() == "sendCodeToEmail"){
                    if (resRes?.getsucces() == "true"){


                        SingletonDataInMyApp.setmyEmailUser(resRes.getemail()!!)
                        val intentCodeValidePassword = Intent (context, CodeValidePassword::class.java )
                        startActivity(intentCodeValidePassword)
                    }
                    else{

                    }

                }


            }
        })




        // Inflate the layout for this fragment

        //erreur hedhiii chadetni barcha wakt
        //return inflater.inflate(R.layout.fragment_code_valide_password, container, false)
        return view
    }




    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment codeValidePasswordFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            codeValidePasswordFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}

