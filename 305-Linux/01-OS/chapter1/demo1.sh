# 文件是文件名加引号数字

#跳转用户家目录
cd ~

# 创建temp文件夹
mkdir temp
cd temp

#创建文件并验证
echo "hello" > a.txt
echo "hello" > b.txt
ls -li

# 创建硬连接
ln a.txt abc
ls -li

cat a.txt
echo "123456" > a.txt
cat abc

# 删除看看连接还能不能看
rm a.txt
cat abc

# mv 就是建立一个硬连接并把原来的连接删除掉,#也就是ln-li的inode号不变
ls -li
mv b.txt fun.txt
ls -li


# 软连接 ls 查看详细信息的时候前面是l开头lrwxr-xr-x@ l代表软连接
ln -s c.txt link
ls -li
# 其中7538227 lrwxr-xr-x@ 1 m  staff  5 Oct 24 10:15 link -> c.txt 可以看出来link的大小是5里面存放的就是c.txt这5个字符的字符串


# 软连接的风险，mv改名
ls -li
mv b.txt web
ls -li
cd link
# 如下所示
# /Users/m/Home/300-ProjectHome/300-LearnPartProject/305-Linux/01-OS/chapter1/demo1.sh:cd:43:
# no such file or directory: link


