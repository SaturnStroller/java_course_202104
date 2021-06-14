1.本地部署MySQL  
2.建schema--mall,mall1,mall2.
名字随意，需要修改yml中的数据源配置  
3.执行建表sql
```sql
create table t_order
(
	id bigint not null
		primary key,
	user_id bigint null comment 'user表主键',
	ori_price decimal(14,2) default '0.00' null comment '原价',
	price decimal(14,2) default '0.00' null comment '当前价',
	status tinyint null comment '状态：0-已下单 1-已支付 2-已删除 3-已过期',
	create_time datetime null comment '下单时间',
	pay_time datetime null comment '支付时间',
	del_time datetime null comment '过期/删除时间',
	constraint uidx_order_id
		unique (id)
)
comment '订单表'
;
```
4.启动项目  
5.测试地址：  
插入：http://localhost:8080/test/insert  
查询：http://localhost:8080/test/select