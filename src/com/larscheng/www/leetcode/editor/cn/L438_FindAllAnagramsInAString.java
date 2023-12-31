//给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。 
//
// 异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "cbaebabacd", p = "abc"
//输出: [0,6]
//解释:
//起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
//起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
// 
//
// 示例 2: 
//
// 
//输入: s = "abab", p = "ab"
//输出: [0,1,2]
//解释:
//起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
//起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
//起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
// 
//
// 
//
// 提示: 
//
// 
// 1 <= s.length, p.length <= 3 * 10⁴ 
// s 和 p 仅包含小写字母 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 1353 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L438_FindAllAnagramsInAString{
      
  public static void main(String[] args) {
       Solution solution = new L438_FindAllAnagramsInAString().new Solution();
       solution.findAnagrams("cbaebabacd","abc");
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int s_len = s.length(), p_len = p.length();

        if (s_len < p_len) {
            return ans;
        }

        int[] window_count = new int[26];
        int[] p_count = new int[26];

        //统计首个窗口
        for (int i = 0; i < p_len; i++) {
            window_count[s.charAt(i) - 'a']++;
            p_count[p.charAt(i) - 'a']++;
        }

        //检查首个窗口是否符合
        if (Arrays.equals(window_count, p_count))
            ans.add(0);

        //窗口开始滑动,左右都按照同频率滑动
        for (int i = 0; i < s_len - p_len; i++) {
            window_count[s.charAt(i) - 'a']--;    // 左指针移动
            window_count[s.charAt(i + p_len) - 'a']++;  // 右指针移动

            //判断是否满足异位词的条件，满足加入到ans中
            if (Arrays.equals(window_count, p_count))
                ans.add(i + 1);

        }

        return ans;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


}