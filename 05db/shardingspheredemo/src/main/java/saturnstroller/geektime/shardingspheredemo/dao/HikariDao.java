package saturnstroller.geektime.shardingspheredemo.dao;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import saturnstroller.geektime.shardingspheredemo.bean.Order;

import java.util.List;

/**
 * @Description
 * @Author SaturnStroller
 */
@Repository
public class HikariDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    /**
     * @Description  查询-读从库
     * @Author SaturnStroller
     */
    public void select() throws Exception{
        String sql = "select * from t_order where id = 1";
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
        System.out.println(JSON.toJSONString(orders));
    }
    /**
     * @Description  更新-主库；查询-从库
     * @Author SaturnStroller
     */
    public void insert() throws Exception{
        String sql = "insert into t_order(id,price) values (101,99)";
        jdbcTemplate.update(sql);
        //插入完都查询出来
        sql = "select * from t_order";
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
        System.out.println(JSON.toJSONString(orders));
    }
    /**
     * @Description  加了事务，更新-主库；查询-主库
     * @Author SaturnStroller
     */
    @Transactional
    public void insert2() throws Exception{
        String sql = "insert into t_order(id,price) values (101,99)";
        jdbcTemplate.update(sql);
        //插入完都查询出来
        sql = "select * from t_order";
        List<Order> orders = jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Order.class));
        System.out.println(JSON.toJSONString(orders));
    }

}
