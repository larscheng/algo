//给你一个整数数组 nums ，返回数组中最大数和最小数的 最大公约数 。 
//
// 两个数的 最大公约数 是能够被两个数整除的最大正整数。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [2,5,6,9,10]
//输出：2
//解释：
//nums 中最小的数是 2
//nums 中最大的数是 10
//2 和 10 的最大公约数是 2
// 
//
// 示例 2： 
//
// 输入：nums = [7,5,6,8,3]
//输出：1
//解释：
//nums 中最小的数是 3
//nums 中最大的数是 8
//3 和 8 的最大公约数是 1
// 
//
// 示例 3： 
//
// 输入：nums = [3,3]
//输出：3
//解释：
//nums 中最小的数是 3
//nums 中最大的数是 3
//3 和 3 的最大公约数是 3
// 
//
// 
//
// 提示： 
//
// 
// 2 <= nums.length <= 1000 
// 1 <= nums[i] <= 1000 
// 
//
// Related Topics 数组 数学 数论 👍 44 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.Collection;

public class L1979_FindGreatestCommonDivisorOfArray{
      
  public static void main(String[] args) {
       Solution solution = new L1979_FindGreatestCommonDivisorOfArray().new Solution();
      System.out.println(solution.findGCD(new int[]{2,5,6,9,10}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int num : nums) {
            if (num > max) max = num;
            if (num < min) min = num;
        }

        int temp;
        // 5%3=1,3%1=0          result=1
        // 8%3=2,3%2=1,2%1=0    result=1
        // 10%2=0               result=2
        while (max % min != 0) {
            temp = min;
            min = max % min;
            max = temp;
        }
        return min;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int findGCD(int[] nums) {
            int max = Arrays.stream(nums).max().getAsInt();
            int min = Arrays.stream(nums).min().getAsInt();
            int num = 0;
            for (int i = 1; i <= min; i++) {
                if (max % i == 0 && min % i == 0) {
                    num = i;
                }
            }
            return num;
        }
    }

    class Solution2 {
        public int findGCD(int[] nums) {
            Arrays.sort(nums);
            int num = 0;
            for (int i = 1; i <= nums[0]; i++) {
                if (nums[0] % i == 0 && nums[nums.length - 1] % i == 0) {
                    num = i;
                }
            }
            return num;
        }
    }
}