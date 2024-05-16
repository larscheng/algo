//给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。 
//
// 
//
// 
// 
// 示例 1： 
// 
// 
//
// 
//输入：s = "(()"
//输出：2
//解释：最长有效括号子串是 "()"
// 
//
// 示例 2： 
//
// 
//输入：s = ")()())"
//输出：4
//解释：最长有效括号子串是 "()()"
// 
//
// 示例 3： 
//
// 
//输入：s = ""
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 3 * 10⁴ 
// s[i] 为 '(' 或 ')' 
// 
//
// Related Topics 栈 字符串 动态规划 👍 2506 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Stack;

public class L32_LongestValidParentheses{
      
  public static void main(String[] args) {
       Solution solution = new L32_LongestValidParentheses().new Solution();
      System.out.println(solution.longestValidParentheses("()((())"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * dp[i] 表示以下标i字符结尾的最长连续括号长度
     * - s[i]=')'并且s[i-1]='('时，....()，dp[i] = dp[i-2]+2
     * - s[i]=')'并且s[i-1]=')'时，....))，如果s[i-dp[i-1]-1]='(',
     * 则dp[i] = dp[i-1]+dp[i-dp[i-1]-2]+2
     *
     * dp[0]=0
     */
    public int longestValidParentheses(String s) {
        int res = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length() ; i++) {
            if (s.charAt(i)==')'){
                if (s.charAt(i - 1) == '(') {
                    //临界值i-2>=0
                    dp[i] = (i - 2 >= 0 ? dp[i - 2] : 0) + 2;
                } else if (i - dp[i - 1] - 1 >= 0 && s.charAt(i - dp[i - 1] - 1) == '(') {
                    dp[i] = dp[i - 1] + ((i - dp[i - 1] - 2 >= 0) ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                res = Math.max(res,dp[i]);
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        /**
         * 栈
         * 遇到（ ：下标入栈
         * 遇到 ）：
         * - 栈空：重新开始计算最长连续长度，定义start=i（start默认值为-1）
         * - 非空：弹出栈顶值(temp)与当前下标(i)匹配成功
         *      - 当前栈为空，max(i-start,res)
         *      - 当前栈不空，max(i-k,res),k为当前栈顶值
         */
        public int longestValidParentheses(String s) {
            Stack<Integer> stack = new Stack<>();
            int res = 0;
            int start = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)=='('){
                    stack.push(i);
                }else {
                    if (stack.isEmpty()){
                        start = i;
                    }else {
                        stack.pop();
                        if (stack.isEmpty()) {
                            res = Math.max(i - start, res);
                        } else {
                            res = Math.max(i - stack.peek(), res);
                        }
                    }
                }
            }
            return res;
        }
    }
class Solution1 {
    /**
     * 栈
     * 创建一个标记数组，用于标记匹配成功的下标
     * 遍历字符，匹配成功的下标在标记数组中置为1
     * 遍历标记数组，统计最长的连续被标记个数
     * ps：将匹配和连续分离
     * O(n)/O(n)
     */
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int[] temp = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch=='('){
                stack.push(i);
            }else if (!stack.isEmpty()){
                Integer index = stack.pop();
                if (s.charAt(index)=='('){
                    temp[i]=temp[index]=1;
                }
            }
        }
        int count =0;
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (temp[i]==1){
                count++;
            }else {
                res = Math.max(res,count);
                count=0;
            }
        }
        return Math.max(res,count);
    }
}

}