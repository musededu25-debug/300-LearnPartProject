create database if not exists exercise2 default character set utf8mb4 collate utf8mb4_general_ci;

       use database exercise2;

       create table if not exists stu(
           `id` int primary key  auto_increment  comment 'ID唯一标识',
           `name` varchar(10) not null unique comment '姓名',
           `age` int check( age > 0 and age <= 120) comment '年龄',
           `status` char(1) default 1 comment '状态',
           `gender` char(1)  comment '性别'
       )engine=InnoDB ,default charset=utf8mb4 , comment = '学生表';


        insert into stu(name,age,status,gender) values
                                ('Tom1',19,'1','男'),
                                ('Tom2',25,'0','男');