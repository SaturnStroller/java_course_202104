package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.*;

/**
 * 线程池的awaitTermination
 * @Description 几种线程池实现方式类似
 * @Author SaturnStroller
 */
public class ThreadPoolDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start...");
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            System.out.println("sub start...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("sub end...");
        });
        executorService.awaitTermination(3, TimeUnit.SECONDS);
        System.out.println("main end...");
    }
}
