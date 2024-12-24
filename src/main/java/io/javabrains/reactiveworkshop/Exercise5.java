package io.javabrains.reactiveworkshop;

import org.reactivestreams.Subscription;
import reactor.core.publisher.BaseSubscriber;

import java.io.IOException;

public class Exercise5 {

    public static void main(String[] args) throws IOException {

        // Use ReactiveSources.intNumberMono() and ReactiveSources.userMono()

        // Subscribe to a flux using the error and completion hooks
//        ReactiveSources.intNumbersFlux().subscribe(
//                results -> System.out.println(results),
//                error -> System.out.println(error.getMessage()),
//                () -> System.out.println("Done")
//        );

        // Subscribe to a flux using an implementation of BaseSubscriber
        MySubscriber<Integer> subscriber = new MySubscriber<>();
        ReactiveSources.intNumbersFlux().subscribe(subscriber);

        System.out.println("Press a key to end");
        System.in.read();
    }

}

class MySubscriber<T> extends BaseSubscriber<T> {
    public void hookOnSubscribe(Subscription subscription) {
        System.out.println("Subscribe happened");
        request(1);
    }

    public void hookOnNext(T t) {
        System.out.println(t.toString() + " received.");
        request(1);
    }
}