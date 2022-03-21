package coroutine

import kotlinx.coroutines.*

class SuspendFunc {

    /* coroutine에서의 suspend란 ?
     비동기 실행중인 coroutine 함수를 잠시 중지시킬 수 있는 함수를 설정(표시)한 것.

     라이브러리 의존성(dependency) - "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.0"
     *** Logger 사용하지 않아서, 출력 결과 보지 못함.

     사용 이유
     - 만약, suspend function이 없다면, Thread가 block될 때 아무것도 하지 못하고 중지됨. 즉, 다른 일을 할 수 없음.
     대신, suspend를 사용한다면 Thread가 block될 때, block시킨 함수의 실행을 잠시 중지시킨 후 다른 함수 작업을 처리할 수 있음.
     그래서, 하나의 Thread에서 쉬지않고(block) 여러 개의 coroutine 함수를 실행시킬 수 있다. block ~ resume 기간까지 다른 작업 수행 가능.
     이런 면에 있어서 coroutine은 light-weight 한다고도 함.

     사용 case
     - 다른 coroutine 함수에서 나온 결과를 받아야 하는 경우
     - 메모리 효율성을 높이기 위해
    */

    companion object {

        /* 1. suspend function 사용하지 않은 코드 */
        fun notSuspendFunc1() {
            // Thread.sleep()은 suspend 함수가 아님.
            Thread.sleep(1000)
            println { "[Not Suspend] After 1s of Thread.sleep in (${Thread.currentThread().name})" }

            Thread.sleep(2000)
            println { "[Not Suspend] After 2s of Thread.sleep in (${Thread.currentThread().name})"}

            println { "[Not Suspend] End in (${Thread.currentThread().name})"}
        }

        fun notSuspendFunc2() {
            Thread.sleep(3000)
            println { "[Not Suspend] After 3s of Thread.sleep in (${Thread.currentThread().name})" }

            Thread.sleep(3000)
            println { "[Not Suspend] After 3s of Thread.sleep in (${Thread.currentThread().name})" }

            println { "[Not Suspend] End in (${Thread.currentThread().name})" }
        }

        /* 2. suspend function 사용한 코드 */
        suspend fun suspendFunc1() {
            // delay() 자체는 suspend 함수이기 때문에, 반드시 suspend function에 사용할 수 있음.
            delay(1000)
            println("[Suspend] After 1s of Thread.sleep in (${Thread.currentThread().name})")

            delay(2000)
            println("[Suspend] After 2s of Thread.sleep in (${Thread.currentThread().name})")

            println("[Suspend] End in (${Thread.currentThread().name})")
        }

        suspend fun suspendFunc2() {
            delay(3000)
            println("[Suspend] After 3s of Thread.sleep in (${Thread.currentThread().name})")

            delay(3000)
            println("[Suspend] After 3s of Thread.sleep in (${Thread.currentThread().name})")

            println("[Suspend] End in (${Thread.currentThread().name})")
        }
    }

}

fun main() {

    /* 1. suspend function 사용X */
    CoroutineScope(Dispatchers.IO).launch {
        // 병렬로 2개의 함수 실행
        CoroutineScope(Dispatchers.IO).async {
            SuspendFunc.notSuspendFunc1()
        }

        CoroutineScope(Dispatchers.IO).async {
            SuspendFunc.notSuspendFunc2()
        }
    }

    /* 2. suspend function 사용 */
    CoroutineScope(Dispatchers.IO).launch {
        // 병렬로 2개의 함수 실행
        CoroutineScope(Dispatchers.IO).async {
            SuspendFunc.suspendFunc1()
        }

        CoroutineScope(Dispatchers.IO).async {
            SuspendFunc.suspendFunc2()
        }
    }

}