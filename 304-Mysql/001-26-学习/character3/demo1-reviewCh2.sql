-- init


-- 查看数据库
SHOW DATABASES;

-- 创建 exercise 数据库
CREATE DATABASE IF NOT EXISTS exercise
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 使用数据库
USE exercise;

CREATE TABLE IF NOT EXISTS emp(
    `no` BIGINT(20) AUTO_INCREMENT PRIMARY KEY NOT NULL COMMENT '员工编号',
    name VARCHAR(20) NOT NULL COMMENT '姓名',
    sex VARCHAR(2) DEFAULT '男' COMMENT '性别',
    age TINYINT(3) UNSIGNED NOT NULL COMMENT '年龄',
    dept VARCHAR(20) NOT NULL COMMENT '所属部门',
    salary DOUBLE(10, 2) NOT NULL COMMENT '薪资'
    )ENGINE=InnoDB CHARSET=UTF8 COMMENT '员工表';

INSERT INTO emp(`no`, name ,sex, age, dept, salary) VALUES(DEFAULT, '张三', '男',
                                                           22, '研发部', 13000);
INSERT INTO emp(name ,sex, age, dept, salary) VALUES('李刚', '男', 24, '研发部',
                                                     14000);
INSERT INTO emp VALUES(DEFAULT, '金凤', '女', 23, '财务部', 8000);
INSERT INTO emp(name ,sex, age, dept, salary) VALUES('肖青', '女', 26, '财务部',
                                                     9000), ('张华', '男', 28, '研发部', 15000),('董钰', '女', 24, '研发部', 12000);
INSERT INTO emp VALUES(DEFAULT, '吴梅', '女', 24, '测试部', 9000),(DEFAULT, '王玲',
                                                                   '女', 26, '测试部', 9500);


-- 吴梅涨薪资
update emp set salary=11000 where name='吴梅';

-- 研发部金凤离职
delete from emp where name='金凤';

-- 从员工表中统计研发部的最高薪
select max(salary) '最高',min(salary) '最低', avg(salary) '平均' from emp where dept='研发部';

-- 统计各部门员工数量
select dept ,count(*) from emp group by dept;

-- 统计薪资在10000以上的员工信息并按薪资高到低
select name ,salary from emp where salary>10000 order by  salary desc;

-- 员工表分页查询员工信息，每页5个，按薪水高到低，查询第二页
select name from emp order by salary desc limit 5,5;

-- 删表
truncate table emp;
drop table if exists emp;

-- 删库
drop database if exists exercise;


