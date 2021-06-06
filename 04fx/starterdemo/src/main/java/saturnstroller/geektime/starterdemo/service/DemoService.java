package saturnstroller.geektime.starterdemo.service;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DemoService {
    private String name;

    public void m(){
        System.out.println("teacher's name is " + name);
    }
}
