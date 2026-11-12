# heima/ — 黑马 MyBatis 课程学习区

> **给 Claude 的话**：先读本文件（1 分钟）建立上下文，再按需打开相关文件。
> 不要扫描 `target/`、`.mvn/`，也不要跨目录翻其他课程（001-JavaSE、002-JavaWeb、005-SpringMVC）。
> 只有用户明确说要复习 SpringMVC 时，才去读 `303-Java/005-SpringMVC/heima/CLAUDE.md`。

## 1. 这个目录是干什么的

存放跟着黑马新版课程学习 **MyBatis** 的工程，之后还会在这里进入 **SSM 整合**，再往后是 Spring Boot。

- **用户身份**：Spring 初学者。学过老版 Spring（Spring 5 概念）和 Spring 6 / SpringMVC；
  **没有 Servlet 基础，也没有 JDBC 基础**；Java 集合与泛型不熟（用户自述“快忘完了”）。
- **学习目标**：逐个吃透知识点，不是快速出活；理解“配置在解决什么问题”，为 Spring Boot 打底。
- **讲解要求**：
  - 全程中文，代码注释也中文
  - 先讲“为什么”再讲“怎么写”
  - **不引入用户还没学过的技术**；必须用新东西时先说明它是什么、属于哪一层
  - 术语要分清来源：**MyBatis 的 / JDBC 规范的 / Spring 的**
  - 证据给“跑出来的输出”（控制台 SQL、查询结果、异常栈的关键行），不要甩源码截图或字节码推导
- **薄弱点处理**：MyBatis 里 `List<Book>`、`Map<String,Object>`、泛型 Mapper 到处是。
  遇到用户卡住，用 2 分钟的具体例子补一下集合/泛型，不要展开讲泛型理论。
- **工作方式**：用户自己建工程、自己写代码；Claude 只出题 + 检查，不代写练习。

## 2. 当前位置与学习路线

```
Spring 5 概念 → SpringMVC（已学完，005 目录保留复习）
        → MyBatis（当前阶段，本目录）
        → SSM 整合（Spring + SpringMVC + MyBatis）
        → Spring Boot
```

**贯穿整个阶段的一句话**：现在手写的 `mybatis-config.xml`、数据源、事务、Mapper 注册，
就是 Spring Boot 以后用 `application.yml` + starter 帮你省掉的东西。理解比能跑更重要。

交接说明在 `HANDOFF-from-springmvc.md`，只在需要回顾上一阶段结论时看，不必每次通读。

## 3. 固定技术栈（不要擅自改动）

| 项 | 版本 | 备注 |
|---|---|---|
| 编译级别 | Java 17 | 本机 JDK 27，与 SpringMVC 阶段一致 |
| 构建 | Maven 3.10.0 | 普通 jar 工程，不是 war |
| MyBatis | 3.5.19 | 课程若指定其他 3.5.x，以课程为准 |
| MySQL 驱动 | `com.mysql:mysql-connector-j` 8.4.0 | LTS 版；驱动类 `com.mysql.cj.jdbc.Driver` |
| 日志 | logback-classic 1.5.38 | 看 MyBatis 执行的 SQL |
| 测试 | JUnit 5.14.4 | `src/test/java`，用 `@Test` |
| IDE | IntelliJ IDEA 2026.3 Ultimate | 自带 Database 工具 |
| 数据库 | 远端 MySQL | 经**本机 3307** 端口转发（不是 3306）；本机无 mysql CLI / Docker |

升级 / 降级版本前先问用户；课程明确要求某个版本时，按课程改，并在本节登记。

## 4. 学习目标（MyBatis 阶段）

按“能自己说出来、能自己写出来”验收，不以“能跑”为唯一标准：

1. **说清 MyBatis 省了什么**：连接获取与关闭、Statement 创建、参数设置、结果集到对象的映射。
   用户没有 JDBC 基础，只做“手工 JDBC vs MyBatis”的对比，不深挖 JDBC 底层。
2. **核心配置**：会写 `mybatis-config.xml` 的 `environments` / `transactionManager` / `dataSource` / `mappers`，
   会用 `jdbc.properties` 抽离连接信息。
