package com.kevinjanvier.understandmvvm.data.network.responses

import com.kevinjanvier.understandmvvm.data.db.entities.User

data class AuthResponse(
    val isSuccessful:Boolean?= null,
    val message:String?= null,
    val user:User?= null
)