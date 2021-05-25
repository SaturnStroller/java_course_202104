package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * CountDownLatch
 * @Description
 * @Author SaturnStroller
 */
public class CountDownLatchDemo {
    static AtomicInteger a = new AtomicInteger();
    static CountDownLatch countDownLatch = new CountDownLatch(5);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        for (int i = 0; i < 5; i++) {
            new SubForCountDownLatch().start();
        }
        countDownLatch.await();
        System.out.println("i=" + a.get());
        System.out.println("main end...");
    }

    public static class SubForCountDownLatch extends Thread{
        @Override
        public void run() {
            System.out.println("sub start...");
            try {
                Thread.sleep(2000);
                a.incrementAndGet();
                System.out.println("sub end...");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }
}
