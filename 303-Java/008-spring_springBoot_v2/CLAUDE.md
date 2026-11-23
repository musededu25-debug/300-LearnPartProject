# 008-spring_springBoot_v2 · Spring 7 重塑与 Spring Boot 4 进阶

> **给 AI 的话**：先读本文件建立上下文，再按需打开具体代码文件。不要扫描 `target/`，也不要跨目录翻旧练习（`003-Maven`、`004-springAndSpringBoot` 等）。

## 1. 定位与学习路线

本目录用于**系统重塑 Spring 核心机制**，并沿既定路线平滑推进至 Spring Boot 与 MyBatis-Plus。当前阶段以**纯 Spring 7 为主**。

```text
Spring 7 核心重塑（当前：fufu-spring-core，短平快 3–5 天）
  3 组极简验证实验吃透内核：IoC/生命周期 → 动态代理与 AOP → 声明式事务
        ↓
SSM 整合（轻量化 1–2 天，纯注解跑通 1 张表即止，不做大项目）
  看清父子容器分工、SqlSessionFactoryBean/@MapperScan、数据源与事务串联
        ↓
Spring Boot 4（🔥 核心主战场，后续新增 fufu-springboot-* 子模块）
  自动配置原理 → Starter → application.yml → Web / 整合 MyBatis / 测试打包
        ↓
MyBatis-Plus（⚡ 提效阶段）
  BaseMapper / LambdaQueryWrapper / 分页 / 逻辑删除（复杂联查回退原生 MyBatis XML）
```

- **学习方式（重要）**：**不跟着老课件一步一步抄大段代码**，也不只看不练；用**极简验证实验（Mini-Lab）+ JUnit 6 测试**把 Spring Boot 底层依然在跑的核心机制吃透：
  - **🔥 必手敲**：注解驱动与构造器注入 + Bean 生命周期（`@PostConstruct`/`@PreDestroy`/`BeanPostProcessor`）；极简 JDK 代理 + `@Aspect`/`@Around` + **AOP 自调用失效**；`JdbcTemplate` 极简转账 + `@Transactional` 回滚、**事务失效场景**、`REQUIRED` vs `REQUIRES_NEW`。
  - **🟡 看懂思路即可（不照敲）**：XML 配 `<bean>`/`<aop:config>`/`<tx:advice>`、`JdbcTemplate` 复杂 CRUD、编程式事务 `TransactionTemplate`。
  - **❌ 直接跳过**：`c3p0`、`commons-dbutils`、JUnit 4、旧课 2.39–2.45 的 Spring 5 旧“新特性”与 WebFlux。
- **讲解与协助约定**：中文交流与中文注释；先讲「为什么」再讲「怎么写」；不主动代写练习答案，以出题（题目 + 验收标准）、排查、验收为主。

### Spring 阶段极简实验清单（进度登记）

| 编号 | 实验主题 | 核心验收点 | 状态 |
|------|----------|------------|------|
| Lab 01 | IoC 注解驱动 + 构造器注入 + Bean 生命周期 | `@Configuration`/`@Bean`、`@PostConstruct`/`@PreDestroy`、`BeanPostProcessor` 执行顺序、循环依赖现象 | ⏳ 待开始 |
| Lab 02 | 动态代理 + 注解式 AOP + 自调用失效 | 手写极简 JDK 代理、`@Aspect` + `@Around` 统计耗时、复现并解释 `this.xxx()` 自调用绕过代理 | ⏳ 待开始 |
| Lab 03 | 声明式事务 `@Transactional` + 传播行为与失效 | 极简转账正常提交与异常回滚、复现自调用/非 public/吞异常失效、对比 `REQUIRED` 与 `REQUIRES_NEW` | ⏳ 待开始 |

---

## 2. 工程形态（多模块聚合父工程）

- 根目录 `pom.xml` 是聚合父工程（`com.fufu:fufu-spring-springboot-parent`，`packaging=pom`）。
- **版本隔离原则**：根 `pom.xml` 只在 `<dependencyManagement>` 中统一管理版本与插件，**不直接声明 `<dependencies>`**；这样纯 Spring 子模块与后续 Spring Boot 子模块互不污染。
- **当前模块**：`fufu-spring-core`（一个模块内按专题分包，避免每个小节建一个 Maven 模块）。
- **后续进军 Spring Boot**：在根 `pom.xml` 的 `<modules>` 新增 `fufu-springboot-*` 子模块，独立使用 Spring Boot 4 的父工程或 BOM。

推荐包结构（用到再建，不提前建空包）：

