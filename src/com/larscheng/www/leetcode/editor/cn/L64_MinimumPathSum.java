//给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。 
//
// 说明：每次只能向下或者向右移动一步。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
//输出：7
//解释：因为路径 1→3→1→1→1 的总和最小。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[1,2,3],[4,5,6]]
//输出：12
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 200 
// 0 <= grid[i][j] <= 200 
// 
//
// Related Topics 数组 动态规划 矩阵 👍 1680 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L64_MinimumPathSum{
      
  public static void main(String[] args) {
       Solution solution = new L64_MinimumPathSum().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 2维dp数组需要使用额外空间
     * 可以直接在原二维数组上直接操作，记录每个位置的最小路径和
     * O(m*n)/O(1)
     */
    public int minPathSum(int[][] grid) {

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (i==0&&j==0){
                    continue;
                }else if (i==0){
                    grid[i][j] += grid[i][j-1];
                } else if (j==0) {
                    grid[i][j] += grid[i-1][j];
                }else {
                    grid[i][j] += Math.min(grid[i][j - 1], grid[i - 1][j]);
                }
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    /**
     * dp[i][j]表示到达(i,j)的最小路径和
     * dp[i][j] = Min(dp[i][j-1],dp[i-1][j])+grid[i][j]
     * 起点的最小路径和为grid[0][0]，dp[0][0] = grid[0][0]
     * dp[0][1] = dp[0][0]+grid[0][1]
     * i=0时，dp[0][j] = dp[0][j-1]+grid[0][j]
     * j=0时，dp[i][0] = dp[i-1][0]+grid[i][0]
     * O(m*n)/O(m*n)
     */
    public int minPathSum(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < grid.length; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < grid[0].length; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }
        return dp[grid.length - 1][grid[0].length - 1];
    }
}

}