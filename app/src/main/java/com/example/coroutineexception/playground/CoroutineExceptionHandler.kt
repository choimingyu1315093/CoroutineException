package com.example.coroutineexception.playground

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch (ceh) {
        launch {
            printRandom3()
        }
        launch {
            printRandom4()
        }
    }
    job.join()
}

suspend fun printRandom3(){
    delay(1000L)
}

suspend fun printRandom4(){
    delay(500L)
    throw ArithmeticException()
}

val ceh = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("Something happened: $throwable")
}