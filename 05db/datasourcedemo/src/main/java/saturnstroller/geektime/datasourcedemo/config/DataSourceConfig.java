package saturnstroller.geektime.datasourcedemo.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 多数据源配置
 * @Author SaturnStroller
 */
@Configuration
@Slf4j
public class DataSourceConfig {

    @Bean(name = "masterDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    public HikariDataSource masterDataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    @Bean(name = "slave1DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave1")
    public HikariDataSource slave1DataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }
    @Bean(name = "slave2DataSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave2")
    public HikariDataSource slave2DataSource(){
        return DataSourceBuilder.create().type(HikariDataSource.class).build();
    }

    /**
     * 把所有的数据源配置到动态数据源配置里
     * 1主2从,主为默认
     */
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(
            @Qualifier("masterDataSource") DataSource masterDataSource,
            @Qualifier("slave1DataSource") DataSource slave1DataSource,
            @Qualifier("slave2DataSource") DataSource slave2DataSource){
        Map<Object,Object> dataSourceMap = new HashMap<>();
        dataSourceMap.put("masterDataSource",masterDataSource);
        dataSourceMap.put("slave1DataSource",slave1DataSource);
        dataSourceMap.put("slave2DataSource",slave2DataSource);
        return new DynamicDataSource(masterDataSource,dataSourceMap);
    }
    @Bean
    public PlatformTransactionManager transactionManager(DynamicDataSource dynamicDataSource){
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean("sqlSessionFactoryBean")
    public SqlSessionFactoryBean sqlSessionFactoryBean(@Qualifier("dynamicDataSource") DataSource dynamicDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        // 配置数据源，此处配置为关键配置，如果没有将 dynamicDataSource作为数据源则不能实现切换
        sessionFactory.setDataSource(dynamicDataSource);

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        // 扫描映射文件
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:mapper/**/*Mapper.xml"));
        return sessionFactory;
    }







//    @Bean
//    public PlatformTransactionManager masterManager(DataSource masterDataSource){
//        return new DataSourceTransactionManager(masterDataSource);
//    }
//
//
//    @Bean
//    public PlatformTransactionManager slave1Manager(DataSource slave1DataSource){
//        return new DataSourceTransactionManager(slave1DataSource);
//    }
//
//
//    @Bean
//    public PlatformTransactionManager slave2Manager(DataSource slave2DataSource){
//        return new DataSourceTransactionManager(slave2DataSource);
//    }



//    @Bean
//    @ConfigurationProperties("spring.datasource.slave1")
//    public DataSourceProperties slave1DataSourceProperties(){
//        return new DataSourceProperties();
//    }
//    @Bean(name = "slave1DataSource")
//    public DataSource slave1DataSource(){
//        DataSourceProperties properties = slave1DataSourceProperties();
//        return properties.initializeDataSourceBuilder().build();
//    }
//    @Bean
//    @ConfigurationProperties("spring.datasource.slave2")
//    public DataSourceProperties slave2DataSourceProperties(){
//        return new DataSourceProperties();
//    }
//    @Bean(name = "slave2DataSource")
//    public DataSource slave2DataSource(){
//        DataSourceProperties properties = slave2DataSourceProperties();
//        return properties.initializeDataSourceBuilder().build();
//    }
}
