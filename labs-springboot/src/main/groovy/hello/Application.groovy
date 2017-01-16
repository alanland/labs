package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration

/**
 * @author 王成义
 * @version 1/16/17
 */
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@EnableAutoConfiguration
class Application {

    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "Hello World!"
    }

    static void main(String[] args) throws Exception {
        SpringApplication.run(Application.class, args)
    }
}
