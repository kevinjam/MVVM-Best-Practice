package com.kevinjanvier.understandmvvm.ui.auth

import android.view.View
import androidx.lifecycle.ViewModel
import com.kevinjanvier.understandmvvm.data.repository.UserRepository
import com.kevinjanvier.understandmvvm.util.ApiException
import com.kevinjanvier.understandmvvm.util.Coroutines
import com.kevinjanvier.understandmvvm.util.NoInternetException

class AuthViewModel(
    private val repository: UserRepository
) : ViewModel() {

    var email: String? = null
    var password: String? = null

    var authListener: AuthListener? = null

    fun getLoggerdInUser() = repository.getUser()

    fun onLoginButtonClick(view: View) {
        authListener!!.onStarted()
        if (email.isNullOrEmpty() || password.isNullOrEmpty()) {
            //display error
            authListener!!.onFailure("Invalid email or password")
            return
        }
        //success

        Coroutines.main {
            try {
                val authResponse = repository.userLogin(email!!, password!!)
                authResponse.user?.let {
                    authListener!!.onSuccess(it)
                    //save to the local db
                    repository.saveUser(it)
                    return@main

                }
                authResponse.message?.let { authListener?.onFailure(it) }
            } catch (e: ApiException) {
                authListener?.onFailure(e.message!!)
            }catch (e:NoInternetException){
                authListener?.onFailure(e.message!!)
            }



        }

    }
}