//给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。 
//
// 示例: 
//
// 输入: [0,1,0,3,12]
//输出: [1,3,12,0,0] 
//
// 说明: 
//
// 
// 必须在原数组上操作，不能拷贝额外的数组。 
// 尽量减少操作次数。 
// 
// Related Topics 数组 双指针

/**
 * 双指针法：i 指向第一个非0元素，j 指向第一个0元素
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 快慢指针：快指针 i 指向第一个非0元素，慢指针 j 指向第一个0元素
    public void moveZeroes(int[] nums) {
        int j = 0;  // 指向第一个0的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != j) {
                    nums[j] = nums[i];
                    nums[i] = 0;
                }
                j++;
            }
        }
    }

    public void moveZeroes1(int[] nums) {
        int j = -1;  // 指向第一个0的位置
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && j == -1) {
                j = i;
            } else if (nums[i] != 0 && j != -1) {
                nums[j] = nums[i];
                nums[i] = 0;
                j++;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
