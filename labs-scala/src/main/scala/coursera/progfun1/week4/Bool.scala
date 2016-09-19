package coursera.progfun1.week4

/**
  * @author 王成义
  * @version 8/1/16
  */
abstract class Bool {
  def ifThenElse[T](t: => T, e: => T): T

  def unary_! : Bool = ifThenElse(False, True)

  def ==(x: Bool): Bool = ifThenElse(x, x.unary_!)

  def !=(x: Bool): Bool = ifThenElse(x.unary_!, x)

  def &&(x: => Bool): Bool = ifThenElse(x, True)

  def ||(x: => Bool): Bool = ifThenElse(False, x)

  override def toString: String = super.toString
}

object True extends Bool {
  override def ifThenElse[T](t: => T, e: => T): T = t

  override def toString: String = "true"
}

object False extends Bool {
  override def ifThenElse[T](t: => T, e: => T): T = e

  override def toString: String = "false"
}

object Bool extends App {
  println(True)

}