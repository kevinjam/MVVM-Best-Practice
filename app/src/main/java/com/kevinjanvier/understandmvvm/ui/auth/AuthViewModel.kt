package com.kevinjanvier.understandmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel

class AuthViewModel :ViewModel() {

    var email :String ?= null
    var password:String ? = null

    var authListener:AuthListener?= null

    fun onLoginButtonClick(view:View){
        authListener!!.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()){
            //display error
            authListener!!.onFailure("Invalid email or password")
            return
        }
        //success
        authListener!!.onSuccess()

    }
}