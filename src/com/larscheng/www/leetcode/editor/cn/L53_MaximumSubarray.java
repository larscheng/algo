//给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。 
//
// 子数组 是数组中的一个连续部分。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
//输出：6
//解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：nums = [5,4,-1,7,8]
//输出：23
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁵ 
// -10⁴ <= nums[i] <= 10⁴ 
// 
//
// 
//
// 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。 
//
// Related Topics 数组 分治 动态规划 👍 6568 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L53_MaximumSubarray{
      
  public static void main(String[] args) {
       Solution solution = new L53_MaximumSubarray().new Solution();
      System.out.println(solution.maxSubArray(new int[]{-2, 1, -3, 4, -1, 2, 1, -5, 4}));
//      System.out.println(solution.maxSubArray(new int[]{-1}));
//      System.out.println(solution.maxSubArray(new int[]{-2,1}));
//      System.out.println(solution.maxSubArray(new int[]{-1,-2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxSubArray(int[] nums) {

        // 当dp[i-1]>0时，dp[i] = dp[i-1]+nums[i]
        // 当dp[i-1]<=0时，dp[i] = nums[i]
        // 所以dp[i]最终是在这两种情况中取最大值
        // dp[i] = max(dp[i-1]+nums[i],nums[i])
        // 利用滚动变量优化动态规划代码
        // O(n)/O(1)
        int pre = 0;
        int res = nums[0];
        for (int num : nums) {
            //dp[i] = max(dp[i-1]+nums[i],nums[i])
            pre = Math.max(pre + num, num);

            res = Math.max(res, pre);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    public int maxSubArray(int[] nums) {
        //动态规划 定义状态(子问题)->状态转移方程
        //子问题：
        // nums[i]结尾的连续子数组与nums[i-1]结尾的连续子数组只差了一个nums[i]
        // dp[i]表示以nums[i]结尾的连续子数组的最大和，理想状态下则有dp[i] = dp[i-1]+nums[i]
        // 因为dp[i-1]存在两种情况，所以
        //状态转移方程：
        // 当dp[i-1]>0时，dp[i] = dp[i-1]+nums[i]
        // 当dp[i-1]<=0时，dp[i] = nums[i]
        // 最终取dp[]中的最大值
        // O(n)/O(n)
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
            res = Math.max(dp[i], res);
        }
        return res;
    }
}
    //暴力2层循环，复杂度O(n^2)超时
    class Solution1 {
        public int maxSubArray(int[] nums) {
            int res = Integer.MIN_VALUE;
            for (int i = 0; i < nums.length; i++) {
                int sum = nums[i];
                res = Math.max(res, sum);
                for (int j = i + 1; j < nums.length; j++) {
                    res = Math.max(res, sum += nums[j]);
                }
            }
            return  res;
        }
    }
}