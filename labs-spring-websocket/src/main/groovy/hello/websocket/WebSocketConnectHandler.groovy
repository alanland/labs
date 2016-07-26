/*
 * Copyright 2014-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package hello.websocket

import hello.RedisService
import hello.data.ActiveWebSocketUser
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationListener
import org.springframework.messaging.MessageHeaders
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectEvent

import java.security.Principal

@Component
public class WebSocketConnectHandler<S>
        implements ApplicationListener<SessionConnectEvent>, WebsocketHeaderAbility {
    @Autowired
    private SimpMessageSendingOperations messagingTemplate;

    @Autowired
    RedisService redis;

    @Override
    public void onApplicationEvent(SessionConnectEvent event) {
        MessageHeaders headers = event.getMessage().getHeaders();
        String sessionId = headers.get('simpSessionId')

        Principal user = SimpMessageHeaderAccessor.getUser(headers);
//        if (user == null) {
//            return;
//        }
        String id = SimpMessageHeaderAccessor.getSessionId(headers);
        String userName = getWebsocketUser(headers)
        ActiveWebSocketUser wsUSer = redis.getObject(userName) as ActiveWebSocketUser
        if (!wsUSer) {
            wsUSer = new ActiveWebSocketUser(id, userName, Calendar.getInstance())
            redis.setObject(userName, wsUSer);
        }
        this.messagingTemplate.convertAndSend("/topic/friends/signin",
                Arrays.asList(userName));
    }
}
