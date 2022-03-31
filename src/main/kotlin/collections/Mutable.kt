package collections

/**
 * Mutable과 Immutable의 차이는 Collection Data의 불변성/가변성 차이다.
 * Mutable : 변할 수 있는. Collection 함수 사용 가능(add, remove 등)
 * Immutable : 변할 수 없는(default). 그렇기 때문에 Collection 함수 사용 불가(add, remove 등)
 * List, Set, Map 전부 Mutable/Immutable 가능
 */
class Mutable {
    fun immutable() {
        val tempList = listOf<Int>(50, 60, 70)
        // 아래처럼 add, remove 등 함수 사용 불가능. 내부 데이터 값 변경은 못함
    }
    
    fun mutable() {
        val tempList = mutableListOf<Int>(50, 60, 70)
        tempList.add(80)
        tempList.remove(50)
        tempList.removeAt(0)

        /** arrayListOf는 Mutable */
        val tempList2 = arrayListOf<Int>(50, 60, 10)  
        tempList2.add(20)
    }
}