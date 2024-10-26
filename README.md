어디에도 속하지 않지만 원래부터 존재하는 전역 GlobalScope. 단, 어디에도 속하지 않아서 잘 안쓴다.
GlobalScope 대신 CoroutineScope를 사용한다.

CoroutineExceptionHandler(CEH)
runBlocking에서는 사용 불가!!

SupervisorJob
부모 또는 자식 코루틴이 취소되면 그에 포함되는 모든 코루틴이 취쇠된다.
단, SupervisorJob을 사용하면 예외처리가 아래 방향으로만 전달된다. 
따라서 자식 코루틴이 취소가 되더ㅗ라도 부모 코루틴에는 지장을 주지 않는다.
runBlocking {
	val job1 = launch {}
	val job2 = launch {}
}
이때 job2가 취소되면 부모코루틴인 runBlocking이 취소되고 그로인해 job1도 취소가된다.
그런데 SupervisorJob을 사용하면 job2가 취소되면 그의 자식 코루틴은 취소가 되지만 부모코루틴에는 영향이 없다
(CoroutineFastcampus FirstCoroutine 프로젝트 FifthActivity에서 확인가능)

CoroutineScope + SupervisorJob = SupervisorScope
단, SupervisorScope를 사용하려면 예외가 발생하는 코루틴에 CEH를 붙여줘야 한다.
