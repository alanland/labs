package hello

import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

/**
 * @author 王成义
 * @version 6/30/16
 */

@Controller
public class GreetingController {

    @MessageMapping("/hello2")
    @SendTo("/topic/greetings")
    public Greeting greeting(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Hello, " + message.getName() + "!");
    }

}
