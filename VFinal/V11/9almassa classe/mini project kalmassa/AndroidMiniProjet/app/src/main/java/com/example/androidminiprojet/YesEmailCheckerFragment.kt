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
import com.example.androidminiprojet.network.RequestClass.PostEmailCheckerValidate
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
 * Use the [YesEmailCheckerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class YesEmailCheckerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_yes_email_checker, container, false)

        val inputCodeEmailCheckerText = view.findViewById<TextView>(R.id.inputCodeEmailChecker)
        val btnValidateEmailChecker = view.findViewById<Button>(R.id.btnValidateEmailChecker)



        btnValidateEmailChecker.setOnClickListener({

            val postEmailCheckerValidate: PostEmailCheckerValidate = PostEmailCheckerValidate(
                myemail = SingletonDataInMyApp.getmyEmailUser(),
                mycode = inputCodeEmailCheckerText.text.toString()
            )



            CoroutineScope(Dispatchers.IO).launch {
                val myApi: IAPINet = MySingApi.getApiClient()!!
                val resRes = myApi.postemailValide(postEmailCheckerValidate)
                if (resRes.getMessage() == "postEmailValideService") {
                    if (resRes.getsucces() == "true") {
                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context,"code email checker valide", Toast.LENGTH_SHORT).show()
                            val intentToMAin = Intent (context, PickImageForProfileActivity::class.java )
                            startActivity(intentToMAin)
                        }

                    }
                    else{

                        Handler(Looper.getMainLooper()).post {
                            Toast.makeText(context,"code email checker invalide", Toast.LENGTH_SHORT).show()

                        }

                    }
                }


            }

        })





        return view
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YesEmailCheckerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YesEmailCheckerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}