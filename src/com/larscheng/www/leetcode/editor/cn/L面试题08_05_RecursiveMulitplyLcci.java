//递归乘法。 写一个递归函数，不使用 * 运算符， 实现两个正整数的相乘。可以使用加号、减号、位移，但要吝啬一些。
//
// 示例1: 
//
// 
// 输入：A = 1, B = 10
// 输出：10
// 
//
// 示例2: 
//
// 
// 输入：A = 3, B = 4
// 输出：12
// 
//
// 提示: 
//
// 
// 保证乘法范围不会溢出 
// 
//
// Related Topics 位运算 递归 数学 👍 100 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L面试题08_05_RecursiveMulitplyLcci{
      
  public static void main(String[] args) {
       Solution solution = new L面试题08_05_RecursiveMulitplyLcci().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int multiply(int A, int B) {
        // A*B = A个B相加
        // A为小的数，减少计算(递归)次数
        int x = Math.min(A, B);
        int y = Math.max(A, B);

        return method(x, y);
    }

    private int method(int x, int y) {
        if (x == 0 || y == 0) {
            return 0;
        }
        if (x == 1) {
            return y;
        }
        if (y == 1) {
            return x;
        }
        return method(x - 1, y) + y;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}