package learing

import scala.beans.BeanProperty

/**
  * @author 王成义
  * @version 6/28/16
  */
// 简单类和无参方法
class Counter {
  // // 必须初始化字段
  private var value = 0

  def increment() = value += 1

  def current = value
}

object Counter {
  def main(args: Array[String]) {
    val counter = new Counter()
    counter.increment()
    println(counter.current)
  }
}


class Person {
  var age = 0
}

object Person {
  def main(args: Array[String]) {
    val person = new Person()
    println(person.age)
    person.age_=(3)
    person.age = 4 // same as call `age_=` method
  }
}

class Person2 {
  var privateAge = 0

  def age = privateAge

  def age_=(v: Int) = {
    if (v > privateAge) privateAge = v
  }
}

class Person3 {
  @BeanProperty var name: String = _
}


class PersonWithConstructor {
  private var name = ""
  private var age = 0

  def this(name: String) {
    this()
    this.name = name
  }

  def this(name: String, age: Int) {
    this(name)
    this.age = age
  }
}

object PersonWithConstructor {
  def main(args: Array[String]) {
    val p1 = new PersonWithConstructor()
    val p2 = new PersonWithConstructor("alan")
    val p3 = new PersonWithConstructor("alan", 3)
  }
}


class Person4(val name: String, val age: Int) {
  println("xxx")

  def m() {}
}

class Person5(name: String, age: Int) {}

class Person6 private(age: Int) {}

class OuterPerson {

  class InnerPerson {}

  def test(inner: InnerPerson) {}

  // 类型投影
  def test2(inner: OuterPerson#InnerPerson) {}

}

object OuterPerson {
  def main(args: Array[String]) {
    val outer1 = new OuterPerson
    val outer2 = new OuterPerson
    val inner1 = new outer1.InnerPerson
    val inner2 = new outer2.InnerPerson

    // outer2.test(inner1) # 报错,不是同一个类型
    outer2.test(inner2)
    outer2.test2(inner1)
    outer2.test2(inner2)

    println(inner1.asInstanceOf[outer1.InnerPerson])
    println(inner1.asInstanceOf[outer2.InnerPerson])
    println(inner1.asInstanceOf[OuterPerson#InnerPerson])

    println(inner1.isInstanceOf[outer1.InnerPerson])
    println(inner1.isInstanceOf[outer2.InnerPerson])
    println(inner1.isInstanceOf[OuterPerson#InnerPerson])

  }
}