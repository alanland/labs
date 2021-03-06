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

  val path = "labs-spark/src/main/resources/people.json"

  // For implicit conversions like converting RDDs to DataFrames
  import spark.implicits._

  // creating data frames
  val df = spark.read.json(path)
  df.show()

  df.printSchema()
  df.select("name").show()

  // Select everybody, but increment the age by 1
  df.select($"name", $"age" + 1).show()

  // Select people older than 21
  df.filter($"age" > 21).show()

  // Count people by age
  df.groupBy("age").count().show()

  /**
    * Running SQL Queries Programmatically
    */
  // Register the DataFrame as a SQL temporary view
  df.createOrReplaceTempView("people")
  val sqlDf = spark.sql("select * from people")
  sqlDf.show()

  /**
    * Global Temporary View
    */
  // Register the DataFrame as a global temporary view
  df.createGlobalTempView("people")

  // Global temporary view is tied to a system preserved database `global_temp`
  spark.sql("select * from global_temp.people").show()

  // Global temporary view is cross-session
  spark.newSession().sql("select * from global_temp.people").show()

}
