package com.kevinjanvier.understandmvvm.ui.auth.home.quotes

import com.kevinjanvier.understandmvvm.R
import com.kevinjanvier.understandmvvm.data.db.entities.Quote
import com.kevinjanvier.understandmvvm.databinding.ItemQuotesBinding
import com.xwray.groupie.databinding.BindableItem

class QuoteItem(
    private val quote: Quote) : BindableItem<ItemQuotesBinding>() {
    override fun getLayout() = R.layout.item_quotes

    override fun bind(viewBinding: ItemQuotesBinding, position: Int) {
        viewBinding.setQuote(quote)
    }
}