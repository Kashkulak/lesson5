package com.company;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Downloaders extends Thread {
    private Semaphore semaphore;
    private CountDownLatch countDownLatch;
    private int speedDownload = 100;
    private CountDownLatch countDownLatch1;

    public Downloaders(String name, Semaphore semaphore, CountDownLatch countDownLatch,
                       CountDownLatch countDownLatch1) {
        super(name);
        this.countDownLatch1 = countDownLatch1;
        this.semaphore = semaphore;
        this.countDownLatch = countDownLatch;
        start();
    }

    @Override
    public synchronized void run() {
        try {
            countDownLatch1.await();
            semaphore.acquire();
            System.out.println(getName() + " скачивает из сервера файл");
            sleep(500 / speedDownload*1000);

            System.out.println(getName() + " успешно скачал файл из сервера");
            countDownLatch1.countDown();
            semaphore.release();
        } catch (InterruptedException ignored) {
        }
    }
}