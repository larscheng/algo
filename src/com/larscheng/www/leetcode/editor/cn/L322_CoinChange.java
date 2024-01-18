//给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。 
//
// 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。 
//
// 你可以认为每种硬币的数量是无限的。 
//
// 
//
// 示例 1： 
//
// 
//输入：coins = [1, 2, 5], amount = 11
//输出：3 
//解释：11 = 5 + 5 + 1 
//
// 示例 2： 
//
// 
//输入：coins = [2], amount = 3
//输出：-1 
//
// 示例 3： 
//
// 
//输入：coins = [1], amount = 0
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 12 
// 1 <= coins[i] <= 2³¹ - 1 
// 0 <= amount <= 10⁴ 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 👍 2682 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L322_CoinChange{
      
  public static void main(String[] args) {
       Solution solution = new L322_CoinChange().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /***
     *
     * 定义dp数组 dp[amount+1] dp[i]表示，凑出面值i，最少需要dp[i]枚硬币
     * i<0时，dp[i]=-1
     * i=0时，dp[i]=0  即目标金额为0，不需要任何硬币
     * i>0，并且i-coins[j]>0时, dp[i]= min(dp[i],dp[i-coin[j]]+1)
     *
     *
     *
     *
     *
     * @param coins
     * @param amount
     * @return
     */
    int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);

        dp[0] = 0;
        for (int i = 0; i < dp.length; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], 1 + dp[i - coin]);
                }
            }
        }
        return (dp[amount] == amount + 1) ? -1 : dp[amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}