package hello

import org.apache.spark.{SparkConf, SparkContext}

/**
  * @author 王成义
  * @version 6/29/16
  */
trait SparkApp extends App {
  //  val conf = new SparkConf().setMaster("local[2]").setAppName("NetworkWordCount")
  //  val master = "spark://alan:7077"
  val master = "local[*]"
  val conf = new SparkConf()
    .setMaster(master)
    .setAppName("Spark Pie")
  val spark = new SparkContext(conf)
  val sc = spark
}
