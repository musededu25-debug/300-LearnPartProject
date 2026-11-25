# 010-SpringBoot · Spring Boot 4 学习（课程章节对照）

> **给 AI 的话**：先读本文件建立上下文，再按需打开具体代码。不要扫描 `target/`，不要跨目录翻 `008-spring_springBoot_v2` 的纯 Spring 练习；Spring Boot 相关一律在本目录。

## 1. 定位与分工（重要）

- **本目录只做 Spring Boot**；`008-spring_springBoot_v2` 只保留纯 Spring 7 核心与 SSM 整合，两者互不依赖。
- **学习方式：跟本地黑马 SpringBoot 课程按章节走**，但**代码一律按 Spring Boot 4 写**，不照抄课程里 Boot 2 的旧代码。每章由 AI 给出「课程原写法 → Boot 4 写法」对照，并负责编译/运行/排错。
- **讲解与协助约定**：中文交流、中文注释；先讲「为什么」再讲「怎么写」；AI 负责出题、验收和排错，不代写整章练习答案。
- **课程资料（只读，位于项目外）**：
  - 源码：`/Users/m/Home/000-WorkHome/000-收集箱/新建文件夹/SSM资料/04-springboot.rar`
    - 解压后模块：`springboot_01_quickstart` … `springboot_09_ssm`
  - 笔记：`/Users/m/Home/000-WorkHome/000-收集箱/新建文件夹/SSM资料/课程笔记.rar`
    - 内含 `基础框架8笔记/SpringBoot笔记/SpringBoot.md`（第 1–5 章）
    - 以及 `基础框架8笔记/Mybatisplus笔记/MyBatisPlus.md`（后续阶段用）
  - 本机没装解压工具时可用 `lsar 文件.rar` 查看目录、`unar 文件.rar` 解压。

### 课程章节 → 本项目落地（进度登记）

> 章节名以本地笔记 `SpringBoot.md` 的一级/二级标题为准。完成一节就更新状态。

| 章 | 课程内容 | 本项目落地 | 状态 |
|---|---|---|---|
| 1 | SpringBoot 简介 + 快速入门（起步依赖、内嵌 Tomcat、`@RestController`） | `fufu-springboot-demo1` 已跑通 + 新版 IDEA 建模块验证 | ✅ 第一遍过关（待复习） |
| 2 | 配置文件：properties / yaml、读取数据（`@Value` / `@ConfigurationProperties`）、多环境、配置文件分类 | 已在 `demo1` 对照并独立在 `demo2` 跑通 YAML + `record` + `@ConfigurationPropertiesScan` + `@Value` | ✅ 第一遍过关（待复习） |
| 3 | SpringBoot 整合 JUnit | `demo1` 已有 `@SpringBootTest` 示例；概念已通读，切片测试待结合 `05-JUnit` 补跑 | 🟡 学习中 |
| 4 | SpringBoot 整合 MyBatis（含 Druid 数据源） | 新建独立子模块（需远端 MySQL `127.0.0.1:3307`） | ⏳ 待开始 |
| 5 | 综合案例：图书管理（课程用 `tbl_book`，SSM 分层） | 新建 `fufu-springboot-case` 独立模块 | ⏳ 待开始 |
| 6+ | MyBatis-Plus（标准 CRUD、分页、条件查询、逻辑删除、代码生成器） | 新建模块，按 `MyBatisPlus.md` 推进 | ⏳ 待开始 |
| 补充 | 原理篇（自动配置原理、自定义 starter） | 笔记 `05-自动配置原理与自定义Starter.md` 已就位，代码按需实操 | ⏳ 待开始 |

---

## 2. 版本策略：课程是 Boot 2，本机用 Boot 4

**不要照抄课程的 Boot 2 代码**。Boot 2 已停止维护，课程里的 `javax.*` 在 JDK 21 上不可用；本工程统一使用：

