package com.kevinjanvier.understandmvvm

import android.app.Application
import com.kevinjanvier.understandmvvm.data.db.AppDatabase
import com.kevinjanvier.understandmvvm.data.network.MyApi
import com.kevinjanvier.understandmvvm.data.network.NetworkConnectInterceptor
import com.kevinjanvier.understandmvvm.data.preferences.PreferenceProvider
import com.kevinjanvier.understandmvvm.data.repository.QuotesRepository
import com.kevinjanvier.understandmvvm.data.repository.UserRepository
import com.kevinjanvier.understandmvvm.ui.auth.AuthViewModelFactory
import com.kevinjanvier.understandmvvm.ui.auth.home.profile.ProfileViewModelFactory
import com.kevinjanvier.understandmvvm.ui.auth.home.quotes.QuotesViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class MVVMApplication : Application(), KodeinAware {


    override val kodein: Kodein
        get() = Kodein.lazy {

            import(androidXModule(this@MVVMApplication))

            bind() from singleton { NetworkConnectInterceptor(instance()) }
            bind() from singleton { MyApi(instance()) }
            bind() from singleton { AppDatabase(instance()) }
            bind() from singleton { PreferenceProvider(instance()) }
            bind() from singleton { UserRepository(instance(), instance()) }
            bind() from singleton { QuotesRepository(instance(), instance(), instance()) }
            bind() from provider { AuthViewModelFactory(instance()) }
            bind() from provider { ProfileViewModelFactory(instance()) }
            bind() from provider { QuotesViewModelFactory(instance()) }
        }


}