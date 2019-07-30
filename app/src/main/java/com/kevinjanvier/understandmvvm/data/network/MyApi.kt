package com.kevinjanvier.understandmvvm.data.network

import com.kevinjanvier.understandmvvm.data.network.responses.AuthResponse
import com.kevinjanvier.understandmvvm.data.network.responses.QuotesResponse
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface MyApi {

    @FormUrlEncoded
    @POST("login")
    suspend fun userLogin(@Field("email")email:String,
                  @Field("password")password:String)
    :Response<AuthResponse>
    //suspend fucntion can be pause and resume at anytime


//    Signup
    @FormUrlEncoded
    @POST("signup")
    suspend fun userSignup(
    @Field("name") name:String,
    @Field("email") email: String,
    @Field("password") password: String
):Response<AuthResponse>



    @GET("quotes")
    suspend fun getQuotes():Response<QuotesResponse>

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