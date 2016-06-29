package hello

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import reactor.bus.Event
import reactor.bus.EventBus
import reactor.core.publisher.WorkQueueProcessor

import static reactor.bus.selector.Selectors.$

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class Application {

    @Bean
    EventBus createEventBus() {
//        return EventBus.config().concurrency(10).firstEventRouting().get();
//        return EventBus.create();
        return EventBus.create(WorkQueueProcessor.create("bus", 4), 100);
    }

    private static void run(EventBus bus) throws Exception {
        bus.on($("topic"), { Event<String> ev ->
            String s = ev.getData();
            System.out.printf("Got %s on thread %s%n", s, Thread.currentThread());
        });
        bus.notify("topic", Event.wrap("Hello World!"));
        System.out.printf("Notify on thread %s%n", Thread.currentThread());
        bus.notify("topic", Event.wrap("Hello World!"));
        System.out.printf("Notify on thread %s%n", Thread.currentThread());
        bus.notify("topic", Event.wrap("Hello World!"));
    }

    static void send(EventBus bus) {
        bus.receive($("job.sink"), { Event<String> ev ->
            return ev.getData().toUpperCase();
        });

        bus.sendAndReceive(
                "job.sink",
                Event.wrap("Hello World!"),
                { s ->
                    System.out.printf("Got %s on thread %s%n", s, Thread.currentThread())
                }
        );
    }

    public static void main(String[] args) throws Exception {
        ApplicationContext app = SpringApplication.run(Application.class, args);
        EventBus bus = app.getBean(EventBus.class);
        run(bus);
        send(bus);
    }

}