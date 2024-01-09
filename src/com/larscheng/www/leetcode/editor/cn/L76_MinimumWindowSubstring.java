//给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。 
//
// 
//
// 注意： 
//
// 
// 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。 
// 如果 s 中存在这样的子串，我们保证它是唯一的答案。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "ADOBECODEBANC", t = "ABC"
//输出："BANC"
//解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
// 
//
// 示例 2： 
//
// 
//输入：s = "a", t = "a"
//输出："a"
//解释：整个字符串 s 是最小覆盖子串。
// 
//
// 示例 3: 
//
// 
//输入: s = "a", t = "aa"
//输出: ""
//解释: t 中两个字符 'a' 均应包含在 s 的子串中，
//因此没有符合条件的子字符串，返回空字符串。 
//
// 
//
// 提示： 
//
// 
// m == s.length 
// n == t.length 
// 1 <= m, n <= 10⁵ 
// s 和 t 由英文字母组成 
// 
//
// 
//进阶：你能设计一个在 
//o(m+n) 时间内解决此问题的算法吗？
//
// Related Topics 哈希表 字符串 滑动窗口 👍 2785 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;

public class L76_MinimumWindowSubstring{
      
  public static void main(String[] args) {
       Solution solution = new L76_MinimumWindowSubstring().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < t.length(); i++) {
            need.put(t.charAt(i), need.getOrDefault(t.charAt(i), 0) + 1);
        }
        int right = 0, left = 0;
        int valid = 0;
        int start = 0, minLen = Integer.MAX_VALUE;
        while (right < s.length()) {
            char cur = s.charAt(right);
            right++;
            // 进行窗口数据一系列更新
            if (need.containsKey(cur)) {
                Integer total = window.getOrDefault(cur, 0);
                window.put(cur, total + 1);
                if (window.get(cur).equals(need.get(cur))) {
                    valid++;
                }
            }
            while (need.size() == valid) {
                if (right - left < minLen) {
                    start = left;
                    minLen = right - left;
                }
                // d 是将移除窗口的字符串
                char d = s.charAt(left);
                // 左边移动窗口
                left++;
                // 进行窗口内数据当一系列更新
                if (window.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }


        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}