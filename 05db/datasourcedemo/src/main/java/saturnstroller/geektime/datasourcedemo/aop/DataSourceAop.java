package saturnstroller.geektime.datasourcedemo.aop;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import saturnstroller.geektime.datasourcedemo.annotation.SaturnDataSource;
import saturnstroller.geektime.datasourcedemo.config.DynamicDataSourceContextHolder;

import java.lang.reflect.Method;
import java.util.Random;

/**
 * @Description  数据源切面
 * @Author SaturnStroller
 */
@Component
@Aspect
@Order(10)
@Slf4j
public class DataSourceAop {

    @Pointcut("execution(* saturnstroller.geektime.datasourcedemo.mapper.*.*(..))  ||@annotation(saturnstroller.geektime.datasourcedemo.annotation.SaturnDataSource)")
    public void dataSourcePointcut() {

    }

    @Before("dataSourcePointcut()")
    public void doBefore(JoinPoint joinPoint) {
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        //获取数据源注解
        SaturnDataSource saturnDataSource = method.getAnnotation(SaturnDataSource.class);
        log.info("当前方法数据源配置注解：{}", JSON.toJSONString(saturnDataSource));
        if (saturnDataSource == null) {
            //尝试获取类上面的注解
            saturnDataSource = joinPoint.getTarget().getClass().getAnnotation(SaturnDataSource.class);
        }

        //获取注解上的数据源的值的信息
        String dataSourceName;
        if (saturnDataSource == null){
            dataSourceName = "master";
        }else {
            dataSourceName = saturnDataSource.value();
        }
        if ("slave".equals(dataSourceName)){
            //表示使用从库，这里可以简单做一下负载均衡(随机)
            Random random = new Random(System.currentTimeMillis());
            dataSourceName = dataSourceName + (random.nextInt(2)+1);
        }

        //给当前的执行SQL的操作设置特殊的数据源的信息
        DynamicDataSourceContextHolder.setDateSoureType(dataSourceName + "DataSource");
    }

    @After("dataSourcePointcut()")
    public void after(JoinPoint point) {
        //清理掉当前设置的数据源，让默认的数据源不受影响
        DynamicDataSourceContextHolder.clearDateSoureType();
    }
}
