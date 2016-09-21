package com.alanland.spark.streaming

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming._

/**
  * @author 王成义
  * @version 9/19/16
  */
object HelloStreaming extends App {
  val conf = new SparkConf().setMaster("local[*]").setAppName("Simple Application")
  val ssc = new StreamingContext(conf, Seconds(1))

  val lines = ssc.socketTextStream("localhost", 9999, StorageLevel.MEMORY_AND_DISK_SER)
  val words = lines.flatMap(_.split(" "))
  val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
  wordCounts.print
  ssc.start()
  ssc.awaitTermination()
}
