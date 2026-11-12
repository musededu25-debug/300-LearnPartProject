create database mybatis;

use mybatis;


drop table if exists tb_user;

CREATE table tb_user(
                        `id` int primary key AUTO_INCREMENT,
                        `username` VARCHAR(20),
                        `password` VARCHAR(20),
                        `gender` CHAR(1),
                        `addr` VARCHAR(30)
);

insert INTO tb_user VALUES (1,'zhangsan','123','男','北京');
insert INTO tb_user VALUES (2,'李四','234','女','天津');
insert INTO tb_user VALUES (3,'王五','11','男','西安');
