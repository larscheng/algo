//给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。 
//
// 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
//输出：[[7,4,1],[8,5,2],[9,6,3]]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
//输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
// 
//
// 
//
// 提示： 
//
// 
// n == matrix.length == matrix[i].length 
// 1 <= n <= 20 
// -1000 <= matrix[i][j] <= 1000 
// 
//
// 
//
// Related Topics 数组 数学 矩阵 👍 1829 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class L48_RotateImage{
      
  public static void main(String[] args) {
       Solution solution = new L48_RotateImage().new Solution();
       solution.rotate(new int[][]{{1,2,3},{4,5,6},{7,8,9}});
       //solution.rotate(new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    /**
     *
     * 1,2,3
     * 4,5,6
     * 7,8,9
     * 水平翻转
     * 7,8,9
     * 4,5,6
     * 1,2,3
     * 左对角线翻转
     * 7,4,1
     * 8,5,2
     * 9,6,3
     * O(n^2)/O(1)
     */
    public void rotate(int[][] matrix) {
        int n  = matrix.length;
        //水平翻转
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n ; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n-1-i][j];
                matrix[n-1-i][j] = temp;
            }
        }
        //左右对角线翻转
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j]=matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {

    /**
     *
     * 1,2,3
     * 4,5,6
     * 7,8,9
     *
     * 7,4,1
     * 8,5,2
     * 9,6,3
     *
     * 3*3中(0,1)=>(1,3-1-0) 第i行j列 =》第j行倒数第i列
     * 3*3中(2,2)=>(2,3-1-0) 第i行j列 =》第j行倒数第i列
     *
     * (i,j) => (j,n-1-i)
     * O(n^2)/O(n^2)
     */
    public void rotate(int[][] matrix) {
        int n  = matrix.length;
        int[][] temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[j][n-1-i] = matrix[i][j];
            }
        }

        for (int i = 0; i < n; i++) {
            System.arraycopy(temp[i], 0, matrix[i], 0, n);
        }
    }
}

}