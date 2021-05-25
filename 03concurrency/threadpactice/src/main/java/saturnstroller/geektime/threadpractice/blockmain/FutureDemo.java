package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.*;

/**
 * Future
 * @Description 几种线程池实现方式类似
 * @Author SaturnStroller
 */
public class FutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start...");
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<Integer> future = executorService.submit(() -> {
            System.out.println("sub start...");
            Thread.sleep(2000);
            int i = 2;
            System.out.println("sub end...");
            return i;
        });
        int ret = future.get();
        executorService.shutdown();
        System.out.println("i=" + ret);
        System.out.println("main end...");
    }
}
