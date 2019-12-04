package com.example.demo;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dequan.yu
 * @version V1.0
 * @description TODO
 * @date 2019/11/27
 **/
public class LockTest {
    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        lock.lock();
        System.out.println();
        lock.unlock();

        AtomicInteger atomicInteger = new AtomicInteger(1);
        atomicInteger.incrementAndGet();
        System.out.println(atomicInteger.get());
    }
}
