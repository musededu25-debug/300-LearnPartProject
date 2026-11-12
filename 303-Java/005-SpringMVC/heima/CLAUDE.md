# heima/ — 黑马 SpringMVC 课程学习区

> **给 Claude 的话**：不要每次通读整个项目。先读本文件（1 分钟）建立上下文，再按需打开与当前问题相关的文件。
> 不要扫描 `target/`、`.mvn/`，也不要跨目录翻其他课程（001-JavaSE、002-JavaWeb 等）。

## 1. 这个目录是干什么的

存放跟着黑马《SpringMVC（基于 Spring 6 + Jakarta EE）》课程学习和练习的工程。

- **用户身份**：Spring 初学者。学过老版 Spring（Spring 5 + `javax`），**没有 Servlet 基础**，还没学 MyBatis / JSON 库 / JDBC。
- **学习目标**：逐个吃透知识点，不是快速出活。
- **讲解要求**：中文；先讲"为什么"再讲"怎么写"；不引入用户还没学过的技术；术语要分清是 **Spring 概念** 还是 **Servlet 规范概念**。

## 2. 固定技术栈（不要改动）

| 项 | 版本 | 备注 |
|---|---|---|
| Spring MVC | 6.2.19 | `jakarta` 命名空间 |
| jakarta.servlet-api | 6.1.0 | `scope=provided` |
| 编译级别 | Java 17 | 本机 JDK 27 |
| Tomcat | 11.0.27（本机） | Homebrew：`/opt/homebrew/opt/tomcat/libexec` |
| 打包 | war | 无 `web.xml`，靠 SCI 初始化 |
| 数据库 | 远端 MySQL | 经**本机 3307** 端口转发（不是 3306）；本机无 mysql CLI / Docker |

> **阶段状态**：SpringMVC 部分已学完（d01–d05、p01、p02）。用户已开始 MyBatis 阶段，
> 交接说明在 `303-Java/006-Mybatis/heima/HANDOFF-from-springmvc.md`；本目录保留作复习用。

## 3. 运行方式（唯一正确的方式）

IDEA Ultimate → Run/Debug Configurations → **Tomcat Server → Local**

- Tomcat Home：`/opt/homebrew/opt/tomcat/libexec`
- Deployment：`fufu-springmvc-xxx:war exploded`，Application context = `/`
- 访问：`http://localhost:8080/...`

**禁止：**

- 使用 `tomcat7-maven-plugin`（自带 Tomcat 7.0.47 = `javax`，与 `jakarta` 不兼容，`/save` 会 404）
- 恢复 archetype 生成的 `web.xml`（2.3 版本会关闭 Servlet 3.0 的 SCI 扫描，启动类不会被调用）
- 升级 / 降级 Spring 或 servlet 版本
- 创建 `main` 方法手动启动容器（本课程靠容器 + SCI 启动）

## 4. 目录与命名规范

| 目录 | 含义 |
|---|---|
| `fufu-springmvc-d01-start` | 跟课程敲的原始工程，`dNN` = 课程第 NN 天 |
| `fufu-springmvc-pNN` | **自建练习工程**，NN 从 01 递增，一个练习 = 一个独立工程 |

每个工程的标准结构（课程讲到哪层就建到哪层，不提前建）：

```
src/main/java/com/fufu/
├── config/      # SpringMVCConfig、ServletContainersInitConfig
├── controller/  # @Controller / @RestController
├── service/     # 业务层（课程讲到再加）
└── domain/      # 实体类（课程讲到再加）
src/main/webapp/ # 静态资源 / 页面（课程讲到再加）
```

**新建工程时直接复制 `heima/pom-template.xml`**，只改 `artifactId` / `name` / `finalName`，其余不动。

模板中必须保留的三块（缺一不可）：

```xml
<properties>
    <maven.compiler.parameters>true</maven.compiler.parameters>   <!-- 必须！否则普通参数绑定报 500 -->
    <maven.compiler.source>17</maven.compiler.source>
    <maven.compiler.target>17</maven.compiler.target>
</properties>
```

依赖版本与 `fufu-springmvc-d01-start` 保持一致，只保留 `<finalName>`。

## 5. 练习规则（2026-11-11 约定）

- **一个知识点 = 一个独立工程**（`fufu-springmvc-pNN`），互不干扰
- **用户自己建工程**，Claude 只出题 + 检查，不代建
- 每次练习交付 = **题目 + 验收标准**（代码由用户自己写）
- 出题只用用户**已经学过**的概念；还没讲到的东西放在"预告"里，等课程讲完再做
- 用户完成一个练习后，Claude 检查并在此登记

| 编号 | 主题 | 对应课程内容 | 状态 |
|---|---|---|---|
| p01 | 多 Controller + 路径映射 + 包扫描边界 | 快速入门 | ✅ 已通过 2026-11-11 |
| p02 | 图书管理全栈：静态资源 + REST CRUD + 前后端联调（MyBatis 前的综合练习） | REST / 案例 | 已跑通 2026-11-12（后端骨架用户写，CRUD 逻辑由 Claude 补完，用户需自己复写一遍） |
| p03 | （可选）把 d03 的接口改造成 RESTful | REST 风格 | 已完成内容并入 p02，可跳过 |

