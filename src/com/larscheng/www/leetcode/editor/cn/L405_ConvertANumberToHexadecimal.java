//给定一个整数，编写一个算法将这个数转换为十六进制数。对于负整数，我们通常使用 补码运算 方法。 
//
// 注意: 
//
// 
// 十六进制中所有字母(a-f)都必须是小写。 
// 十六进制字符串中不能包含多余的前导零。如果要转化的数为0，那么以单个字符'0'来表示；对于其他情况，十六进制字符串中的第一个字符将不会是0字符。 
// 给定的数确保在32位有符号整数范围内。 
// 不能使用任何由库提供的将数字直接转换或格式化为十六进制的方法。 
// 
//
// 示例 1： 
//
// 
//输入:
//26
//
//输出:
//"1a"
// 
//
// 示例 2： 
//
// 
//输入:
//-1
//
//输出:
//"ffffffff"
// 
//
// Related Topics 位运算 数学 👍 293 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L405_ConvertANumberToHexadecimal{
      
  public static void main(String[] args) {
       Solution1 solution = new L405_ConvertANumberToHexadecimal().new Solution1();
      System.out.println(solution.toHex(-26));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public String toHex(int num) {
        if (num == 0) {
            return "0";
        }
        StringBuffer sb = new StringBuffer();
        // 32位二进制，4位一个16进制，8组，
        // xxxx xxxx xxxx xxxx xxxx xxxx xxxx xxxx
        // f    f    f    f    f    f    f    f
        for (int i = 7; i >= 0; i --) {
            int val = (num >> (4 * i)) & 0xf;
            if (sb.length() > 0 || val > 0) {
                char digit = val < 10 ? (char) ('0' + val) : (char) ('a' + val - 10);
                sb.append(digit);
            }
        }
        return sb.toString();
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public String toHex(int num) {
            String hex = "";
            if(num < 0) {
                num = (int)(Math.pow(2, 32) + num);
            };
            while (num>0){
                int temp = num%16;
                if (temp<10){
                    hex = temp + hex;
                }else {
                    char a  = (char)('a'+temp-10);
                    hex= a + hex;
                }
                num/=16;
            }
            return hex;
        }
    }

}