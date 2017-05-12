package cn.com.ittx.license.register

import de.codecentric.boot.admin.config.EnableAdminServer
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration

/**
  * @author 王成义
  * @version 5/11/17
  */
@SpringBootApplication(exclude = Array(classOf[SessionAutoConfiguration]))
@EnableAdminServer
class Application {

}

object Application extends App {
  SpringApplication.run(classOf[Application])
}
