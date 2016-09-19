package learing

/**
  * @author 王成义
  * @version 6/28/16
  */
object MapsAndTuples extends App {
  // immutable Map
  val scores1 = Map("Alice" -> 10, "Bob" -> 3)

  //
  val scores2 = ""

  // 创建一个内容不可变的Map[String, Int]
  val scores3 = Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  // 创建一个内容可变的Map[String, Int]
  val scores4 = scala.collection.mutable.Map("Alice" -> 10, "Bob" -> 3, "Cindy" -> 8)
  // 创建一个空Map[String, Int]
  val scores5 = new scala.collection.mutable.HashMap[String, Int]
  // 替换掉->操作符
  val scores6 = Map(("Alice", 10), ("Bob", 3), ("Cindy", 8))

}
