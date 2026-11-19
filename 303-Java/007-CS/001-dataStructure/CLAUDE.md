# 001-dataStructure · 数据结构与算法

本目录是数据结构与算法的练习工程：**手写数据结构 + LeetCode 题解 + JUnit 5 单元测试**。
目标是把「写出来的代码」和 Obsidian 里的「知识笔记」对应起来，而不是堆一堆跑不起来的 demo。

## 一、工程形态（先记住这一条）

- **只有一个 Maven 项目**，就是本目录的 `pom.xml`。
- **不要**给每道题、每个小节建一个 Maven 模块或独立项目；Maven 模块才是重资产（各自的 pom、target、IDE 配置）。
- 题目用**包**分组、用**类**区分；一个 Java 文件通常只有几 KB，几百道题也就几百 KB，Maven / Git / IDEA 完全无压力。
- 判断标准不是「文件多不多」，而是「能不能按专题找到、能不能单独跑测试、改一处会不会牵动别处」。

```
001-dataStructure/
├── pom.xml
├── CLAUDE.md
└── src/
    ├── main/java/com/fufu/ds/
    │   ├── common/            # ListNode、TreeNode 等公共节点类（第一次用到时再建）
    │   ├── structure/         # 自己实现的数据结构
    │   │   ├── array/         # 动态数组、前缀和……
    │   │   ├── linkedlist/    # 单/双链表、LRU……
    │   │   ├── stack/  queue/ hashtable/
    │   │   ├── tree/   heap/  graph/
    │   │   └── sort/   search/
    │   └── problems/          # 刷题，按专题分包，和笔记专题一一对应
    │       ├── array/  twopointers/  slidingwindow/  binarysearch/
    │       ├── linkedlist/  stack/  tree/  graph/
    │       └── backtracking/  dp/  greedy/
    └── test/java/com/fufu/ds/ # 与 main 同包同名 + Test，目录镜像 main
```

> 目录是「用到再建」，不要一次性生成空包。Java 包名不能以数字开头，所以专题用英文名。

## 二、文件怎么拆（回答「一题一个 demo 会不会太多」）

| 场景 | 拆法 | 原因 |
|------|------|------|
| 刷一道 LeetCode | 一个题解类 + 一个测试类 | 按题回看、单独运行、失败时定位清楚 |
| 实现一个数据结构 | 一个结构类 + 一个测试类 | 结构是独立单元，应该有自己的 API 和测试 |
| 同一题的多种解法 | **同一个类里多个方法**（`twoSumBruteForce` / `twoSum`） | 解法不是独立单元，放一起才方便对照 |
| 课程小 demo / 对比实验 | 可以一个专题一个类，多个方法 | 例如 `SortingAlgorithms` 放 5 种排序，避免碎片化 |
| 公共节点 / 工具 | 放 `common/`，全项目复用 | 不要每个题都复制一份 ListNode |

命名：

- 题解类：`LC0001TwoSum`（题号 4 位 + 驼峰题名），方法签名尽量保持 LeetCode 原样。
- 测试类：`LC0001TwoSumTest`，与被测类**同包**。
- 数据结构类：`DynamicArray`、`SingleLinkedList`、`MinHeap`。
- 一个 `.java` 文件里只放一个 `public` 类；辅助类可以写成包级私有或静态内部类。

## 三、代码约定

- 注释和 `@DisplayName` 用中文；类头写清「题意 / 思路 / 时间空间复杂度」。
- 每个 public 方法都写：输入约束、返回值、复杂度；关键循环写「循环不变量」。
- 写题先写暴力解，再写最优解；不要把暴力解删掉，对照价值很高。
- 边界优先考虑：`null` / 空数组 / 单元素 / 全相同 / 负数 / 整型溢出。
- **不写 `main` 当测试入口**，统一用 JUnit；`main` 只用于临时打印调试，提交前删掉。
- 不引入 Spring、数据库、Web、Lombok、Mockito；除 JUnit 5 + AssertJ 外保持零依赖。需要新依赖先问。

## 四、单元测试约定

