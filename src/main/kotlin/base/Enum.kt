package base

/** Enum
 * Enum은 Kotlin에선, Comparable Interface를 구현하는 Abstarct class.
 * 중요한 부분인데, Enum은 추상 클래스로 되어있다.
 * Default Enum
 */
enum class Enum(val ColorName: String) {
    RED("빨강"),
    GREEN("초록"),
    BLUE("파랑"); // 끝에 세미콜론 필수

    /**
     * Enum은 추상클래스 이기 때문에, companion object를 선언할 수 있다.
     */
    companion object {
        fun print() {   // Enum에 대한 설명을 출력하는 함수
            enumValues<Enum>()
                .map { "${it.name} 을 한글로 하면 ${it.ColorName} 입니다." }
                .forEach{ println(it) }
        }
    }
}

/**
 * 멤버변수로 함수를 추가할 수 있는 Enum
 */
enum class Color1(
    val colorName: String,
    val getBrightness: (Int) -> String  // 이렇게 멤버변수로 함수를 추가해도 된다.
) {
    RED("빨강", {
        when(it) {
            in 0..100 -> "진한빨강"
            in 101..200 -> "빨강"
            in 201..255 -> "연한빨강"
            else -> throw EnumValueMisMatchedException()
        }
    }),
    GREEN("초록", {
        when(it) {
            in 0..100 -> "진한초록"
            in 101..200 -> "초록"
            in 201..255 -> "연한초록"
            else -> throw EnumValueMisMatchedException()
        }
    }),
    BLUE("파랑", {
        when(it) {
            in 0..100 -> "진한파랑"
            in 101..200 -> "파랑"
            in 201..255 -> "연한파랑"
            else -> throw EnumValueMisMatchedException()
        }
    })
}

/**
 * Interface를 구현하여 함수를 만들 수 있다.
 */
interface BrightnessInterface {
    fun getBrightness(brightness: Int): String
}
enum class Color2(
    val colorName: String
) : BrightnessInterface {
    RED("빨강") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한빨강"
            in 101..200 -> "빨강"
            in 201..255 -> "연한빨강"
            else -> throw EnumValueMisMatchedException()
        }
    },
    GREEN("초록") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한초록"
            in 101..200 -> "초록"
            in 201..255 -> "연한초록"
            else -> throw EnumValueMisMatchedException()
        }
    },
    BLUE("파랑") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한파랑"
            in 101..200 -> "파랑"
            in 201..255 -> "연한파랑"
            else -> throw EnumValueMisMatchedException()
        }
    }
}

/**
 * 추상 메소드를 활용하여 함수를 만들 수 있다.
 * (Enum은 추상클래스 이기 때문에)
 */
enum class Color3(
    val colorName: String
) {
    RED("빨강") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한빨강"
            in 101..200 -> "빨강"
            in 201..255 -> "연한빨강"
            else -> throw EnumValueMisMatchedException()
        }
    },
    GREEN("초록") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한초록"
            in 101..200 -> "초록"
            in 201..255 -> "연한초록"
            else -> throw EnumValueMisMatchedException()
        }
    },
    BLUE("파랑") {
        override fun getBrightness(brightness: Int) = when(brightness) {
            in 0..100 -> "진한파랑"
            in 101..200 -> "파랑"
            in 201..255 -> "연한파랑"
            else -> throw EnumValueMisMatchedException()
        }
    };  // 끝에 세미콜론 필수

    abstract fun getBrightness(brightness: Int): String
}

fun main() {
    /**
     * 변수 활용
     * -> name: Enum value의 이름 반환
     * -> ordinal : Enum value의 인덱스 반환
     */
    println("Enum.BLUE.name(${Enum.BLUE.name})은 선언한 이름, Enum.BLUE.ColorName(${Enum.BLUE.ColorName})은 첫 번째 멤버변수다.")
    println("RED의 index : ${Enum.RED.ordinal} 이고, GREEN의 index : ${Enum.GREEN.ordinal} 이다.")

    /**
     * 함수 활용
     * -> Cloneable : clone()
     * -> Comparable : compareTo(). 두 Enum value 사이의 인덱스 차 반환
     * -> enumValues<Enum>() : 해당 Enum의 value를 Array로 만들어 반환
     * -> enumValueOf<Enum>(name: String) : 해당 Enum 중에서 name이란 이름을 가진 Enum 요소를 찾아 반환(name: 선언한 Enum 이름)
     */
    val array = enumValues<Enum>()  // Enum의 value를 담은 array
    println(array.filter { it.ColorName == "빨강" }.map { it.ColorName }.size)    // array는 iterable이기 때문에, 람다 가능
    val color = enumValueOf<Enum>("RED")    // 여기서는 Enum의 name을 넣어야한다.


    /**
     * Enum class에 멤버변수로 함수를 받아 선언하고, 이를 활용하는 방법
     */
    val color1Pair = 100 to Color1.GREEN
    getColorNameOfBrightness(color1Pair)

    /**
     * Enum class는 추상클래스이기 때문에, companion object를 사용할 수 있다.
     * 이를 구현한 함수 테스트
     */
    Enum.print()
}

/**
 * 밝기에 따른 색상 이름 구하기
 * -> Enum의 멤버변수로 함수 구현
 * -> 추상메소드로 함수 구현한 방식도 이와 동일
 */
private fun getColorNameOfBrightness(pair: Pair<Int, Color1>): String {
    val (brightness, color) = pair

    return color.getBrightness(brightness)
}

/**
 * 밝기에 따른 색상 이름 구하기
 * -> Enum의 interface를 구현한 함수
 * -> interface를 parameter로 받는다.
 */
@JvmName("getColorNameOfBrightness1")
private fun getColorNameOfBrightness(pair: Pair<Int, BrightnessInterface>): String {
    val (brightness, color) = pair

    return color.getBrightness(brightness)
}

/**
 *
 */

class EnumValueMisMatchedException() : RuntimeException()
// 참고 : banziha104.github.io