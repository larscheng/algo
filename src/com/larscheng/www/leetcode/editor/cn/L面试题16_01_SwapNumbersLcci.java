//编写一个函数，不用临时变量，直接交换numbers = [a, b]中a与b的值。 
//
// 示例： 
//
// 
//输入: numbers = [1,2]
//输出: [2,1]
// 
//
// 提示： 
//
// 
// numbers.length == 2 
// -2147483647 <= numbers[i] <= 2147483647 
// 
//
// Related Topics 位运算 数学 👍 97 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L面试题16_01_SwapNumbersLcci{
      
  public static void main(String[] args) {
       Solution solution = new L面试题16_01_SwapNumbersLcci().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[] swapNumbers(int[] numbers) {
        //a^b
        numbers[0] = numbers[0] ^ numbers[1];
        //a^b ^b=a
        numbers[1] = numbers[0] ^ numbers[1];
        //a^b^a=b
        // a^b = numbers[0] = numbers[0] ^ numbers[1];
        // a = numbers[1] = numbers[0] ^ numbers[1];
        numbers[0] = numbers[0] ^ numbers[1];
        return numbers;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}