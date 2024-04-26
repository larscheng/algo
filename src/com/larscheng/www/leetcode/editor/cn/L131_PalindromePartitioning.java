//给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串 。返回 s 所有可能的分割方案。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "aab"
//输出：[["a","a","b"],["aa","b"]]
// 
//
// 示例 2： 
//
// 
//输入：s = "a"
//输出：[["a"]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 16 
// s 仅由小写英文字母组成 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 1777 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L131_PalindromePartitioning{
      
  public static void main(String[] args) {
       Solution solution = new L131_PalindromePartitioning().new Solution();
      System.out.println(solution.partition("aab"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //回文串特性，abccba是回文串，首位字符相同，去除首位，bccb仍为回文串
     // 收集到dp数组的值后，通过回溯收集所有回文串
    public List<List<String>> partition(String s) {
        int length = s.length();
        //dp[i][j]表示s[i:j]子串是否为回文串
        boolean[][] dp = new boolean[length][length];
        for (int i = 0; i < length; i++) {
            //长度为1的串必为回文串
            dp[i][i]=true;
            if (i>=1){
                //空字符串是回文串
                dp[i][i-1]=true;
            }
        }

        for (int i = length-1; i >=0; i--) {
            for (int j = i+1; j <length ; j++) {
                if (s.charAt(i)==s.charAt(j)){
                    dp[i][j]=dp[i+1][j-1];
                }else {
                    dp[i][j]=false;
                }
            }
        }
        List<List<String>> res = new ArrayList<>();
        backtrack(dp,s,length,new ArrayList<>(),res,0);
        return res;

    }

    private void backtrack(boolean[][] dp, String str, int length, List<String> temp, List<List<String>> res, int index) {
        if (index == length){
            res.add(new ArrayList<>(temp));
        }

        for (int i = index; i < length; i++) {
            if (dp[index][i]){
                temp.add(str.substring(index, i + 1));
                backtrack(dp, str, length, temp, res, i + 1);
                temp.remove(temp.size()-1);
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}