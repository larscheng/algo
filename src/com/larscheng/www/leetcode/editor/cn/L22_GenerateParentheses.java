//数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 3
//输出：["((()))","(()())","(())()","()(())","()()()"]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：["()"]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 8 
// 
//
// Related Topics 字符串 动态规划 回溯 👍 3563 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L22_GenerateParentheses{
      
  public static void main(String[] args) {
       Solution solution = new L22_GenerateParentheses().new Solution();
       Solution1 solution1 = new L22_GenerateParentheses().new Solution1();
      System.out.println(solution.generateParenthesis(3));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    // 记录左括号，右括号的数量，递归构建合法的括号字符串
    // 当左括号小于n，递归追加左括号，回溯时重置状态
    // 当右括号小于左括号数量，递归追加右括号，回溯时重置状态
    // 终止条件：括号字符串长度==2n
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        backtrack(res, new StringBuilder(), 0, 0, n);
        return res;
    }

    private void backtrack(List<String> res, StringBuilder cur, int left, int right, int max) {
        if(cur.length()==max*2){
            res.add(cur.toString());
            return;
        }
        if (left < max) {
            cur.append("(");
            backtrack(res, cur, left + 1, right, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (right < left) {
            cur.append(")");
            backtrack(res, cur, left, right + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    // 暴力递归
    // 递归生成完整数量的括号字符数组，递归过程中校验每轮的括号数组是否有效
    public List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        createAll(new char[2*n],0,res);
        return res;
    }

    private void createAll(char[] chars, int index, List<String> res) {
        if (index==chars.length){
            if (valid(chars)){
                res.add(new String(chars));
            }
        }else {
            chars[index]='(';
            createAll(chars,index+1,res);

            chars[index]=')';
            createAll(chars,index+1,res);
        }
    }

    private boolean valid(char[] chars) {
        int blacne = 0;
        for (char c : chars) {
            if (c=='('){
                ++blacne;
            }else {
                --blacne;
            }
            if (blacne<0){
                return false;
            }
        }
        return blacne==0;
    }
}

}