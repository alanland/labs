package learing

/**
  * @author 王成义
  * @version 6/29/16
  */
object Hello {
  def main(args: Array[String]) {
    print(args)
  }
}


object HelloApp extends App {
  print(args)
}

object ColorEnum extends Enumeration {
  val Red, Blue = Value
  val Purple = Value(10, "紫色")
  // Value(11,"Green")
  val Yellow = Value(44)
  val Green = Value
  val Color3 = Value("color name")
}

object ColorEnumApp extends App {

  import ColorEnum._

  def doSome(color: Value) = {
    if (color == Red) "stop"
    else if (color == Yellow) "hurry up"
    else "go"
  }
}