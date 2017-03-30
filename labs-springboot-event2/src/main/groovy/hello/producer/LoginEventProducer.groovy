package hello.producer

import hello.event.LoginEvent
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.ApplicationEventPublisher
import org.springframework.stereotype.Component

/**
 * @author 王成义
 * @version 1/16/17
 */
@Component
class LoginEventProducer {

    static final Logger logger = LoggerFactory.getLogger(LoginEventProducer.class)

    ApplicationEventPublisher publisher

    def TodoEventProducer(ApplicationEventPublisher publisher) {
        this.publisher = publisher
    }

    void create(String todo) {
        logger.info("thread '{}' creating todo '{}'", Thread.currentThread(), todo)
        publisher.publishEvent(new LoginEvent(todo))
    }
}
