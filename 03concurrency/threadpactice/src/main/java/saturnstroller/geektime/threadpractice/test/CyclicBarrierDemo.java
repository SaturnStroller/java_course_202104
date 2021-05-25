package saturnstroller.geektime.threadpractice.test;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier
 * @Description
 * @Author SaturnStroller
 */
public class CyclicBarrierDemo {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        new SubAForCyclicBarrier().start();
        new SubBForCyclicBarrier().start();
        System.out.println("main end...");
    }

    public static class SubAForCyclicBarrier extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                cyclicBarrier.await();
                System.out.println("sub A start...");
                Thread.sleep(2000);
                System.out.println("sub A end...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static class SubBForCyclicBarrier extends Thread{
        @Override
        public void run() {
            try {
                Thread.sleep(4000);
                cyclicBarrier.await();
                System.out.println("sub B start...");
                Thread.sleep(4000);
                System.out.println("sub B end...");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
