package collections

/** Sequences
 * kotlin에서 Collections와 마찬가지로 Iterable한 자료구조(Iterator, Collection, List, Map, Set ..)
 * 하지만, Collections : Eager evaluation / Sequences : Lazy evaluation (Java의 Stream : Lazy evaluation)
 * 의 차이가 있다.
 * Eager evaluation : 빠른 실행. 필요에 관계없이 지금 연산중인 부분은 전부 연산한다.
 * Lazy evaluation : 늦은 실행. 지금 하지 않아도 되는 연산은 최대한 뒤로 미루고, 어쩔 수 없이 연산이 필요할 때 연산하는 방식.
 */
class Sequences {
    companion object {
        /**
         * Collections과 Sequences 동작 비교
         */
        /** Collections 활용 */
        fun collectionsSample() {
            val greeting = "안녕 나의 이름은 이해원이야 만나서 반가워 잘 지내보자".split(" ")
            val lengthsList = greeting.filter {
                println("단어 : $it")
                it.length >= 3
            }.map {
                println("길이 : ${it.length}")
                it   // return value
            }.take(3)   // 선착순 n개까지만 가져옴.

            println("길이가 3 이상인 단어들의 목록: $lengthsList")
        }

        /** Sequences 활용 */
        fun sequencesSample() {
            val greeting = "안녕 나의 이름은 이해원이야 만나서 반가워 잘 지내보자".split(" ")
            val greetingSequence = greeting.asSequence()    // asSequence()로 collections -> sequences로 변환
            
            val lengthsSequence = greetingSequence.filter {
                println("단어 : $it")
                it.length >= 3
            }.map {
                println("길이 : ${it.length}")
                it   // return value
            }.take(3)   // 선착순 n개까지만 가져옴.

            println("길이가 3 이상인 단어들의 목록: ${lengthsSequence.toList()}")
        }
    }
}

fun main() {
    /**
     * collections 는 모든 원소를 filter 수행한 후, 순차적으로 map, take를 수행한다.
     * 즉, take로 3개까지만 찾아내면 되는데 모든 원소에 대해 filter를 수행한다.
     * 거의 너비우선탐색
     */
    Sequences.collectionsSample()   // collections
    println()

    /**
     * sequences 는 모든 원소를 바로 filter하지 않고, 순차적으로 하나의 원소마다 filter -> map -> take를 수행한다.
     * 결과적으로, 3개까지만 찾아내면 되기 때문에 3개의 조건을 달성하면 다른 함수를 호출하지 않아도 된다.
     * 거의 깊이우선탐색
     */
    Sequences.sequencesSample()     // sequences
}