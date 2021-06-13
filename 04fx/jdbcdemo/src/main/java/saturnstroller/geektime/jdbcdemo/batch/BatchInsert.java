package saturnstroller.geektime.jdbcdemo.batch;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @Description  batch插入100万条数据，耗时：13s
 *
 *               使用DataGrip导入csv文件100万条数据，耗时：44s
 * @Author SaturnStroller
 */
@SpringBootTest
public class BatchInsert {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Test
    public void insert() throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO mall.t_order (id, user_id, ori_price, price, status) VALUES ");

        for (int i = 1; i <= 1000000; i++) {
            sb.append("(").append(101000000L + i).append(",101000001,").append(i)
                    .append(",").append(i).append(",0), ");
        }
        String sql = sb.toString();
        sql = sql.substring(0,sql.length()-2);

        java.util.Date start = new java.util.Date();
        System.out.println("-----insert start..."+ start);
        jdbcTemplate.execute(sql);
        System.out.println("-----insert end...total spend "+ ((new java.util.Date().getTime() - start.getTime())/1000));
    }
}
