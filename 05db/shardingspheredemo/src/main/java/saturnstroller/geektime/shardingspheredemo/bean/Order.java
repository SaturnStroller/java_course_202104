package saturnstroller.geektime.shardingspheredemo.bean;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description  
 * @Author SaturnStroller
 */
@Data
public class Order {
    private Long id;

    private Long userId;

    private BigDecimal oriPrice;

    private BigDecimal price;

    private Byte status;

    private Date createTime;

    private Date payTime;

    private Date delTime;
}
