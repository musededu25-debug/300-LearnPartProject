-- MySQL8.4以前的改密码用的是mysql_native_password
alter user 'root'@'localhost' identified with mysql_native_password by '新密码';
flush privileges;

-- 8.4+用更加安全的插件caching_sha_password
alter user 'root'@'localhost' identified with cache_sha_password by '新密码';
flush privileges;
