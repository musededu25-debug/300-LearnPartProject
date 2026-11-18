# Git 提交规范（300-LearnPartProject）

> 目标：让每次 commit 都能回答三个问题——**改了什么、为什么改、影响哪里**。
>
> 反例就在本仓库的历史里：`mac`、`afternoon`、`night-wondows`、`1`、`history`、`springMvc from mac`。
> 这些提交过两周自己都看不懂，更别说回滚和查问题。

## 1. 一条提交长什么样

```text
<type>(<scope>): <subject>

<body 可选：为什么改、改了什么>

<footer 可选：BREAKING CHANGE、关联 issue>
```

### type（必填）

| type | 用在什么时候 | 本项目例子 |
|------|--------------|-----------|
| `feat` | 新增功能、新增练习、新增示例 | 新增 MyBatis 的 BrandMapper 练习 |
| `fix` | 修复错误、编译不过、结果不对 | 修正中文乱码、修正 SQL 映射 |
| `refactor` | 重构，行为不变 | 拆分 DAO / Service、调整包结构 |
| `docs` | 文档、注释、笔记、说明 | 更新 CLAUDE.md、补 README |
| `test` | 新增或修改测试 | 增加 BrandMapperTest |
| `build` | 构建、依赖、Maven 配置 | 升级依赖、改 pom.xml |
| `chore` | 杂务、配置、.gitignore | 忽略 target/、整理目录 |
| `style` | 纯格式，不改逻辑（少用） | 缩进、空行 |
| `revert` | 回滚某次提交 | revert 掉错误的改动 |

### scope（建议填）

用**学习模块或技术名**，不要写设备名、时间段：

```text
javase / javaweb / maven / spring / springmvc / mybatis
mysql / linux / html / css / review
skills / repo
```

需要更细时用斜杠：`mybatis/d03`、`spring/aop`。

### subject（必填）

- 动词开头，说“做了什么”，不说“改了哪些文件”
- 中文可以，**不超过 50 个字**
- 结尾不加句号
- 一次提交只做一件事

### body（建议有）

复杂改动写清楚：

```text
feat(mybatis): 增加 BrandMapper 动态代理与结果映射练习

为什么：
- 课件只演示了 XML，需要把接口代理、@Param、resultMap 串起来

做了什么：
- 新增 BrandMapper 接口与 XML
- 增加 BrandMapperTest，覆盖查询、插入、动态条件

验证：
- mvn test 通过
```

## 2. 反例 → 正例

| 反例 | 问题 | 改成 |
|------|------|------|
| `mac` | 只说明在哪台机器 | `feat(mybatis): 完成 BrandMapper 单表 CRUD` |
| `afternoon` | 只说明时间 | `fix(springmvc): 修正参数绑定示例的中文乱码` |
| `night-wondows` | 时间 + 设备，无信息 | `docs(review): 补充 Spring AOP 复盘笔记` |
| `1` | 等于没写 | `feat(maven): 新增 maven04 依赖范围示例` |
| `springMvc from mac` | 设备与主题混在一起 | `feat(springmvc): 增加 RESTful 员工 CRUD 示例` |
| `history` | 指向不明 | `docs(repo): 整理 2026-11 学习记录` |

## 3. 提交前 30 秒

```bash
git status          # 看清哪些改了、哪些已暂存
git diff            # 工作区还没暂存的改动
git diff --cached   # 已经暂存、马上要提交的改动
```

### 读懂 git status 的字母

| 标记 | 含义 |
|------|------|
| `M` | 已跟踪文件被修改，未暂存 |
| `A` | 新文件已暂存 |
| `AM` | 新文件已暂存，但暂存后又改过，需要重新 `git add` |
| `??` | 未跟踪的新文件 |
| `!!` | 被 .gitignore 忽略 |

### 按“一件事”暂存

```bash
# 只加这次提交需要的路径，不要把整个仓库一把梭
git add 303-Java/006-Mybatis/heima/fufu-mybatis-d03
git add 303-Java/006-Mybatis/heima/CLAUDE.md

git diff --cached
git commit -m "feat(mybatis): 增加 BrandMapper 动态代理与结果映射练习"
```

`git add .` 不是不能用，但**必须先 `git status` + `git diff` 看一遍**，否则很容易把 `target/`、临时文件、密钥一起提交。

想只提交一个文件里的部分改动，用：

```bash
git add -p 303-Java/006-Mybatis/heima/fufu-mybatis-d03/src/main/java/com/fufu/mapper/BrandMapper.java
```

## 4. 本仓库当前的提交怎么拆

现在的 `git status` 大概是这样：

