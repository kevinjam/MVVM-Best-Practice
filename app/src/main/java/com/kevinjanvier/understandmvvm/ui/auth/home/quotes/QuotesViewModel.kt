package com.kevinjanvier.understandmvvm.ui.auth.home.quotes

import androidx.lifecycle.ViewModel
import com.kevinjanvier.understandmvvm.data.repository.QuotesRepository
import com.kevinjanvier.understandmvvm.util.lazyDeffered

class QuotesViewModel(
   repository: QuotesRepository
) : ViewModel() {

    val quotes by lazyDeffered {
        repository.getQuotes()
    }

}




