package saturnStroller.geekTime.course_202104.jvm;

import java.io.*;

/**
 * @Description  2.自定义ClassLoader
 * @Author SaturnStroller
 */
public class HelloClassLoader extends ClassLoader {
    static String filePath = "E:\\corese_git\\01jvm\\";

    public static void main(String[] args) throws Exception {
        //加密文件-仅首次使用
//        encFile();
        new HelloClassLoader().findClass("saturnStroller.jvm.Hello").newInstance();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = decFile(filePath + "Hello.xlass");
        return super.defineClass(name, bytes, 0, bytes.length);
    }

    public static byte[] decFile(String fileName){
        try {
            FileInputStream in = new FileInputStream(fileName);
            ByteArrayOutputStream  out = new ByteArrayOutputStream();
            int ch;
            while (-1 != (ch = in.read())) {
                ch = 255 - ch;
                out.write(ch);
            }
            byte[] bytes = out.toByteArray();
            in.close();
            out.close();
            return bytes;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void encFile(){
        try {
            InputStream in = new FileInputStream(filePath + "Hello.class");
            OutputStream out = new FileOutputStream(filePath + "Hello.xlass");
            int ch;
            while (-1 != (ch = in.read())) {
                ch = 255 - ch;
                out.write(ch);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
