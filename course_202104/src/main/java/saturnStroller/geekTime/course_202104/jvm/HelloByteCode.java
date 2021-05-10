package saturnStroller.geekTime.course_202104.jvm;

/**
 * @Description 1.byteCode练习
 * @Author SaturnStroller
 * javap -c -verbose HelloByteCode
 */
public class HelloByteCode {
    static int a = 5;

    public static void main(String[] args) {
       a ++;
       int b = subMethod(a);
       System.out.println(b);
    }

    public static int subMethod(int a){
        for (int i = 0; i < 10; i++) {
            if (a == i){
                return a * 5;
            }
        }
        return 0;
    }
}
