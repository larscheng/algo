//给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。 
//
// 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。 
//
// 你可以假设除了整数 0 之外，这个整数不会以零开头。 
//
// 
//
// 示例 1： 
//
// 
//输入：digits = [1,2,3]
//输出：[1,2,4]
//解释：输入数组表示数字 123。
// 
//
// 示例 2： 
//
// 
//输入：digits = [4,3,2,1]
//输出：[4,3,2,2]
//解释：输入数组表示数字 4321。
// 
//
// 示例 3： 
//
// 
//输入：digits = [0]
//输出：[1]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= digits.length <= 100 
// 0 <= digits[i] <= 9 
// 
//
// Related Topics 数组 数学 👍 1366 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L66_PlusOne{
      
  public static void main(String[] args) {
       Solution solution = new L66_PlusOne().new Solution();
      System.out.println(Arrays.toString(solution.plusOne(new int[]{9,9})));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] plusOne(int[] digits) {
        //数组元素值0-9，尾部+1，进位后当前为必为0
        for (int i = digits.length - 1; i >= 0; i--) {
            digits[i]++;
            digits[i] = digits[i] % 10;
            if (digits[i] != 0) {
                //不为0，无进位直接返回
                return digits;
            }
        }
        //有进位，数组首位增加一位1，其余项都为0
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public int[] plusOne(int[] digits) {
            // 进位
            int carry = 0;
            for (int i = digits.length-1; i >=0 ; i--) {
                int temp = carry + digits[i];
                if (i == digits.length-1){
                    temp++;
                }
                digits[i] = temp%10;
                //进位
                carry = temp/10;
                //没有进位直接结束
                if (carry==0){
                    break;
                }
            }
            if (carry > 0) {
                int[] ints = Arrays.copyOf(new int[]{carry}, digits.length + 1);
                System.arraycopy(digits, 0, ints, 1, digits.length);
                return ints;
            }
            return digits;
        }
    }
}