package saturnstroller.geektime.shardingspheredemo.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saturnstroller.geektime.shardingspheredemo.dao.HikariDao;

/**
 * @Description
 * @Author SaturnStroller
 */
@RestController
@RequestMapping("/test")
public class DemoController {
    @Autowired
    private HikariDao hikariDao;

    @GetMapping("/select")
    public void select() throws Exception{
        hikariDao.select();
    }

    @GetMapping("/insert")
    public void insert() throws Exception{
        hikariDao.insert();
    }

    @GetMapping("/insert2")
    public void insert2() throws Exception{
        hikariDao.insert2();
    }
}
