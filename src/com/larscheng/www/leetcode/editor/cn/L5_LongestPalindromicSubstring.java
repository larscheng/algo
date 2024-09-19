//给你一个字符串 s，找到 s 中最长的 回文 子串。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母组成 
// 
//
// Related Topics 双指针 字符串 动态规划 👍 7216 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L5_LongestPalindromicSubstring{
      
  public static void main(String[] args) {
       Solution solution = new L5_LongestPalindromicSubstring().new Solution();
      System.out.println(solution.longestPalindrome("babad"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /***
     *
     * 回文串中心点向外发散，aba、abca，中心点有两种情况奇数和偶数
     *
     * 遍历每一个元素，作为中心点，向两边扩散，找回文串，记录最长的回文串
     *
     *
     */
    public String longestPalindrome(String s) {
        String res = "";

        for (int i = 0; i < s.length(); i++) {
            String s1 = findStr(s,i,i);
            String s2 = findStr(s,i,i+1);
            res = res.length()>s1.length()?res:s1;
            res = res.length()>s2.length()?res:s2;
        }
        return res;
    }

    private String findStr(String s, int left, int right) {
        //找到回文串之后，会继续扩展，如果下一个不是回文串，结束while
        while (left>=0&&right<=s.length()-1&&s.charAt(left)==s.charAt(right)) {
            left--;
            right++;
        }
        //结束while之前上一轮是回文串left+1，substring左闭右开，right不用-1
        return s.substring(left+1, right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution3 {
        /**
         * 动态规划
         * 串aba是回文串，cabac也一定是回文串
         * dp[i][j]表示字符串s[i:j]是否是回文串
         * 字符串s[i:j]的长度为len=j-i+1
         * 如果len<=2,dp[i][j] = (s[i]==s[j])
         * 如果len >2,dp[i][j] = (s[i]==s[j]) && dp[i+1][j-1]
         * O(n*n)/O(n*n)
         */
        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return null;
            }
            if (s.length()==1){
                return s;
            }
            int start=0;
            int maxLen = 0;
            boolean[][] dp = new boolean[s.length()][s.length()];

            for (int i = s.length()-1; i >=0 ; i--) {
                for (int j = i; j < s.length() ; j++) {
                    int len = j-i+1;
                    //如果len<=2,dp[i][j] = (s[i]==s[j])
                    //如果len >2,dp[i][j] = (s[i]==s[j]) && dp[i+1][j-1]
                    dp[i][j] = (s.charAt(i)==s.charAt(j)) && (len <= 2 || dp[i + 1][j - 1]);
                    if (dp[i][j] && len > maxLen) {
                        maxLen = len;
                        start = i;
                    }
                }
            }
            return s.substring(start, start + maxLen);
        }
    }

    class Solution2 {
        /**
         * 中心展开
         *
         * 回文串分为奇数串和偶数串
         * 奇数串：可以把每一个字符作为回文串的中心位置向两边扩展，i(i,i),单独字符也是回文串
         * 偶数串：可以把两个字符中的间隙作为回文串中心向两边扩展，i(i,i+1)
         * 当left>=0&&right<s.length&&s[left]==s[right]时，回文串的长度为right-left-1
         * O(n*n)/O(1)
         *
         */
        public String longestPalindrome(String s) {
            if (s == null || s.isEmpty()) {
                return null;
            }
            if (s.length()==1){
                return s;
            }
            int start=0,end=0;
            int maxLen = 0;
            for (int i = 0; i < s.length(); i++) {
                //奇数串，从当前位置为中心
                int len1 = expandCenter(s,i,i);
                //偶数串，从当前位置的间隙为中心
                int len2 = expandCenter(s,i,i+1);
                maxLen = Math.max(len1,len2);
                //maxLen > end - start 表示只记录首个最长回文串
                //maxLen > end - start+1 表示只记录最后一个最长回文串
                if (maxLen > end - start+1) {
                    start = i-(maxLen-1)/2;
                    end = i+maxLen/2;
                }
            }
            return s.substring(start, end + 1);
        }

        private int expandCenter(String s, int left, int right) {
            while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
                left--;
                right++;
            }
            // 1-(-1)-1 = 1
            return right - left - 1;
        }
    }

    class Solution1 {
        public String longestPalindrome(String s) {
            if (s==null|| s.isEmpty()){
                return null;
            }
            if (s.length()==1){
                return s;
            }
            int strLen = s.length();
            int len = 1;
            int maxLen = 0;
            int left, right = 0;
            int start = 0;
            for (int i = 0; i < strLen; i++) {
                left = i - 1;
                right = i + 1;
                //向左扩展
                while (left >= 0 && s.charAt(left) == s.charAt(i)) {
                    len++;
                    left--;
                }
                //向右扩展
                while (right<strLen && s.charAt(right)==s.charAt(i)){
                    len++;
                    right++;
                }
                while (left>=0&&right<strLen&&s.charAt(left)==s.charAt(right)){
                    len+=2;
                    left--;
                    right++;
                }
                if (len>maxLen){
                    maxLen = len;
                    start = left;
                }
                len = 1;
            }
            return s.substring(start+1, start + maxLen + 1);
        }
    }
}