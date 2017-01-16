package hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationEventPublisher
import org.springframework.context.event.EventListener
import org.springframework.core.ResolvableType
import org.springframework.core.ResolvableTypeProvider
import org.springframework.stereotype.Component

/**
 * @author 王成义
 * @version 1/16/17
 */
@Component
class TodoCreatedEventListener {

    @EventListener
    void handle(TodoCreatedEvent event) {
        1
    }
}

class TodoCreatedEvent {

    private String title

    TodoCreatedEvent(String title) {
        this.title = title
    }
}

class Todo {}

class Bid {}

@Component
class TodoCreatedEventProducer {

    private final ApplicationEventPublisher publisher

    @Autowired
    TodoCreatedEventProducer(ApplicationEventPublisher publisher) {}

    void createTodo(Todo todo) {
        publisher.publishEvent(new TodoCreatedEvent(todo.getTitle()))
    }

}

@EventListener
void onBidCeated(EntityCreatedEvent<Bid> event) {
}

class BidCreatedEvent extends EntityCreatedEvent<Bid> {

    BidCreatedEvent(Bid source) {
        super(source)
    }
}

class EntityCreatedEvent<T> implements ResolvableTypeProvider {

    private T source

    EntityCreatedEvent(T source) {
        this.source = source
    }

    @Override
    ResolvableType getResolvableType() {
        return ResolvableType.forClassWithGenerics(getClass(),
                ResolvableType.forInstance(source))
    }
}

