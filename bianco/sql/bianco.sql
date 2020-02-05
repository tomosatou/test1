set names utf8;
set foreign_key_checks =0;
drop database if exists biancodb;

create database if not exists biancodb;
use biancodb;

drop table if exists user_info;

create table user_info(
	id int not null primary key auto_increment,
	user_id varchar(16)not null unique,
	password varchar(16)not null,
	family_name varchar(32)not null,
	first_name varchar(32)not null,
	family_name_kana varchar(32)not null,
	first_name_kana varchar(32)not null,
	sex tinyint default 0,
	email varchar(32),
	status tinyint default 0,
	logined tinyint not null default 0,
	regist_date datetime not null,
	update_date datetime
);

drop table if exists product_info;

create table product_info(
	id int not null primary key auto_increment ,
	product_id int not null unique,
	product_name varchar(100)not null unique,
	product_name_kana varchar(100)not null unique,
	product_descriotion varchar(255),
	category_id int not null,
	foreign key(category_id)references m_category(category_id),
	price int not null,
	image_file_path varchar(100)not null,
	image_file_name varchar(50)not null,
	release_date datetime,
	release_company varchar(50),
	status tinyint default 1,
	regist_date datetime not null,
	update_date datetime
);

drop table if exists cart_info;

create table cart_info(
	id int not null primary key auto_increment,
	user_id varchar(16) not null,
	product_id int not null,
	foreign key(product_id)references product_info(product_id),
	product_count int not null,
	regist_date datetime not null,
	update_date datetime
);

drop table if exists purchase_history_info;

create table purchase_history_info(
	id int not null primary key auto_increment,
	user_id varchar(16)not null,
	foreign key(user_id)references user_info(user_id),
	product_id int not null,
	foreign key(product_id)references product_info(product_id),
	product_count int not null,
	price int not null,
	destination_id int not null,
	regist_date datetime not null,
	update_date datetime
);

drop table if exists destination_info;

create table destination_info(
	id int not null primary key auto_increment,
	user_id varchar(16)not null,
	foreign key(user_id)references user_info(user_id),
	family_name varchar(32)not null,
	first_name varchar(32)not null,
	family_name_kana varchar(32)not null,
	first_name_kana varchar(32)not null,
	email varchar(32),
	tel_number varchar(13),
	user_address varchar(50)not null,
	regist_date datetime not null,
	update_date datetime
);


drop table if exists m_category;

create table m_category(
	id int not null primary key auto_increment,
	category_id int not null unique,
	category_name varchar(20)not null unique,
	category_description varchar(100),
	regist_date datetime not null,
	update_date datetime
);


