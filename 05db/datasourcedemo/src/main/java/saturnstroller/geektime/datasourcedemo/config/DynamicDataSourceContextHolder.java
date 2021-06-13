package saturnstroller.geektime.datasourcedemo.config;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description  当前线程数据源设置
 * @Author SaturnStroller
 */
@Slf4j
public class DynamicDataSourceContextHolder {
    private static final ThreadLocal<String> DATASOURCE_CONTEXT_HOLDER = new ThreadLocal<>();

    /**
     * 设置数据源
     */
    public static void setDateSoureType(String dateSoureType) {
        log.info("设置数据源={}", dateSoureType);
        DATASOURCE_CONTEXT_HOLDER.set(dateSoureType);
    }

    /**
     * 获得数据源
     */
    public static String getDateSoureType() {
        return DATASOURCE_CONTEXT_HOLDER.get();
    }

    /**
     * 清空数据源
     */
    public static void clearDateSoureType() {
        DATASOURCE_CONTEXT_HOLDER.remove();
    }
}
