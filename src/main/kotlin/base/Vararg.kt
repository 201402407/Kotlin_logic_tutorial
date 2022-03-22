package base

import base.Vararg.Companion.showAll
import base.Vararg.Companion.sum

class Vararg {

    /** vararg: 가변인자. 즉, 매개변수의 개수를 동적으로 지정해 줄 수 있게 하는 변수. */
    companion object {

        /** Java as-is
        * Java는 Overroding이 가능하기 때문에, 아래 코드와 같이 함수 선언이 가능하다.
        * 하지만, 아래처럼 int parameter의 개수가 몇 개가 들어올 지 모르는데 예상 가능한 개수만큼 함수를 선언할 수는 없다.
        * */
//        int sum(int a) {}
//        int sum(int a, int b) {}
//        int sum(int a, int b, int c) {}
//        int sum(int a, int b, int c, int d) {}
//        int sum(int a, int b, int c, int d, int e) {}

        /**
         * 그래서 나온게, Java에서는 가변 인자를 사용할 수 있다.
         * 배열 타입도 인자로 줄 수 있고, 가변 인자는 내부적으로 배열을 생성해서 사용함.
         */
//        int sum(int...nums) { for(int num : nums) { ... } }

        /**
         * Kotlin은 간단하고, 명료하다.
         * 인자 앞에 vararg를 붙이면 된다.
         */
        fun sum(vararg nums: Int) = nums.sum()  // parameter로 받은 숫자들을 전부 더하는 함수
        fun showAll(vararg s: String) { // parameter로 받은 문자열을 전부 보여주는 함수
            println(s.joinToString())
        }
    }
}

fun main() {
    // 여러 개수의 parameter를 지정해서 넘기기
    val result1 = Vararg.sum(1)
    val result2 = Vararg.sum(1, 2, 3, 4, 5, 6, 7)
    println("result1's result : $result1, result2's result : $result2")

    // 배열을 인자로 넘기기
    /** Int Array의 경우에는 String과는 다르게 arrayOf() 로 Array를 만들 경우 Array<Integer>
     * 가 된다. 그래서, 별도로 intArrayOf()를 사용하거나, toIntArray()를 사용하여
     * Array<Int>로 변경해야한다. */
    val nums = intArrayOf(1, 2, 3, 4, 5, 6, 7)
    val nums2: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6, 7)
//    Vararg.sum(nums)    // error(Type mismatch!)
    sum(*nums)
    sum(*nums2.toIntArray())

    val test = arrayOf("A", "B", "C")
//    showAll(test) // // error(Type mismatch!)
    showAll(*test) // *(spread operator) 를 앞에 붙이면 가능해짐
}