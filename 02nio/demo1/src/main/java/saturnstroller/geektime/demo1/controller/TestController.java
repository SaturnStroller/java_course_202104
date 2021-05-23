package saturnstroller.geektime.demo1.controller;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class TestController {

    @ResponseBody
    @GetMapping ("/test")
    public String test(@RequestParam("name") String name,@RequestParam("age") Integer age){
        System.out.println("8801请求来了：name-"+name+",age-"+age);
        Map<String,Object> map = new HashMap<>();
        map.put("name",name+"8801");
        map.put("age",age);
        return JSON.toJSONString(map);
    }
}
