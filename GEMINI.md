# 300-LearnPartProject · 项目指南与上下文协议

本仓库是个人编程学习与练习总库（当前重心为 **Java 后端 / Spring 生态 + 数据结构与算法**），配套独立的 Obsidian 知识库用于沉淀笔记。

---

## 1. 新对话冷启动协议（省额度、禁全扫）

开启新对话后，按以下顺序建立上下文，**严禁全库扫描**：

1. **先看本文件的模块导航表**，确认当前任务属于哪个子模块。
2. **按需读取对应子模块的 `CLAUDE.md`**（含当前学习进度、固定技术栈、代码/测试约定、踩坑速查、关联笔记落点），再按需打开具体源码文件。
3. **全局细节以根目录 `CLAUDE.md` 为补充**：如涉及待实机验证项、Obsidian CLI Windows 路径等，按需查阅 `CLAUDE.md`。
4. **禁止扫描以下目录**：`target/`、`.idea/`、`.mvn/`、历史归档目录，以及与当前任务无关的其他子工程。

---

## 2. 项目结构与 `CLAUDE.md` 导航

| 目录 | 状态 | 说明 | 详细上下文 |
|------|------|------|------------|
| `303-Java/010-SpringBoot/` | 🟢 当前主线 | Spring Boot 4 学习（跟黑马课程章节、代码按 Boot 4 改写；JDK 21，多模块父工程，当前模块 `fufu-springboot-demo1`） | `303-Java/010-SpringBoot/CLAUDE.md` |
| `303-Java/008-spring_springBoot_v2/` | 🟡 纯 Spring / SSM | 纯 Spring 7 核心重塑 → 轻量 SSM 整合（Spring Boot 已拆到 `010-SpringBoot`，本目录不再新增 Boot 子模块；JDK 21，多模块父工程） | `303-Java/008-spring_springBoot_v2/CLAUDE.md` |
| `303-Java/007-CS/001-dataStructure/` | 🟢 持续刷题 | 手写数据结构与 LeetCode 题解（JDK 21，单模块 Maven + JUnit 5） | `303-Java/007-CS/001-dataStructure/CLAUDE.md` |
| `303-Java/006-Mybatis/heima/` | 🟡 阶段练习 | 黑马 MyBatis 课程（JDK 17，多模块父工程） | `303-Java/006-Mybatis/heima/CLAUDE.md` |
| `303-Java/005-SpringMVC/heima/` | ✅ 已完结 | 黑马 SpringMVC 课程（JDK 17 + Tomcat 11，保留复习） | `303-Java/005-SpringMVC/heima/CLAUDE.md` |
| `303-Java/001`–`004`、`009-tools` | 📦 历史归档 | 早期 JavaSE / JavaWeb / Maven / 旧 Spring / 工具练习，非必要不翻阅 | — |
| `301-HTML`、`302-CSS`、`304-Mysql`、`305-Linux` | 📦 独立/基础练习 | 前端、MySQL、Linux/OS 基础练习目录，仅在明确提及对应主题时进入 | — |

### 通用协作与环境约定

- **交流与教学方式**：全程中文交流与中文注释；先讲「为什么」再讲「怎么写」。
- **练习边界**：遇到练习模块（如 `fufu-spring-p01`、`*-pNN` 或课程整章练习），**不主动代写练习答案**，以出题（题目 + 验收标准）、排查、验收为主，并按对应 `CLAUDE.md` 更新进度表。
- **通用环境**：本机装有 JDK 21/25/27（PATH 默认 java 是 27），新工程统一按 **JDK 21** 编译；Maven 3.10；远端 MySQL 经本机 **`127.0.0.1:3307`** 端口转发（不是 3306）；数据库账号密码只放本地 `jdbc.properties` / `application.yml`，不写入文档。

---

## 3. Obsidian 笔记路径与省额度读取协议

### 知识库路径（双端同一套内容，按当前系统取路径）

