package com.alanland.lang

/**
  * @author 王成义
  * @version 4/14/17
  */
object ImplicitUsage extends App {
  implicit val n: Int = 5

  def add(x: Int)(implicit y: Int) = x + y

  add(5) // takes n from the current scope, res: Int =

  assert(add(5) == n + 5)


  import scala.math._

  def foo[T](t: T)(implicit integral: Integral[T]) = {
    println(integral)
    t
  }

  foo(0)

  foo(0L)
}
