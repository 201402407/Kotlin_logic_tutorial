package contract

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

/** contract
 * 함수가 컴파일러가 이해하는 방식으로 동작을 명시적으로 설명할 수 있도록 한다.
 * ex) null check하기 위한 함수에 @ExperimentalContracts와 contract{} 를 통해 null check 하고,
 * 이 함수를 호출한 다음에는 parameter로 들어온 값을 null 이 아니다. 라고 컴파일러를 이해시킴.
 * 그래서 이후 코드에는 ?나 !!를 사용하지 않아도 동작한다.
 */
class Contract {
    companion object {

        /**
         * 반환 값이 Boolean인 경우
         * contract {
         *  implies ()
         * } 에서 () 안의 조건이 참이면 앞의 returns()의 ()로 리턴해주고,
         * 동시에 implies () 의 () 조건을 컴파일러에게 이해시켜준다.
         */
        @ExperimentalContracts
        fun isNotNullAndReturnBoolean(value: Any?): Boolean {
            contract {
                returns(true) implies (value != null)  // value가 null이 아니면, true를 리턴하면서 컴파일러에게 value가 null이 아님을 이해시켜줌.
            }

            return value != null
        }

        /**
         * 반환 값이 null인 경우
         * implies () 안의 조건이 참이면 함수 종료.
         */
        @ExperimentalContracts
        fun isNotNullAndReturnNull(value: String?) {
            contract {
                returns() implies (value != null)  // value가 null이 아니면, 함수 종료.
            }

            if(value == null) {
                throw IllegalArgumentException("value is Null!")    // value가 null인 경우, exception throw.
            }
        }


        /** callsInPlace
         * Type Check가 아닌, lambda function 내에서 함수 호출 횟수를 명시적으로 알려주기 위해 사용.
         * InvocationKind
         * .AT_LEAST_ONCE: 최소 한 번 이상 실행
         * .AT_MOST_ONCE: 최대 한 번만 실행
         * .EXACTLY_ONCE: 정확히 한 번만 실행
         */
        @ExperimentalContracts
        fun invokeLambda(lambda: () -> Unit) {
            contract {
                callsInPlace(lambda, InvocationKind.EXACTLY_ONCE)   // EXACTLY_ONCE : 정확히 한 번만 실행
            }
            lambda()
        }
    }
}

@ExperimentalContracts
fun main(): String {
    val nullableValue: String? = "null"

    /** isNotNull function의 contract return 값이 boolean인 경우
     * isNotNull function의 contract를 통해 nullableValue 값이 null이 아님을 컴파일러가 이해하기 때문에, ?를 사용하지 않아도 error가 발생하지 않음. */
    if(Contract.isNotNullAndReturnBoolean(nullableValue)) {
        val notNullableValue: String = nullableValue
    }
//    val notNullableValue: String = nullableValue // 타입 불일치 에러 발생!
    /** String.isNullOrEmpty() 함수 내부에는 contract가 포함되어 있음. */
    if(!nullableValue.isNullOrBlank()) {
        val notNullableValue: String = nullableValue
    }


    /** isNotNull function의 contract return 값이 null인 경우 */
    val notNullValue: String? = "notNullValue"
    Contract.isNotNullAndReturnNull(notNullValue)

    /*
    exception 없이 다음 코드가 진행된다면, exception 발생 없이 contract 조건을 만족했기 때문에
    이후 코드부터는 notNullValue 변수 값이 null 이 아님을 컴파일러가 이해했으므로 nullable(?나 !!) 명시가 필요없다.
     */
    val temp: String = notNullValue // 컴파일 에러 발생X
    notNullValue.let {  // 컴파일 에러 발생X
        println("notNullValue는 진짜 null이 아니구나!")
    }

    /**
     * callsInPlace 를 사용하지 않는 경우
     * -> 1) Captured~ : invokeLambda function이 몇 번 호출되는지(0~N) 명확히 모르기 때문에, val 변수 특성 상 여러 번 변할 수 있어 오류
     * -> 2) Variable~ : 마찬가지로, invokeLambda 함수가 몇 번 호출되는지(1~N) 명확히 모르기 때문에, return 값이 다를 수 있어 오류
     * */
    /*
    val value: String
    Contract.invokeLambda {
        value = "TEMP"  // Captured values initialization is forbidden due to possible reassignment
    }
    return value    // Variable 'value' must be initialized
    */

    /**
     * callsInPlace 를 사용하는 경우
     */
    val value: String
    Contract.invokeLambda {
        value = "TEMP"  // 오류 발생 X - 무조건 한 번만 호출할 걸 알기 때문에
    }
    return value    // 오류 발생 X - 무조건 한 번만 호출할 걸 알기 때문에
}