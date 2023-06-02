package com.example.androidminiprojet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.androidminiprojet.RecyclerView.AdpaterAllUsers
import com.example.androidminiprojet.RecyclerView.AllUsersRVData
import com.example.androidminiprojet.network.IAPINet
import com.example.androidminiprojet.network.MySingApi
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONArray



class UsersFragment : Fragment() {



    val listUser : ArrayList<AllUsersRVData> = ArrayList<AllUsersRVData>()



    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_users, container, false)

        val RVAllUsers : RecyclerView = view.findViewById(R.id.idRVAllUsers)
        val serachAllUser : SearchView = view.findViewById(R.id.idSearchUser)


        CoroutineScope(Dispatchers.IO).launch {
            val myApi: IAPINet = MySingApi.getApiClient()!!
            val resRes = myApi.getAllUser()
            val bodyRespString = resRes.body()?.string()
            val myObj: JSONArray = JSONArray(bodyRespString)
            Log.i("test", "1")
            Log.i("test", myObj.toString())
            Log.i("test", myObj.length().toString())

            for (i in 0 until myObj.length()) {
                val user = myObj.getJSONObject(i)
                Log.i("test", user.toString())

                val UserInRV = AllUsersRVData(
                    firstName = user.get("firstname")!! as String,
                    lastNamee = user.get("name")!! as String,
                    nameImage = R.drawable.men

                )

                listUser.add(UserInRV)



            }







//        val myRecyclerView = findViewById<RecyclerView>(R.id.idRV)
//        myRecyclerView.layoutManager = LinearLayoutManager(this)
//        val adapter = Rc1RV(getListEducation(),this)
//        myRecyclerView.adapter = adapter

        }

        RVAllUsers.layoutManager = LinearLayoutManager(activity)
        val adapter = AdpaterAllUsers(listUser,activity)

        RVAllUsers.adapter = adapter
        RVAllUsers.setItemAnimator(SlideInUpAnimator())

        //searchView
        //--------------------------------------------------------------------------------------------------------------------------------------



        // on below line we are adding on query
        // listener for our search view.
        serachAllUser.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // on below line we are checking
                // if query exist or not.
                //RVAllUsers.removeAllViews()

                Log.i("serachView","query")
                Log.i("serachView",query!!)
                RVAllUsers.adapter = null


                val adapterFinal = AdpaterAllUsers(
                    getListUserInThisQuery(listUser, query),
                    activity
                )

                RVAllUsers.adapter = adapterFinal
                RVAllUsers.setItemAnimator(SlideInUpAnimator())







                return true
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                // if query text is change in that case we
                // are filtering our adapter with
                // new text on below line.

                //RVAllUsers.removeAllViews()

                Log.i("serachView","newText")
                Log.i("serachView",newText!!)

                RVAllUsers.adapter = null


                val adapterFinal = AdpaterAllUsers(
                    getListUserInThisQuery(listUser, newText),
                    activity
                )

                RVAllUsers.adapter = adapterFinal
                RVAllUsers.setItemAnimator(SlideInUpAnimator())





                return false
            }
        })



        //--------------------------------------------------------------------------------------------------------------------------------------
        return view
    }

    private fun getListUserInThisQuery(listUser: ArrayList<AllUsersRVData>, query: String) : ArrayList<AllUsersRVData> {
        val listFinal : ArrayList<AllUsersRVData> = ArrayList<AllUsersRVData>()

        listUser.forEach(action = {
            if (it.firstName.contains(query) || it.lastNamee.contains(query)){
                listFinal.add(it)
            }
        })

        return listFinal
    }


}