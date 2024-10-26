package com.example.coroutineexception.playground

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.joinAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

//부모 또는 자식 코루틴이 취소되면 그에 포함되는 모든 코루틴이 취소된다.
//단, SupervisorJob을 사용하면 예외처리가 아래 방향으로만 전달된다.
//따라서 자식 코루틴이 취소 되더라도 부모 코루틴에는 지장을 주지 않는다.
fun main() = runBlocking {
    val scope = CoroutineScope(Dispatchers.IO + SupervisorJob() + ceh)
    val job1 = scope.launch { printRandom5() }
    val job2 = scope.launch { printRandom6() }
    joinAll(job1, job2) // job1.join + job2.join 과 같은 의미다
}

suspend fun printRandom5(){
    delay(1000L)
    println("printRandom5 ${Random.nextInt(0, 500)}")
}

suspend fun printRandom6(){
    delay(500L)
    throw ArithmeticException()
}

val ceh2 = CoroutineExceptionHandler { coroutineContext, throwable ->
    println("Something happened: $throwable")
}
