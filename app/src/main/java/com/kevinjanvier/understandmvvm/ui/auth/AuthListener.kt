package com.kevinjanvier.understandmvvm.ui.auth

import com.kevinjanvier.understandmvvm.data.db.entities.User

interface AuthListener {
    fun onStarted()
    fun onSuccess(user:User)
    fun onFailure(message:String)
}