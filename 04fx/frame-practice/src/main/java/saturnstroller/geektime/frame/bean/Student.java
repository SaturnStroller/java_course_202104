package saturnstroller.geektime.frame.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Student {
   private String name;
   private int age;

   public void m(){
      System.out.println("Student method called...");
   }
}
