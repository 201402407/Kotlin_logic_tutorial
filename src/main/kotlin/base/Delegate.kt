package base

/** by로 Delegate Pattern을 쉽게 구현
 * Delegate Pattern은 디자인 패턴 중 하나로, 어떤 기능을 자신이 처리하지 않고 다른 객체(대리인)에 위임시켜서 그 객체가 일을 처리하도록 하는 것
 * 다른 객체(대리인)가 만들어놓은 방식(코드) 그대로 가져와서 사용해야하는데, 인터페이스다보니 사용할 때마다 그대로 코드를 가져오는 것은 낭비.
 * 이는, 보일러플레이트 코드가 많이 생겨나고, 유지비용 측면에서 큰 손해다.
 *
 * -> 보통 상속대신 구현으로 많이 얘기하는데, 상속은 객체의 유연성이 떨어지기 때문에 보통 인터페이스를 정의하고 이를 구현하는 방식으로 개발.
 * -> 상속은 is-a 관계로, 부모 클래스의 모든 기능을 가져오고, 구현은 has-a 관계로, 입맛에 맞게 각자가 용도에 맞게 기능구현한다.
 */
class Delegate(mSquare: Square): Square {
    val square: Square = mSquare
    /**
     * 아래와 같이 인터페이스를 구현해야하는 boilerplate(보일러플레이트) 코드 발생. 
     * -> param으로 받은 mSquare 객체 또한 인터페이스를 구현해뒀을텐데.. 비슷한 코드 재생성하게 되서 보일러플레이트 코드 발생
     *
     * 인터페이스를 구현하지 않아도 되지만, 이를 실제로 활용하게 되면 내부변수에 직접 접근해야한다. (캡슐화 + 불필요한 코드 사용)
     * */
    override fun getWidth(): Int {
        return square.getWidth()
    }

    override fun getHeight(): Int {
        return square.getHeight()
    }
}

/**
 * by 키워드를 활용하여 Delegate Pattern 구현.
 * 이를 통해, Square 인터페이스를 구현한 parameter 객체의 코드를 그대로 가져올 필요가 없고, overriding하지 않아도 된다.
 * -> 보일러플레이트 코드 감소
 */
class BestDelegate(mSquare: Square): Square by mSquare {
    val square: Square = mSquare
}

class NewSquare(): Square {
    override fun getWidth(): Int {
        return 111
    }

    override fun getHeight(): Int {
        return 222
    }
}

fun main() {
    val square = NewSquare()
    val delegateSquare = Delegate(square)
    println("Width : ${delegateSquare.getWidth()}, height: ${delegateSquare.getHeight()}")
    println()

    val bestDelegateSquare = BestDelegate(square)
    println("Width : ${bestDelegateSquare.getWidth()}, height: ${bestDelegateSquare.getHeight()}")
}

interface Square {
    fun getWidth() : Int
    fun getHeight() : Int
}