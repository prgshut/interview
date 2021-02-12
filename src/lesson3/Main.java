package lesson3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int poolSize = 10;
        ExecutorService ex = Executors.newFixedThreadPool(poolSize);
        Object lockObject = new Object();
        AtomicInteger counter = new AtomicInteger(0);
        ex.submit(new PingPong(lockObject, "ping", counter));
        ex.submit(new PingPong(lockObject, "pong", counter));

        Counter myCounter = new Counter();
        for (int i = 0; i < poolSize; i++) {
            ex.execute(() -> {
                for (int j = 0; j < 100; j++) {
                    myCounter.increment();
                }
            });
        }

        ex.shutdown();
        ex.awaitTermination(1, TimeUnit.HOURS);
        System.out.println(myCounter.get());
    }
}
