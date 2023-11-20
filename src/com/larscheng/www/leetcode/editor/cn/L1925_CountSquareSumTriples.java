//一个 平方和三元组 (a,b,c) 指的是满足 a² + b² = c² 的 整数 三元组 a，b 和 c 。 
//
// 给你一个整数 n ，请你返回满足 1 <= a, b, c <= n 的 平方和三元组 的数目。 
//
// 
//
// 示例 1： 
//
// 输入：n = 5
//输出：2
//解释：平方和三元组为 (3,4,5) 和 (4,3,5) 。
// 
//
// 示例 2： 
//
// 输入：n = 10
//输出：4
//解释：平方和三元组为 (3,4,5)，(4,3,5)，(6,8,10) 和 (8,6,10) 。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 250 
// 
//
// Related Topics 数学 枚举 👍 20 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1925_CountSquareSumTriples{
      
  public static void main(String[] args) {
       Solution solution = new L1925_CountSquareSumTriples().new Solution();
      System.out.println(solution.countTriples(10));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int countTriples(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                int c = i * i + j * j;
                int sqrt = (int) Math.sqrt(c);
                if (sqrt <= n && sqrt * sqrt == c) {
                    count++;
                }
            }
        }
        return count;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}