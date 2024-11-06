//给你两个字符串 s1 和 s2 ，写一个函数来判断 s2 是否包含 s1 的 排列。如果是，返回 true ；否则，返回 false 。 
//
// 换句话说，s1 的排列之一是 s2 的 子串 。 
//
// 
//
// 示例 1： 
//
// 
//输入：s1 = "ab" s2 = "eidbaooo"
//输出：true
//解释：s2 包含 s1 的排列之一 ("ba").
// 
//
// 示例 2： 
//
// 
//输入：s1= "ab" s2 = "eidboaoo"
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s1.length, s2.length <= 10⁴ 
// s1 和 s2 仅包含小写字母 
// 
//
// Related Topics 哈希表 双指针 字符串 滑动窗口 👍 1034 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class L567_PermutationInString{
      
  public static void main(String[] args) {
       Solution solution = new L567_PermutationInString().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * s2是否包含s1的排列，通过滑动窗口不断在s2寻找满足s1字符出现频率的子串，
     * 如果窗口的大小，大于等于s1长度，此时窗口内可能存在符合条件的子串
     * 进行valid和字符频率判断，并不断缩小窗口，缩小窗口后，判断是否满足s1字符出现频率，如果满足，返回true
     */
    public boolean checkInclusion(String s1, String s2) {
        Map<Character,Integer> window = new HashMap<>();
        Map<Character,Integer> need = new HashMap<>();
        for (char c : s1.toCharArray()) {
            need.put(c,need.getOrDefault(c,0)+1);
        }
        int left = 0;
        int right = 0;
        int valid = 0;
        while (right<s2.length()){
            char c = s2.charAt(right);
            right++;
            if (need.containsKey(c)){
                window.put(c,window.getOrDefault(c,0)+1);
                if (Objects.equals(window.get(c), need.get(c))){
                    valid++;
                }
            }
            //right-left大于等于s1.length()，说明窗口已经有可能有满足s1的字符，开始收缩左窗口
            while (right - left >= s1.length()){
                if (valid == need.size()){
                    return true;
                }
                char d = s2.charAt(left);
                left++;
                if (need.containsKey(d)){
                    if (Objects.equals(window.get(d), need.get(d))){
                        valid--;
                    }
                    window.put(d,window.getOrDefault(d,0)-1);
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}