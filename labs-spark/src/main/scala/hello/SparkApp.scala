package hello

import org.apache.spark._

/**
  * @author 王成义
  * @version 6/29/16
  */
trait SparkApp extends App {
  //  val master = "spark://alan:7077"
  val master = "local[*]"
  val conf = new SparkConf()
    .setMaster(master)
    .setAppName("Spark Pie")
  val spark = new SparkContext(conf)
}
