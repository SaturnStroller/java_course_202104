package saturnstroller.geektime.datasourcedemo.controller;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import saturnstroller.geektime.datasourcedemo.bean.Order;
import saturnstroller.geektime.datasourcedemo.mapper.OrderMapper;

import java.math.BigDecimal;
import java.util.Date;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {
    @Autowired
    private OrderMapper orderMapper;

    @GetMapping("/insert")
    public void testInsert(){
        for (int i = 10; i < 20; i++) {
            Order order = new Order();
            order.setId((long) i);
            order.setUserId(0L);
            order.setPrice(new BigDecimal(i));
            order.setCreateTime(new Date());
            orderMapper.insertSelective(order);
        }
    }

    @GetMapping("/select")
    public void testSelect(){
        //从库1的userId设置的是1，从库2的userId设置的是2，主库userId设置的0
        //根据userId判断是不是走了对应的从库即可
        log.info("查询结果:{}", JSON.toJSONString(orderMapper.selectByPrimaryKey((1L))));
    }
}
