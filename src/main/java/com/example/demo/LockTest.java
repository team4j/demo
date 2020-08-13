package com.example.demo;

/**
 * Created by dequan.yu on 2020/7/16.
 */
public class LockTest {

}
interface SuperThread extends Runnable{
    void syncMethod();
}
class Thread0 implements SuperThread {
    Object lock;

    public Thread0(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

    }

    @Override
    public void syncMethod() {
        synchronized (lock) {
            sleepMethod();
        }
    }

    public void sleepMethod() {
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 implements SuperThread {
    Object lock;

    public Thread1(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {

    }

    @Override
    public void syncMethod() {
        synchronized (lock) {
            waitMethod();
        }
    }

    public void waitMethod() {
        try {
            this.lock.wait(100000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}