| 项 | 课程（Boot 2.x） | 本工程（实测 2026-11-25） |
|---|---|---|
| Spring Boot | 2.x | **4.1.3** |
| Spring Framework | 5.x | **7.0.11**（由 Boot 管理，不手写版本） |
| JDK | 8 | **21** |
| 内嵌 Tomcat | 9.x | **11.0.27** |
| 测试 | JUnit 4 + `@RunWith` | **JUnit 6.0.3 + `org.junit.jupiter.api`** |
| Servlet / 注解包名 | `javax.servlet.*`、`javax.annotation.*` | **`jakarta.servlet.*`、`jakarta.annotation.*`** |
| MySQL 驱动 | `mysql:mysql-connector-java:5.1.x` | **`com.mysql:mysql-connector-j:8.4.0`** |
| 数据源 | Druid（裸依赖 + `type`） | Boot 默认 HikariCP；按课程学 Druid 时引 `druid-spring-boot-4-starter` 或裸 `druid`（见第 6 节） |

基础用法（`@SpringBootApplication`、starter、`application.yml`、分层、`@RestController`、`@SpringBootTest`）Boot 2 与 4 几乎一致，**不要因为版本不同就换一套写法**。

**Boot 4 生态可用性（已查 Maven Central）**：

| 组件 | Boot 4 对应版本 |
|---|---|
| MyBatis | `org.mybatis.spring.boot:mybatis-spring-boot-starter:4.1.0` |
| MyBatis-Plus | `com.baomidou:mybatis-plus-spring-boot4-starter:3.5.18` |
| Druid | `com.alibaba:druid-spring-boot-4-starter:1.2.28` |

> 新增依赖前先确认是否有 Boot 4 对应物；不确定就查 Maven Central，不要凭记忆写 2.x 版本。

---

## 3. 工程形态与目录约定

**先区分两个「模块」概念，课程里容易混**：

- **Maven 子模块**：一个可独立启动的 Spring Boot 应用，按章节/案例划分（本项目做法）。
- **包分层**：同一个应用内部的 `controller` / `service` / `mapper` / `pojo`，这是**代码分层**，不是 Maven 模块。

当前结构：

```text
010-SpringBoot/
├── CLAUDE.md
├── pom.xml                          # 聚合父工程，parent = spring-boot-starter-parent 4.1.3
└── fufu-springboot-demo1/           # 第 1 章：快速入门
    ├── pom.xml
    └── src/
        ├── main/java/com/fufu/boot/
        │   ├── FufuBootDemo1Application.java   # 启动类必须在根包
        │   ├── controller/HelloController.java
        │   └── service/HelloService.java
        ├── main/resources/application.yml
        └── test/java/com/fufu/boot/FufuBootDemo1ApplicationTests.java
```

约定：

- 包根 `com.fufu.boot`，启动类放最外层包；新增包必须是它的子包，否则 `@ComponentScan` 扫不到。
- 用到再建包（`mapper` / `pojo` / `config` / `exception` 等），不提前建空目录。
- 新章节需要不同依赖组合（MyBatis、Druid、MP）时，**新建子模块**并在父 `pom.xml` 的 `<modules>` 登记，不往 `demo1` 里堆。
- 子模块坐标：`com.fufu:fufu-springboot-<名字>`，版本继承父工程 `1.0-SNAPSHOT`。

---

## 4. 固定技术栈（2026-11 实测基线）

| 项 | 版本 | 说明 |
|---|---|---|
| JDK / 编译级别 | **21** | 父 POM 固定 `java.version=21`；本机 PATH 默认 java 是 27，编译仍按 21 |
| Maven | 3.10.2 | — |
| Spring Boot | **4.1.3** | 4.1 线最新补丁；4.2.0 刚发布，暂不追 |
| Spring Framework | 7.0.11 | 由 Boot 管理 |
| Web | `spring-boot-starter-web` | 内嵌 Tomcat 11 + Spring MVC + Jackson |
| 测试 | `spring-boot-starter-test` | JUnit 6 / AssertJ / Mockito，仅 test 作用域 |
| 数据库 | 远端 MySQL 8.4.11 | 经本机 **`127.0.0.1:3307`** 转发（不是 3306），库名按课程案例 |
| 连接池 | HikariCP（Boot 默认） / Druid（课程指定） | 课程第 4 章讲 Druid 时再引 |
| 日志 | Logback（Boot 默认） | 无需额外配置；日志级别写在 `application.yml` |

