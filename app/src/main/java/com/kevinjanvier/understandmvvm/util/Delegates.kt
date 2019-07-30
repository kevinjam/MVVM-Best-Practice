package com.kevinjanvier.understandmvvm.util

import kotlinx.coroutines.*

/**
 * inside code modek
 */
fun <T> lazyDeffered(block:suspend CoroutineScope.() -> T):Lazy<Deferred<T>>{
    return lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            block.invoke(this)
        }
    }
}