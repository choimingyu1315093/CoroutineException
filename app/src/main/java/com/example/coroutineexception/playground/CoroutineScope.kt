package com.example.coroutineexception.playground

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

fun main() {
    val scope = CoroutineScope(Dispatchers.Default)
    val job = scope.launch (Dispatchers.IO){
        launch {
            printRandom2()
        }
    }
    Thread.sleep(1000L)
}

suspend fun printRandom2(){
    delay(500L)
    println(Random.nextInt(0, 500))
}