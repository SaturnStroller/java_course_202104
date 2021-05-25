package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * CompletableFuture
 * @Description
 * @Author SaturnStroller
 */
public class CompletableFutureDemo {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        System.out.println("main start...");
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("sub start...");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = 2;
            System.out.println("sub end...");
            return i;
        });

        int ret = future.get();
        System.out.println("i=" + ret);
        System.out.println("main end...");
    }
}
