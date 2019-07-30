package com.kevinjanvier.understandmvvm.ui.auth.home.profile

import androidx.lifecycle.ViewModel;
import com.kevinjanvier.understandmvvm.data.repository.UserRepository

class ProfileViewModel(
    repository: UserRepository
) : ViewModel() {

    val user = repository.getUser()

}