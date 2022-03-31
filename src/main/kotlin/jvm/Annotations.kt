package jvm

import org.intellij.lang.annotations.Language

//class Annotation constructor(a: String, b: Int = 10, c: Boolean = true) {
class Annotations {

    /** @JvmField
     * 이 어노테이션을 사용하면 kotlin이 자동으로 생성해주던 getter/setter 함수를 생성하지 않게 한다.
     * 즉, 이 변수의 getter/setter 함수가 자동 생성되지 않게 됨.
     */
    @JvmField var jvmFieldValue: String = "jvmField"

    companion object {
        /** @JvmStatic
         * 이 어노테이션을 사용하지 않으면, static으로 활용하기 위해 사용한 companion object 안의 변수는
         * getter/setter가 상위 클래스 바로 밑에 생성되지 않고, Companion class에 생성된다.
         * 그래서, Annotation.Companion.getTemp() / Annotation.Companion.setTemp("static") 이렇게 사용해야한다.
         * @JvmStatic 을 변수 앞에 선언하면, 상위 클래스 바로 밑에 getter/setter가 생성된다.
         * 따라서, Annotation.getTemp() / Annotation.setTemp("static") 으로 활용 가능(참고로, 이 어노테이션을 붙인다고 Companion에 생기는 함수가 사라지지 않는다.)
         */
        @JvmStatic var jvmStaticValue: String = "jvmStatic"


    }


    /** @JvmName
     * kotlin -> java 컴파일 시 Jvm 시그니처를 변경해준다. = java에서 호출하는 kotlin의 Name이 변경된다.
     * Intellij에서는 아래와 같은 함수 선언 시 컴파일 에러를 표시해주는데, kotlin -> java 시 함수의 parameter가 List이기 때문에,
     * List의 Generic Type은 구분할 수 없다. 그래서, 동일한 함수로 판단해서 overloading이 되지 않아 에러가 발생한다.
     */
    /** @JvmName을 사용하지 않은 경우 */
//    fun jvmName(a : List<Int>) = null
//    fun jvmName(a : List<String>) = null

    /** @JvmName을 사용한 경우. kotlin 코딩 시 아래 함수대로 사용하면 됨. (Java에서는 Annotation 설정한 이름으로 호출해야함. */
    @JvmName("jvmNameIntArray")
    fun jvmName(a : List<Int>) = null
    @JvmName("jvmNameStringArray")
    fun jvmName(a : List<String>) = null

    /** @JvmName을 사용했을 때, java로 컴파일 된다면? */
    /*
    public static Object jvmNameIntArray(@NotNull List a) { // fun jvmName(a : List<Int>) = null
        return null;
    }
    public static Object jvmNameStringArray(@NotNull List a) { // fun jvmName(a : List<String>) = null
        return null;
    }
    */


    /** @Throws
     * 함수에 예외를 throw할 때, Annotation으로 Exception을 명시할 수 있다.
     */
    @Throws(java.lang.NullPointerException::class)
    fun throwsFunc(s: String) = null

    /** Java에서의 코드 */
    /*
    public static final Object throwsFunc(@NotNull String s) throws NullPointerException {
        return null;
    }
     */



    /** @JvmOverloads
     * kotlin 함수의 overloading Methods를 자동으로 생성해주는 Annotation.
     * kotlin -> java 시, kotlin에서 자주 사용하는 default parameters(arguments) 는 kotlin에서는 자동으로 이 arguments를 제외한
     * parameter만 받을 수 있는 함수를 만들어주기 때문에 별도 함수 선언 없이 사용할 수 있지만, java는 default arguments를 모르기 때문에
     * 자동으로 생성해주지 않는다.
     * 자바 코드로 직접 활용하는 일이 없으면 몰라도 됨..
     */
    @JvmOverloads constructor(a: String, b: Int = 10, c: Boolean = true) // java에서 b, c 가 없는 parameter를 가진 함수를 만들어줌


    /** @JvmInline
     * Kotlin 1.5 버전부터 정식으로 추가된 value class와 같이 Kotlin의 다른 버전과의 호환을 위해 사용함.
     * ** value class : Utils, Durations, DateFormatter와 같은 Wrapper class를 사용하고자 할 때 객체 생성 후 함수 호출하는
     * 비용을 줄이기 위해 사용함.
     * == kotlin -> java 시 객체를 활용하는 코드에서 가능하면 class의 property를 활용하는 코드에서 class Type이 아닌 primitive type으로 변경해줌.
     * (현재, val property만 사용가능하고, 1개만 선언 가능함)
     */
    @JvmInline value class QueryInput(@Language("GraphQL") val query: String)

    /* 참고 : https://velog.io/@dhwlddjgmanf/Kotlin-1.5%EC%97%90-%EC%B6%94%EA%B0%80%EB%90%9C-value-class%EC%97%90-%EB%8C%80%ED%95%B4-%EC%95%8C%EC%95%84%EB%B3%B4%EC%9E%90 */
    @JvmInline
    value class Duration private constructor(
        val millis: Long,
    ) {
        companion object {
            fun millis(millis: Long) = Duration(millis)
            fun seconds(seconds: Long) = Duration(seconds * 1000)
        }
    }

    /* 이를 사용하는 함수 */
    fun reserveAlarm(alarmMessage: String, duration: Duration) =
        println("$duration.millis millis 후에 [$alarmMessage] 알람이 울립니다.")
//    reserveAlarm("학교 가야지", Duration.seconds(2)) // 함수 호출 시

    /** 위 @JvmInline과 value class를 동시에 사용하는 경우 Kotlin -> Java 컴파일 시 */
    /*
    fun reserveAlarm-UiEZ_Y8(alarmMessage: String, duration: Long) =    // parameter type이 Duration class 에서 Long primitive type으로 자동 변환됨.
    println("$duration millis 후에 [$alarmMessage] 알람이 울립니다.")
     */
}