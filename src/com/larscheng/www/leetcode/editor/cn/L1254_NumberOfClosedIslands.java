//二维矩阵 grid 由 0 （土地）和 1 （水）组成。岛是由最大的4个方向连通的 0 组成的群，封闭岛是一个 完全 由1包围（左、上、右、下）的岛。 
//
// 请返回 封闭岛屿 的数目。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[1,1,1,1,1,1,1,0],[1,0,0,0,0,1,1,0],[1,0,1,0,1,1,1,0],[1,0,0,0,0,1,
//0,1],[1,1,1,1,1,1,1,0]]
//输出：2
//解释：
//灰色区域的岛屿是封闭岛屿，因为这座岛屿完全被水域包围（即被 1 区域包围）。 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = [[0,0,1,0,0],[0,1,0,1,0],[0,1,1,1,0]]
//输出：1
// 
//
// 示例 3： 
//
// 
//输入：grid = [[1,1,1,1,1,1,1],
//             [1,0,0,0,0,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,1,0,1,0,1],
//             [1,0,1,1,1,0,1],
//             [1,0,0,0,0,0,1],
//             [1,1,1,1,1,1,1]]
//输出：2
// 
//
// 
//
// 提示： 
//
// 
// 1 <= grid.length, grid[0].length <= 100 
// 0 <= grid[i][j] <=1 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 矩阵 👍 305 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L1254_NumberOfClosedIslands{
      
  public static void main(String[] args) {
       Solution solution = new L1254_NumberOfClosedIslands().new Solution();
      int[][] arr = new int[][]{
              {0,0,1,1,0,1,0,0,1,0},
              {1,1,0,1,1,0,1,1,1,0},
              {1,0,1,1,1,0,0,1,1,0},
              {0,1,1,0,0,0,0,1,0,1},
              {0,0,0,0,0,0,1,1,1,0},
              {0,1,0,1,0,1,0,1,1,1},
              {1,0,1,0,1,1,0,0,0,1},
              {1,1,1,1,1,1,0,0,0,0},
              {1,1,1,0,0,1,0,1,0,1},
              {1,1,1,0,1,1,0,1,1,0}
      };
     int[][] arr2 =  new int[][]{
              {1, 1, 1, 1, 1, 1, 1, 0},
              {1, 0, 0, 0, 0, 1, 1, 0},
              {1, 0, 1, 0, 1, 1, 1, 0},
              {1, 0, 0, 0, 0, 1, 0, 1},
              {1, 1, 1, 1, 1, 1, 1, 0}
      };
      System.out.println(solution.closedIsland(arr));

  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     *
     * 0-岛，1-水，边缘都是水，找封闭岛的数量
     * 边缘的0都属于干扰数据，包括从边缘0延伸出去的0都是干扰数据，可以通过dfs先全部替换为1-水
     * 然后，在通过双层循环找岛，此时找到的岛都是封闭岛
     *
     * 1、替换边缘延伸出来的干扰岛
     * 2、dfs寻找封闭岛
     *
     */
    int closedIsland(int[][] grid) {
        int res = 0;
        int m = grid.length;
        int n = grid[0].length;
        //小于3行3列无法形成封闭岛
        if (m<3||n<3){
            return res;
        }
        //第1行最后1行干扰岛处理
        for (int j = 0; j < n; j++) {
            dfs(grid, 0, j);
            dfs(grid, m - 1, j);
        }
        //第1列和最后1列干扰岛处理
        for (int i = 0; i < m; i++) {
            dfs(grid, i, 0);
            dfs(grid, i, n - 1);
        }
        //外围一圈不用看，都是干扰岛
        for (int i = 1; i < m-1; i++) {
            for (int j = 1; j < n-1; j++) {
                if (grid[i][j]==0){
                    dfs(grid,i,j);
                    res++;
                }
            }
        }
        return res;
    }

    private void dfs(int[][] grid, int i, int j) {
        if (i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]==1){
            //越界或者本身就是1，直接跳过
            return;
        }
        //干扰岛全替换为水-1
        grid[i][j]=1;
        //从当前干扰岛蔓延出去的0也都是干扰岛，一律处理
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        // 主函数：计算封闭岛屿的数量
        int closedIsland(int[][] grid) {
            int m = grid.length, n = grid[0].length;
            for (int j = 0; j < n; j++) {
                // 把靠上边的岛屿淹掉
                dfs(grid, 0, j);
                // 把靠下边的岛屿淹掉
                dfs(grid, m - 1, j);
            }
            for (int i = 0; i < m; i++) {
                // 把靠左边的岛屿淹掉
                dfs(grid, i, 0);
                // 把靠右边的岛屿淹掉
                dfs(grid, i, n - 1);
            }
            // 遍历 grid，剩下的岛屿都是封闭岛屿
            int res = 0;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if (grid[i][j] == 0) {
                        res++;
                        dfs(grid, i, j);
                    }
                }
            }
            return res;
        }

        // 从 (i, j) 开始，将与之相邻的陆地都变成海水
        void dfs(int[][] grid, int i, int j) {
            int m = grid.length, n = grid[0].length;
            if (i < 0 || j < 0 || i >= m || j >= n) {
                return;
            }
            if (grid[i][j] == 1) {
                // 已经是海水了
                return;
            }
            // 将 (i, j) 变成海水
            grid[i][j] = 1;
            // 淹没上下左右的陆地
            dfs(grid, i + 1, j);
            dfs(grid, i, j + 1);
            dfs(grid, i - 1, j);
            dfs(grid, i, j - 1);
        }
    }
}