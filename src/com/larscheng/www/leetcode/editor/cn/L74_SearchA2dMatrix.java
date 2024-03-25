//给你一个满足下述两条属性的 m x n 整数矩阵： 
//
// 
// 每行中的整数从左到右按非严格递增顺序排列。 
// 每行的第一个整数大于前一行的最后一个整数。 
// 
//
// 给你一个整数 target ，如果 target 在矩阵中，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
// 
//
// 示例 2： 
// 
// 
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
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
// 1 <= m, n <= 100 
// -10⁴ <= matrix[i][j], target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 矩阵 👍 907 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L74_SearchA2dMatrix{
      
  public static void main(String[] args) {
       Solution solution = new L74_SearchA2dMatrix().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //从右上角往左下角找
    //O(m+n)/O(1)
    //类似题目240搜索二维矩阵
    public boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length,n = matrix[0].length;
        int x = 0, y =n-1;
        while (x<m&&y>=0){
            if (matrix[x][y]==target){
                return true;
            } else if (matrix[x][y]>target) {
                //目标值在左侧
                y--;
            }else {
                //目标值在下面的行内
                x++;
            }
        }
        return false;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}