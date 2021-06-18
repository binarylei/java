# 数据结构与算法练习

## Array

- [283. 移动零](<https://leetcode-cn.com/problems/move-zeroes/>)：[方法1](<https://leetcode-cn.com/submissions/detail/131299376/>)、[中文题解](https://leetcode-cn.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/)、[国际版题解](https://leetcode.com/problems/move-zeroes/solution/yi-dong-ling-by-leetcode-solution/)。相似题：插入排序。
- [11. 盛最多水的容器](<https://leetcode-cn.com/problems/container-with-most-water/>)：[方法1 - 暴力求解 - 双层遍历](<https://leetcode-cn.com/problems/container-with-most-water/submissions/>)、[方法2 - 左右夹逼](<>)。
- [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)：斐波那契数列，递归和非递归求解
- [15. 三数之和](https://leetcode-cn.com/problems/3sum/)：方法1-暴力求解-三层遍历、方法2-双指针。相似题：[1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

## 栈、队列、优先队列、双端队列

- [20. 有效的括号](https://leetcode-cn.com/problems/valid-parentheses/)：方法1-栈
- [155. 最小栈](https://leetcode-cn.com/problems/min-stack/)：方法1-双栈结构。相似题：[剑指 Offer 09. 用两个栈实现队列](https://leetcode-cn.com/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/)
- [84. 柱状图中最大的矩形](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/)：方法1-暴力求解-双层遍历、方法2-求单柱子的左右边界、方法3-栈结构。参考题解-[暴力解法、栈（单调栈、哨兵技巧）](https://leetcode-cn.com/problems/largest-rectangle-in-histogram/solution/bao-li-jie-fa-zhan-by-liweiwei1419/)
- [239. 滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)：参考题解-[双向队列视频动画](https://leetcode-cn.com/problems/sliding-window-maximum/solution/shi-pin-jie-xi-shuang-duan-dui-lie-hua-dong-chuang/)+[代码实现](https://leetcode-cn.com/problems/sliding-window-maximum/solution/shuang-xiang-dui-lie-jie-jue-hua-dong-chuang-kou-2/)
- [641. 设计循环双端队列](https://leetcode-cn.com/problems/design-circular-deque/)
- [42. 接雨水](https://leetcode-cn.com/problems/trapping-rain-water/)
- 用 add first 或 add last 这套新的 API 改写 Deque 的代码
- 分析 Queue 和 Priority Queue 的源码

## 哈希表

- [242. 有效的字母异位词](https://leetcode-cn.com/problems/valid-anagram/)：方法1-暴力求解-排序、方法2-暴力求解-hash。参考题解-[官方题解](https://leetcode-cn.com/problems/valid-anagram/solution/you-xiao-de-zi-mu-yi-wei-ci-by-leetcode-solution/)
- [49. 字母异位词分组](https://leetcode-cn.com/problems/group-anagrams/)
- [1. 两数之和](https://leetcode-cn.com/problems/two-sum/)

## 二叉树

- [144. 二叉树的前序遍历](https://leetcode-cn.com/problems/binary-tree-preorder-traversal/)：前序遍历(中左右)、中序遍历(左中右)、后序遍历(左右中)。方法1-递归。参考题解-[颜色标记法-一种通用且简明的树遍历方法](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/solution/yan-se-biao-ji-fa-yi-chong-tong-yong-qie-jian-ming/)
- [94. 二叉树的中序遍历](https://leetcode-cn.com/problems/binary-tree-inorder-traversal/)：
- [590. N叉树的后序遍历](https://leetcode-cn.com/problems/n-ary-tree-postorder-traversal/)：
- [589. N叉树的前序遍历](https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/)：
- [429. N 叉树的层序遍历](https://leetcode-cn.com/problems/n-ary-tree-level-order-traversal/)：

## 递归

- [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)
- [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)：N格子问题

### 二叉树

- [226. 翻转二叉树](https://leetcode-cn.com/problems/invert-binary-tree/)
- [98. 验证二叉搜索树](https://leetcode-cn.com/problems/validate-binary-search-tree/)
- [104. 二叉树的最大深度](https://leetcode-cn.com/problems/maximum-depth-of-binary-tree/)
- [111. 二叉树的最小深度](https://leetcode-cn.com/problems/minimum-depth-of-binary-tree/)
- [297. 二叉树的序列化与反序列化](https://leetcode-cn.com/problems/serialize-and-deserialize-binary-tree/)
- [236. 二叉树的最近公共祖先](https://leetcode-cn.com/problems/lowest-common-ancestor-of-a-binary-tree/)
- [105. 从前序与中序遍历序列构造二叉树](https://leetcode-cn.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/)

### 排序组合

- [46. 全排列（中等）](https://leetcode-cn.com/problems/permutations/)
- [47. 全排列 II（中等）](https://leetcode-cn.com/problems/permutations-ii/)：思考为什么造成了重复，如何在搜索之前就判断这一支会产生重复；
- [39. 组合总和（中等）](https://leetcode-cn.com/problems/combination-sum/)
- [40. 组合总和 II（中等）](https://leetcode-cn.com/problems/combination-sum-ii/)
- [40. 组合总和 II（中等）](https://leetcode-cn.com/problems/combination-sum-ii/)
- [77. 组合（中等）](https://leetcode-cn.com/problems/combinations/)
- [78. 子集（中等）](https://leetcode-cn.com/problems/subsets/)
- [90. 子集 II（中等）](https://leetcode-cn.com/problems/subsets-ii/)：剪枝技巧同 47 题、39 题、40 题；
- [60. 第 k 个排列（中等）](https://leetcode-cn.com/problems/permutation-sequence/)：利用了剪枝的思想，减去了大量枝叶，直接来到需要的叶子结点；
- [93. 复原 IP 地址（中等）](https://leetcode-cn.com/problems/restore-ip-addresses/)

## 分治、回溯

- [50. Pow(x, n)](https://leetcode-cn.com/problems/powx-n/)
- [78. 子集](https://leetcode-cn.com/problems/subsets/)
- [169. 多数元素](https://leetcode-cn.com/problems/majority-element/)
- [17. 电话号码的字母组合](https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
- [51. N 皇后](https://leetcode-cn.com/problems/n-queens/)

## 深度优先搜索和广度优先搜索

- [102. 二叉树的层序遍历](https://leetcode-cn.com/problems/binary-tree-level-order-traversal/)
- [433. 最小基因变化](https://leetcode-cn.com/problems/minimum-genetic-mutation/)：n格子问题
- [22. 括号生成](https://leetcode-cn.com/problems/generate-parentheses/)
- [515. 在每个树行中找最大值](https://leetcode-cn.com/problems/find-largest-value-in-each-tree-row/)
- [127. 单词接龙](https://leetcode-cn.com/problems/word-ladder/)
- [126. 单词接龙 II](https://leetcode-cn.com/problems/word-ladder-ii/)
- [200. 岛屿数量](https://leetcode-cn.com/problems/number-of-islands/)
- [529. 扫雷游戏](https://leetcode-cn.com/problems/minesweeper/)

## 贪心算法

- [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)
- [860. 柠檬水找零](https://leetcode-cn.com/problems/lemonade-change/)
- [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
- [455. 分发饼干](https://leetcode-cn.com/problems/assign-cookies/)
- [874. 模拟行走机器人](https://leetcode-cn.com/problems/walking-robot-simulation/)
- [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)
- [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)

## 二分查找

- [69. x 的平方根](https://leetcode-cn.com/problems/sqrtx/)
- [367. 有效的完全平方数](https://leetcode-cn.com/problems/valid-perfect-square/)
- [33. 搜索旋转排序数组](https://leetcode-cn.com/problems/search-in-rotated-sorted-array/)
- [74. 搜索二维矩阵](https://leetcode-cn.com/problems/search-a-2d-matrix/)
- [153. 寻找旋转排序数组中的最小值](https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array/)

## 动态规划

- [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)
- [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)
- [1143. 最长公共子序列](https://leetcode-cn.com/problems/longest-common-subsequence/)
- [70. 爬楼梯](https://leetcode-cn.com/problems/climbing-stairs/)
- [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)
- [120. 三角形最小路径和](https://leetcode-cn.com/problems/triangle/)
- [53. 最大子序和](https://leetcode-cn.com/problems/maximum-subarray/)
- [152. 乘积最大子数组](https://leetcode-cn.com/problems/maximum-product-subarray/)
- [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)
- [198. 打家劫舍](https://leetcode-cn.com/problems/house-robber/)
- [213. 打家劫舍 II](https://leetcode-cn.com/problems/house-robber-ii/)
- [121. 买卖股票的最佳时机](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/)
- [122. 买卖股票的最佳时机 II](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/)
- [123. 买卖股票的最佳时机 III](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii/)
- [309. 最佳买卖股票时机含冷冻期](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/)
- [188. 买卖股票的最佳时机 IV](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iv/)
- [714. 买卖股票的最佳时机含手续费](https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/)

### 高级 DP 实战题目

- [279. 完全平方数](https://leetcode-cn.com/problems/perfect-squares/)
- [72. 编辑距离](https://leetcode-cn.com/problems/edit-distance/) ](https://leetcode-cn.com/problems/edit-distance/)（重点）
- [55. 跳跃游戏](https://leetcode-cn.com/problems/jump-game/)
- [45. 跳跃游戏 II](https://leetcode-cn.com/problems/jump-game-ii/)
- [62. 不同路径](https://leetcode-cn.com/problems/unique-paths/)
- [63. 不同路径 II](https://leetcode-cn.com/problems/unique-paths-ii/)
- [980. 不同路径 III](https://leetcode-cn.com/problems/unique-paths-iii/)
- [322. 零钱兑换](https://leetcode-cn.com/problems/coin-change/)
- [518. 零钱兑换 II](https://leetcode-cn.com/problems/coin-change-2/)

### 课后作业

- [32. 最长有效括号](https://leetcode-cn.com/problems/longest-valid-parentheses/)
- [64. 最小路径和](https://leetcode-cn.com/problems/minimum-path-sum/)
- [91. 解码方法](https://leetcode-cn.com/problems/decode-ways/)
- [221. 最大正方形](https://leetcode-cn.com/problems/maximal-square/)
- [363. 矩形区域不超过 K 的最大数值和](https://leetcode-cn.com/problems/max-sum-of-rectangle-no-larger-than-k/)
- [403. 青蛙过河](https://leetcode-cn.com/problems/frog-jump/)
- [410. 分割数组的最大值](https://leetcode-cn.com/problems/split-array-largest-sum/)
- [552. 学生出勤记录 II](https://leetcode-cn.com/problems/student-attendance-record-ii/)
- [621. 任务调度器](https://leetcode-cn.com/problems/task-scheduler/)
- [647. 回文子串](https://leetcode-cn.com/problems/palindromic-substrings/)
- [76. 最小覆盖子串](https://leetcode-cn.com/problems/minimum-window-substring/)
- [312. 戳气球](https://leetcode-cn.com/problems/burst-balloons/)

      

## 参考链接

- [养成收藏精选代码的习惯（示例）](http://shimo.im/docs/R6g9WJV89QkHrDhr)

