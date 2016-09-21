package com.alanland.spark.rdd

import hello.SparkApp
import org.apache.spark.rdd.RDD

/**
  * @author 王成义
  * @version 6/23/16
  */
object HelloRDD2 extends SparkApp {
  val dir = "/home/alan/workspace/github/alanland/labs/labs-spark/src/main/resources/"
  val lines = sc.textFile(dir + "data.txt")
  val rdd = sc.parallelize(1 to 9, 3)

  //
  object MyFunctions {
    def func1(int: Int): Int = {
      int * 2
    }
  }

  rdd.map(MyFunctions.func1).foreach(println(_))

  //
  class MyClass extends java.io.Serializable {
    val field = "Hello"

    def doStuff(rdd: RDD[String]): RDD[String] = {
      rdd.map(x => field + x)
    }
  }

  new MyClass().doStuff(rdd.map(_.toString)).collect().foreach(println(_))

  //
  class MyClass2 extends java.io.Serializable {
    def func1(s: String) = {
      s + "_abc"
    }

    def doStuff(rdd: RDD[String]): RDD[String] = {
      rdd.map(func1(_))
    }
  }

  new MyClass2().doStuff(rdd.map(_.toString)).foreach(println(_))

  //
  val pairs = lines.map(s => (s, 1))
  val counts = pairs.reduceByKey((a, b) => a + b)
  counts.foreach(println)
  counts.sortByKey().foreach(println)

  //


  spark.stop()
}
