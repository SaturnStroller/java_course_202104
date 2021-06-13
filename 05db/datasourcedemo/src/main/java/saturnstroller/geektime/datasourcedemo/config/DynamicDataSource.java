package saturnstroller.geektime.datasourcedemo.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.Map;

/**
 * @Description  动态数据源配置
 * 关键就是继承了AbstractRoutingDataSource
 * @Author SaturnStroller
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    /**
     * 获取当前线程的数据源
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.getDateSoureType();
    }

    /**
     * 设置默认数据源和已经存在的所有数据源
     */
    public DynamicDataSource(DataSource dataSource, Map<Object,Object> dataSourceMap){
        super.setDefaultTargetDataSource(dataSource);
        super.setTargetDataSources(dataSourceMap);
        super.afterPropertiesSet();
    }
}
