# 项目约定

## Obsidian 笔记

- 知识库（双端同一套内容，按当前系统取路径）：
  - macOS：`/Users/m/Home/200-MarkdownHome/version_2`（已注册为 Obsidian 库，可用 `obsidian vault=version_2 …`）
  - Windows：`A:\01-home\200-MarkdownHome\01-\obsidian_version2`
- **查笔记先走路由，不要扫库**：读库内 `00-Meta/00-README.md` 的「AI 读取协议」，再到 `00-Meta/01-INDEX.md` 的「主题直达」定位主题索引；只读命中的 `00-INDEX-*`，需要细节或修改时才读章节正文
- **搜索先收窄**：先用 `grep -l` 或文件名找候选，再读候选；不要 `cat` 整个目录、不要批量读章节
- 写笔记前先读库内 `00-Meta/02-SPEC.md`：命名、frontmatter、目录归属都按它来；只改目标文件
- 触发规则（不要把 Obsidian 语法凭记忆写）：
  - 写/改 Markdown 笔记 → 先调用 `obsidian-markdown` skill
  - 改 `.base` 文件 → 先调用 `obsidian-bases` skill
  - 用命令行批量操作库 → 先调用 `obsidian-cli` skill
  - 从网页摘取内容进笔记 → 先调用 `defuddle` skill（不要手抄网页）
  - 建/改 `.canvas` 白板、思维导图、流程图 → 先调用 `json-canvas` skill
- **速查表**：写完工具类主题（Git、JUnit、Logback、Maven 这类命令/API/配置项多的），若内容里出现大量可查询项，同步产出 `99-速查表-<主题>.md` 放进该主题目录。速查表只做「查」不做「教」：命令表、注解表、模板、`我想…→ 用什么` 索引。已配：团队开发、JUnit、Logback、Maven、IDEA、HTTP、MissingSemester、Vim
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

## 笔记中仍需实机确认的点

下面几条已按官方文档、随包 Keymap 或源码字节码核对过，但受本机环境限制没做真机验证。改到相关章节时先实测，确认后删掉对应条目：

- IDEA macOS 快捷键（`⌘⌥⇧F8`、`⌥⇧F5`、`⌥⌘R`）来自 JetBrains 默认 Keymap 与随包 `Mac OS X 10.5+.xml`，未在 macOS 上实按。
- 「Pause 是会话级暂停」按 JDI `VirtualMachine.suspend()` 语义与官方文档推导，未在 Tomcat 多线程场景下逐线程验证。
- IDEA 2026.3 Breakpoints 对话框里异常断点分类写作 `Java Exception Breakpoints`，未在 GUI 中截图核对。
- Tomcat Local 调试端口 `54987` 取自本机 `.idea/workspace.xml`，属于配置项；重建 Run Configuration 后会变。
- IDEA HTTP Client 环境变量优先级「私有环境覆盖公共环境」来自 `HttpCompositeEnvironment#getVariableValue` 字节码；官方文档「变量类型」一节仍写着相反结论，属文档滞后。

## 技能加载（每台新机器做一次）

技能源目录是 `.agents/skills/`（随 git 同步），但 Claude Code 只扫描 `.claude/skills/`，而 `.claude/` 被 `.gitignore` 忽略，所以要在本机建一次链接：

- Windows（仓库根，目录联接不需要管理员权限）：
  ```powershell
  cmd /c mklink /J ".claude\skills" ".agents\skills"
  ```
- macOS：
  ```bash
  mkdir -p .claude && ln -s ../.agents/skills .claude/skills
  ```

链接建好后**要重启 Claude Code 会话**才会加载技能；没重启时直接读 `.agents/skills/*/SKILL.md` 并按其中的规则手工执行。

## Obsidian CLI

- Windows 可执行文件：`A:\01-home\200-AppHome\Markdown\01-obsidian\home\Obsidian.com`（同目录有 `Obsidian.exe`），该目录默认不在 PATH。
- 首次使用需在 Obsidian「设置 → 通用 → 高级」里启用命令行接口；未启用时 `Obsidian.com help` 会返回 `Command line interface is not enabled.`。
- 未启用不阻塞工作：直接按文件系统路径读写知识库即可。
- `defuddle` 未全局安装，需要摘网页时用 `npx -y defuddle parse <url> --md`。
