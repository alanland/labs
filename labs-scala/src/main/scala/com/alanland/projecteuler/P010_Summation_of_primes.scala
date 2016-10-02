package com.alanland.projecteuler

import com.alanland.projecteuler.al.PrimeUtil

/**
  * Summation of primes
  *
  * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
  * *
  * Find the sum of all the primes below two million.
  *
  * @author 王成义
  * @version 10/2/16
  */
object P010_Summation_of_primes extends App {
  val max = 2000000l
  val sum = PrimeUtil.primes.takeWhile(_ < max).toList.sum
  println(sum)
}