- **macOS**：`/Users/m/Home/200-MarkdownHome/version_2`（已注册为 Obsidian 库，可用 `obsidian vault=version_2 …`）
- **Windows**：`A:\01-home\200-MarkdownHome\01-\obsidian_version2`
- **旧库（只读参考）**：`/Users/m/Home/200-MarkdownHome/通用/100-编程相关/102-学习笔记/`（按需迁移，不批量重写）

### 读取协议（严禁全库扫描）

1. **首次读取笔记**：先读库内 `00-Meta/00-README.md` 的「AI 读取协议」，了解知识库组织方式与读取边界。
2. **查主题走两级索引**：先读 `00-Meta/01-INDEX.md` 的「主题直达」定位目标主题 → 只读命中的 `00-INDEX-*.md` → 确需细节或修改时才读具体章节正文。
3. **搜索先收窄**：优先用 `grep -l` 或按文件名找候选，再读候选；**禁止 `cat` 整个目录或批量通读章节**。
4. **写/改笔记前必读规范**：先读 `00-Meta/02-SPEC.md`（命名、frontmatter、目录归属全按它来），**只改目标文件**；写章节笔记必须遵守 `chapter.md` 模板的「**新手友好 4 条铁律**」（① 代码前写明前置依赖/SQL；② 首次出现的陌生注解/语法配大白话来源与定位；③ 保留易导错的 `import`/`import static`；④ 跨主题概念在首次出现处挂定向 `[[wikilink]]`）。
5. **速查表约定**：写完工具类或高频框架主题（命令/API/配置项多，如 Git、JUnit、Logback、Maven、Spring、SpringBoot）时，按需同步产出 `99-速查表-<主题>.md`（只做「查」不做「教」）。
6. **PDF 课件与多 Agent 批量写笔记防卡死**：严禁直接用多模态 `read_file` 盲吞大 PDF（> 3MB 或数十页）——先用 `pdftotext` 试提纯文本，**文本型 PDF 一律转 `.txt` 分段按需读**，**扫描/图片型小 PDF（≤ 3–5MB）才原样按需读**；开启多 Agent 批量写笔记时严格遵守 `02-SPEC.md` 第十二节（单章 350–550 行分段写入、优先轻模型、必挂 3–5 分钟巡检定时器、停滞 > 5 分钟果断 `kill` 止损、收尾自动查断链与表格转义）。

### 常用笔记直达入口（相对知识库根目录）

- Spring：`01-learn/02-Framework/01-Spring/00-INDEX-Spring.md`
- Spring Boot：`01-learn/02-Framework/03-SpringBoot/00-INDEX-SpringBoot.md`
- 数据结构：`01-learn/04-CS/`（先查 `00-Meta/01-INDEX.md` 确认具体目录）
- 算法与刷题：`07-Algorithms/NN-专题/`（专题索引 `00-INDEX-<专题>.md`，单题 `LCxxxx-题名.md`）

---

## 4. 项目 Skills 复用规则

本项目的技能源目录为 **`.agents/skills/`**（`.claude/skills/` 是指向它的软链接，Antigravity 会自动发现并加载 `.agents/skills/` 下的技能，可直接调用）。

处理笔记或网页内容时，**不要凭记忆手写 Obsidian 语法**，按场景触发对应 skill：

| 场景 | 使用的 Skill | 备注 |
|------|-------------|------|
| 写 / 改 Markdown 笔记 | `obsidian-markdown` | 遵循 Obsidian 语法、wikilinks、callouts、frontmatter |
| 编辑 `.base` 视图文件 | `obsidian-bases` | 表格/看板/筛选/公式视图 |
| 命令行批量操作知识库 | `obsidian-cli` | 若 CLI 未启用不阻塞，直接按文件系统路径读写 |
| 从网页摘取内容进笔记 | `defuddle` | 用 `npx -y defuddle parse <url> --md`，不要手抄网页 |
| 创建 / 修改 `.canvas` 白板 | `json-canvas` | 思维导图、流程图、白板节点与连线 |
| 查找或扩展新技能 | `find-skills` | 需要新能力时检索可用技能 |
