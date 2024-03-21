//编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性： 
//
// 
// 每行的元素从左到右升序排列。 
// 每列的元素从上到下升序排列。 
// 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 5
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,4,7,11,15],[2,5,8,12,19],[3,6,9,16,22],[10,13,14,17,24],[18,21
//,23,26,30]], target = 20
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// m == matrix.length 
// n == matrix[i].length 
// 1 <= n, m <= 300 
// -10⁹ <= matrix[i][j] <= 10⁹ 
// 每行的所有元素从左到右升序排列 
// 每列的所有元素从上到下升序排列 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 分治 矩阵 👍 1439 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L240_SearchA2dMatrixIi{
      
  public static void main(String[] args) {
       Solution solution = new L240_SearchA2dMatrixIi().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      // 行内2分查找
      // O(m*logn)/O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        for (int[] ints : matrix) {
            int left = 0, right = ints.length - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (ints[mid] == target) {
                    return true;
                } else if (ints[mid] > target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        // 暴力循环遍历找
        // O(m*n)/O(1)
        public boolean searchMatrix(int[][] matrix, int target) {
            int m = matrix.length, n = matrix[0].length;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j]==target){
                        return true;
                    }
                }
            }
            return false;
        }
    }
class Solution1 {
    // 从矩阵右上角开始找，如果当前数字大于target，说明目标数字在左侧，y--；如果当前数字小于target，说明目标数字在下面的行内，x++
    // O(m+n)/O(1)
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length, n = matrix[0].length;
        int x = 0, y = n - 1;
        while (x < m && y >= 0) {
            if (matrix[x][y] == target) {
                return true;
            } else if (matrix[x][y] > target) {
                y--;
            } else {
                x++;
            }
        }
        return false;
    }
}

}