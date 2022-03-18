package base

class BaseGrammer {

//    <p>조건식</p>
//    <p>Any와 instanceof</p>

    companion object {
        /* 1. val/var */
        val a : Int = 1 // val : read-only 변수. 즉, 변할 수 없는 변수. (java의 final, C/C++/typescript의 const)
        var b : Int = 1 // var : 기본적인 변수. 즉, 변할 수 있는 변수.

        /* 2. nullable(?와 !!) */
        fun nullableExample() {
            /* fail case
            // ?가 없기 때문에, aa 변수는 무조건 null이 들어올 수 없다.
            var aa: Int = 10
            aa = null
             */

            /* success case */
            var bb: String? = "null"  // ?를 붙이면 null 일 수도 있다는 뜻. 즉, nullable 하다는 것을 명시.
            bb = null   // nullable한 변수이기 때문에, null로 값 변경 가능

            println("${bb}")    // null 이라는 문자열로 print 출력됨
            println("${bb!!}")  // !!를 붙이면 null 인 경우, NPE 에러 발생시킨다. 이같은 경우에는 bb 변수 값이 null이므로 NPE 에러 발생.

        }
    }
}

fun main() {
    BaseGrammer.nullableExample()
}