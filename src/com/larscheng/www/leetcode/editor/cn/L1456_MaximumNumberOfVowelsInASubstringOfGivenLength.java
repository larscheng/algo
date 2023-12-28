//给你字符串 s 和整数 k 。 
//
// 请返回字符串 s 中长度为 k 的单个子字符串中可能包含的最大元音字母数。 
//
// 英文中的 元音字母 为（a, e, i, o, u）。 
//
// 
//
// 示例 1： 
//
// 输入：s = "abciiidef", k = 3
//输出：3
//解释：子字符串 "iii" 包含 3 个元音字母。
// 
//
// 示例 2： 
//
// 输入：s = "aeiou", k = 2
//输出：2
//解释：任意长度为 2 的子字符串都包含 2 个元音字母。
// 
//
// 示例 3： 
//
// 输入：s = "leetcode", k = 3
//输出：2
//解释："lee"、"eet" 和 "ode" 都包含 2 个元音字母。
// 
//
// 示例 4： 
//
// 输入：s = "rhythms", k = 4
//输出：0
//解释：字符串 s 中不含任何元音字母。
// 
//
// 示例 5： 
//
// 输入：s = "tryhard", k = 4
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10^5 
// s 由小写英文字母组成 
// 1 <= k <= s.length 
// 
//
// Related Topics 字符串 滑动窗口 👍 83 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class L1456_MaximumNumberOfVowelsInASubstringOfGivenLength{
      
  public static void main(String[] args) {
       Solution solution = new L1456_MaximumNumberOfVowelsInASubstringOfGivenLength().new Solution();
       solution.maxVowels("leetcode",3);
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int maxVowels(String s, int k) {
        int window = 0;
        for (int i = 0; i < k; i++) {
            window += check(s.charAt(i));
        }
        int result = window;
        for (int i = k; i < s.length(); i++) {
            //i为当前窗口右端新元素，i-k是上一个窗口左端元素
            //当前窗口元音数 = 上一窗口元音数 + 当前窗口新元素是否元音 - 上一个窗口左端元素是否元音
            window = window + check(s.charAt(i)) -check(s.charAt(i-k));
            result = Math.max(result, window);
        }
        return result;
    }
    int check(char s){
        return s == 'a' || s == 'e' || s == 'i' || s == 'o' || s == 'u' ? 1 : 0;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}