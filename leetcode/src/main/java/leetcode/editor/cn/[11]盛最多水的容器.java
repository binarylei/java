//给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, 
//ai) 和 (i, 0)。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 说明：你不能倾斜容器，且 n 的值至少为 2。 
//
// 
//
// 
//
// 图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 
//
// 示例： 
//
// 输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
// Related Topics 数组 双指针


//leetcode submit region begin(Prohibit modification and deletion)
// 数组遍历，双指针(左右边界中间收敛：左右夹逼)。时间复杂度 O(n)
class Solution {
    public int maxArea(int[] height) {
        int maxArea = 0;
        int i = 0;
        int j = height.length - 1;
        while (i < j) {
            int area = (j - i) * Math.min(height[i], height[j]);
            maxArea = Math.max(area, maxArea);
            if (height[i] < height[j]) {
                i++;
            } else {
                j--;
            }
        }
        return maxArea;
    }
}

// 数组遍历，注意外层循环和内层循环的开始和结束位置。时间复杂度 O(n^2)
//class Solution {
//    public int maxArea(int[] height) {
//        int maxArea = 0;
//        for (int i = 0; i < height.length - 1; i++) {
//            for (int j = i + 1; j < height.length; j++) {
//                int area = (j - i) * Math.min(height[i], height[j]);
//                maxArea = Math.max(area, maxArea);
//            }
//        }
//        return maxArea;
//    }
//}

//leetcode submit region end(Prohibit modification and deletion)