- 默认用 JUnit 5 自带的 `Assertions.assertEquals`，写最普通、能一眼看懂的测试：

  ```java
  @Test
  @DisplayName("找不到目标返回 -1")
  void binarySearch1_notFound() {
      Assertions.assertEquals(-1, BinarySearch.binarySearch1(new int[]{1, 3}, 5));
  }
  ```

- 一个方法只测一种情况，方法名用 `被测方法_场景`，例如 `binarySearch1_found`、`binarySearch1_notFound`。
- 每个函数写 3–5 个测试方法、每个方法 1–3 个断言就够；优先覆盖：能找到、找不到、空数组、单元素、重复元素。
- 不写参数化测试、对拍、复杂链式断言；先把基本行为锁定住，需要时再加。
- 同一个类以后加进阶版，直接在测试类里加 `binarySearch2_xxx`，不要另建测试类。
- pom 里虽然带了 AssertJ，但默认不用；`Assertions.assertEquals` 够用。
- **不要断言运行时间**（机器负载会让测试随机失败）；复杂度写在注释里。

常用命令（在本目录执行）：

```bash
mvn test                                   # 跑全部测试
mvn -Dtest=LC0001TwoSumTest test           # 只跑一个测试类
mvn -Dtest='*Tree*' test                   # 按类名通配
mvn -q -Dtest=LC0001TwoSumTest test        # 安静模式，只看失败
```

## 五、新增一道题的动线

1. 判断专题 → 在 `problems/<topic>/` 下建 `LCxxxx题名.java`。
2. 写 LeetCode 同款方法签名 + 暴力解 + 最优解，类头 Javadoc 写思路和复杂度。
3. 在 `src/test/java` 的同包下建 `LCxxxx题名Test.java`，先写示例用例，再补边界。
4. 跑 `mvn -Dtest=LCxxxx题名Test test`，绿了再继续。
5. 如果这题值得记录，按第六节同步到笔记。

## 六、笔记同步（Obsidian）

**总规则以项目根 `/Users/m/Home/300-ProjectHome/300-LearnPartProject/CLAUDE.md` 为准**，这里只记本工程的落点：

- 知识库：`/Users/m/Home/200-MarkdownHome/version_2`
- 数据结构本体知识（实现、复杂度、适用场景）→ `01-learn/04-CS/` 下的 DataStructures 主题。
  - 先按 `00-Meta/02-SPEC.md` 分配编号并登记，再更新 `00-Meta/01-INDEX.md` 的「主题直达」；不要凭记忆建目录。
- 刷题与专题套路 → `07-Algorithms/NN-专题/`：
  - 一个专题一个 `00-INDEX-<专题>.md`
  - 一道题一篇 `LCxxxx-题名.md`
- 写/改笔记前：先走 `00-Meta/00-README.md` 的 AI 读取协议 → `01-INDEX.md` → 目标索引；再读 `02-SPEC.md`；并调用 `obsidian-markdown` skill。不要凭记忆写 Obsidian 语法。
- **分工**：代码以本仓库为准；笔记只放「思路、模板、卡点、复杂度、复习记录」，不要复制整份可运行代码，避免两边不同步。
- **回链**：题解类 Javadoc 里写笔记的相对路径（如 `07-Algorithms/01-双指针/LC0015-三数之和.md`），笔记里可写本仓库文件路径。
- 同步时机：一个专题告一段落，或某道题值得复盘时；不要每改一行代码就动笔记，一次只改目标文件。

## 七、明确不要做的事

- 不要为每道题建独立 Maven 模块/项目。
- 不要收编到别的 Spring 工程里，也不要把 Spring 依赖带进来。
- 不要提交 `target/`（根 `.gitignore` 已覆盖）。
- 不要批量重写笔记或移动已有笔记；只改目标文件。
- 不要用 `System.currentTimeMillis()` 断言性能。

## 八、环境

- 本地 JDK 27，Maven 3.10；`maven.compiler.release` 设为 **21**（LTS，方便直接粘贴到 LeetCode / 面试环境）。
- 需要 JDK 21+；如果以后要用 JDK 27 新语法，改 pom 里的 `maven.compiler.release` 为 `27`。
