package com.kevinjanvier.understandmvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kevinjanvier.understandmvvm.data.db.AppDatabase
import com.kevinjanvier.understandmvvm.data.db.entities.Quote
import com.kevinjanvier.understandmvvm.data.network.MyApi
import com.kevinjanvier.understandmvvm.data.network.SafeApiRequest
import com.kevinjanvier.understandmvvm.data.preferences.PreferenceProvider
import com.kevinjanvier.understandmvvm.util.Coroutines
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

private val MINIMUM_INTERVAL =6
class QuotesRepository(
    private val api:MyApi,
private val db :AppDatabase,
    private val pref:PreferenceProvider
):SafeApiRequest() {

    private val quotes = MutableLiveData<List<Quote>>()

    init {
        quotes.observeForever {

            saveQuotes(it)
        }
    }

    //call from viewmodel
    suspend fun getQuotes():LiveData<List<Quote>>{
        return withContext(Dispatchers.IO){
            fetchQuotes()
            db.getQuoteDao().getQuotes()
        }
    }

    //fetch quote from api
    private suspend fun  fetchQuotes(){
        val lastSavedAt = pref.getLastSaved()
        if (lastSavedAt == null || isFetchNeeded(LocalDateTime.parse(lastSavedAt))){
            val response = apiRequest { api.getQuotes() }
            quotes.postValue(response.quotes)

        }
    }

    private fun isFetchNeeded(savedAt: LocalDateTime):Boolean{
        
        return ChronoUnit.HOURS.between(savedAt, LocalDateTime.now()) > MINIMUM_INTERVAL
    }

    private fun saveQuotes(quotes: List<Quote>?) {
        Coroutines.io {
            pref.saveLastSaved(LocalDateTime.now().toString())
            db.getQuoteDao().saveAllQuotes(quotes!!)
        }
    }
}