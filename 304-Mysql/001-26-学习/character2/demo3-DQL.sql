-- 建库和表


-- 查看数据库
SHOW DATABASES;

-- 创建 exercise 数据库
CREATE DATABASE IF NOT EXISTS exercise
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 使用数据库
USE exercise;

-- 创建表 stu_course
CREATE TABLE IF NOT EXISTS stu_course (
                                          `number` INT NOT NULL AUTO_INCREMENT PRIMARY KEY COMMENT '课程编号',
                                          `name` VARCHAR(20) NOT NULL COMMENT '课程名字',
    `score` DECIMAL(5,2) NOT NULL COMMENT '学分'
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='课程表';

-- 课程表重命名为 course
ALTER TABLE stu_course RENAME TO course;

-- 查看表
SHOW TABLES;

-- 添加学时字段
ALTER TABLE course ADD `time` TINYINT UNSIGNED NOT NULL DEFAULT 0 COMMENT '学时';

-- 修改课程表学分精度为 decimal(3,1)
ALTER TABLE course MODIFY score DECIMAL(3,1) NOT NULL COMMENT '学分';

-- 查看表结构
show full columns form coure;


-- 得到（number,name,score,time)

-- 插入数据
insert into course values(1,'Java基础',4,40),
                         (2,'数据库',3,20),
                         (3,'Jsp',5,40),
                         (4,'Spring',4,5),
                         (5,'Spring MVC',2,5),
                         (6,'SSM',2,3),
                         (7,'Spring Boot',2,2);

-- 查看表里面的数据
select * from course;

-- 将数据库的学分改为4，学时改为15
update course set `score`=4 , `time`=15 where `name`='数据库';


-- 到此为止

-- 看course表里面的内容
select * from course;

-- 看name
select name from course;
-- 改别名
select name as '课程名字' from course;

-- 看课程表里面编号小于5的
select * from course where `number`<5;

-- 查看课程表名字为'Java基础'的学分和学时
select score as '学分' ,time as '学时' from course where `name`='Java基础';

-- 看所有的score
select score from course;

-- 看去除版本
select distinct score from course;

-- 查看课程名字为非空的数据
select * from course where name is not null;
-- 看数据为空就是is null
select * from course where name is null;

-- 看学分在2-4的课程
select * from course where score between 2 and 4;

-- 给列取别名
select score as '学分' , time as '学时' from course;

-- 表的别名
select * from course c where c.name='Java基础';

-- 查看以J开头的课程
select * from course where name like 'J%';

-- 看name里面又pr的课程
select * from course where name like '%pr%';

-- 看name以Boot结尾的课程
select * from course where name like '%Boot';

-- 看课程里面编号为1，3，5的
select * from course where number in(1,3,5);

-- 删表
truncate table course;

-- 删库跑路
drop database exercise;






