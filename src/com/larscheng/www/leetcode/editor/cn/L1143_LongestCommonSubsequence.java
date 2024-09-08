//给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。 
//
// 一个字符串的 子序列 是指这样一个新的字符串：它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。 
//
// 
// 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。 
// 
//
// 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：text1 = "abcde", text2 = "ace" 
//输出：3  
//解释：最长公共子序列是 "ace" ，它的长度为 3 。
// 
//
// 示例 2： 
//
// 
//输入：text1 = "abc", text2 = "abc"
//输出：3
//解释：最长公共子序列是 "abc" ，它的长度为 3 。
// 
//
// 示例 3： 
//
// 
//输入：text1 = "abc", text2 = "def"
//输出：0
//解释：两个字符串没有公共子序列，返回 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text1.length, text2.length <= 1000 
// text1 和 text2 仅由小写英文字符组成。 
// 
//
// Related Topics 字符串 动态规划 👍 1496 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1143_LongestCommonSubsequence{
      
  public static void main(String[] args) {
       Solution solution = new L1143_LongestCommonSubsequence().new Solution();
      System.out.println(solution.longestCommonSubsequence("abc", "aea"));
  }

    /**
     * dp[i][j] 代表text1前i个字符text1[0:i-1]，与text2前j个字符text2[0:j-1] 的最长公共子序列长度
     * 当text[i-1]==text[j-1]时,说明两个字符串最后一个字符相同
     *  此时在text1前i-1个字符，与text2前j-1个字符的最长公共子序列长度(dp[i-1][j-1])的基础上+1即可
     *  dp[i][j] = dp[i-1][j-1] + 1
     * 当text[i-1]!=text[j-1]时,说明两个字符串最后一个字符不同
     *  此时需要考虑两种情况
     *   text1前i-1个字符 与 text2前j个字符的 最大公共子序列长度  dp[i-1][j]
     *   text1前i个字符 与 text2前j-1个字符的 最大公共子序列长度  dp[i][j-1]
     *  取两者最大值即可 dp[i][j] = Max(dp[i-1][j], dp[i][j-1])
     *
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            char c1 = text1.charAt(i - 1);
            for (int j = 1; j <= n; j++) {
                char c2 = text2.charAt(j - 1);
                if (c1 == c2) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[m][n];
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}