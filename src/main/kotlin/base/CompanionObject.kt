package base

class CompanionObject {
    /** companion object: static한 느낌의 singleton object */
    /** case 1. default companion object
    companion object {
        const val URL_PATH = "/sample"
    }
     */

    /** case 2. companion object에 이름 생성 가능
    companion object Temp {
        const val URL_PATH = "/sample"
        const val TEMP_VALUE = 2
    }
     */

    /** case 3. companion object interface 구현 */
    companion object: TempInterface {
        override fun temp() {
            println("companion object 로도 인터페이스를 구현할 수 있따!")
        }
    }
}

/** case 4. 추상 클래스에서도 companion object 사용 가능 */
abstract class AbstractClass {
    companion object {
        val temp: String = "TEMP"
        private fun getAbstractTemp(): String {
            return this.temp
        }
    }
}

fun main() {
    println("[QUESTION 1] companion object 란?")
    println("companion object는 간단하게, Java에서의 static한 느낌의 singleton object 라고 이해하면 된다.")
    CompanionObject.temp()
}

// case 3. 을 위해 임시로 작성한 인터페이스
interface TempInterface {
    fun temp()
}