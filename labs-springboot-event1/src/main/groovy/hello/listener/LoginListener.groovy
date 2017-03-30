package hello.listener

import hello.event.LoginEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component

/**
 * @author 王成义
 * @version 1/16/17
 */
@Component
class LoginListener {

    static final Logger logger = LoggerFactory.getLogger(LoginListener.class);

    @EventListener
    void handle(LoginEvent event) {
        logger.info("'{}' handling todo '{}'", Thread.currentThread(), event);
    }

}
