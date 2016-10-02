package com.alanland.projecteuler

/**
  * Sum square difference
  *
  * The sum of the squares of the first ten natural numbers is,
  *
  * 1*1 + 2*2 + ... + 102 = 385 1
  * The square of the sum of the first ten natural numbers is,
  *
  * (1 + 2 + ... + 10)2 = 552 = 3025
  * Hence the difference between the sum of the squares of the first ten natural numbers and the square of the sum is 3025 − 385 = 2640.
  *
  * Find the difference between the sum of the squares of the first one hundred natural numbers and the square of the sum.
  *
  * @author 王成义
  * @version 10/2/16
  */
object P006_Sum_square_difference extends App {
  val s = (1 to 100).sum
  val res = s * s - (1 to 100).map(i => i * i).sum
  println(res)

}
