package base

/** Scope function
 * Scope function은 상위 Context Object를 코드 블럭 내에서 활용해 만들고, 이를 실행할 수 있게 하는 함수.
 * Scope function을 사용하면 람다식을 이용해서 호출하게 되는데
 * 일시적으로 Scope(범위)가 생기게 되고,
 * 이 범위 안에서는 호출한 상위 함수에서 전달해준 Context에 대해
 * it 또는 this 라는 Context Object를 활용하여 접근할 수 있다.
 * it과 this는 각 Scope function 별로 다르다. -> it은 function 내부에서 명시해야 사용가능하다. */

/** this는 Context Object의 변수 이름과 메소드 로컬 변수의 이름이 동일한 경우 로컬 변수를 우선해서 참조한다.. */
class Scope {
    companion object {

        /** let
         * Context Object 값을 활용하여 하나 이상의 함수를 호출할 때 사용
         * 추가로, null이 아닌 경우에 람다 함수를 호출하고자 할 때 사용. (?. 로 Safe Call)
         * Context Object: it
         * Return Value : lambda result(결과 값 반환. 마지막 라인)
         */
        val name: String = "HaeWon"
        val myName = name?.let {    // safe call(?.으로 not-null인 경우 let 함수 호출
            val myName = "$it LEE"
            println("이름 : $it 이고, 전체 이름은 $myName 입니다.")
            myName  // Return value(마지막 라인)
        }


        /** run
         * let과 활용 방도는 비슷하지만, 보통 let은 not-null인 경우에 사용하고,
         * run은 내부에 선언하는 변수들이 많고, 객체 초기화 / 반환 값 계산에 사용됨.
         * Context Object: this
         * Return value: lambda result(결과 값 반환. 마지막 라인)
         */
        val user = User("Lee Haewon", 28)
        val greetingMessage = user.run {    // this이기 때문에, 앞의 Object 이름을 명시해서 사용하지 않아도 됨.
            var temp: String = "1"
            var temp2: String = "2"
            var temp3: String = "3"
            "안녕하세요! 제 이름은 ${name}이고, 나이는 $age 입니다. 잘부탁드립니다."    // Return value(마지막 라인 값)
        }


        /** apply
         * 간단하게, 객체 초기화 시에 사용됨
         * 왜냐하면, Context Object를 다시 Return하기 때문에.
         * Context Object: this
         * Return value: Context Object(호출한 객체 리턴)
         */
        fun applyGreeting() {
            // apply 사용 전
            val appliedUser = User("Lee haewon", 28)
            println(greetingMessage(appliedUser))

            // apply 사용 후
            val rewritedUser = appliedUser.apply {  // this이기 때문에, 앞의 Object 이름을 명시해서 사용하지 않아도 됨.
                name = "이해원"
                age = 10000
            }
            println(greetingMessage(rewritedUser))  // name과 age가 appliedUser와는 다르다.
        }
        fun greetingMessage(user: User): String = "안녕하세요! 제 이름은 ${user.name}이고, 나이는 ${user.age} 입니다. 잘부탁드립니다."


        /** also
         * 기존 객체를 수정하거나 변경하지 않고, 간단한 디버깅 / 로깅 작업을 위한 코드 또는 부가적인 작업을 위해 사용
         * Context Object: it
         * Return value: Context Object(호출한 객체 리턴 - also는 이를 잘 활용하지 않음)
         */
        val alsoGreeting = user.also {
            println(greetingMessage(it))
        }


        /** with
         * 다른 Scope function과는 다르게 arguments(매개변수)로 Object를 받고 블럭으로 전달한다.
         * 코드 블럭 내에서 return value 없이 사용할 때 주로 활용함.
         * Context Object: this
         * Return value: lambda result(결과 값 반환. 마지막 라인)
         */
        val withGreeting = with(user) {
            println("${name}이 함께 인사합니다.")
            println(greetingMessage(this))
        }

        /** takeIf
         * 조건 함수가 true일 때, T를 리턴하며 false일 경우 null을 리턴
         * T.takeIf{} 로 활용
         * Context Object: it
         */
        fun takeIf() : User {
            val user = User("name", 10)
            // 가장 기본적인 코드
            if(user != null && user.status) {
                // doThis()
            }

            // 조금 개선
            if(user?.status) {
                // doThis()
            }

            // if 생략하고 가독성 증가
            user?.takeIf { it.status }?.apply { // 이 때, ?.를 사용하지 않으면 takeIf의 결과가 null이든 아니든 apply를 호출된다.
                // doThis()
            }

            // 또 다른 예시
            // user 객체의 id가 Null 또는 Blank인 경우에는 기존의 user 객체를 리턴하고, id에 값이 존재하면 새로운 User 객체를 리턴한다.
            return user?.takeIf { it.id.isNullOrBlank() } ?: User("name", 20)
        }

        /** takeUnless
         * 조건 함수가 false일 때, T를 리턴하며 true일 경우 null을 리턴(takeIf와 반대)
         * T.takeUnless{} 로 활용
         * Context Object: it
         */
        fun takeUnless() : User {
            val user = User("name", 10)
            // 가장 기본적인 코드
            if(user != null && user.status) {
                // doThis()
            }

            // 조금 개선
            if(user?.status) {
                // doThis()
            }

            // if 생략하고 가독성 증가
            user?.takeUnless { !it.status }?.apply { // 이 때, ?.를 사용하지 않으면 takeIf의 결과가 null이든 아니든 apply를 호출된다.
                // doThis()
            }

            // 또 다른 예시
            // user 객체의 id가 Null 또는 Blank인 경우에는 새로운 User 객체를 리턴하고, id에 값이 존재하면 기존의 user 객체를 리턴한다.
            return user?.takeUnless { it.id.isNullOrBlank() } ?: User("name", 20)
        }
    }
}

class User(var name: String, var age: Int) {
    var id: String = ""
    var status: Boolean = false
}

fun main() {
    println(Scope.myName)               // let
    println()

    println(Scope.greetingMessage)      // run
    println()

    Scope.applyGreeting()               // apply
    println()

    Scope.alsoGreeting                  // also
    println()

    Scope.withGreeting                  // with
    println()

    println("[takeIf] wanted: 10 , result: ${Scope.takeIf().age}")             // takeIf
    println("[takeUnless] wanted: 20 , result: ${Scope.takeUnless().age}")         // takeUnless
}