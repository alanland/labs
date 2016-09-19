package hello.guide

import hello.SparkApp

/**
  * @author 王成义
  * @version 6/29/16
  */
object rdds extends SparkApp {

  // Parallelized Collections
  val data = Array(1, 2, 3, 4, 5)
  val distData = sc.parallelize(data)

  // 外部文件
  val distFile = sc.textFile("gradle.properties")
  var length = distFile.map(s => s.length).reduce((a, b) => a + b)
  println(length)

  sc.stop()
}
