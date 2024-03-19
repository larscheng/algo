//给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[1,2,3,6,9,8,7,4,5]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
//输出：[1,2,3,4,8,12,11,10,9,5,6,7]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= m, n <= 10 
// -100 <= matrix[i][j] <= 100 
// 
//
// Related Topics 数组 矩阵 模拟 👍 1631 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L54_SpiralMatrix{
      
  public static void main(String[] args) {
       Solution solution = new L54_SpiralMatrix().new Solution();
       //1,2,3
       //4,5,6
       //7,8,9
      System.out.println(solution.spiralOrder(new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12}}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        int left = 0, top = 0, right = matrix[0].length - 1, bottom = matrix.length - 1;
        int count = 1;
        int total = matrix[0].length * matrix.length;
        while (count <= total) {
            for (int i = left; i <= right && count <= total; i++) {
                res.add(matrix[top][i]);
                count++;
            }
            top++;
            for (int i = top; i <= bottom && count <= total; i++) {
                res.add(matrix[i][right]);
                count++;
            }
            right--;

            for (int i = right; i >= left && count <= total; i--) {
                res.add(matrix[bottom][i]);
                count++;
            }
            bottom--;
            for (int i = bottom; i >= top && count <= total; i--) {
                res.add(matrix[i][left]);
                count++;
            }
            left++;
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}