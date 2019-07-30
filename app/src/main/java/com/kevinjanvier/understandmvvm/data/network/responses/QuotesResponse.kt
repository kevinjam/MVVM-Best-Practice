package com.kevinjanvier.understandmvvm.data.network.responses

import com.kevinjanvier.understandmvvm.data.db.entities.Quote

data class QuotesResponse(
    val isSuccessful: Boolean? = false,
    val quotes: List<Quote>

)