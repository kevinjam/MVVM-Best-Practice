package com.kevinjanvier.understandmvvm.ui.auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kevinjanvier.understandmvvm.R
import com.kevinjanvier.understandmvvm.data.db.entities.User
import com.kevinjanvier.understandmvvm.databinding.ActivitySignupBinding
import com.kevinjanvier.understandmvvm.ui.auth.home.HomeActivity
import com.kevinjanvier.understandmvvm.util.hide
import com.kevinjanvier.understandmvvm.util.show
import com.kevinjanvier.understandmvvm.util.snacbar
import kotlinx.android.synthetic.main.activity_signup.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class SignupActivity : AppCompatActivity() , KodeinAware, AuthListener {

    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)
        val binding: ActivitySignupBinding = DataBindingUtil.setContentView(this, R.layout.activity_signup)

        val viewModel = ViewModelProviders.of(this, factory).get(AuthViewModel::class.java)

        binding.viewmodel = viewModel

        viewModel.authListener = this

        //

        viewModel.getLoggerdInUser().observe(this, Observer { user->
            if (user != null){
                Intent(this, HomeActivity::class.java).also {
                    it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                }
            }
        })

    }

    override fun onStarted() {
//        toast("Login started")
        progressbar_signup.show()
    }

    override fun onSuccess(user: User) {
        progressbar_signup.hide()
        // root_layout.snacbar("Login Success ${user.name}")



    }

    override fun onFailure(message: String) {
        progressbar_signup.hide()
        root_layout_signup.snacbar(message)

    }
}
