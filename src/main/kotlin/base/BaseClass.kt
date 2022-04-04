package base

import sun.jvm.hotspot.HelloWorld.e

/** 기본 클래스 선언. public 생략 가능(default) */
class BaseClass {

}

/** case 1. default */
//class BaseClass() {}

/** case 2. 생성자의 parameter가 없을 경우 () 생략 가능 */
class BaseClass2 {}

/** case 3. class parameter에 var/val 변수를 선언하면, 클래스 내부 멤버변수 및 생성자 선언을 동시에 할 수 있다. */
class BaseClass3(val temp: String) {}

/** case 4. class parameter에 일반 변수를 받게 되면, 클래스 내부 멤버변수로 선언되지 않는다. */
class BaseClass4(temp: String) {}

/** case 5. class parameter로 받은 일반 변수 값을 클래스 내부 멤버변수에 초기화한다. */
class BaseClass5(temp: String) {
    val name: String = temp
}

/** case 6. init(기본 생성자가 호출된 직후 바로 실행되는 코드 블럭 */
class BaseClass6(private val temp: String, name: String) {
    var name: String
    var id: String

    init {
        this.name = name
        this.id = "TEMP"
        println("temp :: $temp, name : $name, id : $id")
    }
}

/** case 7. constructor(보조 생성자. 추가적인 생성자 함수를 작성하고 싶을 때 활용) */
class BaseClass7(name: String) {
    var name: String
    var id: String? = null

    init {
        this.name = name
        println("name : $name")
    }

    // 보조 생성자(Overloading) 생성자.
    // 보조 생성자를 호출하게되면, this() 에 의해 init 기본생성자를 호출한 다음 보조 생성자 호출됨.
    // 즉, 무조건 기본 생성자 호출 후 보조 생성자 호출됨. (일종의 super)
    constructor(name: String, id: String) : this(name) {
        this.name = name
        this.id = id
        println("name : $name, id : $id")
    }
}

/** case 8. class parameter에 받은 일반 변수의 Default 값 설정 가능. */
class BaseClass8(name: String = "NONAME") {
    var name: String
    init {
        this.name = name
        println("name : $name")
    }
}

/** case 9. open class
 * Java같은 경우에는 기본적으로 이 클래스에 final이 붙어있지 않으면 다른 클래스에서 모두 이 클래스를 상속할 수 있다.
 * 하지만, Kotlin은 클래스와 메소드 전부 default로 final이 선언되어있다.
 * 그래서, 어떤 클래스가 자가자신의 클래스를 상속할 수 있도록 허용하려면 클래스 앞에 open 변경자를 붙여야 한다.(메소드 포함) */
open class BaseClass9 {  // open을 붙였기 때문에, BaseClass9 클래스는 상속 허용
    fun getStr(): String = "GET_STR"
    open fun openGetStr(): String = "OPEN_GET_STR"  // open을 붙였기 때문에, openGetStr 메소드는 Overriding 허용
}
/** open class 는 상속 가능 */
class InheritanceBaseClass9 : BaseClass9() {
    /* getStr' in 'BaseClass9' is final and cannot be overridden 발생.
    override fun getStr(): String {
        return super.openGetStr()
    }
     */

    /** open fun 함수는 overriding 가능 */
    override fun openGetStr(): String {
        return "super.openGetStr() : OPEN_GET_STR"
    }
}

/** case 10. 상속과 구현 차이 */
interface BaseClass10Interface {}
open class BaseClass10Parent {}
class BaseClass10Impl: BaseClass10Interface {}  // 인터페이스 구현은 인터페이스 명 끝에 ()가 없다.
class BaseClass10Child: BaseClass10Parent() {}  // 부모클래스 상속은 부모클래스 명 끝에 ()가 있다.

