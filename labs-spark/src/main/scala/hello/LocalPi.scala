package hello

import scala.math.random

/**
  * @author 王成义
  * @version 6/23/16
  */
// scalastyle:off println
object LocalPi {
  def main(args: Array[String]) {
    var count = 0
    for (i <- 1 to 100000) {
      val x = random * 2 - 1
      val y = random * 2 - 1
      if (x * x + y * y < 1) count += 1
    }
    println("Pi is roughly " + 4 * count / 100000.0)
  }
}

// scalastyle:on println