3. **SqlSession**：理解 `SqlSessionFactory` 全局一个、`SqlSession` 每次操作一个；
   知道 `openSession()` 默认**不自动提交**，增删改要 `commit()` 或 `openSession(true)`。
4. **Mapper 两种写法**：XML（`namespace`、`id`、`parameterType`、`resultType`）和注解
   （`@Select` / `@Insert` / `@Update` / `@Delete`），知道各自适用场景。
5. **参数传递**：单参、多参 `@Param`、POJO、`Map`、集合；理解 `#{}`（预编译占位符）与 `${}`（字符串拼接）的区别和注入风险。
6. **结果映射**：`resultType`、`resultMap`、列名与属性名不一致、下划线转驼峰（`mapUnderscoreToCamelCase`）。
7. **动态 SQL**：`<if>`、`<where>`、`<set>`、`<foreach>`、`<sql>` / `<include>`。
8. **会排查**：看到 `Invalid bound statement (not found)`、参数找不到、字段全 `null` 时，知道按什么顺序定位。
9. **了解层面**（课程讲到再验收）：一级 / 二级缓存、分页（PageHelper 或课程方案）。
10. **为 SSM 整合铺垫**：能说清“MyBatis 自己管 SqlSession”与“交给 Spring 管”的区别；
    知道 `mybatis-spring` 存在即可，不提前展开。

**不要求**：手写连接池、JDBC 底层原理、MyBatis 源码、MyBatis-Plus。

## 5. 目录与命名规范

| 目录 | 含义 |
|---|---|
| `fufu-mybatis-dNN` | 跟课程敲的原始工程，`dNN` = 课程第 NN 天/第 NN 个知识点 |
| `fufu-mybatis-pNN` | **自建练习工程**，NN 从 01 递增，一个练习 = 一个独立工程 |

- **一个知识点 = 一个独立工程**，互不干扰。
- 本阶段是 **Maven jar 工程**，不启动 Tomcat；验证靠 JUnit5 测试（或课程要求的 `main` 方法）。
- 新增知识点：在 `heima/pom.xml` 的 `<modules>` 里加一行，再新建 `fufu-mybatis-dNN` 子模块；
  子模块 pom 只写 `parent` / `artifactId` / `packaging` / `name`（需要干净 jar 名再加 `finalName`），
  依赖和版本全部由父工程提供，不要重复声明。
- 通用文件放 `heima/` 根目录，不放进子模块：
  - `pom.xml`：聚合父工程 `fufu-mybatis-parent`；
  - `logback-template.xml`：日志模板，复制到具体工程的 `src/main/resources/logback.xml` 后才生效
    （Logback 只自动加载 `logback.xml` / `logback-test.xml`，文件名不对等于没配）。

标准结构（课程讲到哪层就建到哪层，不提前建）：

```
src/main/java/com/fufu/
├── pojo/        # 实体类，与表字段对应（手写 getter/setter/无参构造）
├── mapper/      # Mapper 接口
├── service/     # 业务层（课程讲到再加）
└── utils/       # MyBatisUtil 等工具类（课程讲到再加）
src/main/resources/
├── mybatis-config.xml
├── jdbc.properties     # 连接信息，账号密码只放这里
├── logback.xml         # 控制台 SQL 日志（模板见 heima/logback-template.xml）
└── com/fufu/mapper/    # Mapper XML（也可与接口同包，二选一，全工程统一）
src/test/java/com/fufu/
└── ...MapperTest.java  # JUnit5 测试
```

**模板里不能删的两块**：

```xml
<resources>   <!-- 让 src/main/java 下的 Mapper XML 进入 target/classes -->
<maven-surefire-plugin>  <!-- JUnit5 测试能被执行 -->
```

## 6. 练习规则（沿用 SpringMVC 阶段约定）

- 用户自己建工程、自己写代码；Claude **只出题 + 验收**，不代建、不代写答案。
- 每次练习交付 = **题目 + 验收标准**（验收标准要能实际检查：预期 SQL、预期输出、异常消失等）。
- 只用用户**已经学过**的概念出题；没讲到的内容放进“预告”，等课程讲完再做。
- 用户完成后，Claude 检查并在下表登记结果；不预填、不虚构进度。

