
create table t_goods
(
	id bigint not null
		primary key,
	name varchar(64) null comment '品名',
	summary varchar(256) null comment '商品简介',
	ori_price decimal(14,2) default '0.00' null comment '原价',
	price decimal(14,2) default '0.00' null comment '当前价',
	status tinyint default '1' null comment '状态：0-已下架 1-有效',
	create_time datetime null,
	update_time timestamp null,
	photo1 varchar(128) null comment '图片地址1',
	photo2 varchar(128) null comment '图片地址2',
	photo3 varchar(128) null comment '图片地址3',
	constraint uidx_goods_id
		unique (id)
)
comment '商品表'
;

create index idx_goods_name
	on t_goods (name)
;

create index idx_goods_price
	on t_goods (price)
;

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

create index idx_order_paytime
	on t_order (pay_time)
;

create index idx_order_price
	on t_order (price)
;

create index idx_order_userid
	on t_order (user_id)
;

create table t_order_detail
(
	id bigint not null
		primary key,
	order_id bigint null comment 'order表主键',
	goods_id bigint null comment 'googds表主键',
	goods_nums decimal default '0' null comment '商品数量',
	price decimal(14,2) default '0.00' null comment '价格',
	create_time datetime null,
	update_time timestamp null,
	status tinyint default '1' null comment '状态：0-已删除 1-有效',
	constraint uidx_od_id
		unique (id)
)
comment '订单详情表'
;

create index idx_od_goodsid
	on t_order_detail (goods_id)
;

create index idx_od_orderid
	on t_order_detail (order_id)
;

create table t_user
(
	id bigint not null
		primary key,
	nick_name varchar(64) null comment '用户名',
	name varchar(64) null comment '姓名',
	login_account varchar(64) null comment '登录账号',
	login_pwd varchar(64) null comment '登录密码',
	cert_no varchar(128) null comment '证件号',
	phone varchar(32) null comment '绑定手机号',
	photo varchar(128) null comment '头像地址',
	last_login_time datetime null comment '上次登录时间',
	create_time datetime null,
	update_time timestamp null,
	status tinyint default '1' null comment '状态：0-已注销 1-有效',
	gender tinyint null comment '性别：0-女 1-男',
	constraint uidx_user_id
		unique (id)
)
comment '用户表'
;

create index idx_user_login
	on t_user (login_account, login_pwd)
;