```text
 M 303-Java/006-Mybatis/heima/CLAUDE.md
AM 303-Java/006-Mybatis/heima/fufu-mybatis-d03/.../BrandMapper.java
AM 303-Java/006-Mybatis/heima/fufu-mybatis-d03/.../BrandMapper.xml
AM 303-Java/006-Mybatis/heima/fufu-mybatis-d03/.../BrandMapperTest.java
?? .agents/
?? CLAUDE.md
?? skills-lock.json
```

不要一次全提交，拆成两组：

```bash
# 第一组：MyBatis 练习
git add 303-Java/006-Mybatis/heima/fufu-mybatis-d03
git add 303-Java/006-Mybatis/heima/CLAUDE.md
git commit -m "feat(mybatis): 增加 BrandMapper 动态代理与结果映射练习"

# 第二组：项目 skills 与约定
git add .agents CLAUDE.md skills-lock.json
git commit -m "docs(skills): 加入 Obsidian 笔记 skills 与项目约定"
```

`AM` 的文件说明暂存后又被 IDE 改过，提交前再 `git add` 一次，确保提交的是最终版本。

## 5. 分支怎么用

日常学习可以直接在 `main` 上提交，不必每次开分支。以下情况再开：

- 大重构、可能失败、想保留退路
- 一次实验性改动，不确定要不要
- 想边写边对照

```bash
git switch -c feat/mybatis-d03
# 写代码、提交
git switch main
git merge --no-ff feat/mybatis-d03
```

分支名也用 `type/scope` 格式：`feat/mybatis-d03`、`fix/springmvc-encoding`。

## 6. Push

```bash
git push origin main
```

本仓库的 `origin` 配了多个地址，先用这个命令确认：

```bash
git remote -v
git push --dry-run origin main
```

如果输出里有 GitHub 和 Gitee 两个 push 地址，`git push origin main` 会按配置逐个推送；任何一处失败都要看报错，别只看最后一行。

只想推某一个远端时，用显式地址：

```bash
git push git@gitee.com:F_chengzheng/300-learn-part-project.git main
git push git@github.com:musededu25-debug/300-LearnPartProject.git main
```

推送前确认：**没有密钥、没有 target、没有 .idea**。

## 7. 不该提交的东西

本仓库的 `.gitignore` 已经忽略了大部分：

```text
.idea/  *.iml  out/  target/
*.class  *.log  *.jar  *.war
```

还需要注意：

- **数据库密码**：`db.properties`、`druid.properties` 这类文件里有 `passWord` / `password` 字段，而且目前是被 Git 跟踪的。
  学习库的密码即使不敏感，也不要养成提交习惯。推荐改成：
  - 提交 `db.properties.example`（只有 key，没有真实密码）
  - 本地用 `db.local.properties`，并加进 `.gitignore`
  - 如果真实密码已经进过历史，换掉密码，而不是只删文件
- **IDE 自动生成**：`.idea/`、`*.iml`、`target/`
- **构建产物**：`*.class`、`*.jar`、`*.war`
- **临时文件**：日志、`*.bak`、导出的 SQL 备份
- **AI / 工具的本地缓存**：已经被 `.gitignore` 忽略的 `.claude/` 等

## 8. 改错了怎么办

| 场景 | 命令 | 注意 |
|------|------|------|
| 还没 commit，想撤销暂存 | `git restore --staged <file>` | 工作区改动保留 |
| 还没 commit，想丢弃改动 | `git restore <file>` | **改动直接没了** |
| 刚 commit，想改说明 | `git commit --amend` | 只对**未 push**的提交安全 |
| 刚 commit，漏了文件 | `git add <file>` + `git commit --amend --no-edit` | 同上 |
| 已 push，想撤销 | `git revert <hash>` | 生成反向提交，不改历史 |
| 想看某次提交改了什么 | `git show <hash>` | 排查问题最常用 |

不要对已经 push 的 `main` 做 `git reset --hard` 再强推；用 `git revert`。

## 9. 每次提交的检查清单

- [ ] 这次提交只做了一件事
- [ ] message 有 `type(scope): subject`，不是 `mac` / `afternoon` / `1`
- [ ] `git diff --cached` 看过，没有误加文件
- [ ] 没有 `target/`、`.idea/`、`*.class`、日志
- [ ] 没有密码、token、私钥
- [ ] 需要时补了 body（为什么 + 做了什么 + 怎么验证）
- [ ] push 后 `git status` 干净

## 10. 直接抄的模板

```text
<type>(<scope>): <不超过 50 字的主题>

为什么：
- ...

做了什么：
- ...

验证：
- ...
```

最小可用版本：

```bash
git add <本次相关的路径>
git commit -m "feat(mybatis): 完成 BrandMapper 单表 CRUD 练习"
git push origin main
```
