package com.alanland.spark.rdd

import hello.SparkApp

/**
  * @author 王成义
  * @version 6/23/16
  */
object HelloRDD extends SparkApp {
  val dir = "/home/alan/workspace/github/alanland/labs/labs-spark/src/main/resources/"

  val a = sc.parallelize(1 to 9, 3)

  a.take(10).map(println)

  a.map(x => x * 2).collect().foreach(println(_))
  a.collect().foreach(println(_))

  a.map(x => (x, x)).foreach(println(_))
  a.map(x => (x, x)).mapValues("x" + _ + "x").foreach(println(_))
  //  a.map(x => (x, x)).mapPartitionsWithIndex


  val data = Array(1, 2, 3, 4, 5)
  val distData = sc.parallelize(data)
  println(distData.reduce((a, b) => a + b).toString)
  // 求和

  val distFile = sc.textFile(dir + "data.txt")
  val lineLengths = distFile.map(s => s.length).reduce((a, b) => a + b)
  println(lineLengths)


  spark.stop()
}
