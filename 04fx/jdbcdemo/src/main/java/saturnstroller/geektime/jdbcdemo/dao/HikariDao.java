package saturnstroller.geektime.jdbcdemo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import saturnstroller.geektime.jdbcdemo.bean.Student;

import java.util.List;

/**
 * @Description  Hikari
 *      spring-boot-starter-jdbc默认使用的Hikari数据源
 * @Author SaturnStroller
 */
@Repository
public class HikariDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Student> queryById(int id) throws Exception{
        //根据id查询
        String sql = "select * from student where id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class),id);
    }

    public List<Student> updateById(int id,String name) throws Exception{
        //根据id更新
        String sql = "update student set name = ? where id = ?";
        jdbcTemplate.update(sql,name,id);
        //更新完查询出来
        sql = "select * from student where id = ?";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class),id);
    }

    public List<Student> insert(String name) throws Exception{
        //根据id更新
        String sql = "insert into student(name) values (?)";
        jdbcTemplate.update(sql,name);
        //插入完都查询出来
        sql = "select * from student";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
    }

    public List<Student> delete(int id) throws Exception{
        //根据id更新
        String sql = "delete from student where id = ?";
        jdbcTemplate.update(sql,id);
        //删除完都查询出来
        sql = "select * from student";
        return jdbcTemplate.query(sql, BeanPropertyRowMapper.newInstance(Student.class));
    }

}
