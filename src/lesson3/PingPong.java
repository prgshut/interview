package lesson3;

import java.util.concurrent.atomic.AtomicInteger;

public class PingPong  extends Thread{
    private final Object lockObject;
    private final String name;
    private final AtomicInteger counter;
    private static final int LIMIT = 10;

    public PingPong(Object lockObject, String name, AtomicInteger counter) {
        this.lockObject = lockObject;
        this.name = name;
        this.counter = counter;
    }

    @Override
    public void run() {
        synchronized (lockObject) {
            while (counter.incrementAndGet() <= LIMIT) {
                try {
                    System.out.println(Thread.currentThread().getName() + " : " + name);
                    lockObject.notify();
                    lockObject.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            lockObject.notify();
            System.out.println();
        }
    }
}
