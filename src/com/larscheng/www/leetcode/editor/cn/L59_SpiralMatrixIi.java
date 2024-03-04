//给你一个正整数 n ，生成一个包含 1 到 n² 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：n = 3
//输出：[[1,2,3],[8,9,4],[7,6,5]]
// 
//
// 示例 2： 
//
// 
//输入：n = 1
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 20 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1257 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L59_SpiralMatrixIi{
      
  public static void main(String[] args) {
       Solution solution = new L59_SpiralMatrixIi().new Solution();
       
  }

    /**
     * 1,2,3
     * 8,9,4
     * 7,6,5
     *
     * 1,   2,  3,  4
     * 12,  13, 14, 5
     * 11,  16, 15, 6
     * 10,  9,  8,  7
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        int num = 1;
        int left = 0;
        int right = n - 1;
        int top = 0;
        int bottom = n - 1;
        while (num <= n * n) {
            for (int i = left; i <= right; i++) {
                res[top][i] = num++;
            }
            top++;
            for (int i = top; i <= bottom; i++) {
                res[i][right] = num++;
            }
            right--;
            for (int i = right; i >= left; i--) {
                res[bottom][i] = num++;
            }
            bottom--;
            for (int i = bottom; i >= top; i--) {
                res[i][left] = num++;
            }
            left++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}