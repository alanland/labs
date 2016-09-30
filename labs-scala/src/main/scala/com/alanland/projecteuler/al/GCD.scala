package com.alanland.projecteuler.al

/**
  * @author 王成义
  * @version 9/30/16
  */
object GCD extends App {

  def gcd(a: Int, b: Int): Int = if (b == 0) a.abs else gcd(b, a % b)

  def lcm(a: Int, b: Int) = (a * b).abs / gcd(a, b)
}
