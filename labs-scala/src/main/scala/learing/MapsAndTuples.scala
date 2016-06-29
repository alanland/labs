package learing

as mutable

/**
  * @author 王成义
  * @version 6/28/16
  */
object MapsAndTuples extends App {
  // immutable Map
  val scores = Map("Alice" -> 10, "Bob" -> 3)

  //
  val scores =

  // 创建一个内容不可变的Map[String, Int]
  val scores = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  // 创建一个内容可变的Map[String, Int]
  val scores = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  // 创建一个空Map[String, Int]
  val scores = new scala.collection.mutable.Map[String, Int]
  // 替换掉->操作符
  val scores = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))

}
