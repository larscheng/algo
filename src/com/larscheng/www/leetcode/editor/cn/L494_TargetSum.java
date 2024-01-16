//给你一个非负整数数组 nums 和一个整数 target 。 
//
// 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ： 
//
// 
// 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。 
// 
//
// 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,1,1,1], target = 3
//输出：5
//解释：一共有 5 种方法让最终目标和为 3 。
//-1 + 1 + 1 + 1 + 1 = 3
//+1 - 1 + 1 + 1 + 1 = 3
//+1 + 1 - 1 + 1 + 1 = 3
//+1 + 1 + 1 - 1 + 1 = 3
//+1 + 1 + 1 + 1 - 1 = 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [1], target = 1
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 20 
// 0 <= nums[i] <= 1000 
// 0 <= sum(nums[i]) <= 1000 
// -1000 <= target <= 1000 
// 
//
// Related Topics 数组 动态规划 回溯 👍 1840 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L494_TargetSum{
      
  public static void main(String[] args) {
       Solution solution = new L494_TargetSum().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 观察示例数据 nums = [1,1,1,1,1], target = 3，要满足构造结果等于target，nums数组要满足以下规律
     *
     * 数组之和sum，加-的数字之和neg，加+的数字之和sum-neg
     * sum-neg-neg=target【4 - 1=3】
     * sum - 2*neg=target【5-2*1=3】
     * neg =(sum-target)/2
     *
     * 前置条件：sum-target>0并且必须为偶数
     * 转化题意，
     * 只要我们能从nums中找到若干元素，并且和为规律中计算出的neg，那就说明存在一种符合条件的表达式方案
     *
     * 定义二维数组dp[n+1][neg+1],或者画表格，nums从i=1开始（i=0为边界值）
     * dp[i][j]表示在nums数组前i个元素中选取若干元素，且这些元素和为j的方案数
     * i=0时无元素，dp[0][j]: j=0,dp[0][0]=1; j>0,dp[0][j]=0
     *
     * i>0时有元素，对于num[i]存在选or不选
     * 如果nums[i] >j,当前元素大于j，选不了他，所以沿用i-1的方案为dp[i-1][j]
     * 如果nums[i]<=j,当前元素可选可不选
     * - 如果不选则方案为dp[i-1][j]
     * - 如果选他则方案为dp[i-1][j-nums[i]]
     * 此时的总方案为 dp[i-1][j] + dp[i-1][j-nums[i]]
     *
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = Arrays.stream(nums).sum();
        if (sum - target < 0 || (sum - target) % 2 != 0) {
            return 0;
        }
        int len = nums.length;
        int neg = (sum - target) / 2;
        int[][] dp = new int[len + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (num <= j) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[len][neg];
    }

}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * 回溯
     * 通过回溯遍历每个数字，对每个数字都会选择使用+或者-
     * 当回溯到最后一个数字，判断表达式的和sum是否等于target
     * O(2^n)/O(n)
     */
    int count = 0 ;
    public int findTargetSumWays(int[] nums, int target) {
        backtrace(nums,target,0,0);
        return count;
    }

    private void backtrace(int[] nums, int target, int index, int sum) {
        if (index == nums.length){
            if (sum==target){
                count++;
            }
        }else {
            backtrace(nums,target,index+1,sum+nums[index]);
            backtrace(nums,target,index+1,sum-nums[index]);
        }
    }
}

}