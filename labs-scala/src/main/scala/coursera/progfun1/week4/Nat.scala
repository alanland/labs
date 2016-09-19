package coursera.progfun1.week4

/**
  * @author 王成义
  * @version 8/1/16
  */
abstract class Nat {
  def isZero: Boolean

  def predecessor: Nat

  def successor: Nat

  def +(that: Nat): Nat

  def -(that: Nat): Nat
}

object Zero extends Nat {
  override def isZero: Boolean = true

  override def predecessor: Nat = throw new Error("0.predecessor")

  override def successor: Nat = ???

  override def +(that: Nat): Nat = ???

  override def -(that: Nat): Nat = ???
}

abstract class Succ(n: Nat) extends Nat {

}
