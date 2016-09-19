package com.alanland.redis.service

import javax.annotation.PostConstruct

import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import redis.clients.jedis.exceptions.JedisConnectionException
import redis.clients.jedis.{JedisPool, JedisPoolConfig}

/**
  * @author 王成义
  * @version 9/19/16
  */
@Service
class RedisService {

  @PostConstruct
  def initIt(): Unit = {
  }

}

object RedisService {
  val logger = LoggerFactory.getLogger(getClass)

  val config = new JedisPoolConfig
  config.setMaxTotal(100)
  config.setMaxIdle(10)
  config.setMaxWaitMillis(1000)
  config.setTestOnBorrow(true)
  config.setMinIdle(5)
  val pool: JedisPool = new JedisPool(
    config, "localhost", 6379, 3000,
    "ttx2011",
    1
  )

  def getString(key: String): String = {
    val jedis = pool.getResource
    try {
      return jedis.get(key)
    } catch {
      case e: JedisConnectionException =>
        e.printStackTrace()
        pool.returnBrokenResource(jedis)
      case e: Exception =>
        e.printStackTrace()
    } finally {
      pool.returnResource(jedis)
    }
    null
  }

  def setString(key: String, value: String): Unit = {
    val jedis = pool.getResource
    try {
      jedis.set(key.trim, value)
    } catch {
      case e: JedisConnectionException =>
        pool.returnBrokenResource(jedis)
        e.printStackTrace()
      case e: Exception =>
        e.printStackTrace()
    } finally {
      pool.returnResource(jedis)
    }
  }

  def main(args: Array[String]) = {
    for (i <- 0 to 100) {
      setString(i.toString, i.toString)
    }
  }
}