package com.alanland.spark.streaming

import java.io.PrintWriter

import org.apache.spark.SparkConf
import org.apache.spark.storage.StorageLevel
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
  * @author 王成义
  * @version 9/21/16
  */
object NetworkWordCount {
  def main(args: Array[String]): Unit = {
    var host = "localhost"
    var port = 9999
    if (args.length >= 2) {
      host = args(0)
      port = args(1).toInt
    }

    val conf = new SparkConf().
      setMaster("local[*]").
      setAppName("network word count")
    val ssc = new StreamingContext(conf, Seconds(2))

    val lines = ssc.socketTextStream(host, port, StorageLevel.MEMORY_AND_DISK)
    val words = lines.flatMap(_.split(" "))
    val wordCounts = words.map(x => (x, 1)).reduceByKey(_ + _)
    wordCounts.print()
    writeResult(wordCounts.count().toString)
    ssc.start()
    ssc.awaitTermination()
  }

  def writeResult(s: String): Unit = {
    def file = "/home/alan/workspace/github/alanland/labs/labs-spark/src/main/scala/com/alanland/spark/streaming/a.txt"
    val out = new PrintWriter(file, "UTF-8")
    try {
      out.print(s)
    } finally {
      out.close()
    }
  }
}
