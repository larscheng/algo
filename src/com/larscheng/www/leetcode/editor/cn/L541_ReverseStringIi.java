//给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。 
//
// 
// 如果剩余字符少于 k 个，则将剩余字符全部反转。 
// 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：s = "abcdefg", k = 2
//输出："bacdfeg"
// 
//
// 示例 2： 
//
// 
//输入：s = "abcd", k = 2
//输出："bacd"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 10⁴ 
// s 仅由小写英文组成 
// 1 <= k <= 10⁴ 
// 
//
// Related Topics 双指针 字符串 👍 618 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L541_ReverseStringIi{
      
  public static void main(String[] args) {
       Solution solution = new L541_ReverseStringIi().new Solution();
       solution.reverseStr("abcde",2);
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String reverseStr(String s, int k) {

        char[] charArray = s.toCharArray();
        int length = charArray.length-1;
        //要翻转的长度为i+k-1=size
        //size永远和数组总长度取最小
        for (int i = 0; i <= length; i+=2*k) {
            reverse(charArray, i, Math.min(i+k-1,length));
        }

        return new String(charArray);

    }

    public void reverse(char[] charArray,int left,int right){
        while (left<right) {
            char temp = charArray[right];
            charArray[right]=charArray[left];
            charArray[left]=temp;
            right--;
            left++;
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}