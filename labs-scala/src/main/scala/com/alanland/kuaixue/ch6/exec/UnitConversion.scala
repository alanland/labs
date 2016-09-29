package com.alanland.kuaixue.ch6.exec

/**
  * @author 王成义
  * @version 9/26/16
  */
abstract class UnitConversion {
  def convert(): Unit
}

object InchesToCentimeters extends UnitConversion {
  override def convert(): Unit = {

  }
}

object GallonsToLiters extends UnitConversion {
  override def convert(): Unit = ???
}

object MilesToKiloMeters extends UnitConversion {
  override def convert(): Unit = ???
}

