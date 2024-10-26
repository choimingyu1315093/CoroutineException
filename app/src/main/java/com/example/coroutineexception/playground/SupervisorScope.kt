package com.example.coroutineexception.playground

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.supervisorScope
import kotlin.random.Random

//CoroutineScope + SupervisorJob = SupervisorScope
//단, SupervisorScope를 사용하려면 CEH를 붙여줘야 한다.
suspend fun printRandom7(){
    delay(1000L)
    println("printRandom7 ${Random.nextInt(0, 500)}")
}

suspend fun printRandom8(){
    delay(500L)
    throw ArithmeticException()
}

suspend fun supervisoredFunc() = supervisorScope {
    launch { printRandom7() }
    launch (ceh3){ printRandom8() }
}

val ceh3 = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("Something happened: $throwable")
}

fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO)
    val job = scope.launch {
        supervisoredFunc()
    }
    job.join()
}