| 编号 | 主题 | 对应课程内容 | 状态 |
|---|---|---|---|
| p01 | （用户开始第一个练习时填写） | | |

## 7. 代码约定

- 包名统一 `com.fufu.xxx`。
- 类顶部用中文注释标出“第几步 / 这个类解决什么问题”。
- 实体类**手写** getter / setter / 无参构造；**暂不用 Lombok**（课程讲到再单独加，加之前先问用户）。
- Mapper 接口与 XML：`namespace` = 接口全限定名，XML 的 `id` = 方法名；同包或按 `mapper-locations` 放置，全工程统一一种方式。
- SQL 日志的 logger 名：XML 方式 = `namespace`（`namespace.id` 是它的子 logger），接口方式 = 接口全限定名。
  改 `namespace` 时必须同步改 `logback.xml` 的 `<logger name>`，否则 SQL 日志不打印。
- 入门 demo 阶段（d01）暂时是 `namespace="test"` + `selectList("test.selectAll")`；
  课程引入 Mapper 接口后，`namespace` 必须改成接口全限定名，logger 配置同步切到 `com.fufu.mapper`。
- SQL 关键字大写，参数用 `#{}`；`${}` 只在课程明确讲的场景（如动态表名/排序）使用，并说明风险。
- 测试类放 `src/test/java`，用 JUnit5；测试要可重复执行（自己准备/清理数据），不要依赖执行顺序。
- 数据库密码不写进代码、不写进本文件；放 `jdbc.properties`。

## 8. 数据库与环境

- 连接地址：`127.0.0.1:3307`（**不是 3306**，本机端口转发到远端 MySQL）。
- URL 模板：

```text
jdbc:mysql://127.0.0.1:3307/你的库名?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8
```

- 驱动类：`com.mysql.cj.jdbc.Driver`。
- 账号 / 密码只放本地 `jdbc.properties`，**本文件不记录**。
- 本机没有 `mysql` 命令行，也没有 Docker；用 IDEA 的 Database 工具或图形客户端建库、建表、看数据。
- **动手写代码前先确认**：3307 能连上、目标库和表已经建好。连不上先解决环境，不要先怀疑 MyBatis。
- 2026-11-12 已确认：本机 3307 可连接，库 `mybatis` / 表 `tb_user` 可查询。

## 9. 常见坑（MyBatis 专属）