### p02 接口约定（前端 `src/main/webapp/static/app.js` 依赖，不要擅自改）

| 方法 | 路径 | 请求体 | 响应 |
|---|---|---|---|
| GET | `/books` | — | `List<Book>` JSON 数组 |
| GET | `/books/{id}` | — | 单个 Book 或 `null` |
| POST | `/books` | `{name, price}` JSON | 新建的 Book（含 id） |
| PUT | `/books/{id}` | `{name, price}` JSON | 修改后的 Book |
| DELETE | `/books/{id}` | — | 字符串即可 |

`Book`：`Integer id`、`String name`、`Integer price`。页面入口 `http://localhost:8080/static/index.html`。

**依赖提醒**：`@RequestBody` / 返回对象为 JSON 需要 Jackson，pom 里必须有
`com.fasterxml.jackson.core:jackson-databind`（d03 已加 2.22.4）。

**p01 已确认的结论：**

- `@ComponentScan("com.fufu.controller")` 决定哪些包里的类会成为 bean；不在此范围内的 Controller 不会注册 → 该路径 404
- 有 `@ResponseBody`：返回值直接写入响应体
- 无 `@ResponseBody`：返回值被当作**逻辑视图名**，由 ViewResolver 去找同名页面，找不到就 404

## 6. 代码约定

- 包名统一 `com.fufu.xxx`
- 全注解、零 XML、无 `web.xml`
- 类顶部用中文注释标出"第几步 / 这个类解决什么问题"（沿用 `ServletContainersInitConfig` 里 `//4.定义应该servlet容器启动的配置类` 这种风格）
- 新代码优先复用 `fufu-springmvc-d01-start` 里已验证可用的配置类，不要重新发明

## 7. 常见坑（已踩过）

| 现象 | 原因 | 处理 |
|---|---|---|
| `No plugin found for prefix 'tomcat7'` | Maven 没在 pom 里找到该插件 | 本项目不用该插件；改 pom 后在 IDEA 里刷新 Maven 项目 |
| 能打开 `index.jsp` 但所有接口 404 | ① 跑在插件自带 Tomcat 7 上；② `context.register()` 注册错类（最高频） | 换成 IDEA 本地 Tomcat 11；注册 `SpringMvcConfig.class` 并 `return context` |
| `web.xml` 是 archetype 的 2.3 版本 | 实测在 Tomcat 11 上**不会**阻止 SCI 扫描（可用），只是残留、易误导 | 建议删除或换成 6.0 空标签，非必须 |

| 普通参数接口 500：`Name for argument of type [...] not specified ... use the '-parameters' flag` | Spring 6.1 起移除了用局部变量表推断参数名，必须在编译时加 `-parameters` | pom 加 `<maven.compiler.parameters>true</maven.compiler.parameters>`，然后**重新编译**（clean）|
| POST JSON 时报 500：`Optional int parameter 'age' is present but cannot be translated into a null value ... primitive type` | ① 没有 `@RequestBody`，JSON 不会被绑定；② `int` 不能为 null | 用 Params / x-www-form-urlencoded 传参；或把 `int` 改成 `Integer` |
| 日期参数拿到 `null` | ① Postman 用 **form-data**（multipart）发参数，纯 Tomcat 默认不解析，参数拿不到；② pattern 里 `mm` 是**分钟**、`MM` 才是月；③ 不传就是 `null`（对象类型可空） | Postman 用 **Params** 或 **Body → x-www-form-urlencoded**；pattern 用 `yyyy-MM-dd` / `yyyy/MM/dd` 且与传入格式一致 |
| 方法设了对象却返回文本 / 500（`Cannot invoke "Integer.intValue()"`） | ① 返回类型写成了 `String`；② 对 `null` 的包装类型做 `+1` 会拆箱 NPE | 返回类型改成实体类；运算前判空 |
| 改了代码还是 404，但首页能开 | IDEA 的 Tomcat 运行配置里 **Deployment 选的不是这个工程**（每个练习工程要独立的运行配置） | 打开 Run Configuration → Deployment，确认 Artifact 是本工程；不同工程别共用同一个 Tomcat 配置 |

**排查顺序（接口全 404 时按这个走）：**

1. 先确认**跑的是哪个工程**：浏览器访问一个只有本工程才有的路径；或看 IDEA 运行配置的 Deployment 标签里 Artifact 名字
2. 确认地址/端口是 `http://localhost:8080` 且 context 是 `/`
3. 看 `ServletContainersInitConfig` 里 `register()` 的是不是 `SpringMvcConfig.class`、有没有 `return context`
4. 看 Tomcat 启动日志有没有 Spring 的 `DispatcherServlet` 初始化记录
5. 最后才怀疑 `web.xml` / 依赖版本

**约定**：每个工程配一个独立运行配置，命名与工程一致（如 `d01`、`p01`）。
