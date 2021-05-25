package saturnstroller.geektime.threadpractice.blockmain;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition
 * @Description 通过在主线程await(),子线程执行完signal()来阻塞主线程
 * @Author SaturnStroller
 */
public class ConditionDemo {
    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws Exception{
        System.out.println("main start...");
        try {
            lock.lock();
            SubForCondition sub = new SubForCondition();
            sub.start();
            condition.await();
            System.out.println("i=" + sub.get());
            System.out.println("main end...");
        }finally {
            lock.unlock();
        }
    }

    public static class SubForCondition extends Thread{
        private int i ;
        private int get(){
            return i;
        }
        @Override
        public void run(){
            System.out.println("sub start...");
            try {
                lock.lock();
                Thread.sleep(2000);
                i = 2;
                System.out.println("sub end...");
                condition.signal();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
}
