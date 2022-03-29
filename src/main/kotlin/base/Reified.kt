package base

class Reified {

    /** Reified
     * Kotlin -> Java의 Runtime 시점에 Generic Type을 알고 싶을 때 사용.
     * 
     * 보통, Generic Type의 Class Type을 가지고 사용하는 함수를 만들고 싶을 때, Java에서는 Generic Type 정보를 지우기 때문에(Type Erasing)
     * Class Type 정보가 담긴 객체와 함께 parameter로 전달해서 만들어야 한다.
     * 하지만, Reified를 사용하게 되면 Generic Class Type 정보까지 함께 전달할 수 있게 해준다.
     *
     * (참고) 무조건 inline 과 함께 사용해야 한다.
     */

    companion object {
        /** 예시(사용 불가능) */
        /*
        fun <T> printGenericTypes(value: T) {
            when(T::class) {    // Cannot use 'T' as reified type parameter. Use a class instead. 오류 발생.
                String::class -> println("this type is String. $value")
                Int::class -> println("this type is Int. $value")
                else -> println("X")
            }
        }
         */

        /** 위 함수를 Reified를 활용하여 재구현(inline 추가 필요) */
        inline fun <reified T> printGenericTypesRenew(value: T) {
            when (T::class) {
                String::class -> println("this type is String. $value")
                Int::class -> println("this type is Int. $value")
                else -> println("X")
            }
        }

        /** inline function
         * 코틀린에서는 람다를 매개 변수로 사용하는 고차 함수를 “인라인 함수(Inline function)”으로 정의하여 오버 헤드를 줄일 수 있는 방법을 제공
         *
         * function 앞에 inline 키워드를 붙여 사용
         *
         * inline function은 kotlin -> java의 컴파일 단계에서 "함수 호출 = invoke" 방식이 아닌 "코드 복사" 방식으로 코드 변환해줌.
         * -> lambda function 전달 시 발생하는 메모리, 호출 등의 오버헤드 감소 목적. Function Object 생성 및 invoke() 함수 호출에 따른 비용 절감
         *
         * (참고) 특정 람다를 인라인 방식에서 제외하고 싶을 때는 noinline 키워드 사용
         */
        /** inline function */
        fun print(lambda: () -> Unit) {
            println("lambda 함수 호출!")
            lambda()
        }
        /** inline을 사용하지 않고, Java로 컴파일 되면
         * -> 1) 매 번 위 함수 호출 시 Function Object를 생성해야함 + invoke() 함수 호출해야함.
         * -> 2) lambda 함수 내부에 또다른 람다함수를 호출하는 고차 함수 지옥일 경우, 코드의 깊이가 깊어지고 더욱 메모리 낭비가 심해짐.
         */
        /*
        public static final void print(Function1 lambda) {  // Function object 생성되서 parameter로 대입해야함.
            System.out.println("lambda 함수 호출!");
            lambda.invoke();    // invoke() 함수 호출을 통해 람다함수 실행함.
        }
         */
        /** inline을 사용하는 경우 */
        inline fun printInline() {
            println("lambda 함수 호출!")
            /* lambda() 함수 코드가 내부에 코드로 추가되서 변환됨. */
        }

        /** (참고) public inline function은 private function에 접근할 수 없음. */
        inline fun temp() {
//            temp2() // Public-API inline function cannot access non-public-API 'private final fun temp2(): Unit defined in base.Reified.Companion'
        }
        private fun temp2() {
            println("temp2")
        }
        
    }
}

fun main() {
    /** Generic Class Type 결과가 정상적으로 나옴. */
    Reified.printGenericTypesRenew("String")
    Reified.printGenericTypesRenew("Int")
}