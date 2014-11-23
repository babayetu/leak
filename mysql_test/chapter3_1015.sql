-- ctrl + enter to execute current line
show databases;
use test;
show tables;
show columns from city;
describe city;
show status;
show grants;
create table vendors(
vendor_id int(16) not null primary key,
vendor_name varchar(20) not null);
create table products(
prod_id int(16) not null primary key,
prod_name varchar(20) not null,
prod_price double(16,2) not null,
vendor_id int(16) not null);

insert into vendors(vendor_id,vendor_name) values(1,'ebay');
insert into vendors(vendor_id,vendor_name) values(2,'paypal');
insert into vendors(vendor_id,vendor_name) values(3,'taobao');

insert into products(prod_id,prod_name,prod_price,vendor_id)
values(1,'dangle', 20, 2);
insert into products(prod_id,prod_name,prod_price,vendor_id)
values(2,'second hand products', 10, 1);
insert into products(prod_id,prod_name,prod_price,vendor_id)
values(3,'fake products', 5, 3);

select vendor_name, prod_name, prod_price from vendors,products
where vendors.vendor_id = products.vendor_id;
select vendor_name, prod_name, prod_price from vendors,products;
select vendor_name, prod_name, prod_price from vendors inner join products
on vendors.vendor_id = products.vendor_id;