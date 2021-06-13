package saturnstroller.geektime.datasourcedemo.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import saturnstroller.geektime.datasourcedemo.annotation.SaturnDataSource;
import saturnstroller.geektime.datasourcedemo.bean.Order;
import saturnstroller.geektime.datasourcedemo.bean.OrderExample;

/**
 * @Description  Mapper使用自定义的数据源选择注解
 * @Author SaturnStroller
 */
@Mapper
@SaturnDataSource
public interface OrderMapper {
    /**
     * 从库注解，使用从库
     */
    @SaturnDataSource("slave")
    long countByExample(OrderExample example);

    int deleteByExample(OrderExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    int insertSelective(Order record);

    @SaturnDataSource("slave")
    List<Order> selectByExample(OrderExample example);

    @SaturnDataSource("slave")
    Order selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByExample(@Param("record") Order record, @Param("example") OrderExample example);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);
}