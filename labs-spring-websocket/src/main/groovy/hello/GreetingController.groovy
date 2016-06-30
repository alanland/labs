package hello

import org.springframework.messaging.handler.annotation.DestinationVariable
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
    public Greeting greeting2(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Hello2, " + message.getName() + "!");
    }

    @MessageMapping("/hello3")
    @SendTo("/topic/greetings")
    public Greeting greeting3(HelloMessage message) throws Exception {
        Thread.sleep(500); // simulated delay
        return new Greeting("Hello3, " + message.getName() + "!");
    }

    @MessageMapping("/fleet/{fleetId}/driver/{driverId}")
    @SendTo("/topic/fleet/{fleetId}")
    public Greeting simple(@DestinationVariable String fleetId, @DestinationVariable String driverId) {
        return new Greeting(fleetId + driverId);
    }

}
