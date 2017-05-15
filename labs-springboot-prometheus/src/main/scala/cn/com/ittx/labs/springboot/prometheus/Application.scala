package cn.com.ittx.labs.springboot.prometheus

import io.prometheus.client.spring.boot.{EnablePrometheusEndpoint, EnableSpringBootMetricsCollector}
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

/**
  * @author 王成义
  * @version 5/15/17
  */
@SpringBootApplication
@EnablePrometheusEndpoint
@EnableSpringBootMetricsCollector
class Application {

}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
