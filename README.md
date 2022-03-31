# Kotlin_logic_tutorial

## 코틀린의 기본기를 다지는 가이드 및 튜토리얼

    [!] 코틀린의 기초 문법과 지식이 부족하다면?

    [!] 코틀린 코딩 중 헷갈리는 부분이 있다면?

    [!] 코틀린을 좀 더 DEEP하게 파고싶다면?

    [!] 코틀린의 NEW TECH LOGIC을 공부하고싶다면?

## 1. Tutorial Init

-   ### IDE
    -   Intellij ultimate
    -   VS Code
-   ### Language
    -   Kotlin

## 2. Tutorial List

### ** base(기본 문법) **

<br>
<details>
  <summary> 기초 문법 모음 </summary>
  <br>
  <p>1. var/val</p>
  <p>2. nullable(?와 !!)</p>
  <p>3. 조건식</p>
  <p>4. Any와 instanceof</p>
  <p>5. When</p>
  <p>6. loop(for, while)</p>
  <p>7. null과 not-null(안전 호출, 엘비스 연산자)</p>
  <p>8. object</p>
  <p>base/BaseGrammer.kt</p>
</details>
<details>
  <summary> Companion Object </summary>
  <br>
  <p>companion object는 간단하게, Java에서의 static한 느낌의 singleton object 라고 이해하면 된다.</p>
  <p>base/CompanionObject.kt</p>
</details>
<details>
  <summary> by lazy와 lateinit </summary>
  <br>
  <p>lateinit은 처음 값의 Type만 지정해주고, 실제 값까지 함께 선언하지 않는다. 이후에 값을 지정해줘야 함</p>
  <p>by lazy는 read-only 느낌으로, 변할 수 있는 값을 활용해 고정 값을 대입할 때 주로 사용된다. val만 선언 가능</p>
  <p>base/Lazy.kt</p>
</details>
<details>
  <summary> 기초적인 Class 선언 </summary>
  <br>
  <p>case 1. default 선언</p>
  <p>case 2. 생성자의 parameter가 없을 경우 () 생략 가능</p>
  <p>case 3. class parameter에 var/val 변수를 선언하면, 클래스 내부 멤버변수 및 생성자 선언을 동시에 할 수 있다.</p>
  <p>case 4. class parameter에 일반 변수를 받게 되면, 클래스 내부 멤버변수로 선언되지 않는다.</p>
  <p>case 5. class parameter로 받은 일반 변수 값을 클래스 내부 멤버변수에 초기화한다.</p>
  <p>case 6. init(기본 생성자가 호출된 직후 바로 실행되는 코드 블럭</p>
  <p>case 7. constructor(보조 생성자. 추가적인 생성자 함수를 작성하고 싶을 때 활용)</p>
  <p>case 8. class parameter에 받은 일반 변수의 Default 값 설정 가능.</p>
  <p>base/BaseClass.kt</p>
</details>
<details>
  <summary> Scope function </summary>
  <br>
  <p>Scope function은 상위 Context Object를 코드 블럭 내에서 활용해 만들고, 이를 실행할 수 있게 하는 함수. (자유롭게 연계 가능)</p>
  <p>let</p>
  <p>run</p>
  <p>apply</p>
  <p>also</p>
  <p>with</p>
  <p>takeIf / takeUnless</p>
  <p>base/Scope.kt</p>
</details>
<details>
  <summary> Reified / inline </summary>
  <br>
  <p>Reified는 Kotlin -> Java의 Runtime 시점에 Generic Type을 알고 싶을 때 사용. Reified를 사용하게 되면 Generic Class Type 정보까지 함께 전달 가능</p>
  <p>(참고) 무조건 inline 과 함께 사용해야 한다.</p>
  <p>inline function는 inline function은 kotlin -> java의 컴파일 단계에서 "함수 호출 = invoke" 방식이 아닌 "코드 복사" 방식으로 코드 변환해줌.</p>
  <p>base/Reified.kt</p>
</details>
<details>
  <summary> sealed class </summary>
  <br>
  <p>sealed class를 super class로 하는 하위 클래스(child class)의 종류를 제한하고, 이를 컴파일러에게 알려주기 위해 사용.</p>
  <p>-> Enum과의 가장 큰 차이점은, Enum은 single instance 이지만, sealed class는 여러 개의 하위 클래스 객체를 생성할 수 있다.</p>
  <p>base/Sealed.kt</p>
</details>
<details>
  <summary> vararg </summary>
  <br>
  <p>가변인자. 즉, 매개변수의 개수를 동적으로 지정해 줄 수 있게 하는 변수.</p>
  <p>base/Vararg.kt</p>
</details>
<details>
  <summary> enum </summary>
  <br>
  <p>Enum은 Kotlin에선, Comparable Interface를 구현하는 Abstarct class.</p>
  <p>base/Enum.kt</p>
</details>
<details>
  <summary> contract / callsInPlace </summary>
  <br>
  <p>contract는 함수가 컴파일러가 이해하는 방식으로 동작을 명시적으로 설명할 수 있도록 한다.</p>
  <p>callsInPlace는  람다 함수를 사용할 때, 그 함수의 호출 횟수를 명시적으로 컴파일러에게 이해시켜주기 위해 사용</p>
  <p>contract/Contract.kt</p>
</details>
<details>
  <summary> Coroutine </summary>
  <br>
  <p>suspend function</p>
  <p>비동기 실행중인 coroutine 함수를 잠시 중지시킬 수 있는 함수를 설정(표시)한 것.</p>
  <p>coroutine/SuspendFunc.kt</p>
</details>
<details>
  <summary> Annotations </summary>
  <br>
  <p>Kotlin -> Java로 컴파일되는 과정에서 활용되는 Annotations.</p>
  <p>@JvmField</p>
  <p>@JvmStatic</p>
  <p>@JvmName</p>
  <p>@Throws</p>
  <p>@JvmOverloads</p>
  <p>@JvmInline</p>
  <p>(특별추가) value class</p>
  <p>jvm/Annotations.kt</p>
</details>

## 3. Tutorial Description

<br/>
<br/>

## 4. Maker : Leehaewon

<br/>
<br/>

## etc.

-   mail : banner4@naver.com
-   start : 2022.03.18
