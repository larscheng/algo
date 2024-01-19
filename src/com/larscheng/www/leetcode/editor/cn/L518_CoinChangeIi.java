//给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。 
//
// 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。 
//
// 假设每一种面额的硬币有无限个。 
//
// 题目数据保证结果符合 32 位带符号整数。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：amount = 5, coins = [1, 2, 5]
//输出：4
//解释：有四种方式可以凑成总金额：
//5=5
//5=2+2+1
//5=2+1+1+1
//5=1+1+1+1+1
// 
//
// 示例 2： 
//
// 
//输入：amount = 3, coins = [2]
//输出：0
//解释：只用面额 2 的硬币不能凑成总金额 3 。
// 
//
// 示例 3： 
//
// 
//输入：amount = 10, coins = [10] 
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= coins.length <= 300 
// 1 <= coins[i] <= 5000 
// coins 中的所有值 互不相同 
// 0 <= amount <= 5000 
// 
//
// Related Topics 数组 动态规划 👍 1215 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L518_CoinChangeIi{
      
  public static void main(String[] args) {
       Solution solution = new L518_CoinChangeIi().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * 数组dp[i][j] 表示从前i个硬币中凑出总金额j，共有dp[i][j]种组合方式
     * i=0，j=0，dp[0][0]=1，凑出总金额0，不需要任何硬币，且只有1中组合方式
     * i=0，j>0，dp[0][j]=0，没有可选硬币，无有效的组合方式
     * 对于第i个硬币，
     * 如果不使用它，dp[i][j] = dp[i-1][j]
     * 如果用它，因为硬币可重复选择，此时第i个硬币可能会被选择k次
     *  假设选择coin[i]1次，存在组合数就等于 dp[i-1][j-coin[i]*1]
     *  假设选择coin[i]2次，存在组合数就等于 dp[i-1][j-coin[i]*2]
     *  假设选择coin[i]k次，存在组合数就等于 dp[i-1][j-coin[i]*k]
     *  可选的次数k受j的限制，coins[i] * k <= j
     * 即dp[i][j] += dp[i-1][j-coins[i]*k]
     *
     *
     *
     *
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                //不选coin时
                dp[i][j] = dp[i - 1][j];
                //选择coin时
                for (int k = 1; k * coin <= j; k++) {
                    dp[i][j] += dp[i - 1][j - coin * k];
                }
            }
        }
        return dp[len][amount];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     *
     * 数组dp[i][j] 表示从前i个硬币中凑出总金额j，共有dp[i][j]种组合方式
     * i=0，j=0，dp[0][0]=1，凑出总金额0，不需要任何硬币，且只有1中组合方式
     * i=0，j>0，dp[0][j]=0，没有可选硬币，无有效的组合方式
     * 对于第i个硬币，
     * 如果不使用它，dp[i][j] = dp[i-1][j]
     * 如果用它，因为硬币可重复选择，此时第i个硬币可能会被选择k次
     *  假设选择coin[i]1次，存在组合数就等于 dp[i-1][j-coin[i]*1]
     *  假设选择coin[i]2次，存在组合数就等于 dp[i-1][j-coin[i]*2]
     *  假设选择coin[i]k次，存在组合数就等于 dp[i-1][j-coin[i]*k]
     *  可选的次数k受j的限制，coins[i] * k <= j
     * 即dp[i][j] += dp[i-1][j-coins[i]*k]
     *
     * 最终简化为 dp[i][j] = dp[i-1][j]+ dp[i][j-coins[i]]
     *
     *
     *
     *
     * @param amount
     * @param coins
     * @return
     */
    public int change(int amount, int[] coins) {
        int len = coins.length;
        int[][] dp = new int[len + 1][amount + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= len; i++) {
            int coin = coins[i - 1];
            for (int j = 0; j <= amount; j++) {
                if (coin > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - coin];
                }
            }
        }
        return dp[len][amount];
    }
}

}