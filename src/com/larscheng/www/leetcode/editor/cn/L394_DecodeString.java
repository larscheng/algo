//给定一个经过编码的字符串，返回它解码后的字符串。 
//
// 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。 
//
// 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。 
//
// 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "3[a]2[bc]"
//输出："aaabcbc"
// 
//
// 示例 2： 
//
// 
//输入：s = "3[a2[c]]"
//输出："accaccacc"
// 
//
// 示例 3： 
//
// 
//输入：s = "2[abc]3[cd]ef"
//输出："abcabccdcdcdef"
// 
//
// 示例 4： 
//
// 
//输入：s = "abc3[cd]xyz"
//输出："abccdcdcdxyz"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 30 
// 
// s 由小写英文字母、数字和方括号
// '[]' 组成 
// s 保证是一个 有效 的输入。 
// s 中所有整数的取值范围为
// [1, 300] 
// 
//
// Related Topics 栈 递归 字符串 👍 1632 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Deque;
import java.util.LinkedList;

public class L394_DecodeString{
      
  public static void main(String[] args) {
       Solution solution = new L394_DecodeString().new Solution();
      System.out.println(solution.decodeString("3[a2[c]]"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 两个栈分别存放重复次数、需要重复的串
     * 遍历字符串：
     *  如果是数字，先记录数字，
     *  如果是字符，先记录字符，
     *  如果是[,将当前数字压栈，当前字符串压栈，临时变量置为初始值
     *  如果是],pop重复次数，pop待重复子串，进行拼接，拼接后的字符串记录为临时字符串
     * 如此往复
     * O(n)/O(1)
     * @param s
     * @return
     */
    public String decodeString(String s) {
        Deque<Integer> countStack = new LinkedList<>();
        Deque<StringBuilder> tempStack = new LinkedList<>();
        int count=0;
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)){
                count = count*10 + current-'0';
            }else if (current=='['){
                countStack.push(count);
                tempStack.push(result);
                count=0;
                result = new StringBuilder();
            } else if (current==']') {
                StringBuilder pop = tempStack.pop();
                Integer currentCount = countStack.pop();
                pop.append(String.valueOf(result).repeat(Math.max(0, currentCount)));
                result = pop;
            }else {
                result.append(current);
            }
        }
        return result.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}