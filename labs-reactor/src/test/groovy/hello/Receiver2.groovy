package hello

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import reactor.bus.Event
import reactor.bus.EventBus

import javax.annotation.PostConstruct
import java.util.function.Consumer

import static reactor.bus.selector.Selectors.$

/**
 * @author 王成义
 * @version 6/16/16
 */

@Service
class Receiver2 implements Consumer<Event<Integer>> {

    RestTemplate restTemplate = new RestTemplate();

    @Autowired
    EventBus bus;

    public void accept(Event<Integer> ev) {
//        QuoteResource quoteResource =
//                restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", QuoteResource.class);
//        System.out.println("Quote " + ev.getData() + ": " + quoteResource.getValue().getQuote());
        System.out.printf(" %s %s%n", Thread.currentThread(), ev.getData());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @PostConstruct
    void initIt() {
        bus.on($("quotes"), this);
    }

}