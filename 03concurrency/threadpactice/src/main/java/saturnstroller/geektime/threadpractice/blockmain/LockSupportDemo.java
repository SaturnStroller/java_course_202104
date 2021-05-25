package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.locks.LockSupport;

/**
 * LockSupport
 * @Description 通过主线程park(),在子线程unpark(主线程)来阻塞主线程
 * @Author SaturnStroller
 */
public class LockSupportDemo {
    static Thread main;

    public static void main(String[] args) {
        System.out.println("main start...");
        main = Thread.currentThread();
        SubForLockSupport sub = new SubForLockSupport();
        sub.start();
        LockSupport.park();
        System.out.println("i=" + sub.get());
        System.out.println("main end...");
    }

    public static class SubForLockSupport extends Thread{
        private int i ;
        private int get(){
            return i;
        }
        @Override
        public void run() {
            System.out.println("sub start...");
            try {
                Thread.sleep(2000);
                i = 2;
                System.out.println("sub end...");
                LockSupport.unpark(main);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