| 现象 | 原因 | 处理 |
|---|---|---|
| `Invalid bound statement (not found)` | ① `namespace` 不是接口全限定名；② XML 的 `id` 与方法名不一致；③ XML 没被复制到 `target/classes`；④ `<mappers>` 没注册；⑤ `mapper-locations` 路径不匹配 | 按文末排查顺序逐条查 |
| `src/main/java` 下的 XML 在 `target` 里找不到 | Maven 默认只复制 `src/main/resources` | 模板的 `<resources>` 块必须保留；改完执行 clean / rebuild |
| `Parameter 'xxx' not found. Available parameters are [arg0, arg1, param1, param2]` | 多参数方法没有 `@Param`，MyBatis 只能看到 `arg0/param1` | 每个参数加 `@Param("业务名")`；或封装成 POJO |
| 查询结果对象字段全是 `null` | 列名与属性名不一致（如 `user_name` vs `userName`） | 用 `resultMap`，或开启 `mapUnderscoreToCamelCase` |
| 插入/更新“成功”但库里没数据 | `openSession()` 默认不自动提交 | 调 `sqlSession.commit()`，或 `openSession(true)` |
| `Public Key Retrieval is not allowed` | MySQL 8 默认认证方式 + 未允许公钥检索 | URL 加 `allowPublicKeyRetrieval=true` |
| `The server time zone value 'CST' is unrecognized` | 时区未指定 | URL 加 `serverTimezone=Asia/Shanghai` |
| `Communications link failure` | 3307 转发没开、数据库没启动、端口写错 | 先测端口/连接，再查代码 |
| `Access denied for user ...` | 账号密码错、库名错、连错端口 | 核对 `jdbc.properties` 与 3307 |
| `ClassNotFoundException: com.mysql.cj.jdbc.Driver` | 驱动依赖 scope 写成 `test` / `provided`，或 Maven 没刷新 | 驱动保持默认 compile scope；刷新 Maven |
| `TooManyResultsException` | 用 `selectOne` 查出了多条 | 改 `selectList`，或补唯一条件 |
| `There is no getter for property named 'xxx'` | `#{xxx}` 与参数对象的属性名不一致 | 对齐属性名，或改用 `@Param` |
| `Result Maps collection already contains value for ...` | `resultMap` 的 id 重复 | 全工程唯一，或按 namespace 隔离 |
| 测试一个都没跑 / 不被识别 | 测试类不在 `src/test/java`、没加 `@Test`、JUnit 依赖 scope 错、surefire 未生效 | 检查目录、注解、依赖；保留模板 surefire 配置 |
| 控制台看不到 SQL | ① 文件没放 `src/main/resources` 或没改名为 `logback.xml`；② logger 名与 `namespace` 不一致；③ 没 rebuild，`target/classes` 里还是旧的 | 放对位置并改名；logger 名对齐 namespace；`mvn clean package` 或 Rebuild Project |
| 日志没有颜色 | 用的还是 Logback 默认配置（没有 `%highlight`），说明 `logback.xml` 没生效 | 确认 `src/main/resources/logback.xml` 存在，并已复制进 `target/classes` |
| 中文乱码 | URL 没带字符集 / 库表字符集不对 | URL 加 `characterEncoding=utf8`，库表用 utf8mb4 |
| 改了 pom 依赖不生效 | IDEA 没重新导入 Maven | 刷新 Maven 项目（Reload All Maven Projects） |

**排查顺序（遇到 `Invalid bound statement` 按这个走）：**

1. 看 `target/classes` 对应路径下有没有那个 XML（没有 → 模板 `<resources>` 丢了 / 没 rebuild）
2. 看 XML 的 `namespace` 是否等于接口全限定名，`id` 是否等于方法名
3. 看 `mybatis-config.xml` 的 `<mappers>` 是否注册了这个接口/XML
4. 看 `mapper-locations`（若使用）路径是否能匹配实际位置
5. 最后才怀疑版本、驱动、数据库

## 10. 与 SpringMVC 阶段的关系

- 本阶段**主线完全跟课程自带的表走**（用户已确认），不强行套用 SpringMVC 的 `Book`。
- 可选综合练习：课程把 XML 和注解都讲完后，可把 SpringMVC p02 的内存版 `BookService` 改成 MyBatis 调用，
  Controller 和前端接口约定不动 —— 用来验证“分层”和集合/泛型。**可选，不占主线进度。**
- 需要 SpringMVC 的具体结论（接口约定、Tomcat 配置、已踩的坑）时，读
  `303-Java/005-SpringMVC/heima/CLAUDE.md`，不在本文件重复。

## 11. 待定 / 后续再补

用户暂时还没想到、等课程推进后再定的点，先记在这里，避免 Claude 自行发挥：

- 课程章节与工程编号的对应关系（开课后补，按实际章节登记）
- 数据库库名、表结构、测试数据（由用户按课程准备；本文件不记录账号密码）
- 分页方案：课程用 PageHelper 还是手写 `LIMIT`（讲到再定）
- SSM 整合阶段：用注解配置还是 XML 配置（到时按课程定）
- MyBatis-Plus 是否单独建目录（等 Spring Boot 阶段再定）
- 是否引入 Lombok（课程讲到时先问用户）

## 12. 学习记录

| 日期 | 工程 | 主题 | 结果 / 备注 |
|---|---|---|---|
| 2026-11-12 | fufu-mybatis-d01 | MyBatis 入门：核心配置 + SqlSession + XML Mapper 查询 | 跑通 `selectList("test.selectAll")`，查出 `tb_user` 3 条；彩色日志配置生效 |
