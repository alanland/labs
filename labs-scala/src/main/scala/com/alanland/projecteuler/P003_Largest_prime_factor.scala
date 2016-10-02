package com.alanland.projecteuler

/**
  * Largest prime factor
  *
  * The prime factors of 13195 are 5, 7, 13 and 29.
  *
  * What is the largest prime factor of the number 600851475143 ?
  *
  * @author 王成义
  * @version 9/29/16
  */
object P003_Largest_prime_factor extends App {
  val number = 600851475143l

  def findPrime(n: Long, list: List[Long] = List()): List[Long] = {
    if (n == 1L) return list
    val factor = Stream.from(2).map(_.toLong).toIterator.find(n % _ == 0).get
    findPrime(n / factor, list :+ factor)
  }

  val f = (n: Long, list: List[Long]) => {
    if (n == 1L) list
    else {
      val factor = Stream.from(2).map(_.toLong).toIterator.find(n % _ == 0).get
      findPrime(n / factor, list :+ factor)
    }
  }

  println(findPrime(number))
  println(f(number, List()))
}
