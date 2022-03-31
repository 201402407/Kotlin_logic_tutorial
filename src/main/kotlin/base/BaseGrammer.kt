package base

class BaseGrammer {

    companion object {
        /** 1. val/var */
        val a : Int = 1 // val : read-only 변수. 즉, 변할 수 없는 변수. (java의 final, C/C++/typescript의 const)
        var b : Int = 1 // var : 기본적인 변수. 즉, 변할 수 있는 변수.

        /** 2. nullable(?와 !!) */
        fun nullableExample() {
            /* fail case
            // ?가 없기 때문에, aa 변수는 무조건 null이 들어올 수 없다.
            var aa: Int = 10
            aa = null
             */

            /* success case */
            var bb: String? = "null"  // ?를 붙이면 null 일 수도 있다는 뜻. 즉, nullable 하다는 것을 명시.
            bb = null   // nullable한 변수이기 때문에, null로 값 변경 가능

            println("$bb")    // null 이라는 문자열로 print 출력됨
//            println("${bb!!}")  // !!를 붙이면 null 인 경우, NPE 에러 발생시킨다. 이같은 경우에는 bb 변수 값이 null이므로 NPE 에러 발생.
        }

        /** 3. 조건식 */
        // 기본 조건식
        fun conditionFunc(a: Int, b: Int): Int {
            if(a > b) {
                return a
            }
            else
                return b
        }
        // return 생략 조건식
        fun conditionBetterFunc(a: Int, b: Int): Int = if(a > b) a else b   // return 및 대괄호 생략 가능. 가독성 높아짐.

        /** 4. Any와 instanceof */
        fun getObj(obj: Any): Int? {
            if(obj is String) { // is는 Java의 instanceof 와 같다. Type을 확인하는 연산자.
                println("parameter로 받은 obj의 Type은 String 입니다.")
                return obj.length   // 만약, param으로 받은 obj의 type이 String이면 obj의 length 값을 리턴한다.
            }

            if(obj !is Int) {   // is 앞에 !를 붙이면 not is와 같음.
                println("parameter로 받은 obj의 Type은 Int가 아닙니다.")
            }

            return null // Int?로 리턴 가능하기 때문에 null도 리턴할 수 있다.
        }

        /** 5. When */
        // if 문을 반복해서 사용하지 않고, when을 활용해 간단한 조건 / 결과 코드로 작성할 수 있음. 즉, if-else if-else를 when으로 표현한 것.
        // java의 switch와도 동일한 기능.
        fun casesUsedWhen(obj: Any) {
            when(obj) {
                is Int -> println("obj is Int Type. and value is $obj")
                "string" -> println("obj's value is string")
                3L -> println("obj is 3L")
                !is Boolean -> println("obj is not Boolean Type.")
                else -> println("what is the obj's Type?")  // when은 무조건 하나의 결과를 리턴해야 하므로, else는 무조건 필요하다.
            }
        }

        /** 6. loop(for, while) */
        fun iteratorLoop() {
            var tempList: ArrayList<String> = ArrayList()

            // 기본적인 for loop
            for(str in tempList) {
                println(str)
            }

            // 1에서부터 5까지 순차적으로 증가
            for(i in 1..5) {
                print("$i ")
            }
            println()

            // 10에서부터 2씩 감소
            for(i in 10 downTo 1) {
                print("$i ")
            }
            println()

            // 1에서부터 10까지 2 간격으로 증가시키기(일정 간격으로 증가)
            for(i in 1..10 step 2) {
                print("$i ")
            }
            println()

            // 5를 제외하고 1까지 감소하며 출력(끝 숫자 제외하고 출력)
            for(i in 1 until 5) {
                print("$i ")
            }
            println()

            // while, do-while은 java와 동일
            var a : Int = 1
            while(a <= 10) {
                print("${a++} ")
            }
            println()
        }

        /** 7. null과 not-null */
        val name : String? = null   // nullable
//        val id : String = name!!  // not-null. 그래서, name의 값은 null인데 not-null을 명시했으므로, NPE 발생.
        val notNullStr : String = "null이 아닙니다."
        val id : String = notNullStr!!  // null이 아니기 때문에, not-null 명시 가능

        fun getId() = println(id)

        // 안전 호출 연산자
        fun getLength() {
            val name : String? = null
            println(name?.length)       // output : null

            val id : String = "아이디"
            println(id?.length)         // output : 아이디
        }

        // 엘비스 연산자
        // 엘비스 연산자는 좌측의 코드가 null이 아니면 그 값을 리턴하고, null인 경우 우측의 값을 리턴한다.
        fun elvisOperation() {
            val name : String? = null
            println(name ?: "NULL이었구나..")
            println(name?.length ?: "length도 NULL이었구나..")

            val id : String = "아이디"
            println(id ?: "NULL이었구나 이것도..")

            println(name?.length ?: id ?: "다 NULL이었어..?")
        }

        /** 8. object */
        /** object는 java에서의 싱글톤 패턴이라고 생각하면 된다.
         * Java에서는 instance를 활용하여 멤버 객체 하나만 생성해두고, 이를 여러 곳에서 활용하는 방식으로 코딩했다면,
         * Kotlin에서는 object 하나만으로 위 싱글톤 패턴을 간편하게 구현할 수 있다.
         * 대신, 런타임 시 object의 인스턴스가 생성되기 때문에, 메모리 측면에서 이슈가 있을 수 있다.
         * -> 인스턴스 생성(초기화) 시점 조정을 위해 by lazy, lateinit 사용 */
        object TempObject {
            val temp: String = "TEMP STRING"
        }

        // java 에서의 코드
        /*
        class TempObject {
            static Object obj;

            TempObject() {

            }

            public static synchronized Object getInstance() {
                if(this.obj == null) {
                    this.obj = new Object();
                }

                return this.obj;
            }
        }
         */

        /**
         * 9. pair와 triple
         * pair는 2개의 객체를 저장할 수 있는 하나의 객체
         * triple은 3개의 객체를 저장할 수 있는 하나의 객체
         */
        /* Pair */
        val pair1 = Pair("PAIR", 1234)
        val pair2 = "HELLO" to "WORLD"

        /* pair의 각 객체를 가리키는 방법 2가지 */
        fun pairSample(pair: Pair<String, String>): Pair<String, String> {
            val (hello, world) = "HELLO" to "WORLD" // 새로운 변수 정의
            val list = pair2.toList()   // List 변환 가능
            println("pair1 1번 째 객체: ${pair1.first} 이고, 2번 째 객체: ${pair1.second} 이다.")
            println("pair2 1번 째 객체: ${pair2.component1()} 이고, 2번 째 객체: ${pair2.component2()} 이다.")
            return pair
        }

        /* Triple */
        val triple = Triple<String, String, Int>("HELLO", "WORLD", 505050)
        fun tripleSample(triple: Triple<String, String, Int>): Triple<String, String, Int> {
            val list = triple.toList()
            println("triple 1번 째 객체: ${triple.first} 이고, 2번 째 객체: ${triple.second} 이고, 3번 째 객체: ${triple.third} 이다.")
            println("triple 1번 째 객체: ${triple.component1()} 이고, 2번 째 객체: ${triple.component2()} 이고, 3번 째 객체: ${triple.component3()} 이다.")
            return triple
        }
    }
}

/* TEST */
fun main() {
    BaseGrammer.b = 4                           // 1. var는 변할 수 있음.
    BaseGrammer.nullableExample()               // 2. nullable test
    println()

    BaseGrammer.getObj("String")            // 4. Any와 instanceof - parameter에 String Type 대입 시
    BaseGrammer.getObj(3)                   // 4. Any와 instanceof - parameter에 Int Type 대입 시
    println()

    BaseGrammer.casesUsedWhen("string")     // 5. when - "string" 대입
    BaseGrammer.casesUsedWhen(3L)           // 5. when - Long type 대입
    BaseGrammer.casesUsedWhen(true)         // 5. when - Boolean type 대입(else 탐)
    println()

    BaseGrammer.iteratorLoop()                  // 6. loop(for, while)
    println()

    BaseGrammer.getId()                         // 7. null과 not-null
    BaseGrammer.getLength()                     // 7. null과 not-null - 안전 호출 연산자
    BaseGrammer.elvisOperation()                // 7. null과 not-null - 엘비스 연산자

    BaseGrammer.pairSample(BaseGrammer.pair2)   // 9. pair와 triple
    BaseGrammer.tripleSample(BaseGrammer.triple)// 9. pair와 triple

}