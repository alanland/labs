package coursera.progfun1.week4

/**
  * @author 王成义
  * @version 8/2/16
  */
class AnonFun extends Function1[Int, Int] {
  override def apply(x: Int): Int = x * x
}

object AnonFun extends App {
  def fun = new AnonFun

  println(fun(19))
}

