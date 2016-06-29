package hello

import org.apache.spark._

import scala.math.random

/**
  * @author 王成义
  * @version 6/23/16
  */
object SparkPi {
  //  val master = "spark://alan:7077"
  val master = "local[*]"

  def main(args: Array[String]) {
    val conf = new SparkConf()
      .setMaster(master)
      .setAppName("Spark Pie")
    val spark = new SparkContext(conf)
    val slices = if (args.length > 0) args(0).toInt else 2
    val n = math.min(10000L * slices, Int.MaxValue).toInt // avoid overflow
    val count = spark.parallelize(1 until n, slices).map { i =>
        val x = random * 2 - 1
        val y = random * 2 - 1
        if (x * x + y * y < 1) 1 else 0
      }.reduce(_ + _)
    println("Pi is roughly " + 4.0 * count / n)
    spark.stop()
  }
}


