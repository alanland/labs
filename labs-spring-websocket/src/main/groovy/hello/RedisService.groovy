package hello

import org.springframework.stereotype.Service
import redis.clients.jedis.Jedis
import redis.clients.jedis.JedisPool
import redis.clients.jedis.JedisPoolConfig
import redis.clients.jedis.exceptions.JedisConnectionException

import javax.annotation.PostConstruct

/**
 * ＠author 王成义
 * @since 1.0 , 2015-01-11.
 */
@Service
class RedisService {

    JedisPool pool

    @PostConstruct
    void initIt() {
        try {
            JedisPoolConfig poolConfig = new JedisPoolConfig();
            //控制一个pool可分配多少个jedis实例，通过pool.getResource()来获取；
            //  如果赋值为-1，则表示不限制；如果pool已经分配了maxActive个jedis实例，则此时pool的状态为exhausted(耗尽)。
            poolConfig.setMaxTotal(100)
            poolConfig.setMaxIdle(10)
            //表示当borrow(引入)一个jedis实例时，最大的等待时间，如果超过等待时间，则直接抛出JedisConnectionException；
            poolConfig.setMaxWaitMillis(1000);
            //在borrow一个jedis实例时，是否提前进行validate操作；如果为true，则得到的jedis实例均是可用的；
            poolConfig.setTestOnBorrow(true);
            poolConfig.setMinIdle(5)
            pool = new JedisPool(
                    poolConfig,
                    'localhost',
                    6379,
                    3000,
                    'ttx2011',
                    0
            );
        } catch (Exception e) {
            e.printStackTrace()
        }
    }

    String get(String key) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.get(key)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    Long getLong(String key) {
        Jedis jedis = pool.getResource()
        try {
            def v = jedis.get(key)
            return v ? new Long(v) : 0
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }
//        try (Jedis jedis = pool.getResource()) {
//            /// ... do stuff here ... for example
//            jedis.set("foo", "bar");
//            String foobar = jedis.get("foo");
//            jedis.zadd("sose", 0, "car"); jedis.zadd("sose", 0, "bike");
//            Set<String> sose = jedis.zrange("sose", 0, -1);
//        }

    void set(String key, String value) {
        Jedis jedis = pool.getResource()
        try {
            jedis.set(key.trim(), value)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    void del(String... keys) {
        Jedis jedis = pool.getResource()
        try {
            keys.each {
                jedis.del(it)
            }
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    void setObject(String key, Object o) {
        Jedis jedis = pool.getResource()
        try {
            if (key != null && o != null) {
                try {
                    jedis.set(key.trim().bytes, SerializeUtil.serialize(o))
                } catch (Throwable e) {
                    logger.error(StackTraceHelper.getStackTrace(e))
                }
            }
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    Object getObject(String key) {
        Jedis jedis = pool.getResource()
        try {
            if (key == null)
                return null
            try {
                return SerializeUtil.deserialize(jedis.get(key.trim().bytes))
            } catch (Throwable e) {
                logger.error(StackTraceHelper.getStackTrace(e))
                return null
            }
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    Long incr(String key) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.incr(key)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    Long incrBy(String key, long integer) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.incrBy(key, integer)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    String hget(String key, String fieldKey) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.hget(key, fieldKey)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    Map hgetAll(String key) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.hgetAll(key)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    void hset(String key, String fieldKey, String value) {
        Jedis jedis = pool.getResource()
        try {
            jedis.hset(key, fieldKey, value)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    void lpush(String key, String value) {
        Jedis jedis = pool.getResource()
        try {
            jedis.lpush(key, value)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    void lpushAll(String key, String... values) {
        Jedis jedis = pool.getResource()
        try {
            jedis.lpush(key, values)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            pool.returnResource(jedis);
        }
    }

    List lgetAll(String key) {
        Jedis jedis = pool.getResource()
        try {
            return jedis.lrange(key, 0, -1)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    def withJedis(Closure closure) {
        pool.getResource().withCloseable closure
        Jedis jedis = pool.getResource()
        try {
            return closure.call(jedis)
        } catch (JedisConnectionException e) {
            e.printStackTrace();
            pool.returnBrokenResource(jedis);
            return null
        } catch (Exception e) {
            e.printStackTrace();
            return null
        } finally {
            pool.returnResource(jedis);
        }
    }

    @Deprecated
    Jedis jedis() {
        pool.getResource()
    }

}
