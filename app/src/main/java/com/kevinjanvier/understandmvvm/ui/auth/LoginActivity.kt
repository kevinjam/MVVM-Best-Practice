package com.kevinjanvier.understandmvvm.ui.auth

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.kevinjanvier.understandmvvm.R
import com.kevinjanvier.understandmvvm.databinding.ActivityLoginBinding
import com.kevinjanvier.understandmvvm.util.toast

import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), AuthListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_login)
        val binding:ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

        val viewModel = ViewModelProviders.of(this).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

    }

    override fun onStarted() {

        toast("Login started")
    }

    override fun onSuccess() {
        toast("Login Success")

    }

    override fun onFailure(message: String) {
        toast(message)

    }
}
