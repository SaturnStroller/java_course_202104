package saturnstroller.geektime.jdbcdemo.dao;

import org.springframework.stereotype.Repository;
import saturnstroller.geektime.jdbcdemo.bean.Student;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description  Jdbc原生
 * @Author SaturnStroller
 */
@Repository
public class JdbcDao {

    /**
     * @Description  查询
     * @Author SaturnStroller
     */
    public List<Student> queryById(int id) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fund?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url,"root","root");
            statement = con.createStatement();
            //根据id查询
            String sql = "select * from student where id = " + id;
            ResultSet rs = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Student(rs.getInt("id"),rs.getString("name")));
            }
            return list;
        }finally {
            if (statement != null){
                statement.close();
            }
            if (con != null){
                con.close();
            }
        }
    }

    /**
     * @Description  插入  （更新，删除类似）
     * @Author SaturnStroller
     */
    public List<Student> insert(String name) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fund?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        Connection con = null;
        Statement statement = null;
        try {
            con = DriverManager.getConnection(url,"root","root");
            statement = con.createStatement();
            //插入
            String sql = "insert into student(name) values ('" + name + "')";
            statement.executeUpdate(sql);
            //插入完再查出来
            sql = "select * from student";
            ResultSet rs = statement.executeQuery(sql);
            List<Student> list = new ArrayList<>();
            while (rs.next()){
                list.add(new Student(rs.getInt("id"),rs.getString("name")));
            }
            return list;
        }finally {
            if (statement != null){
                statement.close();
            }
            if (con != null){
                con.close();
            }
        }
    }


}
