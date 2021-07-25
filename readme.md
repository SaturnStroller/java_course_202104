### 训练营作业

#### 00code	 course_202104  

#### 01jvm 

- 第一周作业	第2题（自定义ClassLoader）	[HelloClassLoader.java](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/HelloClassLoader.java)
- 第一周作业	第3题（JVM启动参数关系）	[3-JMM_param.png](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/3-JMM_param.png)
- 第二周作业	第1题（对类GCLogAnalysis.java的4种GC日志分析）	
[Serial_GC_log.png](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/Serial_GC_log.png)
[Parallel_GC_log.png](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/Parallel_GC_log.png)
[CMS_GC_log.png](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/CMS_GC_log.png)
[G1_GC_log.png](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/G1_GC_log.png)
- 第二周作业	第4题（GC总结）	[GCSummary.md](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/GCSummary.md)

#### 02nio 

- 第二周作业	第6题（okhttp请求）	[HttpClientTest.java](https://github.com/SaturnStroller/java_course_202104/blob/main/01jvm/course_202104/src/main/java/saturnStroller/geekTime/course_202104/nio/HttpClientTest.java)  

- 第三周作业	第1题（整合httpClient请求）	[HttpOutboundHandler.java](https://github.com/SaturnStroller/java_course_202104/blob/main/02nio/nettygateway/src/main/java/saturnstroller/geektime/nettygateway/outbound/httpClient/HttpOutboundHandler.java)
- 第三周作业	第3题（过滤器验签）	[HeaderHttpRequestFilter.java](https://github.com/SaturnStroller/java_course_202104/blob/main/02nio/nettygateway/src/main/java/saturnstroller/geektime/nettygateway/filter/HeaderHttpRequestFilter.java)
- 第三周作业	第4题（路由）	[RoundRibbonHttpEndpointRouter.java](https://github.com/SaturnStroller/java_course_202104/blob/main/02nio/nettygateway/src/main/java/saturnstroller/geektime/nettygateway/router/RoundRibbonHttpEndpointRouter.java)

#### 03concurrency

- 第四周作业	第2题（主线程等待子线程执行结束）	[blockmain](https://github.com/SaturnStroller/java_course_202104/blob/main/03concurrency/threadpactice/src/main/java/saturnstroller/geektime/threadpractice/blockmain)
- 第四周作业	第6题（多线程知识点脑图）	[concurrency](https://github.com/SaturnStroller/java_course_202104/blob/main/03concurrency/concurrency.png)

#### 04fx

- 第五周作业	第2题（Bean装配）	[Main.java](https://github.com/SaturnStroller/java_course_202104/blob/main/04fx/frame-practice/src/main/java/saturnstroller/geektime/frame/_Main.java)
- 第五周作业	第8题（Starter）	[starter](https://github.com/SaturnStroller/java_course_202104/blob/main/04fx/starterdemo/src/main/java/saturnstroller/geektime/starterdemo/SaturnStrollerAutoConfiguration.java)
- 第五周作业	第10题（连接数据库）	[jdbc](https://github.com/SaturnStroller/java_course_202104/blob/main/04fx/jdbcdemo/src/main/java/saturnstroller/geektime/jdbcdemo/dao)

#### 05db

- 第六周作业	第6题（电商场景建表）	[mall.sql](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/mall.sql)  


- 第七周作业	第2题（插入100万订单数据）	[insert](https://github.com/SaturnStroller/java_course_202104/blob/main/04fx/jdbcdemo/src/main/java/saturnstroller/geektime/jdbcdemo/batch)
- 第七周作业	第9题（基于AbstractRoutingDataSource的多数据源配置读写分离）	[多数据源配置](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/datasourcedemo/src/main/java/saturnstroller/geektime/datasourcedemo/config)
[aop切面配置](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/datasourcedemo/src/main/java/saturnstroller/geektime/datasourcedemo/aop/DataSourceAop.java)
- 第七周作业	第10题（基于ShardingSphere的多数据源配置读写分离）	[多数据源配置](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/shardingspheredemo/src/main/resources/application.properties) [同一个事务内写后读](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/shardingspheredemo/src/main/java/saturnstroller/geektime/shardingspheredemo/dao/HikariDao.java)

- 第八周作业	第2题（水平分库分表）	[shardingProxy](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/shardingProxyDemo)
- 第八周作业	第6题（hmily tcc）	[tcc-订单](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/hmily-demo/hmily-demo-tcc/hmily-demo-tcc-springcloud/hmily-demo-tcc-springcloud-order/src/main/java/org/dromara/hmily/demo/springcloud/order/service/impl/PaymentServiceImpl.java)
[tcc-账户](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/hmily-demo/hmily-demo-tcc/hmily-demo-tcc-springcloud/hmily-demo-tcc-springcloud-account/src/main/java/org/dromara/hmily/demo/springcloud/account/service/impl/AccountServiceImpl.java)
[tcc-库存](https://github.com/SaturnStroller/java_course_202104/blob/main/05db/hmily-demo/hmily-demo-tcc/hmily-demo-tcc-springcloud/hmily-demo-tcc-springcloud-inventory/src/main/java/org/dromara/hmily/demo/springcloud/inventory/service/impl/InventoryServiceImpl.java)

#### 06rpc
- 第九周作业	第2题（自定义RPC）	[rpc](https://github.com/SaturnStroller/java_course_202104/blob/main/06rpc/rpc01/rpcfx-core/src/main/java/io/kimmking/rpcfx/client/Rpcfx.java)
- 第九周作业	第6题（dubbo+hmily）	[dubbo](https://github.com/SaturnStroller/java_course_202104/blob/main/06rpc/himly-tcc-dubbo/transaction/src/main/resources/spring-dubbo.xml)


#### 07redis
- 第十一周作业	第8题（redis分布式锁，计数器）	[redis分布式锁](https://github.com/SaturnStroller/java_course_202104/tree/main/07redis/redis/src/main/java/io/kimmking/cache/RedissionLockDemo.java)
[redis计数器](https://github.com/SaturnStroller/java_course_202104/tree/main/07redis/redis/src/main/java/io/kimmking/cache/CounterDemo.java)
- 第十一周作业	第9题（redis PubSub）	[redis Pub](https://github.com/SaturnStroller/java_course_202104/tree/main/07redis/redis/src/main/java/io/kimmking/cache/PubDemo.java)


#### 08mq
- 第十二周作业	第1题（redis高可用）	[redis高可用](https://github.com/SaturnStroller/java_course_202104/blob/main/08mq/sentinel.conf)
[redis计数器](https://github.com/SaturnStroller/java_course_202104/tree/main/07redis/redis/src/main/java/io/kimmking/cache/CounterDemo.java)
- 第十二周作业	第6题（activemq）	[producer](https://github.com/SaturnStroller/java_course_202104/blob/main/08mq/jms-activemp/src/main/java/com/example/jms/activemq/jmsactivemp/jms/JmsProducer.java)
[consumer](https://github.com/SaturnStroller/java_course_202104/blob/main/08mq/jms-activemp/src/main/java/com/example/jms/activemq/jmsactivemp/jms/JmsConsumer.java)
