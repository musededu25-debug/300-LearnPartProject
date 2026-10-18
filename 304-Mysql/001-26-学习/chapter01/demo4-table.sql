--使用lesson数据库
USE lesson;

--创建学生表

    --问题1 unsigned 的拼写
    --问题2 double 不用not null
    --问题3建表的字符集设置是 default charset=utf8mb4
    --问题5 名字是反引号`不少引号'
    --问题6 备注是comment 不少commit

    create table if not exists student (
        `id` int not null primary key comment '学号',
        `name`  varchar(5) not null comment '名字',
        `sex` tinyint  unsigned not null default 0 comment '性别-0男 1女 2其他',
        `age` tinyint  unsigned not null default 0 comment '年龄' ,
        `score` decimal(5,2)   comment '成绩'
    ) engine=InnoDB default charset=utf8mb4 comment= '学生表';

--练习创建学生表2

    create table if not exists student2 (
        `id` int not null comment '学号',
        `name` varchar(20) not null comment '名字',
        `sex` tinyint(1) default 0 comment '0-男 1-女 2-其他',
        `age` tinyint(3) default 0 comment '年龄默认0',
        `score` decimal(5,2) comment '成绩'
    )engine=InnoDB default charset=utf8mb4 comment='学生表2';


--修改表名
    alter table student rename as stu;
    alter table student2 rename as stu1;

--在stu里面加入phone
    alter table stu add `phone` varchar(11) not null comment '电话';

--查看表结构
    desc stu;

--查看完整表结构
    SHOW FULL COLUMNS FROM stu;

--将stu表的sex字段修改为varchar长度为2，默认为男，注释性别，男女
    alter table stu modify sex varchar(2) default '男' comment '默认为男，注释性别，男女';

--将stu表的phone修改为mobile ，属性不变
    alter table stu change phone mobile varchar(11) not null comment '电话';
    --或者只改名字的话（上面执行下面就不行了，因为名字改了）
        alter table stu rename columns pohne to mobile ;

--删掉stu里面的mobile
    alter table stu drop mobile;


--删表stu1
    drop table if exists stu1;




