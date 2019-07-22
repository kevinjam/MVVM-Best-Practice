package com.kevinjanvier.understandmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kevinjanvier.understandmvvm.data.network.MyApi

class UserRepository {


    fun userLogin(email:String, password:String):LiveData<String>{

        val loginResponse = MutableLiveData<String>()
        MyApi().userLogin(email, password)
        return loginResponse
    }
}