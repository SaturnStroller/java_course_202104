package saturnstroller.geektime.jdbcdemo.dao;

import org.springframework.stereotype.Repository;
import saturnstroller.geektime.jdbcdemo.bean.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description  PrepareStatement方式
 * @Author SaturnStroller
 */
@Repository
public class PrepareStatementDao {

    /**
     * @Description  查询
     * @Author SaturnStroller
     */
    public List<Student> queryById(int id) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fund?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection(url,"root","root");
            //根据id查询
            statement = con.prepareStatement("select * from student where id = ?");
            statement.setInt(1,id);
            ResultSet rs = statement.executeQuery();
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
     * @Description  更新  （插入，删除类似）
     * @Author SaturnStroller
     */
    public List<Student> update(int id,String name) throws Exception{
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/fund?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        Connection con = null;
        PreparedStatement statement = null;
        try {
            con = DriverManager.getConnection(url,"root","root");
            //更新
            String sql = "update student set name = ? where id = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1,name);
            statement.setInt(2,id);
            statement.executeUpdate();
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
