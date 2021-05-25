package saturnstroller.geektime.threadpractice.blockmain;

/**
 * join()
 * @Description  通过在主线程join子线程来阻塞主线程
 * @Author SaturnStroller
 */
public class ThreadJoinDemo {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("main start...");
        SubForThreadJoin sub = new SubForThreadJoin();
        sub.start();
        sub.join();
        System.out.println("i=" + sub.get());
        System.out.println("main end...");
    }

    public static class SubForThreadJoin extends Thread{
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
