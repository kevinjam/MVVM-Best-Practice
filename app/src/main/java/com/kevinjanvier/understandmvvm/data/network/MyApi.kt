package com.kevinjanvier.understandmvvm.data.network

import com.kevinjanvier.understandmvvm.data.network.responses.AuthResponse
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(@Field("email")email:String,
                  @Field("password")password:String)
    :Response<AuthResponse>
    //suspend fucntion can be pause and resume at anytime


    companion object{
        operator fun invoke(networkConnectInterceptor: NetworkConnectInterceptor):MyApi{
            val okHttpclient = OkHttpClient.Builder()
                .addInterceptor(networkConnectInterceptor)
                .build()


            return Retrofit.Builder()
                .client(okHttpclient)
                .baseUrl("https://api.simplifiedcoding.in/course-apis/mvvm/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

                .create(MyApi::class.java)
        }
    }

}