package com.kevinjanvier.understandmvvm.ui.auth.home.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kevinjanvier.understandmvvm.data.repository.UserRepository

class ProfileViewModelFactory (
    private val repository: UserRepository) :ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ProfileViewModel(repository) as T
    }
}


