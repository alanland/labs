package com.alanland.projecteuler

/**
  * 10001st prime
  *
  * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
  *
  * What is the 10 001st prime number?
  *
  * @author 王成义
  * @version 10/2/16
  */
object P007_10001st_prime extends App {

  val primes = 2 #:: Stream.from(3, 2).filter(isPrime)

  def isPrime(n: Int): Boolean =
    primes.takeWhile(p => p * p <= n).forall(n % _ != 0)

  println(primes.take(10001).toList.last)

}
