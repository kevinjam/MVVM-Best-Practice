package com.kevinjanvier.understandmvvm.util

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//lambda in kotlin
object Coroutines {
    fun main (work:suspend (() ->Unit)) =
            CoroutineScope(Dispatchers.Main).launch {
                work()
            }
}