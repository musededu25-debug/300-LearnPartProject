#!/bin/sh

# 记录脚本启动时所在目录（chapter1）
START_DIR=$(pwd)
TEMP_DIR="$START_DIR/temp"

echo "创建并进入temp文件夹"
# 重复运行时先清掉旧 temp，保证从干净状态开始
rm -rf -- "$TEMP_DIR"
mkdir "$TEMP_DIR"
cd "$TEMP_DIR" || exit 1

echo "--- 创建文件并验证 ---"
echo "hello" > a.txt
echo "hello" > b.txt
ls -li

echo "--- 创建硬链接 ---"
ln a.txt abc
ls -li

echo "--- 改 a.txt 内容，abc 看到同样内容 ---"
cat a.txt
echo "123456" > a.txt
cat abc

echo "--- 删除 a.txt，硬链接 abc 仍可用 ---"
rm a.txt
cat abc

echo "--- mv 改名，inode 不变 ---"
ls -li
mv b.txt fun.txt
ls -li

echo "--- 创建软链接 ---"
# 先创建 c.txt，避免 link 悬空
echo "c-file" > c.txt
ln -s c.txt link
ls -li

echo "--- 软链接风险：mv 改名后 link 悬空 ---"
mv c.txt c_renamed.txt
ls -li
# 此时 link -> c.txt 已经悬空
cat link 2>&1 || echo "cat link 失败：软链接目标 c.txt 不存在"

echo "--- 删除 b.txt 到 web 的演示（b.txt 已改名，这里用 fun.txt） ---"
mkdir -p web
mv fun.txt web/
ls -li
ls -li web

echo "--- 回到起始目录，删除 temp ---"
cd "$START_DIR" || exit 1
pwd
ls -ld temp
rm -rf -- "$TEMP_DIR"
echo "删除后："
ls -ld temp 2>&1 || echo "temp 已不存在"