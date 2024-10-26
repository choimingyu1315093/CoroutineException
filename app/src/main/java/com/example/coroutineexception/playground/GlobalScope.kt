package com.example.coroutineexception.playground

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random

//어디에도 속하지 않지만 원래부터 존재하는 전역 GlobalScope. 단, 어디에도 속하지 않기 때문에 관리가 어렵다는 단점(그래서 잘 안쓴다)
fun main() {
    val job = GlobalScope.launch (Dispatchers.IO){
        launch {
            printRandom()
        }
    }
    Thread.sleep(1000L)
}

suspend fun printRandom(){
    delay(500L)
    println(Random.nextInt(0, 500))
}