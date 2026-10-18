-- 复制上一节的创建和修改表，不删

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





-- 本课内容

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

-- 删掉课程编号为1的数据
delete from course where `number`=1;

-- 清空表里面的数据
truncate table course;

-- 删库跑路
DROP DATABASE IF EXISTS exercise;


