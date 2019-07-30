package com.kevinjanvier.understandmvvm.ui.auth

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.kevinjanvier.understandmvvm.R
import com.kevinjanvier.understandmvvm.data.db.entities.User
import com.kevinjanvier.understandmvvm.databinding.ActivityLoginBinding
import com.kevinjanvier.understandmvvm.ui.auth.home.HomeActivity
import com.kevinjanvier.understandmvvm.util.hide
import com.kevinjanvier.understandmvvm.util.show
import com.kevinjanvier.understandmvvm.util.snacbar
import kotlinx.android.synthetic.main.activity_login.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class LoginActivity : AppCompatActivity(), AuthListener , KodeinAware{


    override val kodein by kodein()

    private val factory : AuthViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        val networkConnectInterceptor = NetworkConnectInterceptor(this)
//        val api = MyApi(networkConnectInterceptor)
//        val db = AppDatabase(this)
//
//        val repository = UserRepository(api,db)
//        val factory = AuthViewModelFactory(repository)




        // setContentView(R.layout.activity_login)
        val binding: ActivityLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)

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
        progress_bar.show()
    }

    override fun onSuccess(user:User) {
        progress_bar.hide()
       // root_layout.snacbar("Login Success ${user.name}")



    }

    override fun onFailure(message: String) {
       progress_bar.hide()
        root_layout.snacbar(message)

    }
}
