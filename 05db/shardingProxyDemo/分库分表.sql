#建表
CREATE TABLE t_order (order_id BIGINT NOT NULL AUTO_INCREMENT, user_id INT NOT NULL, status VARCHAR(50), PRIMARY KEY (order_id));
#插入
insert into t_order(user_id, status)  values (1,'OK'),(2,'FAIL'),(1,'FAIL'),(2,'OK');
#查询
select * from t_order;