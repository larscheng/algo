//给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。 
//
// 测试用例的答案是一个 32-位 整数。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [2,3,-2,4]
//输出: 6
//解释: 子数组 [2,3] 有最大乘积 6。
// 
//
// 示例 2: 
//
// 
//输入: nums = [-2,0,-1]
//输出: 0
//解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 2 * 10⁴ 
// -10 <= nums[i] <= 10 
// nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数 
// 
//
// Related Topics 数组 动态规划 👍 2238 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L152_MaximumProductSubarray{
      
  public static void main(String[] args) {
       Solution solution = new L152_MaximumProductSubarray().new Solution();
      System.out.println(solution.maxProduct(new int[]{2, 3, -2, 4}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * [2,3,-2,4]
     * 假设dp[i]表示子数组num[0:i]中连续元素的最大乘积
     * dp[0]=num[0]
     * dp[1]=max(dp[0],dp[0]*num[1])
     * dp[i] = max(num[i],dp[i-1]*num[i])
     *
     * 但num[i]存在负数，如果num[i]为负数，肯定期望dp[i-1]是一个负数，或者是一个足够小的数
     * 所以ep[i]表示子数组num[0:i]中连续元素的最小乘积
     * ep[i] = min(num[i],ep[i-1]*num[i])
     */
    public int maxProduct(int[] nums) {
        int[] maxDp = new int[nums.length];
        int[] minDp = new int[nums.length];
        maxDp[0]=nums[0];
        minDp[0]=nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i]>=0){
                maxDp[i] = Math.max(nums[i],maxDp[i-1]*nums[i]);
                minDp[i] = Math.min(nums[i],minDp[i-1]*nums[i]);
            }else {
                //当前元素为负数，找上一轮最小结果，来计算最大乘积
                maxDp[i] = Math.max(nums[i],minDp[i-1]*nums[i]);
                //当前元素为负数，找上一轮最大结果，来计算最小乘积
                minDp[i] = Math.min(nums[i],maxDp[i-1]*nums[i]);
            }
        }
        int res = maxDp[0];
        for (int i = 1; i < nums.length; i++) {
            res = Math.max(res,maxDp[i]);
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}