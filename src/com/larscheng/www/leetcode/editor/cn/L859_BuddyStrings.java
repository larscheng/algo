//给你两个字符串 s 和 goal ，只要我们可以通过交换 s 中的两个字母得到与 goal 相等的结果，就返回 true ；否则返回 false 。 
//
// 交换字母的定义是：取两个下标 i 和 j （下标从 0 开始）且满足 i != j ，接着交换 s[i] 和 s[j] 处的字符。 
//
// 
// 例如，在 "abcd" 中交换下标 0 和下标 2 的元素可以生成 "cbad" 。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ab", goal = "ba"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 相等。 
//
// 示例 2： 
//
// 
//输入：s = "ab", goal = "ab"
//输出：false
//解释：你只能交换 s[0] = 'a' 和 s[1] = 'b' 生成 "ba"，此时 s 和 goal 不相等。 
//
// 示例 3： 
//
// 
//输入：s = "aa", goal = "aa"
//输出：true
//解释：你可以交换 s[0] = 'a' 和 s[1] = 'a' 生成 "aa"，此时 s 和 goal 相等。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length, goal.length <= 2 * 10⁴ 
// s 和 goal 由小写英文字母组成 
// 
//
// Related Topics 哈希表 字符串 👍 307 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

public class L859_BuddyStrings{
      
  public static void main(String[] args) {
       Solution solution = new L859_BuddyStrings().new Solution();
       //solution.buddyStrings("ab","ba");
       //solution.buddyStrings("ab","ab");
       //solution.buddyStrings("aa","aa");
      System.out.println(solution.buddyStrings("abcd", "abcd"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean buddyStrings(String s, String goal) {
        if (s.length()!=goal.length()) {
            return false;
        }
        //字符串相等时，需要有两个重复字符,aabc/aabc
        if (s.equals(goal)){
            int[] count = new int[26];
            for (int i = 0; i < s.length(); i++) {
                count[s.charAt(i) - 'a']++;
                if (count[s.charAt(i) - 'a'] > 1) {
                    return true;
                }
            }
            return false;
        }
        else {
            // 字符串不相等时，应该有2处字符不同，交换两处的字符
            // 找到2处不相等的字符下标，交叉比对是否相同 abcd/bacd
            int first = -1,second =-1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i)!=goal.charAt(i)){
                    if (first < 0) {
                        first = i;
                    } else if (second < 0) {
                        second = i;
                    }else {
                        //超出2处
                        return false;
                    }
                }
            }
            return second>0&&
                    s.charAt(first)==goal.charAt(second)&&
                    s.charAt(second)==goal.charAt(first);
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}