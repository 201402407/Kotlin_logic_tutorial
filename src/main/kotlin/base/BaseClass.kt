package base

/** 기본 클래스 선언. public 생략 가능(default) */
class BaseClass {

}

/** case 1. default */
class Temp() {}

/** case 2. 생성자의 parameter가 없을 경우 () 생략 가능 */
class Temp2 {}

/** case 3. class parameter에 var/val 변수를 선언하면, 클래스 내부 멤버변수 및 생성자 선언을 동시에 할 수 있다. */
class Temp3(val temp: String) {}

/** case 4. class parameter에 일반 변수를 받게 되면, 클래스 내부 멤버변수로 선언되지 않는다. */
class Temp4(temp: String) {}

/** case 5. class parameter로 받은 일반 변수 값을 클래스 내부 멤버변수에 초기화한다. */
class Temp5(temp: String) {
    val name: String = temp
}

/** case 6. init(기본 생성자가 호출된 직후 바로 실행되는 코드 블럭 */
class Temp6(private val temp: String, name: String) {
    var name: String
    var id: String

    init {
        this.name = name
        this.id = "TEMP"
        println("temp :: $temp, name : $name, id : $id")
    }
}

/** case 7. constructor(보조 생성자. 추가적인 생성자 함수를 작성하고 싶을 때 활용) */
class Temp7(name: String) {
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
class Temp8(name: String = "NONAME") {
    var name: String
    init {
        this.name = name
        println("name : $name")
    }
}

fun main() {
    val instance = BaseClass()          // 인스턴스 생성. new 키워드 사용 X

    val temp3 = Temp3("temp")
    println(temp3.temp)                 // Kotlin은 멤버 변수로 저장함과 동시에 getter/setter가 자동으로 생성된다.
    println()

    val temp4 = Temp4("temp")
//    println(temp4.temp)               // temp는 Temp3 class와는 다르게 멤버변수로 선언되지 않아서 ERROR 발생

    val temp5 = Temp5("name")
    println(temp5.name)                 // output : "name"
    println()

    val temp6 = Temp6("temp", "name")   // output : "temp :: temp, name : name, id : TEMP"
    println()

    var temp7 = Temp7("name")               // 기본 생성자만 호출.
    /*
    output : "name : name"
     */
    println()

    var temp77 = Temp7("name", "id")   // 보조 생성자 호출. (이 때, 기본 생성자도 함께 호출됨)
    /*
    output :
    "name : name"
    "name : name, id : id"
     */
    println()

    var temp8 = Temp8()                         // output : "name : NONAME"
    var temp88 = Temp8("HAVE NAME")       // output : "name : HAVE NAME"
}