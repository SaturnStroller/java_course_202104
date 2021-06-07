package saturnstroller.geektime.jdbcdemo.test;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import saturnstroller.geektime.jdbcdemo.bean.Student;
import saturnstroller.geektime.jdbcdemo.dao.HikariDao;
import saturnstroller.geektime.jdbcdemo.dao.JdbcDao;
import saturnstroller.geektime.jdbcdemo.dao.PrepareStatementDao;

import java.util.List;

@SpringBootTest
public class DaoTest {
    @Autowired
    private JdbcDao jdbcDao;
    @Autowired
    private PrepareStatementDao prepareStatementDao;
    @Autowired
    private HikariDao hikariDao;

    /**
     * @Description  原生JDBC方式
     * @Author SaturnStroller
     */
    @Test
    public void testJdbcQuery() throws Exception{
        List<Student> list1 = jdbcDao.queryById(1);
        System.out.println(JSON.toJSONString(list1));
    }
    @Test
    public void testJdbcInsert() throws Exception{
        List<Student> list1 = jdbcDao.insert("许同学");
        System.out.println(JSON.toJSONString(list1));
    }

    /**
     * @Description  PrepareStatement方式
     * @Author SaturnStroller
     */
    @Test
    public void testPrepareStatementQuery() throws Exception{
        List<Student> list1 = prepareStatementDao.queryById(1);
        System.out.println(JSON.toJSONString(list1));
    }
    @Test
    public void testPrepareStatementUpdate() throws Exception{
        List<Student> list1 = prepareStatementDao.update(1,"赵同学");
        System.out.println(JSON.toJSONString(list1));
    }

    /**
     * @Description  Hikari方式
     * @Author SaturnStroller
     */
    @Test
    public void testHikariQuery() throws Exception{
        List<Student> list1 = hikariDao.queryById(1);
        System.out.println(JSON.toJSONString(list1));
    }
    @Test
    public void testHikariUpdate() throws Exception{
        List<Student> list1 = hikariDao.updateById(1,"李同学");
        System.out.println(JSON.toJSONString(list1));
    }
    @Test
    public void testHikariInsert() throws Exception{
        List<Student> list1 = hikariDao.insert("张同学");
        System.out.println(JSON.toJSONString(list1));
    }
    @Test
    public void testHikariDelete() throws Exception{
        List<Student> list1 = hikariDao.delete(2);
        System.out.println(JSON.toJSONString(list1));
    }
}