```text
008-spring_springBoot_v2/
├── pom.xml                       # 聚合父工程（版本与插件统一管理）
├── CLAUDE.md
└── fufu-spring-core/             # 纯 Spring 7 核心学习模块
    ├── pom.xml
    └── src/
        ├── main/java/com/fufu/spring/
        │   ├── ioc/              # 第 1–2 章：解耦推演、IoC 容器、Bean 生命周期与作用域、循环依赖
        │   ├── annotation/       # 第 3 章：@Component 全家桶、@Configuration/@Bean、构造器注入、@Resource
        │   ├── aop/              # 第 4 章：JDK/CGLIB 代理、@Aspect、五种通知、自调用失效
        │   └── tx/               # 第 5 章：JdbcTemplate、@Transactional、传播行为、事务失效场景
        ├── main/resources/
        │   ├── logback.xml       # 彩色控制台日志（含按需开启的事务/SQL logger）
        │   └── jdbc.properties   # 本地数据库连接信息（账号密码只放这里，不进文档）
        └── test/java/com/fufu/spring/  # 与 main 镜像的 JUnit 6 测试类
```

---

## 3. 固定技术栈（2026-11 实效基线）

| 项 | 版本 | 说明 |
|---|---|---|
| JDK / 编译级别 | **JDK 21 LTS** | `maven.compiler.release=21`，开启 `maven.compiler.parameters=true` |
| 构建工具 | Maven 3.10.2 | `compiler-plugin 3.16.0` / `surefire-plugin 3.6.0` |
| Spring Framework | **7.0.11** | 通过 `spring-framework-bom` 统一管理（`spring-context` / `spring-jdbc` / `spring-test`） |
| MyBatis + 整合包 | **3.6.0** + **4.1.0** | `org.mybatis:mybatis` + `mybatis-spring`（4.x 适配 Spring 7） |
| Jakarta Annotations | **3.0.0** | `jakarta.annotation-api`（JDK 11+ 已移出 `javax.annotation`） |
| AOP | **AspectJ 1.9.27** | `aspectjweaver`；CGLIB 由 `spring-core` 内置，**禁止引入老 `cglib`** |
| 连接池 | **HikariCP 7.1.0** | 替代老课件 `c3p0`，与 Spring Boot 默认保持一致 |
| MySQL 驱动 | **8.4.0** | `com.mysql:mysql-connector-j`，驱动类 `com.mysql.cj.jdbc.Driver` |
| 日志 | **Logback 1.5.38** | SLF4J + Logback，Spring 7 通过 `spring-jcl` 自动桥接 |
| 测试 | **JUnit 6.1.3** | 通过 `junit-bom` 管理；兼容 JUnit 5 Jupiter 注解，**禁止引入 JUnit 4** |
| 数据库 | 远端 MySQL | 经**本机 `127.0.0.1:3307`** 端口转发（不是 3306）；本机无 mysql CLI / Docker |

> 升级或新增依赖前先确认，不擅自混入 `spring-boot-starter-*` 到 `fufu-spring-core`。

---

## 4. 关联笔记（Obsidian）

总规则以仓库根 `CLAUDE.md` 为准（先走路由，不扫库）：

- 新库索引（`version_2`）：
  - `01-learn/02-Framework/01-Spring/00-INDEX-Spring.md`
  - `01-learn/02-Framework/03-SpringBoot/00-INDEX-SpringBoot.md`
- 旧库参考（只读，按需迁移）：
  - `通用/100-编程相关/102-学习笔记/011-SSM/02-Spring/00-Spring笔记规范与索引.md`（1–6 章主线与 2020→2026 差异）
  - `通用/100-编程相关/102-学习笔记/011-SSM/02-Spring/课表.md`

**重点攻克（面试高频 🔥🔥🔥）**：
1. IoC / DI 本质、Bean 生命周期扩展点、作用域、循环依赖与三级缓存
2. 构造器注入 vs 字段注入、`@Autowired` vs `@Resource`、`@Configuration` 代理机制
3. JDK 动态代理 vs CGLIB、AOP 核心术语与五种通知、AOP 自调用失效
4. `@Transactional` 声明式事务、7 种传播行为、常见失效场景（自调用、非 public、异常被吞、非 RuntimeException 等）

---

## 5. 代码与测试约定

