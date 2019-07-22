package com.kevinjanvier.understandmvvm.ui.auth

interface AuthListener {
    fun onStarted()
    fun onSuccess()
    fun onFailure(message:String)
}