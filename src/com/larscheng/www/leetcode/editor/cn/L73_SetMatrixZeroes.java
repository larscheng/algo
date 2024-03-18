//给定一个 m x n 的矩阵，如果一个元素为 0 ，则将其所在行和列的所有元素都设为 0 。请使用 原地 算法。 
//
// 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,1,1],[1,0,1],[1,1,1]]
//输出：[[1,0,1],[0,0,0],[1,0,1]]
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[0,1,2,0],[3,4,5,2],[1,3,1,5]]
//输出：[[0,0,0,0],[0,4,5,0],[0,3,1,0]]
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[0].length 
// 1 <= m, n <= 200 
// -2³¹ <= matrix[i][j] <= 2³¹ - 1 
// 
//
// 
//
// 进阶： 
//
// 
// 一个直观的解决方案是使用 O(mn) 的额外空间，但这并不是一个好的解决方案。 
// 一个简单的改进方案是使用 O(m + n) 的额外空间，但这仍然不是最好的解决方案。 
// 你能想出一个仅使用常量空间的解决方案吗？ 
// 
//
// Related Topics 数组 哈希表 矩阵 👍 1033 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L73_SetMatrixZeroes{
      
  public static void main(String[] args) {
       Solution solution = new L73_SetMatrixZeroes().new Solution();
       solution.setZeroes(new int[][]{{0,1,2,0},{3,4,5,2},{1,3,1,5}});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //O(m*n)/O(1)
    public void setZeroes(int[][] matrix) {
        //第一行/第一列有无0
        boolean row0 = false;
        boolean col0 = false;

        for (int i = 0; i < matrix[0].length; i++) {
            if (matrix[0][i]==0){
                row0=true;
                break;
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i][0]==0){
                col0=true;
                break;
            }
        }

        //首行首列用来记录 本行本列有没有0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if(matrix[i][j]==0){
                    matrix[i][0] = matrix[0][j]= 0;
                }
            }
        }
        //根据首行首列的记录，对每个元素进行赋0
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][0] == 0 || matrix[0][j] == 0) {
                    matrix[i][j] =0;
                }
            }
        }
        //首行首列有0，则需要把对应行列赋为0
        if (row0) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[0][j] = 0;
            }
        }
        if (col0) {
            for (int i = 0; i < matrix.length; i++) {
                matrix[i][0] = 0;
            }
        }


        System.out.println(Arrays.deepToString(matrix));
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    //O(m*n)/O(m+n)
    public void setZeroes(int[][] matrix) {
        Set<Integer> row = new HashSet<>();
        Set<Integer> col = new HashSet<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j]==0){
                    row.add(i);
                    col.add(j);
                }
            }
        }
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (row.contains(i) || col.contains(j)) {
                    matrix[i][j] = 0;
                }
            }
        }

        System.out.println(Arrays.deepToString(matrix));
    }
}

}