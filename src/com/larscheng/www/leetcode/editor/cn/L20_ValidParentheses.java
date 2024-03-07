//给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。 
//
// 有效字符串需满足： 
//
// 
// 左括号必须用相同类型的右括号闭合。 
// 左括号必须以正确的顺序闭合。 
// 每个右括号都有一个对应的相同类型的左括号。 
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
//输入：s = "()[]{}"
//输出：true
// 
//
// 示例 3： 
//
// 
//输入：s = "(]"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由括号 '()[]{}' 组成 
// 
//
// Related Topics 栈 字符串 👍 4370 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class L20_ValidParentheses{
      
  public static void main(String[] args) {
       Solution solution = new L20_ValidParentheses().new Solution();
      System.out.println(solution.isValid("()[]{}"));
//      System.out.println(solution.isValid("{[]}"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean isValid(String s) {
        if (s.length() % 2 == 1) {
            return false;
        }
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> map = new HashMap<>() {
            {
                put(')', '(');
                put(']', '[');
                put('}', '{');
            }
        };

        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                if (stack.isEmpty() || !stack.peek().equals(map.get(s.charAt(i)))) {
                    return false;
                }
                stack.pop();
            }else {
                stack.push(s.charAt(i));

            }
        }
        return stack.isEmpty();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public boolean isValid(String s) {
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < s.length(); i++) {
                if (!stack.isEmpty() && ')' == s.charAt(i) && stack.pop() == '(') {
                    continue;
                } else if (!stack.isEmpty() && '}' == s.charAt(i) && stack.pop() == '{') {
                    continue;
                } else if (!stack.isEmpty() && ']' == s.charAt(i) && stack.pop() == '[') {
                    continue;
                } else {
                    stack.push(s.charAt(i));
                }
            }
            return stack.isEmpty();
        }
    }
}