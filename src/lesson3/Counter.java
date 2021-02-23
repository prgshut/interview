package lesson3;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int value;
    private Lock lock;

    public Counter() {
        this.lock = new ReentrantLock();
    }

    public void increment() {
        try {
            lock.lock();
            value++;
        } finally {
            lock.unlock();
        }
    }

    public void decrement() {
        try {
            lock.lock();
            value--;
        } finally {
            lock.unlock();
        }
    }

    public int get() {
        return value;
    }
}
