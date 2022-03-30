package base

/** sealed class
 * sealed class를 super class로 하는 하위 클래스(child class)의 종류를 제한하고, 이를 컴파일러에게 알려주기 위해 사용.
 * sealed class는 abstract class가 되기 때문에, sealed class 자체로는 객체 생성이 불가능하다.
 *
 * 특징
 * -> sealed class의 생성자는 private 이고, public 을 사용할 수 없다.
 * -> sealed class와 그 하위 클래스는 동일한 파일 내에서 정의되어야 한다.
 *
 * 장점
 * -> when 사용 시 else를 구현하지 않아도 됨
 * -> 하위 클래스는 class, data class, object class를 전부 사용할 수 있다.
 *
 * (참고) Enum과의 가장 큰 차이점은, Enum은 single instance 이지만, sealed class는 여러 개의 하위 클래스 객체를 생성할 수 있다.
 */
sealed class Sealed {
    object A: Sealed()
    abstract class B: Sealed()
    data class C(var temp: String) : Sealed()
}

/** 하나의 파일 내에서 구현하는 방식(이렇게도 가능) */
sealed class Sealed2

object A: Sealed()
abstract class B: Sealed()
data class C(var temp: String) : Sealed()



/** sealed class when 사용 예제(1)
 * -> sealed class 중 하위 클래스 하나라도 빠지면 when() compile error 발생. */
sealed class Color {
    object Red: Color()
    object Blue: Color()
    object Green: Color()
}
var color: Color = Color.Red
var myColor = when(color) { // object는 is 생략 가능
    Color.Red -> "RED"
    Color.Blue -> "BLUE"
    Color.Green -> "GREEN"
}


/** sealed class when 사용 예제(2)
 * -> data class 및 다중 객체 생성을 활용하여 함수 활용 */
sealed class Expr {
    data class Number(val num: Int): Expr()
    data class Sum(val num1: Expr, val num2: Expr): Expr()
    object Non: Expr()
}
fun eval(expr: Expr): Int = when(expr) {
    is Expr.Number -> expr.num
    is Expr.Sum -> eval(expr.num1) + eval(expr.num2)
    Expr.Non -> Int.MAX_VALUE
}
val num1 = Expr.Number(20)  // num1 객체 생성(Expr.Number)
val num2 = Expr.Number(30)  // num2 객체 생성(Expr.Number)
val sum = Expr.Sum(num1, num2)
val result = eval(sum)  // result : 50


/** sealed class when 사용 예제(3)
 * -> Generic Type을 활용 - 추후 예정 */