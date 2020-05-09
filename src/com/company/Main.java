package com.company;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {

        CountDownLatch countDownLatch = new CountDownLatch(1);
        CountDownLatch countDownLatch1 = new CountDownLatch(10);
        Semaphore semaphore = new Semaphore(3,true);
        new Uploader(countDownLatch);

        for (int i = 1; i <=10; i++) {
            new Downloaders("Человек "+i,
                    semaphore, countDownLatch,countDownLatch1);
        }
        try {
            countDownLatch1.await();
            System.out.println("Файл из сервера удалится через 10 секунд!");
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}