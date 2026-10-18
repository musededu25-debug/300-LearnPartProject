
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

-- 看成绩大于80