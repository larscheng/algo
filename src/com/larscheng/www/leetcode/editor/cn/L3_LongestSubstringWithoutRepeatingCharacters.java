//给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。 
//
// 
//
// 示例 1: 
//
// 
//输入: s = "abcabcbb"
//输出: 3 
//解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
// 
//
// 示例 2: 
//
// 
//输入: s = "bbbbb"
//输出: 1
//解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
// 
//
// 示例 3: 
//
// 
//输入: s = "pwwkew"
//输出: 3
//解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
//     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
// 
//
// 
//
// 提示： 
//
// 
// 0 <= s.length <= 5 * 10⁴ 
// s 由英文字母、数字、符号和空格组成 
// 
//
// Related Topics 哈希表 字符串 滑动窗口 👍 9829 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;

public class L3_LongestSubstringWithoutRepeatingCharacters{
      
  public static void main(String[] args) {
       Solution solution = new L3_LongestSubstringWithoutRepeatingCharacters().new Solution();
       solution.lengthOfLongestSubstring("abba");
  }

    /**
     * 通过双指针构建滑动窗口，窗口内只能存放无重复的子串，移动窗口过程中，最大窗口值即为最长无重复子串
     * 右指针移动过程中记录每个元素及其下标至哈希表中，并记录最大窗口值
     * 如果出现重复元素，则移动左指针到当前元素的下一位，收缩窗口，继续遍历
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        int result = 0;
        //字符-下标位置
        HashMap<Character, Integer> map = new HashMap<>();
        //左指针
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                //收缩窗口，左指针为重复元素的下一位置
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            //取最大窗口
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}