package saturnstroller.geektime.threadpractice.test;

import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch
 * @Description
 * @Author SaturnStroller
 */
public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(3);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        for (int i = 0; i < 10; i++) {
            new SubForCountSemaphore().start();
        }
        System.out.println("main end...");
    }

    public static class SubForCountSemaphore extends Thread{
        @Override
        public void run() {
            try {
                semaphore.acquire();
                System.out.println("sub start...");
                Thread.sleep(2000);
                System.out.println("sub end...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                semaphore.release();
            }
        }
    }
}