- 包名统一 `com.fufu.spring.<topic>`；类顶部用中文注释说明解决的问题。
- **优先使用构造器注入**（Spring 官方推荐），字段注入仅作对比了解。
- 使用 `jakarta.annotation.*`（`@Resource` / `@PostConstruct` / `@PreDestroy`），**不用 `javax.annotation.*`**。
- 测试统一放在 `src/test/java`，使用 **JUnit 6 + Spring 7** 整合（参考 `Test02`）：
  - 用 **`@SpringJUnitConfig(SpringConfig.class)`**（组合注解，等价于 `@ExtendWith(SpringExtension.class)` + `@ContextConfiguration(classes = SpringConfig.class)`）
  - **不再使用**老教程里的 JUnit 4 写法 `@RunWith(SpringJUnit4ClassRunner.class)`（JUnit 5/6 已移除 `@RunWith`，Spring 7 也已弃用 JUnit 4 支持）
  - 测试类里直接用字段 `@Autowired` 注入 Bean（无需手动 `new AnnotationConfigApplicationContext`）；测试方法用 `org.junit.jupiter.api.Test` + `@DisplayName("中文场景描述")`
- 数据库账号密码只放本地 `jdbc.properties`，不写进代码或文档。

常用命令（在 `008-spring_springBoot_v2` 根目录执行）：

```bash
mvn test                                    # 跑全部测试
mvn -pl fufu-spring-core test               # 只跑指定子模块
mvn -pl fufu-spring-core -Dtest=XxxTest test  # 只跑单个测试类
```

---

## 6. 常见坑速查（含已踩过的坑）

| 现象 | 原因 | 处理 |
|---|---|---|
| `@RunWith` 报红 / 找不到类 | 教程是 JUnit 4 写法，本工程使用 JUnit 6（Jupiter） | 改用 `@SpringJUnitConfig(SpringConfig.class)` 或 `@ExtendWith(SpringExtension.class)` |
| `javax.annotation.*` 找不到 | JDK 11+ 已移除 `javax.annotation` | 改用 `jakarta.annotation.*`（已配 `jakarta.annotation-api 3.0.0`） |
| `NoSuchBeanDefinitionException` 找不到自己的 Service / Dao | `@ComponentScan` 被注释掉或包路径没覆盖到目标类 | 检查主配置类 `SpringConfig` 的 `@ComponentScan("com.fufu.spring")` |
| `@PropertySource` 找不到 `jdbc.properties` | 前缀写成了驼峰 `classPath:` | Spring 只认全小写 **`classpath:jdbc.properties`** |
| HikariCP 建连失败 / 找不到驱动 / 字符集报错 | ① `jdbc.url` 漏写 `jdbc:` 协议头；② URL 参数被截断（如 `characterEncoding=u`）；③ 驱动类写成老版 `com.mysql.jdbc.Driver`；④ 端口写成 3306 | 驱动用 `com.mysql.cj.jdbc.Driver`；端口用 `127.0.0.1:3307`；URL 完整带 `jdbc:mysql://127.0.0.1:3307/mybatis?serverTimezone=Asia/Shanghai&useSSL=false&allowPublicKeyRetrieval=true&characterEncoding=utf8` |
| `mybatis-spring` 版本不兼容 Spring 7 | 误引了适配 Spring 6 的 `3.0.x` | Spring Framework 7.0+ 必须使用 **`mybatis-spring 4.x`**（当前 `4.1.0`） |
| `SqlSessionFactoryBean` 别名包不生效 | 误调用了 `setTypeHandlersPackage(...)` | 实体类别名包应调用 **`setTypeAliasesPackage("com.fufu.spring.pojo")`** |
| 控制台警告 `No MyBatis mapper was found` | `MapperScannerConfigurer` 扫描的包下还没有 Mapper 接口 | 在 `com.fufu.spring.mapper` 下建立 Mapper 接口后自动消失 |
| 构造器/方法参数名报错 | 缺少 `-parameters` 编译参数 | 父 POM 已配 `maven.compiler.parameters=true`，执行 `mvn clean test-compile` |
| AOP / `@Transactional` 不生效 | 同类内部 `this.method()` 自调用绕过了代理对象，或方法非 `public` | 通过代理对象调用 / 拆到另一个 Bean；确保事务方法为 `public` |
| JDK 21 下手动引 `cglib` 报 `InaccessibleObjectException` | 老 `cglib 3.3.0` 不兼容高版本 JDK 模块封装 | 不要引外部 `cglib`，直接使用 Spring 内置代理 |

---

## 7. 学习记录（供后续整理 Obsidian 笔记）

| 日期 | 里程碑 | 验证结果 / 要点 |
|---|---|---|
| 2026-11-23 | 搭建多模块父工程 + Spring 7 整合 HikariCP + MyBatis 3.6 + JUnit 6 | `Test02` 实测通过：成功连接远端 MySQL 8.4.11（`127.0.0.1:3307/mybatis`），`UserMapper.selectAll()` 查出 `tb_user` 共 3 条并输出彩色 SQL 日志；踩坑与版本对照已记入第 5、6 节 |
