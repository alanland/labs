package toturials.tour

/**
  * @author 王成义
  * @version 6/25/16
  */
class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def move(dx: Int, dy: Int) {
    x = x + dx
    y = y + dy
  }

  def moveRight() {
    x += 1
  }

  override def toString: String = "(" + x + ", " + y + ")";
}

object Classes {
  def main(args: Array[String]) {
    val pt = new Point(1, 2)
    println(pt)
    pt.move(10, 10)
    println(pt)
    pt.moveRight
    println(pt)
  }
}