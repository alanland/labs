package com.alanland.projecteuler

/**
  * Smallest multiple
  *
  * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
  *
  * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
  *
  * @author 王成义
  * @version 9/30/16
  */
object P005Smallest_multiple extends App {
  val numbers = (1l to 20).toList.reverse

  def gcd(a: Long, b: Long): Long = if (b == 0) a.abs else gcd(b, a % b)

  def lcm(a: Long, b: Long) = (a * b).abs / gcd(a, b)

  def lcms(a: Long, list: List[Long]): Long = if (list.isEmpty) a else lcms(lcm(a, list.head), list.tail)

  println(lcms(1, numbers))

}
