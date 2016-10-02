package com.alanland.projecteuler

/**
  * Special Pythagorean triplet
  * *
  * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
  * *
  * a2 + b2 = c2
  * For example, 3*3 + 4*4 = 9 + 16 = 25 = 52.
  * *
  * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
  * Find the product abc.
  *
  * @author 王成义
  * @version 10/2/16
  */
object P009_Special_Pythagorean_triplet extends App {
  val isRes = (a: Int, b: Int) => a * a + b * b == (1000 - a - b) * (1000 - a - b)
  for (a <- 1 until 1000) {
    for (b <- 1 until 1000) {
      if (isRes(a, b)) {
        println(a * b * (1000 - a - b))
      }
    }
  }
}
