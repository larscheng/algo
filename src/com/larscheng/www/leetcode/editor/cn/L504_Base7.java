//给定一个整数 num，将其转化为 7 进制，并以字符串形式输出。 
//
// 
//
// 示例 1: 
//
// 
//输入: num = 100
//输出: "202"
//100%7=14，2
//14%7=2，0
//2%7=0，2
// 
//
// 示例 2: 
//
// 
//输入: num = -7
//输出: "-10"
// 
//
// 
//
// 提示： 
//
// 
// -10⁷ <= num <= 10⁷ 
// 
//
// Related Topics 数学 👍 211 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L504_Base7{
      
  public static void main(String[] args) {
       Solution solution = new L504_Base7().new Solution();
      System.out.println(solution.convertToBase7(-127));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String convertToBase7(int num) {
        if (num==0){
            return "0";
        }
        StringBuilder str = new StringBuilder();
        int abs = Math.abs(num);
        while (abs>0){
            str.append(abs % 7);
            abs /=7;
        }
        if (num<0){
            str.append("-");
        }
        return str.reverse().toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}