数据库账号密码只放本地配置（后续可建 `application-local.yml` 并加入 `.gitignore`），**不写进代码、文档或 CLAUDE.md**。

---

## 5. 常用命令（在 `010-SpringBoot` 根目录执行）

```bash
# 编译 + 跑全部测试
mvn test

# 只跑 demo1
mvn -pl fufu-springboot-demo1 test

# 启动 demo1（默认 8080）
mvn -pl fufu-springboot-demo1 spring-boot:run

# 打包成可执行 jar
mvn -pl fufu-springboot-demo1 package
java -jar fufu-springboot-demo1/target/fufu-springboot-demo1-1.0-SNAPSHOT.jar
```

- 本机严格按 JDK 21 运行时：`JAVA_HOME=/opt/homebrew/opt/openjdk@21 mvn ...`（Homebrew 安装，未注册到 `/Library/Java/JavaVirtualMachines`）。
- 实测启动耗时约 0.5 秒，Tomcat 监听 8080。

---

## 6. 课程代码 → Boot 4 改写速查

| 课程写法（Boot 2） | Boot 4 写法 |
|---|---|
| `import javax.servlet.http.HttpServletRequest;` | `import jakarta.servlet.http.HttpServletRequest;` |
| `import javax.annotation.Resource;` | `import jakarta.annotation.Resource;` |
| `@RunWith(SpringRunner.class)` + `@SpringBootTest` | **删掉 `@RunWith`**，只留 `@SpringBootTest` |
| `import org.junit.Test;` | `import org.junit.jupiter.api.Test;` |
| `import org.junit.Assert.*;` | `import org.junit.jupiter.api.Assertions.*;`（或 AssertJ） |
| `@SpringBootTest(classes = XxxApplication.class)` | 同样可用；启动类在根包时直接 `@SpringBootTest` 即可 |
| `mysql:mysql-connector-java` | `com.mysql:mysql-connector-j`（版本由 Boot 管理） |
| Druid 裸依赖 + `spring.datasource.type=com.alibaba.druid.pool.DruidDataSource` | 两种都行：① 保持裸 `druid` + `type`（最贴近课程）；② 换 `druid-spring-boot-4-starter`，配置前缀 `spring.datasource.druid.*` |
| `mybatis-spring-boot-starter:2.x` | `mybatis-spring-boot-starter:4.1.0` |
| `mybatis-plus-boot-starter:3.x` | `mybatis-plus-spring-boot4-starter:3.5.18` |
| `spring.redis.*`（旧课程） | Boot 3+ 起改为 `spring.data.redis.*` |
| `server.servlet.context-path` | 不变；但 Tomcat 11 对 URL 更严格（见第 7 节） |

---

## 7. 常见坑

