package base

/**
 * by lazy와 lateinit
 * 단순히 객체 생성 시 변수 값(또는 인스턴스)의 초기화(또는 생성)이 함께 이뤄지는데,
 * 당장에 사용할 것이 아니면 이 시점을 실제로 사용하는 시점으로 늦추기 위해 사용.
 * 즉, 메모리 효율성(안쓰는데 만들어두면 메모리 낭비이기 때문에) 을 위함.
 * 그럼 null로 선언해두면 되지 않나 ??
 * -> 1) 절대 null이 되면 안되는 값
 * -> 2) val 변수로서, 한 번 값이 대입되면 다시 변하지 않는 값
 */
class Lazy {

    companion object {

        /** Lateinit
         * lateinit은 처음 값의 Type만 지정해주고, 실제 값까지 함께 선언하지 않는다.
         * 이후에 값을 지정해줘야 함. null이 될 수 없고, Primitive type(Int, Long, Double, Float, Boolean 등) 사용이 불가능하다.
         * */
        lateinit var lateInit: String
        fun setLateInitValue() {
//            println(lateInit)   // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property lateInit has not been initialized
            lateInit = "Late-Init"
            println(lateInit)   // output : "Late-Init"
        }

        /** by lazy
         * read-only 느낌으로, 변할 수 있는 값을 활용해 고정 값을 대입할 때 주로 사용됨.
         * val만 선언 가능
         * */
        lateinit var lastName: String
        fun setName() {
//            val name: String by lazy { "temp" }
            val name: String by lazy { lastName + "HAEWON" }    // lastName 값이 할당되면, name 값 또한 할당됨.
//            println(name)   // Exception in thread "main" kotlin.UninitializedPropertyAccessException: lateinit property lastName has not been initialized
            lastName = "LEE"
            println(name)   // output : "LEEHAEWON"
        }
    }

}

fun main() {
    Lazy.setLateInitValue() // lateinit
    Lazy.setName()          // by lazy
}