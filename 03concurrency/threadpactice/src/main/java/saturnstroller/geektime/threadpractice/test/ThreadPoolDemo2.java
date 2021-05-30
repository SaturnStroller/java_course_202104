package saturnstroller.geektime.threadpractice.test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 线程池的shutdown()
 * @Description 线程池的优雅停机方式，等待所有线程执行完关闭线程池
 * @Author SaturnStroller
 */
public class ThreadPoolDemo2 {
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
        executorService.shutdown();
        System.out.println("main end...");
    }
}
