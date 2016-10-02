package com.alanland.projecteuler.al

/**
  * @author 王成义
  * @version 10/2/16
  */
object PrimeUtil {

  val primes = 2 #:: Stream.from(3, 2).filter(isPrime)

  def isPrime(n: Int): Boolean =
    primes.takeWhile(p => p * p <= n).forall(n % _ != 0)
}
