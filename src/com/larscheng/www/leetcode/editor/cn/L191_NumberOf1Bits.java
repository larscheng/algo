//编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为汉明重量）。 
//
// 
//
// 提示： 
//
// 
// 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的
//还是无符号的，其内部的二进制表示形式都是相同的。 
// 在 Java 中，编译器使用二进制补码记法来表示有符号整数。因此，在 示例 3 中，输入表示有符号整数 -3。 
// 
//
// 
//
// 示例 1： 
//
// 
//输入：n = 00000000000000000000000000001011
//输出：3
//解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
// 
//
// 示例 2： 
//
// 
//输入：n = 00000000000000000000000010000000
//输出：1
//解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
// 
//
// 示例 3： 
//
// 
//输入：n = 11111111111111111111111111111101
//输出：31
//解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。 
//
// 
//
// 提示： 
//
// 
// 输入必须是长度为 32 的 二进制串 。 
// 
//
// 
// 
//
// 
//
// 进阶： 
//
// 
// 如果多次调用这个函数，你将如何优化你的算法？ 
// 
//
// Related Topics 位运算 分治 👍 615 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L191_NumberOf1Bits{
      
  public static void main(String[] args) {
       Solution solution = new L191_NumberOf1Bits().new Solution();
      System.out.println(solution.hammingWeight(00000000000000000000000000001011));
  }

//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {
    // you need to treat n as an unsigned value

    /**
     * Example: 当num=15时，
     * 1//num&(num-1)=(1111)&(1110)=(1110)
     * 2//num&(num-1)=(1110)&(1101)=(1100)
     * 3//num&(num-1)=(1100)&(1011)=(1000)
     * 4//num&(num-1)=(1000)&(0111)=0 ，循环停止。共执行4次while循环
     */
    public int hammingWeight(int n) {
        int count = 0 ;
        while (n>=0){
            n = n&(n-1);
            count++;
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
public class Solution1 {
    // you need to treat n as an unsigned value

    /**
     * 通过右移操作符（>>）、按位与操作符（&）实现
     * Example：当num=10(1010)，通过右移操作num>>i，二进制向右移动i位。
     * //i=0，num>>0，右移0位，此时（1010）&（0001）=0
     * //i=1，num>>1，右移1位，此时（0101）&（0001）=1，count++
     * //i=2，num>>2，右移2位，此时（0010）&（0001）=0
     * //i=3，num>>3，右移3位，此时（0001）&（0001）=1，count++
     * ……
     * 因为二进制共32位，所以循环要执行32次后结束，得到count为2
     * O(32)/O(1)
     */
    public int hammingWeight(int n) {
        int count = 0 ;
        for (int i = 0; i < 32; i++) {
            //右移位 与运算 同1为1，其他为0
            count += ((n >> i) & 1);
        }
        return count;
    }
}

}