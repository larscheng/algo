//给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 target 的那 两个 整数，并返回它们的数组下标。 
//
// 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。 
//
// 你可以按任意顺序返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,7,11,15], target = 9
//输出：[0,1]
//解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,4], target = 6
//输出：[1,2]
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,3], target = 6
//输出：[0,1]
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 10⁴ 
// -10⁹ <= nums[i] <= 10⁹ 
// -10⁹ <= target <= 10⁹ 
// 只会存在一个有效答案 
// 
//
// 
//
// 进阶：你可以想出一个时间复杂度小于 O(n²) 的算法吗？ 
//
// Related Topics 数组 哈希表 👍 17826 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L1_TwoSum{
      
  public static void main(String[] args) {
      //Solution solution = new L1_TwoSum().new Solution();
      //Solution1 solution = new L1_TwoSum().new Solution1();
      //Solution2 solution = new L1_TwoSum().new Solution2();
       Solution3 solution = new L1_TwoSum().new Solution3();
      int[] ints = solution.twoSum(new int[]{3, 2, 4}, 6);
      for (int i = 0; i < ints.length; i++) {
          System.out.println(ints[i]);
      }
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] twoSum(int[] nums, int target) {
        //3,2,4:2结束
        for (int i = 0; i < nums.length-1; i++) {
            //3,2,4:4结束
            for (int j = i+1; j < nums.length; j++) {
                if (nums[i]+nums[j]==target){
                    return new int[]{i,j};
                }
            }
        }
        return null;
    }
}
class Solution1 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i],i);
        }
        for (int i = 0; i < nums.length; i++) {
            int num = target - nums[i];

            if (map.containsKey(num) && map.get(target - nums[i]) != i) {
                return new int[]{i, map.get(target - nums[i])};
            }
        }
        return null;
    }
}
class Solution2 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = target-nums[i];
            if (map.containsKey(num)) {
                return new int[]{i, map.get(num)};
            } else {
                map.put(nums[i], i);
            }
        }
        return null;
    }
}
class Solution3 {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int left = 0;
        int right = nums.length-1;
        while (left<=right){
            int left1 = nums[left];
            int right1 = target-left1;

            int right2 = nums[right];
            int left2 = target-right2;
            if (map.containsKey(right1)){
                return new int[]{left,map.get(right1)};
            }else {
                map.put(left1,left);
                left++;
            }
            if (map.containsKey(left2)){
                return new int[]{right,map.get(left2)};
            }else {
                map.put(right2,right);
                right--;
            }
        }
        return null;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}