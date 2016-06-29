package io.pivotal.literx;

import org.junit.Test;
import org.reactivestreams.Subscriber;
import reactor.core.publisher.Flux;
import reactor.core.test.TestSubscriber;

import java.util.ArrayList;
import java.util.List;

/**
 * Learn how to create Flux instances.
 *
 * @author Sebastien Deleuze
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/publisher/Flux.html">Flux Javadoc</a>
 * @see <a href="http://projectreactor.io/core/docs/api/reactor/core/test/TestSubscriber.html">TestSubscriber Javadoc</a>
 */
public class Part01CreateFlux {

//========================================================================================

    @Test
    public void empty() {
        Flux<String> flux = emptyFlux();
        TestSubscriber
                .subscribe(flux)
                .assertValueCount(0)
                .assertComplete();
    }

    // TODO Return an empty Flux
    Flux<String> emptyFlux() {
        return new Flux<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onComplete();
            }
        };
    }

//========================================================================================

    @Test
    public void fromValues() {
        Flux<String> flux = fooBarFluxFromValues();
        TestSubscriber
                .subscribe(flux)
                .assertValues("foo", "bar")
                .assertComplete();
    }

    // TODO Return a Flux that contains 2 values "foo" and "bar" without using an array or a collection
    Flux<String> fooBarFluxFromValues() {
        new Flux<String>() {
            @Override
            public void subscribe(Subscriber<? super String> s) {
                s.onNext("foo");
                s.onNext("bar");
                s.onComplete();
            }
        };
        return Flux.fromArray(new String[]{"foo", "bar"});
    }

//========================================================================================

    @Test
    public void fromList() {
        Flux<String> flux = fooBarFluxFromList();
        TestSubscriber
                .subscribe(flux)
                .assertValues("foo", "bar")
                .assertComplete();
    }

    // TODO Create a Flux from a List that contains 2 values "foo" and "bar"
    Flux<String> fooBarFluxFromList() {
        List<String> l = new ArrayList<String>();
        l.add("foo");
        l.add("bar");
        return Flux.fromIterable(l);
    }

//========================================================================================

    @Test
    public void error() {
        Flux<String> flux = errorFlux();
        TestSubscriber
                .subscribe(flux)
                .assertError(IllegalStateException.class)
                .assertNotComplete();
    }

    // TODO Create a Flux that emits an IllegalStateException
    Flux<String> errorFlux() {
        return null;
    }

//========================================================================================

    @Test
    public void neverTerminates() {
        Flux<String> flux = neverTerminatedFlux();
        TestSubscriber
                .subscribe(flux)
                .assertNotTerminated();
    }

    // TODO Create a Flux that never terminates
    Flux<String> neverTerminatedFlux() {
        return null;
    }

//========================================================================================

    @Test
    public void countEachSecond() {
        Flux<Long> flux = counter();
        TestSubscriber
                .subscribe(flux)
                .assertNotTerminated()
                .awaitAndAssertNextValues(0L, 1L, 2L);
    }

    // TODO Create a Flux that emits an increasing value each 100ms
    Flux<Long> counter() {
        return null;
    }

}
