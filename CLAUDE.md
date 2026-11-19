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
- **速查表**：写完工具类主题（Git、JUnit、Logback、Maven 这类命令/API/配置项多的），若内容里出现大量可查询项，同步产出 `99-速查表-<主题>.md` 放进该主题目录。速查表只做「查」不做「教」：命令表、注解表、模板、`我想…→ 用什么` 索引。已配：团队开发、JUnit、Logback、Maven
- 旧库 `/Users/m/Home/200-MarkdownHome/通用/100-编程相关/102-学习笔记/` 只读，按需迁移，不批量重写

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

- Windows 可执行文件：`A:\01-home\2000-AppHome\Markdown\01-obsidian\home\Obsidian.com`（同目录有 `Obsidian.exe`），该目录默认不在 PATH。
- 首次使用需在 Obsidian「设置 → 通用 → 高级」里启用命令行接口；未启用时 `Obsidian.com help` 会返回 `Command line interface is not enabled.`。
- 未启用不阻塞工作：直接按文件系统路径读写知识库即可。
- `defuddle` 未全局安装，需要摘网页时用 `npx -y defuddle parse <url> --md`。
