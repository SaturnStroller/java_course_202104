package saturnstroller.geektime.threadpractice.test;

public class ReentrantLockDemo {

    public static void main(String[] args) {
        final ReentrantLockCount reentrantLockCount = new ReentrantLockCount();

        for (int i = 0; i < 2; i++) {
            new Thread(() -> reentrantLockCount.get()).start();
        }

        for (int i = 0; i < 2; i++) {
            new Thread(() -> reentrantLockCount.put()).start();
        }
    }
}
