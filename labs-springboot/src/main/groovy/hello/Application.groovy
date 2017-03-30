package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.context.event.ApplicationEventMulticaster
import org.springframework.context.event.SimpleApplicationEventMulticaster
import org.springframework.core.task.SimpleAsyncTaskExecutor
import org.springframework.scheduling.support.TaskUtils

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


    // tell Spring to handle events asynchronously (not in the caller's thread) by redefining the
    // ApplicationEventMulticaster bean with id applicationEventMulticaster. With java config the method name can specify the id.
    @Bean
    ApplicationEventMulticaster applicationEventMulticaster() {
        SimpleApplicationEventMulticaster eventMulticaster = new SimpleApplicationEventMulticaster();
        eventMulticaster.setTaskExecutor(new SimpleAsyncTaskExecutor());
        eventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
        return eventMulticaster;
    }
}
