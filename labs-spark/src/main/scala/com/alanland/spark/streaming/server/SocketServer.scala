package com.alanland.spark.streaming.server

import java.io.{BufferedReader, InputStreamReader, PrintStream}
import java.net.ServerSocket

/**
  * @author 王成义
  * @version 9/19/16
  */
object SocketServer {

  def main(args: Array[String]): Unit = {
    val ss = new ServerSocket(9999)
    while (true) {
      val sock = ss.accept()
      val is = new BufferedReader(new InputStreamReader(sock.getInputStream()))
      val os = new PrintStream(sock.getOutputStream())
      os.println("what's your name?")
    }
  }
}
