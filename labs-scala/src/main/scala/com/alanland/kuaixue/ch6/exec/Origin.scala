package com.alanland.kuaixue.ch6.exec

import java.awt.Point

/**
  * @author 王成义
  * @version 9/26/16
  */
object Origin extends java.awt.Point {
  def apply(x: Int, y: Int): Point = new Point(x, y)
}
