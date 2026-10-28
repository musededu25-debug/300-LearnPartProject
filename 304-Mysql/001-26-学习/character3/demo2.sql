-- init


-- 查看数据库
SHOW DATABASES;

-- 创建 exercise 数据库
CREATE DATABASE IF NOT EXISTS exercise
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci;

-- 使用数据库
USE exercise;

-- 使用demo2_init.sql与demo2_init2.sql文件
    --init table->stu
    --init table->score



-- -- math

-- abs
select abs(-2);

-- floor
select floor(2.5);

-- ceil
select ceil(2.5);

-- truncate
select truncate(1.283746,3);

-- round 返回最近整数,.5进一个（无关正负）
select round(1.3);
select round(1.5);

-- round(x,d) 取小数点后d位并四舍五入
select round(1.2343432,4);


-- mod
select mod(10,3);


-- --string

-- char_length()
select char_length('张三');

-- length
select length('张三');
select length('abs');


-- lower
select lower('ABS');

-- upper
select upper('abn');


-- left
select left('张山吗',2);

-- right
select right('张山吗',2);

-- ltrim去空格
-- rtrim

-- trim

-- replace

-- substring
select substring('duaujdada',2,5);




-- 查询计科和软工各有多少人
select left(class,2), count(*) from stu group by left(class,2);

-- 查询名字有4个字的学生
select name from stu where char_length(name)=4;

-- 查询考试成绩能被10整除的信息

select stu_name from score where mod(score,10)=0;


-- 删库
drop database if exists exercise;

