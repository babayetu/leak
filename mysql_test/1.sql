use test;

create table orderitems (
order_num int not null,
order_item int not null,
prod_id char(10) not null,
quantity int not null default 1,
item_price decimal(8,2) not null,
primary key(order_num, order_item)
) engine = innodb;

create table orders (
order_num int not null auto_increment,
order_date datetime not null,
cust_id int not null,
primary key(order_num)
) engine = innodb;

drop table vendors;

create table vendors (
vend_id int(11) not null auto_increment,
vend_name char(30),
vend_address char(30),
vend_city char(30),
vend_state char(30),
vend_zip char(10),
vend_country char(30),
primary key(vend_id)
) engine = innodb;

insert into orders values(NULL,now(),1);
insert into orders values(NULL,now(),2);

insert into orderitems(order_num,order_item,prod_id,quantity,item_price) values(
1,
2,
'necklice',
1,
99.00
);
insert into vendors values(null, 'yuesao','centry avenue','shanghai', 'shanghai', 200001, 'China');
insert into vendors values(null, 'ashley','avenue 12','chicago', 'chicago', 200001, 'USA');

show tables;

drop table orders,orderitems,products,vendors;

