package saturnstroller.geektime.frame.bean;

import lombok.Data;

@Data
public class Teacher {
    private String name;
    private String age;

    public void m(){
        System.out.println("Teacher method called...");
    }
}
