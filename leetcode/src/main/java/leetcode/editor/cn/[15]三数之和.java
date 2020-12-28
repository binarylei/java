//给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有满足条件且不重复
//的三元组。 
//
// 注意：答案中不可以包含重复的三元组。 
//
// 
//
// 示例： 
//
// 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
//
//满足要求的三元组集合为：
//[
//  [-1, 0, 1],
//  [-1, -1, 2]
//]
// 
// Related Topics 数组 双指针


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

//leetcode submit region begin(Prohibit modification and deletion)
// 双指针夹逼法：https://leetcode-cn.com/problems/3sum/solution/hua-jie-suan-fa-15-san-shu-zhi-he-by-guanpengchn/
class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) break;
            if (i > 0 && nums[i] == nums[i - 1]) continue;           // i去重

            int l = i + 1;
            int r = nums.length - 1;
            while (l < r) {
                int sum = nums[i] + nums[l] + nums[r];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    while (l < r && nums[l] == nums[l + 1]) l++;    // l去重
                    while (l < r && nums[r] == nums[r - 1]) r--;    // r去重
                    l++;
                    r--;
                } else if (sum < 0) l++;
                else r--;
            }
        }
        return result;
    }
}


// 方法一：暴力求解
//class Solution {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Map<String, List<Integer>> resultMap = new HashMap<>();
//        for (int i = 0; i < nums.length - 2; i++) {
//            for (int j = i + 1; j < nums.length - 1; j++) {
//                for (int k = j + 1; k < nums.length; k++) {
//                    if (0 == nums[i] + nums[j] + nums[k]) {
//                        List<Integer> result = Arrays.asList(nums[i], nums[j], nums[k]);
//                        Collections.sort(result);
//                        String resultKey = String.format("%d-%d-%d",
//                                result.get(0), result.get(1), result.get(2));
//                        if (!resultMap.containsKey(resultKey)) {  // 过滤重复解
//                            resultMap.put(resultKey, result);
//                        }
//                    }
//                }
//            }
//        }
//        return new ArrayList<>(resultMap.values());
//    }
//}

// 方法二：HashMap存储num位置，但有重复元素可能会有问题
//class Solution {
//    public List<List<Integer>> threeSum(int[] nums) {
//        Map<String, List<Integer>> resultMap = new HashMap<>();
//        Map<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length - 1; i++) {
//            for (int j = i + 1; j < nums.length; j++) {
//                int value = -nums[i] - nums[j];      // 转化为twoSum
//                Integer index = map.get(value);
//
//                List<Integer> result = Arrays.asList(nums[i], value, nums[j]);
//                Collections.sort(result);
//                String resultKey = String.format("%d-%d-%d",
//                        result.get(0), result.get(1), result.get(2));
//                if (resultMap.containsKey(resultKey)) {  // 过滤重复解
//                    continue;
//                }
//
//                if (index != null && index != i && index != j) { // 命中
//                    resultMap.put(resultKey, result);
//                }
//                map.put(nums[j], j);
//            }
//            map.put(nums[i], i);
//        }
//        return new ArrayList<>(resultMap.values());
//    }
//}
//leetcode submit region end(Prohibit modification and deletion)
