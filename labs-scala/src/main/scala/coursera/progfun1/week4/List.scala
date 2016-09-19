package coursera.progfun1.week4

/**
  * @author 王成义
  * @version 8/2/16
  */

trait List[+T] {
  def isEmpty: Boolean

  def head: T

  def tail: List[T]
  def prepend[U >: T](elem: U): List[U] = new Cons(elem, this)
}

class Cons[T](val head: T, val tail: List[T]) extends List[T] {
  override def isEmpty = false
}


class Nil[T] extends List[T] {
  override def isEmpty = true

  override def head: Nothing = throw new NoSuchElementException("Nil.head")

  override def tail: Nothing = throw new NoSuchElementException("Nil.tail")
}

object List {
  def apply[T](x1: T, x2: T): List[T] = new Cons(x1, new Cons(x2, new Nil))

  def apply[T](x1: T): List[T] = new Cons(x1, new Nil)

  def apply[T]: List[T] = new Nil
}

object test {
}