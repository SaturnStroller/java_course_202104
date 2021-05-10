package saturnStroller.geekTime.course_202104.jvm;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @Description GC日志分析练习
 * @Author SaturnStroller
 *
 * java -XX:+PrintGCDetails -XX:+PrintGCDateStamps -Xmx1g -Xms1g -XX:+UseSerialGC GCLogAnalysis
 */
public class GCLogAnalysis {
    private static Random random = new Random();

    public static void main(String[] args) {
        long startMills = System.currentTimeMillis();
        long timeoutMills = TimeUnit.SECONDS.toMillis(1);
        long endMills = startMills + timeoutMills;
        LongAdder counter = new LongAdder();
        System.out.println("正在执行。。。");
        int cacheSize = 2000;
        //引用循环内部的对象，使对象能够进入old区
        Object[] cachedGarbage = new Object[cacheSize];
        while (System.currentTimeMillis() < endMills){
            Object garbage = generateGarbage(100 * 1024);
            counter.increment();
            int randomIndex = random.nextInt(2 * cacheSize);
            if (randomIndex < cacheSize){
                //随机覆盖对象，使old区对象也有被GC的机会
                cachedGarbage[randomIndex] = garbage;
            }
        }
        System.out.println("执行结束，生成对象次数:" + counter.longValue());
    }

    private static Object generateGarbage(int max){
       int randomSize = random.nextInt(max);
       int type = randomSize % 4;
       Object result = null;
       switch (type){
           case 0:
               result = new int[randomSize];
               break;
           case 1:
               result = new byte[randomSize];
               break;
           case 2:
               result = new double[randomSize];
               break;
           default:
               StringBuilder builder = new StringBuilder();
               String randomString = "randomString-Anything";
               while (builder.length() <randomSize){
                   builder.append(randomString);
                   builder.append(max);
                   builder.append(randomSize);
               }
               result = builder.toString();
               break;
       }
       return result;
    }
}
