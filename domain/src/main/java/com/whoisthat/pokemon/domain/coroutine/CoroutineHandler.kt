package com.whoisthat.pokemon.domain.coroutine

import kotlinx.coroutines.CoroutineExceptionHandler

val coroutineHandler = CoroutineExceptionHandler {
        _, exception -> println("Caught $exception")
}