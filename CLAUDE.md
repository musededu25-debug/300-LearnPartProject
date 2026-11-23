# 项目约定

## Obsidian 笔记

- 知识库：`/Users/m/Home/200-MarkdownHome/version_2`（已注册为 Obsidian 库，可用 `obsidian vault=version_2 …`）
- **查笔记先走路由，不要扫库**：读库内 `00-Meta/00-README.md` 的「AI 读取协议」，再到 `00-Meta/01-INDEX.md` 的「主题直达」定位主题索引；只读命中的 `00-INDEX-*`，需要细节或修改时才读章节正文
- **搜索先收窄**：先用 `grep -l` 或文件名找候选，再读候选；不要 `cat` 整个目录、不要批量读章节
- 写笔记前先读库内 `00-Meta/02-SPEC.md`：命名、frontmatter、目录归属都按它来；只改目标文件
- 触发规则（不要把 Obsidian 语法凭记忆写）：
  - 写/改 Markdown 笔记 → 先调用 `obsidian-markdown` skill
  - 改 `.base` 文件 → 先调用 `obsidian-bases` skill
  - 用命令行批量操作库 → 先调用 `obsidian-cli` skill
  - 从网页摘取内容进笔记 → 先调用 `defuddle` skill（不要手抄网页）
  - 建/改 `.canvas` 白板、思维导图、流程图 → 先调用 `json-canvas` skill
- 旧库 `/Users/m/Home/200-MarkdownHome/通用/100-编程相关/102-学习笔记/` 只读，按需迁移，不批量重写

## 项目结构与子模块导航

按当前任务直接读取对应目录的 `CLAUDE.md`，不要跨目录扫描无关工程或 `target/`：

| 目录 | 状态 | 说明 |
|------|------|------|
| `303-Java/008-spring_springBoot_v2/` | 🟢 当前主线 | Spring 7 核心重塑 → 轻量 SSM 整合 → Spring Boot 4 → MyBatis-Plus（JDK 21，多模块父工程，见 `CLAUDE.md`） |
| `303-Java/007-CS/001-dataStructure/` | 🟢 持续刷题 | 数据结构与 LeetCode 题解（JDK 21，单模块 Maven，见 `CLAUDE.md`） |
| `303-Java/006-Mybatis/heima/` | 🟡 阶段练习 | 黑马 MyBatis 课程（JDK 17，多模块父工程，见 `CLAUDE.md`） |
| `303-Java/005-SpringMVC/heima/` | ✅ 已完结 | 黑马 SpringMVC 课程（JDK 17 + Tomcat 11，保留复习，见 `CLAUDE.md`） |
| `303-Java/001`–`004`、`009-tools` | 📦 历史归档 | 早期 JavaSE / JavaWeb / Maven / 旧 Spring 练习，非必要不翻阅 |

> 通用环境提醒：本机 JDK 21 + Maven 3.10；远端 MySQL 经本机 **`127.0.0.1:3307`** 端口转发（不是 3306），账号密码只放本地配置文件，不写进文档。
