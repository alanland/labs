package hello.websocket

import org.springframework.messaging.MessageHeaders

/**
 * @author 王成义
 * @version 7/21/16
 */
trait WebsocketHeaderAbility {
    String getWebsocketUser(MessageHeaders headers) {
        String userName = headers.nativeHeaders?.user[0]
        return userName
    }
}
