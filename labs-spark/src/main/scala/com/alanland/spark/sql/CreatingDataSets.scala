package com.alanland.spark.sql

import org.apache.spark.sql.SparkSession

/**
  * @author 王成义
  * @version 3/30/17
  */
object CreatingDataSets extends App {

  val spark = SparkSession
    .builder()
    .master("local[*]")
    .appName("Spark Sql basic example")
    .config("spark.some.config.option", "some-value")
    .getOrCreate()

  import spark.implicits._

  case class Person(name: String, age: Long)

  val path = "labs-spark/src/main/resources/people.json"

  // Encoders are created for case classes
  val caseClassDS = Seq(Person("Andy", 32)).toDS()
  caseClassDS.show()

  // Encoders for most common types are automatically provided by importing spark.implicits._
  val primitiveDS = Seq(1, 2, 3).toDS()
  println(primitiveDS.map(_ + 1).collect())

  // DataFrames can be converted to a Dataset by providing a class. Mapping will be done by name
  val df = spark.read.json(path)
  df.printSchema()

  val peopleDS = df.as[Person]
  peopleDS.show()


}
