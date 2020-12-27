/**
 * @author binarylei
 * @version 2020-12-25
 */
package com.binarylei.algorithm.algo.recursive;

/**
 * <pre>
 * // 递归模板
 * public void recur(int level, int param) {
 *   // 1. 递归终止条件：terminator
 *   if (level > MAX_LEVEL) {
 *     // process result
 *     return;
 *   }
 *
 *   // 2. 处理当前层：process current logic
 *   process(level, param);
 *
 *   // 3. 向下一层递归：drill down
 *   recur(level: level + 1, newParam);
 *
 *   // 4. 清理环境：restore current status
 * }
 * </pre>
 */