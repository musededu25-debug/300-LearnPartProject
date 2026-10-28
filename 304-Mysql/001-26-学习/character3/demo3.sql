-- 参考demo2的创建过程


-- if case when

-- 使用exercise数据库
use exercise;

-- if
select id , stu_name , course ,if(score>=60 ,'及格','不及格') score from score;

-- ifNull 没有分数的学生标记为缺考
select id , stu_name , course ,ifnull(score,'缺考') score from score;

-- case

select
    stu_name,
    Max(case when (course = 'Java') then score else 0 end ) Java,
    Max(case when (course = 'Html') then  score else 0 end) Html,
    max(case when (course = 'Jsp') then score else 0 end) Jsp,
    Max(case when (course = 'Spring') then score else 0 end) Spring
from score
group by stu_name;