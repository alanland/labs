package com.alanland.projecteuler

/**
  * Multiples of 3 and 5
  *
  * If we list all the natural numbers below 10 that are multiples of 3 or 5, we get 3, 5, 6 and 9. The sum of these multiples is 23.
  *
  * Find the sum of all the multiples of 3 or 5 below 1000.
  *
  * @author 王成义
  * @version 9/29/16
  */
object P001Multiples_of_3_and_5 extends App {

  var sum = 0
  val list = (0 until 1000).toList

  def f = (x: Int) => x % 3 == 0 || x % 5 == 0

  println(list.filter(f).sum)

}
