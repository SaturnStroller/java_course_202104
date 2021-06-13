package saturnstroller.geektime.jdbcdemo.batch;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/**
 * @Description  插入100万条数据，耗时：2200s
 * @Author SaturnStroller
 */
public class PrepareStatementInsert {

    public static void main(String[] args) throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/mall?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
        Connection con = null;
        PreparedStatement statement = null;
        java.util.Date start = new java.util.Date();
        System.out.println("-----insert start..."+ start);
        try {
            con = DriverManager.getConnection(url,"root","root");
            String sql = "INSERT INTO mall.t_order (id, user_id, ori_price, price, status) VALUES (?, ?, ?, ?, 0)";
            statement = con.prepareStatement(sql);
            for (int i = 1; i <= 100000; i++) {
                System.out.println(i);
                statement.setLong(1,100000000L + i);
                statement.setLong(2,100000001L);
                statement.setInt(3,i);
                statement.setInt(4,i);
                statement.executeUpdate();
            }
        }finally {
            if (statement != null){
                statement.close();
            }
            if (con != null){
                con.close();
            }
        }
        System.out.println("-----insert end...total spend "+ ((new java.util.Date().getTime() - start.getTime())/1000));
    }
}