| 现象 | 原因 | 处理 |
|---|---|---|
| `找不到 javax.servlet.*` / `javax.annotation.*` | 课程是 Boot 2，Boot 3+ 已迁到 Jakarta | 改成 `jakarta.*`，见第 6 节 |
| 测试类报 `@RunWith` 不存在 | JUnit 4 写法；Boot 4 默认 JUnit 6 | 删 `@RunWith`，用 `@SpringBootTest` + `org.junit.jupiter.api.Test` |
| 启动报 `Port 8080 was already in use` | 上一个进程没停 | `lsof -ti tcp:8080` 查 PID 后 `kill`，或改 `server.port` |
| `curl` 带中文参数返回 400 Bad Request | Tomcat 11 拒绝 URL 中未编码的非 ASCII 字符（实测） | 用 `curl --get --data-urlencode "name=小明" ...` 或浏览器访问 |
| 接口 404 | Controller 不在启动类子包下 / 路径拼错 | 确认包在 `com.fufu.boot` 下，检查 `@RequestMapping` |
| `No qualifying bean of type ...` | 类没加 `@Service`/`@Component`，或包没被扫描到 | 补注解 + 检查包路径 |
| 子模块 `mvn spring-boot:run` 找不到主类 | 启动类不在根包，或没配 `spring-boot-maven-plugin` | 启动类移到根包；子模块 `pom.xml` 声明该插件 |
| `mybatis-spring-boot-starter` 启动报兼容错误 | 引了 2.x/3.x | Boot 4 必须用 `4.x` |
| Druid 配置不生效 | 用了 starter 却按裸依赖的前缀写，或反之 | 二选一，前缀对应：starter 用 `spring.datasource.druid.*`；裸依赖用 `spring.datasource.type` |
| `@ConfigurationProperties` 绑不上 | 没加 `@EnableConfigurationProperties`/`@Component`，或字段名不匹配 | 加注解；kebab-case 与字段驼峰对应 |

---

## 8. 代码与测试约定

- 包名 `com.fufu.boot.<layer>`；类顶部中文注释说明「这个类解决什么问题」。
- **构造器注入优先**（Spring 官方推荐）；字段注入只用于测试类或对比讲解。
- 配置写 `application.yml`，不写 `application.properties`（课程两种都会讲，项目统一 yml）。
- 测试：JUnit 6 + `@SpringBootTest`；需要 HTTP 层测试时用 `@SpringBootTest(webEnvironment = RANDOM_PORT)` + `TestRestTemplate`，或 `@WebMvcTest` + `MockMvc`（用到再引入）。
- 数据库账号密码只放本地配置，不提交。

---

## 9. 关联笔记（Obsidian）

总规则以仓库根 `CLAUDE.md` 为准（先走路由，不扫库）：

- `01-learn/02-Framework/03-SpringBoot/00-INDEX-SpringBoot.md`（已建齐 01–05 章 + `98-附录-SpringBoot常用注解手册` + `99-速查表-SpringBoot`，全部对齐 Boot 4.1.3 时效）
- `01-learn/02-Framework/06-MyBatisPlus/00-INDEX-MyBatisPlus.md`（已建齐 01–05 章 + `98-附录-MyBatisPlus常用注解与API手册` + `99-速查表-MyBatisPlus`，对齐 MP 3.5.18 + Boot 4.1.3 + `mybatis-plus-jsqlparser` 分页拆分时效）
- 上游：`01-learn/02-Framework/01-Spring/00-INDEX-Spring.md`、`01-learn/05-Tools/05-JUnit/00-INDEX-JUnit.md`
- 课程旧笔记（空目录留档）：`200-MarkdownHome/通用/100-编程相关/102-学习笔记/012-SpringBoot`

---

## 10. 学习记录

| 日期 | 里程碑 | 验证结果 / 要点 |
|---|---|---|
| 2026-11-25 | 初始化 `010-SpringBoot` 工程 | 父工程继承 `spring-boot-starter-parent:4.1.3`；`demo1` 启动类 + controller/service 分层 + `application.yml` 就位；`mvn test` 通过（JUnit 6.0.3 / Spring 7.0.11 / Tomcat 11.0.27）；实测 `GET /hello` 返回 200 |
| 2026-11-25 | 完成 Spring Boot 4 全套笔记建档 & 第 1–3 章推进 | 建齐 `01–05` 章 + `98` 注解附录 + `99` 速查表并核对 Boot 4.1.3 时效（Undertow 移除 / Jackson 3 / `webmvc-test` / `@MockitoBean` / `RestTestClient`）；第 1、2 章完成第一遍实操过关（`demo1`/`demo2`），第 3 章完成概念通读 |
