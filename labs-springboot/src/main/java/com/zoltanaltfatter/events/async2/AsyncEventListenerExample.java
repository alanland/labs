package com.zoltanaltfatter.events.async2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Zoltan Altfatter
 */
@SpringBootApplication
@EnableAsync
class AsyncEventListenerExample {

    static final Logger logger = LoggerFactory.getLogger(AsyncEventListenerExample.class);

    public static void main(String[] args) throws Exception {
        WebApplicationContext ctx = (WebApplicationContext) SpringApplication.run(AsyncEventListenerExample.class, args);
        Producer producer = ctx.getBean(Producer.class);
        producer.create("asotehuaotehu");
    }

    @Bean
    TaskExecutor taskExecutor() {
        return new SimpleAsyncTaskExecutor();
    }


    static class MedicalRecordUpdatedEvent {

        private String id;

        public MedicalRecordUpdatedEvent(String id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "MedicalRecordUpdatedEvent{" +
                    "id='" + id + '\'' +
                    '}';
        }
    }

    @Component
    static class Receiver {
        @Async
        @EventListener
        void handleAsync(MedicalRecordUpdatedEvent event) {
            logger.info("thread '{}' handling '{}' async event", Thread.currentThread(), event);
        }

        @EventListener
        void handleSync(MedicalRecordUpdatedEvent event) {
            logger.info("thread '{}' handling '{}'  sync event", Thread.currentThread(), event);
        }
    }

    @Component
    static class Producer {

        private final ApplicationEventPublisher publisher;

        public Producer(ApplicationEventPublisher publisher) {
            this.publisher = publisher;
        }

        public void create(String id) {
            publisher.publishEvent(new MedicalRecordUpdatedEvent(id));
            System.out.println("aaaaaaaaaaaaaaaaaaaa");
        }
    }

}
