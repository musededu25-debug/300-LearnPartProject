# 交接：从 SpringMVC 阶段 → MyBatis 阶段

> **这份文件是上一个会话（SpringMVC 学习）留给你的交接说明。**
> 用户即将在这个目录（`303-Java/006-Mybatis/heima`）开始学 MyBatis，之后是 SSM 整合、Spring Boot。
> 请结合本文件 + 当前目录现状，为新阶段创建 `CLAUDE.md`（只写新阶段需要的内容，不要把 SpringMVC 的细节全搬过去；SpringMVC 的说明书在 `303-Java/005-SpringMVC/heima/CLAUDE.md`）。

---

## 1. 用户是谁、怎么教

- **初学者**：正在按黑马新版课程学习；学过老版 Spring（Spring 5 概念），**没有 Servlet 基础、没有 JDBC 基础**
- **语言**：全程中文，代码注释也中文
- **讲解要求**：先讲"为什么"再讲"怎么写"；**不引入用户还没学过的技术**；遇到陌生概念要说清"这是 Spring 的、还是 Servlet/JDBC 的"
- **证据要给"跑出来的输出"**，不要甩字节码/源码截图。用户明确反馈过看不懂底层推导
- **薄弱点（已知）**：Java 集合与泛型不熟（用户自述"快忘完了"），尤其 `List/Map` 的常用操作和泛型的意义 —— MyBatis 里 `List<Book> selectAll()`、`Map<String,Object>` 满地都是，遇到卡点要顺手带一下
- 用户自己会复盘、会追问概念，**不要糊弄**，也不要一次塞太多前置知识

## 2. 环境事实（已在本机确认）

| 项 | 值 |
|---|---|
| 系统 / Shell | macOS (darwin 27.2.0) / zsh |
| JDK | 27（Homebrew `/opt/homebrew/opt/java`） |
| Maven | 3.10.0（`/opt/homebrew/bin/mvn`） |
| IDEA | IntelliJ IDEA 2026.3 **Ultimate**（自带 Maven 也带 HTTP Client / Database 工具） |
| Tomcat | 11.0.27，Homebrew：`CATALINA_HOME=/opt/homebrew/opt/tomcat/libexec` |
| **数据库** | **远端服务器，通过本机 3307 端口转发**（注意：不是 3306）；本机没有 `mysql` 命令行，也没有 Docker，用户大概是用 IDEA 的 Database 工具或图形客户端连的 |
| 仓库 | `~/Home/300-ProjectHome/300-LearnPartProject`，`303-Java/` 下按课程分目录（001-JavaSE … 006-Mybatis） |

## 3. SpringMVC 阶段已完成

- 跟课工程：`fufu-springmvc-d01-start`、`d02-bean-load`、`d03`、`d04`、`d05`
- 自建练习：`fufu-springmvc-p01`（多 Controller/包扫描边界）、`fufu-springmvc-p02`（图书管理全栈：静态资源 + REST CRUD + 前端页面）
- 已掌握：注解配置、组件扫描、请求参数（普通/POJO/集合/日期 `@DateTimeFormat`）、`@RequestBody`/`@ResponseBody`、JSON（Jackson）、REST（`@RestController`/`@PathVariable`/四种方法）、静态资源映射、`AbstractAnnotationConfigDispatcherServletInitializer`
- 课程约定：**一个知识点一个工程**，练习工程命名 `fufu-springmvc-pNN`；**用户自己建工程**，Claude 出题 + 检查，交付=题目+验收标准
- p02 的 `BookService` 是**内存版**（`List<Book>` 当表）——**这是接入 MyBatis 最自然的入口**：把方法体换成 mapper 调用，Controller 不动

## 4. 已踩过的坑（别再让用户踩第二遍）

| 现象 | 原因 | 处理 |
|---|---|---|
| `No plugin found for prefix 'tomcat7'` / 接口全 404 但首页能开 | `tomcat7-maven-plugin` 自带 Tomcat 7（javax），与 `jakarta.servlet` 不兼容 | 用 IDEA 本地 Tomcat 11 跑，**不要用该插件** |
| `Name for argument of type [...] not specified ... '-parameters'` | Spring 6.1+ 不再从局部变量表猜参数名 | pom 必须 `maven.compiler.parameters=true`，并重新编译；或 `@RequestParam("x")`/`@PathVariable("id")` 显式命名 |
| Postman form-data 传参拿到 `null` | 纯 Tomcat 默认不解析 multipart | 用 **Params** 或 **x-www-form-urlencoded** |
| 日期为 `null` / 日期错乱 | `mm` 是分钟、`MM` 才是月；格式与 pattern 不一致会 400 | `yyyy-MM-dd` / `yyyy/MM/DD` 与入参一致 |
| 500 `Cannot invoke "Integer.intValue()"` | 包装类型为 null 时做 `+1` 拆箱 | 运算前判空 |
| 返回对象却是纯文本 | 方法返回类型写成了 `String` | 返回实体类 |
| 接口 404 但代码没问题 | IDEA 运行配置部署的是**别的工程** | 每个工程一个独立运行配置；看 Deployment 里的 Artifact |
| `@RestController("/books")` | 参数是 bean 名，不是路径 | `@RestController` + `@RequestMapping("/books")` |
| `@EnableWebMvc` 写在 Controller 上 | 它属于配置类 | 放 `SpringMvcConfig` |
| Jackson 反序列化失败 | 实体类没有**无参构造** | 补无参构造 |
| pom 继承 `../pom-template.xml` 后移动工程报 `Non-resolvable parent POM` | 父工程要靠相对路径找 | 新阶段**建议用独立 pom**，别再用相对路径继承（`005-SpringMVC/heima/pom-template.xml` 现在被改成了父工程，比较脆） |

## 5. 对 MyBatis 阶段的建议（供参考，不是命令）

1. **先确认数据库能连上**（本机 3307 转发 → 远端），再动手写代码；驱动依赖通常是 `com.mysql:mysql-connector-j`
2. MyBatis 只抓三件事：**SQL 写在哪**（注解 / XML）、**参数怎么传**（`#{}` / `${}`，单参/多参/对象）、**结果怎么映射**（`resultType` / `resultMap`）
3. 练习最好**直接改造 p02 的 BookService**：内存 `List` → mapper 调用，Controller 不变。这样能验证"分层"和"集合/泛型"两件事
4. 若课程先讲 JDBC：只讲清"MyBatis 帮你省了什么"即可，别深挖连接池原理
5. **SSM 整合阶段不要糊弄** —— 用户后面的 Spring Boot 就是把这堆配置自动化。提前提醒他："现在手写的配置，就是 Boot 以后帮你省的"，理解比能跑更重要
6. 保持同样的工作方式：一个知识点一个工程（例如 `mybatis-d01`、练习 `mybatis-p01`），用户自己建，Claude 出题+验收

## 6. 回看入口

- SpringMVC 的完整说明书：`303-Java/005-SpringMVC/heima/CLAUDE.md`
- 用户若要复习 SpringMVC，会回到原来的会话/目录；本文件不替代它
