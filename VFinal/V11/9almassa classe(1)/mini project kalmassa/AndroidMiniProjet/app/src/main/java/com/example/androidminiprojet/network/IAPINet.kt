package com.example.androidminiprojet.network

import android.util.Log
import com.example.androidminiprojet.network.RequestClass.*
import com.example.androidminiprojet.network.ResponseClass.DataLogin
import com.example.androidminiprojet.network.ResponseClass.ResGetForgotPassword
import com.example.androidminiprojet.network.ResponseClass.ResPostAddUser
import com.example.androidminiprojet.network.ResponseClass.RespPostLogin
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import ir.almasapps.kotlinretrofitcrud.Model.MessageRes
import kotlinx.serialization.json.Json
import okhttp3.Callback
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*


/**
 * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
 */




/**
 * Retrofit service object for creating api calls
 */
interface IAPINet {

    @Headers(
        "Accept: application/json",
        "Content-type:application/json"
    )


    @GET("/user/")
    suspend fun getUser(): Call<List<UserRequest>>


    @GET("/user/")
    suspend fun getUserRes(): List<UserRequest>

    @POST("/user/")
    suspend fun addUser(@Body user: UserRequest) : ResPostAddUser


    @GET("/user/passwordforget")
    suspend fun passwordForgot(@Query("email") email:String) : ResGetForgotPassword


    @POST("/user/passwordforget")
    suspend fun postpasswordForgot(@Body postCodePasswordChecker: ReqPostCodePasswordChecker) : MessageRes


    @PUT("/user/passwordforget")
    suspend fun putpasswordForgot(@Body postCodePasswordChecker: putForgotPassword) : MessageRes


    @GET("/user/sendCodeToEmail")
    suspend fun sendCodeToEmail(@Query("email") email:String) : ResGetForgotPassword












    @GET("/user/login")
    suspend fun getLogin(
        @Query("name") name:String,
        @Query("password") password:String
    ) : RespPostLogin


    @GET("/user/login")
    suspend fun getLoginCall(
        @Query("name") name:String,
        @Query("password") password:String
    ) : Response<ResponseBody>




    @GET("/user/login")
    suspend fun getLoginDataClass(
        @Query("name") name:String,
        @Query("password") password:String
    ) : Call<DataLogin>
    @GET("/user/emailValide")
    suspend fun getemailValide(@Query("email") email : String) : MessageRes


    @POST("/user/emailValide")
    suspend fun postemailValide(@Body postEmailCheckerValidate : PostEmailCheckerValidate) : MessageRes



    @POST("/user/loginWithToken")
    suspend fun  getLoginWithToken(@Body requestPostLoginWithToken: RequestPostLoginWithToken) : RespPostLogin












    @GET("/user")
    suspend fun getAllUser() : Response<ResponseBody>



    @GET("/user/ByNameAndFirstname")
    suspend fun getUserByNameAndFirstname(
        @Query("name") name: String,
        @Query("firstname") firstname :String
    ) : Response<ResponseBody>















}

/**
 * A public Api object that exposes the lazy-initialized Retrofit service
 */
object MySingApi {

    private var service: IAPINet? = null

    fun getApiClient(): IAPINet? {
        if (service == null) {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client: OkHttpClient = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "---------------")
            Log.i("android :", "________)))))))")


            val retrofit = Retrofit.Builder()
                .baseUrl("http://192.168.1.129:9090/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build()
            service = retrofit.create(IAPINet::class.java)
        }
        return service
    }
}