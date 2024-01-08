//给你一个整数数组 cost ，其中 cost[i] 是从楼梯第 i 个台阶向上爬需要支付的费用。一旦你支付此费用，即可选择向上爬一个或者两个台阶。 
//
// 你可以选择从下标为 0 或下标为 1 的台阶开始爬楼梯。 
//
// 请你计算并返回达到楼梯顶部的最低花费。 
//
// 
//
// 示例 1： 
//
// 
//输入：cost = [10,15,20]
//输出：15
//解释：你将从下标为 1 的台阶开始。
//- 支付 15 ，向上爬两个台阶，到达楼梯顶部。
//总花费为 15 。
// 
//
// 示例 2： 
//
// 
//输入：cost = [1,100,1,1,1,100,1,1,100,1]
//输出：6
//解释：你将从下标为 0 的台阶开始。
//- 支付 1 ，向上爬两个台阶，到达下标为 2 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 4 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 6 的台阶。
//- 支付 1 ，向上爬一个台阶，到达下标为 7 的台阶。
//- 支付 1 ，向上爬两个台阶，到达下标为 9 的台阶。
//- 支付 1 ，向上爬一个台阶，到达楼梯顶部。
//总花费为 6 。
// 
//
// 
//
// 提示： 
//
// 
// 2 <= cost.length <= 1000 
// 0 <= cost[i] <= 999 
// 
//
// Related Topics 数组 动态规划 👍 1428 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L746_MinCostClimbingStairs{
      
  public static void main(String[] args) {
       Solution solution = new L746_MinCostClimbingStairs().new Solution();
       
  }

    /**
     *
     * 因为可以从0或1台阶开始爬，所以当i=0或1时,最小花费都为0
     * 因为每次可选择向上爬1个或2个台阶，所以到达顶部前，最后一步有以下2种情况
     * 如果最后一步从i-1开始，上爬1个台阶，则到达顶部的最小花费 dp(i) = dp(i-1) + cost[i-1]
     * 如果最后一步从i-2开始，上爬2个台阶，则到达顶部的最小花费 dp(i) = dp(i-2) + cost[i-2]
     *
     * 对这两种情况取最小值，就是爬到i层的最小花费
     * dp[i] = Min(dp(i-1) + cost[i-1],dp(i-2) + cost[i-2])
     * O(n)/O(n)
     *
     * 空间复杂度优化：
     * 观察方程式可发现，dp[i]计算出来后，dp[i-2]以前的数据就不会再用到
     * 所以每次循环只关心dp[i-1]和dp[i-2]，将其分别记做cur、pre，dp[i]记为next
     * next = Min(cur + cost[i-1],pre + cost[i-2])
     *
     * O(n)/O(1)
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int pre = 0;
        int cur = 0;
        for (int i = 2; i <= n; i++) {
            int next = Math.min(cur + cost[i - 1], pre + cost[i - 2]);
            pre = cur;
            cur = next;
        }
        return cur;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int minCostClimbingStairs(int[] cost) {
            int n = cost.length;
            int[] memo = new int[n + 1];
            for (int i = 2; i <= n; i++) {
                memo[i] = Math.min(
                        memo[i - 1] + cost[i - 1],
                        memo[i - 2] + cost[i - 2]
                );
            }
            return memo[n];
        }
    }
}