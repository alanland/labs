package cn.com.ittx.labs.springboot.cors

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.annotation.{Bean, Configuration}
import org.springframework.web.servlet.config.annotation.{CorsRegistry, WebMvcConfigurer, WebMvcConfigurerAdapter}

/**
  * @author 王成义
  * @version 5/16/17
  */
@SpringBootApplication
@Configuration
class Application {

  @Bean
  def corsConfigurer(): WebMvcConfigurer = {
    new WebMvcConfigurerAdapter() {
      override def addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**")
          .allowedOrigins("*")
          .allowedMethods("*")
          .allowCredentials(true)
          .exposedHeaders(
            "Access-Control-Allow-Origin",
            "Access-Control-Allow-Credentials",
            "Cache-Control",
            "Content-Language",
            "Content-Type",
            "Expires",
            "Last-Modified",
            "Pragma",
            "Authorization"
          )
      }
    }
  }

}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
