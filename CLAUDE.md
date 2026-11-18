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
