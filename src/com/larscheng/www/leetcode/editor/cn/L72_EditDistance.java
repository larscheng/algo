//给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数 。 
//
// 你可以对一个单词进行如下三种操作： 
//
// 
// 插入一个字符 
// 删除一个字符 
// 替换一个字符 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：word1 = "horse", word2 = "ros"
//输出：3
//解释：
//horse -> rorse (将 'h' 替换为 'r')
//rorse -> rose (删除 'r')
//rose -> ros (删除 'e')
// 
//
// 示例 2： 
//
// 
//输入：word1 = "intention", word2 = "execution"
//输出：5
//解释：
//intention -> inention (删除 't')
//inention -> enention (将 'i' 替换为 'e')
//enention -> exention (将 'n' 替换为 'x')
//exention -> exection (将 'n' 替换为 'c')
//exection -> execution (插入 'u')
// 
//
// 
//
// 提示： 
//
// 
// 0 <= word1.length, word2.length <= 500 
// word1 和 word2 由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 👍 3392 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L72_EditDistance{
      
  public static void main(String[] args) {
       Solution solution = new L72_EditDistance().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 参考题解：https://leetcode.cn/problems/edit-distance/solutions/188814/dong-tai-gui-hua-java-by-liweiwei1419/
     *
     * dp[i][j]表示为以i-1字符结尾的word1与j-1字符结尾的word2的编辑距离
     * 根据i-1字符和j-1字符的值分情况处理
     * word1[i-1]==word2[j-1]时，与上一步操作距离一样，因为最新的两字符相同，所以dp[i][j] = dp[i-1][j-1]
     * word1[i-1]!=word2[j-1]时，可以通过增、删、换三种方式进行字符串的转换
     * - word1末尾增加字符word2[j-1],使得word1[i]==word2[j-1]，此时则要关注word1[0:i-1]与word2[0:j-2]的编辑距离,dp[i][j] = dp[i][j-1] +1
     * - word1最后一位字符删除，此时则要关注word1[0:i-2]与word2[0:j-1]的编辑距离,dp[i][j] = dp[i-1][j] +1
     * - word1最后一位字符替换为word2[j-1]，此时则要关注word1[0:i-2]与word2[0:j-2]的编辑距离,dp[i][j] = dp[i-1][j-1] +1
     *
     * 所以dp[i][j] = Min(dp[i-1][j-1],dp[i][j-1] +1,dp[i-1][j] +1,dp[i-1][j-1] +1)
     *
     * 临界值：当word1或者word2为空字符串时，编辑距离为目标字符串长度，例如word1="abc",word2="",编辑距离必定为3
     * dp[i][0]=i,dp[0][j]=j
     *
     * 可以通过一维数组优化空间复杂度
     *
     */
    public int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i <= len1; i++) {
            dp[i][0]=i;
        }
        for (int j = 0; j <= len2; j++) {
            dp[0][j]=j;
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i-1)==word2.charAt(j-1)){
                    dp[i][j] = dp[i-1][j-1];
                }else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}