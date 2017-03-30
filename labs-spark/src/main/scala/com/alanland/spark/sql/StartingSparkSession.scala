package com.alanland.spark.sql

/**
  * @author 王成义
  * @version 3/30/17
  */

import org.apache.spark.sql.SparkSession

object StartingSparkSession extends App {
  // spark session
  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Spark Sql basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  // creating data frames
  val df = spark.read.json("labs/spark/src/main/resources/people.json")
  df.show()

}
