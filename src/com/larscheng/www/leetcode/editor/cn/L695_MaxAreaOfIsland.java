//给你一个大小为 m x n 的二进制矩阵 grid 。 
//
// 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。你可以假设 grid 的四个边缘都
//被 0（代表水）包围着。 
//
// 岛屿的面积是岛上值为 1 的单元格的数目。 
//
// 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。 
//
// 
//
// 示例 1： 
// 
// 
//输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,1,1,0,1,
//0,0,0,0,0,0,0,0],[0,1,0,0,1,1,0,0,1,0,1,0,0],[0,1,0,0,1,1,0,0,1,1,1,0,0],[0,0,0,
//0,0,0,0,0,0,0,1,0,0],[0,0,0,0,0,0,0,1,1,1,0,0,0],[0,0,0,0,0,0,0,1,1,0,0,0,0]]
//输出：6
//解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
// 
//
// 示例 2： 
//
// 
//输入：grid = [[0,0,0,0,0,0,0,0]]
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// m == grid.length 
// n == grid[i].length 
// 1 <= m, n <= 50 
// grid[i][j] 为 0 或 1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 1042 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L695_MaxAreaOfIsland{
      
  public static void main(String[] args) {
       Solution solution = new L695_MaxAreaOfIsland().new Solution();
       int[][] grid = new int[][]{
               {0,0,1,0,0,0,0,1,0,0,0,0,0},
               {0,0,0,0,0,0,0,1,1,1,0,0,0},
               {0,1,1,0,1,0,0,0,0,0,0,0,0},
               {0,1,0,0,1,1,0,0,1,0,1,0,0},
               {0,1,0,0,1,1,0,0,1,1,1,0,0},
               {0,0,0,0,0,0,0,0,0,0,1,0,0},
               {0,0,0,0,0,0,0,1,1,1,0,0,0},
               {0,0,0,0,0,0,0,1,1,0,0,0,0}
       };
      System.out.println(solution.maxAreaOfIsland(grid));
  }

    /**
     * 遍历矩阵中每个元素，从当前元素为起点，往上下左右位置进行岛屿搜索，找到岛屿就把结果+1
     * 搜索结束条件：如果搜索到矩阵外，或者搜索到的位置值为0，则直接返回
     * 避免死循环：搜索时，会探查当前位置的上下左右位置，所以每搜索到1个新位置，就将其置为0，避免死循环
     * 最终取 以每个元素为起点，搜索结果的最大值
     *
     * 时间复杂度：O(m*n)，已经搜索过的位置置为0，所以每个位置只会访问1次，复杂度为m*n
     * 空间复杂度：O(m*n)，递归最大深度为m*n
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    //当前位置的上、下、左、右位置偏移值
   public  int[][] DIRECTIONS = {{-1,0}, {1, 0}, {0, -1},{0, 1}};
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                res = Math.max(res, dfs(grid,i,j));
            }
        }
        return res;
    }

        private int dfs(int[][] grid, int x, int y) {
            if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == 0) {
                //当前位置不是岛屿，直接返回0
                return 0;
            }
            //当前位置为岛屿，开始向上下左右方向搜索其他岛屿，把结果累加（当前位置置为0，避免搜索时死循环）
            grid[x][y] = 0;
            int temp = 1;
            for (int[] direction : DIRECTIONS) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                temp += dfs(grid, newX, newY);
            }
            return temp;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)


}