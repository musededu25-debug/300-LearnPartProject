
-- 查看数据库
SHOW DATABASES;

-- 创建 exercise 数据库
CREATE DATABASE IF NOT EXISTS exercise
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 使用数据库
USE exercise;




-- 建表
DROP TABLE IF EXISTS student;
CREATE TABLE student(
    no BIGINT(20) AUTO_INCREMENT NOT NULL PRIMARY KEY COMMENT '学号，主键',
    name VARCHAR(20) NOT NULL COMMENT '姓名',
    sex VARCHAR(2) DEFAULT '男' COMMENT '性别',
    age INT(3) DEFAULT 0 COMMENT '年龄',
    score DOUBLE(5, 2) COMMENT '成绩'
)ENGINE=InnoDB CHARSET=UTF8 COMMENT='学生表';

-- 插入数据

INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '张三', '男', 20,
                                                       59);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '李四', '女', 19,
                                                       62);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '王五', '其他',
                                                       21, 62);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '龙华', '男', 22,
                                                       75);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '金凤', '女', 18,
                                                       80);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '张华', '其他',
                                                       27, 88);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '李刚', '男', 30,
                                                       88);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '潘玉明', '女',
                                                       28, 81);
INSERT INTO student(no, name, sex, age, score) VALUES (DEFAULT, '凤飞飞', '其他',
                                                       32, 90);

-- 改表名为stu
alter table student rename as stu;

-- 分组查询

-- 按性别分组分数大于80的(有错，不能这样）
--select * from stu where score between 60 and 80 group by sex,age;

-- 看成绩大于80的人数
select count(*) from stu  where score>80;

-- 不及格人数和总分
select count(*) '不及格人数',sum(score) '不及格人的总分' from stu where score<60;

-- 学生表中男生女生其他学生的平均分
select sex ,avg(score) from stu group by sex;

-- 学生表中看最大年龄的人
select max(age) from stu;

select name from stu where age=(select max(age) from stu);

-- 找最小分数
select min(score) from stu;

select name ,sex ,score from stu where score=(select min(score) from stu);

-- 20-30学生信息并按照性别分组，找出组里平均成绩大于74的组
select sex,avg(score) from stu where age between 20 and 30 group by sex having avg(score)>74;

-- 18-30之间的学生信息并按照成绩高到低
select * from stu where age between 18 and 30 order by score desc;

-- 删表
truncate table stu;
drop table if exists stu;


-- 删库
drop database if exists exercise;
