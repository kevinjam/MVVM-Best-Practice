package com.kevinjanvier.understandmvvm.data.repository


import com.kevinjanvier.understandmvvm.data.db.AppDatabase
import com.kevinjanvier.understandmvvm.data.db.entities.User
import com.kevinjanvier.understandmvvm.data.network.MyApi
import com.kevinjanvier.understandmvvm.data.network.SafeApiRequest
import com.kevinjanvier.understandmvvm.data.network.responses.AuthResponse
import retrofit2.Response

class UserRepository(
    private val api:MyApi,
    private val db:AppDatabase
):SafeApiRequest() {


    suspend fun userLogin(email:String, password:String): AuthResponse {
       return apiRequest { api.userLogin(email, password) }
    }

    suspend fun saveUser(user:User) = db.getUserDao().upsert(user)

    suspend fun userSignup(
        name:String,
        email:String,
        password: String
    ):AuthResponse{
        return  apiRequest { api.userSignup(name, email, password) }
    }

    fun getUser() = db.getUserDao().getuser()


}