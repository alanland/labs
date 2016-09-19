package coursera.progfun1.week4

/**
  * @author 王成义
  * @version 8/2/16
  */
trait Expr {
  def eval(e: Expr): Int = e match {
    case Number(n) => n
    case Sum(e1, e2) => eval(e1) + eval(e2)
  }
}

case class Number(n: Int) extends Expr

case class Sum(e1: Expr, e2: Expr) extends Expr

object Number {
//  def apply(n: Int) = new Number(n)
}

object Sum {
//  def apply(e1: Expr, e2: Expr) = new Sum(e1, e2)
}

object exprs {
  def show(e: Expr): String = e match {
    case Number(x) => x.toString
    case Sum(x, y) => show(x) + " + " + show(y)
  }
}

object tests extends App{
  println(exprs.show(Sum(Number(1), Number(2))))
}