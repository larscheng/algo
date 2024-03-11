//给你一个只包含三种字符的字符串，支持的字符类型分别是 '('、')' 和 '*'。请你检验这个字符串是否为有效字符串，如果是有效字符串返回 true 。 
//
// 有效字符串符合如下规则： 
//
// 
// 任何左括号 '(' 必须有相应的右括号 ')'。 
// 任何右括号 ')' 必须有相应的左括号 '(' 。 
// 左括号 '(' 必须在对应的右括号之前 ')'。 
// '*' 可以被视为单个右括号 ')' ，或单个左括号 '(' ，或一个空字符串。 
// 一个空字符串也被视为有效字符串。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "()"
//输出：true
// 
//
// 示例 2： 
//
// 
//输入：s = "(*)"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(*))"
//输出：true
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 100 
// s[i] 为 '('、')' 或 '*' 
// 
//
// Related Topics 栈 贪心 字符串 动态规划 👍 624 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L678_ValidParenthesisString{
      
  public static void main(String[] args) {
       Solution solution = new L678_ValidParenthesisString().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean checkValidString(String s) {
        //左括号栈
        Stack<Integer> leftStack = new Stack<>();
        //星号栈
        Stack<Integer> startStack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='('){
                leftStack.push(i);
            }else if (s.charAt(i)=='*'){
                startStack.push(i);
            }else {
                //当前元素为右括号，先从左括号栈弹出进行匹配，再从星号栈中弹出进行匹配
                if (!leftStack.isEmpty()) {
                    leftStack.pop();
                } else if (!startStack.isEmpty()) {
                    startStack.pop();
                } else {
                    //如果两个栈都为空，则不是有效字符串
                    return false;
                }
            }
        }
        //遍历完之后，栈内可能会剩下元素，部分左括号和部分星号,使用*替代右括号进行匹配
        while (!leftStack.isEmpty()&&!startStack.isEmpty()){
            Integer leftIndex = leftStack.pop();
            Integer startIndex = startStack.pop();
            //左括号必须要在星号之前
            if (leftIndex>startIndex){
                return false;
            }
        }

        return leftStack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}