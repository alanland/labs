package hello.web

import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer
import org.springframework.web.socket.config.annotation.StompEndpointRegistry

/**
 * @author 王成义
 * @version 6/30/16
 */
//@Configuration
//@EnableWebSocketMessageBroker
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic");
        // js: stompClient.send('/app/${controller message mapping}')
        config.setApplicationDestinationPrefixes("/app");

//        config.enableStompBrokerRelay("/topic", "/queue")
//                .setRelayHost("localhost") // broker host
//                .setRelayPort(61613) // broker port
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // js: new SockJs('/endpoint')
        registry.addEndpoint("/endpoint").withSockJS();
    }

}