/** case 11. protected 및 internal
 * 보통 아무런 선언이 없으면 kotlin은 public으로 기본 설정됨.
 *
 * protected (class scope 관점)
 * -> Java는 protected 일 때, 다른 패키지에 있는 클래스라도 상속받는다면 overriding 할 수 있다.
 * -> 하지만, Kotlin은 protected인 경우, 무조건 같은 모듈 내에서 상속받았을 경우에만 접근이 가능하다.
 *
 * internal
 * -> 같은 모듈 내에서만 접근 가능함
 * -> 보통 kotlin에서 모듈의 단위는 Gradle로 잡는다.
 * -> Gradle은 서브프로젝트까지 포함하여 한 번에 빌드할 수 있어 메인안에 서브 프로젝트를 끼워넣는다.
 * */
open class BaseClass11Parent {
    private val a = 1
    protected open val b = 2
    internal val c = 3
    val d = 4   // default는 public

    /** case 12. nested class / inner class
     * 중첩 클래스는 class 안에 class를 선언한 것.(inner 선언 X) -> java에서는 static class이고 여기서는 static 생략.
     */
    protected class Nested {
        val e: Int = 5  // public default(private는 외부에서 접근 불가능. Java는 가능)
    }

    /**
     * inner class는 class 앞에 inner 선언.
     */
    inner class NestedInner {
        val f: Int = 6
    }
}
class Subclass: BaseClass11Parent() {
    /** private 접근 불가능. */
    /** protected 접근 가능. -> overriding 필요 */
    override val b: Int
        get() = super.b

    fun temp() {
        println(c)  /** internal 접근 가능. 같은 모듈이기 때문에. */
        println(d)  /** public 접근 가능. */
        println(Nested().e) /** nested class(중첩 클래스) 접근 가능. 단, 객체 생성 필요 */
        println(NestedInner().f)    /** public inner class 접근 가능. */

        BaseClass11Parent.Nested().e    /** (inner class 붙이지 않은 경우) Nested class 객체로 직접 접근 가능 */
//        BaseClass11Parent().Nested().e // 불가능
        BaseClass11Parent().NestedInner().f /** (inner class 붙인 경우) 외부 클래스 객체 생성 후 inner class 객체로 접근 가능 */
//        BaseClass11Parent.NestedInner().f   // 불가능
    }
}
class OuterClass(val obj: BaseClass11Parent) {
    /** (private) obj.a 접근 불가능 */
    /** (protected) obj.b 접근 불가능 -> 상속받은 클래스만 사용 가능. 외부에서 사용할 수 없음. */
    /** (Nested class) obj.Nested() 또는 obj.Nested::e 접근 불가능. protected 이기 때문에. */
    fun temp() {
        println(obj.c)  /** internal 접근 가능. 같은 모듈이기 때문에. */
        println(obj.d)  /** public 접근 가능. */
        println(obj.NestedInner().f)    /** public inner class 접근 가능. */
    }
}

/** case 13. data class
 * DTO(POJOs / POCOs)에 주로 활용되는 class
 * 모든 property에 대해 getter(var 변수는 setter) 제공
 * equals() / hashCode() / toString() / copy() 함수 제공
 * componentN() 함수 제공
 */
data class BaseClass13(val name: String, var temp: Int, val email: String)


fun main() {
    val instance = BaseClass()          // 인스턴스 생성. new 키워드 사용 X

    val temp3 = BaseClass3("temp")
    println(temp3.temp)                 // Kotlin은 멤버 변수로 저장함과 동시에 getter/setter가 자동으로 생성된다.
    println()

    val temp4 = BaseClass4("temp")
//    println(temp4.temp)               // temp는 Temp3 class와는 다르게 멤버변수로 선언되지 않아서 ERROR 발생

    val temp5 = BaseClass5("name")
    println(temp5.name)                 // output : "name"
    println()

    val temp6 = BaseClass6("temp", "name")   // output : "temp :: temp, name : name, id : TEMP"
    println()

    var temp7 = BaseClass7("name")               // 기본 생성자만 호출.
    /*
    output : "name : name"
     */
    println()

    var temp77 = BaseClass7("name", "id")   // 보조 생성자 호출. (이 때, 기본 생성자도 함께 호출됨)
    /*
    output :
    "name : name"
    "name : name, id : id"
     */
    println()

    var temp8 = BaseClass8()                         // output : "name : NONAME"
    var temp88 = BaseClass8("HAVE NAME")       // output : "name : HAVE NAME"
}