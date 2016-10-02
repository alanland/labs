package com.alanland.projecteuler

import scala.collection.mutable.ListBuffer

/**
  * Largest palindrome product
  *
  * A palindromic number reads the same both ways. The largest palindrome made from the product of two 2-digit numbers is 9009 = 91 × 99.
  *
  * Find the largest palindrome made from the product of two 3-digit numbers.
  *
  * @author 王成义
  * @version 9/29/16
  */
object P004_Largest_palindrome_product extends App {

  def isPalindrome(n: Int): Boolean = {
    n.toString.length % 2 == 0 && n.toString.reverse.equals(n.toString)
  }

  val ps = ListBuffer[Int]()

  for (x <- 100 to 999) {
    for (y <- 100 to 999) {
      if (isPalindrome(x * y)) ps += x * y
    }
  }

  println(ps.max)

  class S2 {

    // solution 2
    def maxPalindromeProduct(numbers: List[Int]) = {
      val palindromes =
        for {
          a <- numbers
          b <- numbers
          value = a * b
          if (value.toString == value.toString.reverse)
        } yield value
      palindromes.max
    }
  }

  class S3 {
    /**
      * Version based on functional combinators. Flat map functional combinator will generate a collection with
      * all the possible combinations that will be filtered using isPalindrome function. View method will let the algorithm
      * evaluate each element only when be used.
      *
      * This implementation is faster because the 100-999 range is generated using a view and this evaluates and generate
      * the range at the same time.
      *
      * @return
      */
    def getLargestPalindromeProductFunctionalCombinators(): Int = {
      (100 to 999).view
        .flatMap(i => (i to 999).map(i *))
        .filter(n => isPalindrome(n))
        .max
    }


    def isPalindrome(number: Int): Boolean = {
      val original = number.toString
      original == number.toString.reverse
    }

  }

}
