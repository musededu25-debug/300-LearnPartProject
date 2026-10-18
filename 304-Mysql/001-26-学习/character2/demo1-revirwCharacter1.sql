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

-- 删除课程表
DROP TABLE IF EXISTS course;

-- 删除数据库
DROP DATABASE IF EXISTS exercise;