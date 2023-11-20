//给你一个仅由 0 和 1 组成的二进制字符串 s 。 
//
// 如果子字符串中 所有的 0 都在 1 之前 且其中 0 的数量等于 1 的数量，则认为 s 的这个子字符串是平衡子字符串。请注意，空子字符串也视作平衡子字
//符串。 
//
// 返回 s 中最长的平衡子字符串长度。 
//
// 子字符串是字符串中的一个连续字符序列。 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "01000111"
//输出：6
//解释：最长的平衡子字符串是 "000111" ，长度为 6 。
// 
//
// 示例 2： 
//
// 
//输入：s = "00111"
//输出：4
//解释：最长的平衡子字符串是 "0011" ，长度为  4 。
// 
//
// 示例 3： 
//
// 
//输入：s = "111"
//输出：0
//解释：除了空子字符串之外不存在其他平衡子字符串，所以答案为 0 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 50 
// '0' <= s[i] <= '1' 
// 
//
// Related Topics 字符串 👍 61 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L2609_FindTheLongestBalancedSubstringOfABinaryString{
      
  public static void main(String[] args) {
       Solution solution = new L2609_FindTheLongestBalancedSubstringOfABinaryString().new Solution();
      System.out.println(solution.findTheLongestBalancedSubstring("0001"));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      // O(n)/O(1)
    public int findTheLongestBalancedSubstring(String s) {
        int count = 0, zero = 0, one = 0;
        //01000111
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i)=='0'){
                if (one > 0) {
                    //重置
                    one = 0;
                    zero = 0;
                }
                //记录0个数
                zero++;
            }else {
                //记录1个数
                one++;
                //计算平衡串长度 2 * Math.min(zero, one)
                count = Math.max(2 * Math.min(zero, one), count);
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    // O(n*n/2)

    public int findTheLongestBalancedSubstring(String s) {
        int count = 0;
        //01000111
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1; j < s.length(); j+=2) {
                String substring = s.substring(i, j + 1);
                if (substring.startsWith("0") && substring.endsWith("1")) {
                    int mid = substring.length()/2;
                    if (!substring.substring(0, mid).contains("1") && !substring.substring(mid).contains("0")){
                        count = Math.max(substring.length(),count);
                    }
                }
            }
        }
        return count;
    }
}

}