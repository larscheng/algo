//你现在手里有一份大小为
// n x n 的 网格 grid，上面的每个 单元格 都用 0 和 1 标记好了。其中 0 代表海洋，1 代表陆地。 
//
// 请你找出一个海洋单元格，这个海洋单元格到离它最近的陆地单元格的距离是最大的，并返回该距离。如果网格上只有陆地或者海洋，请返回 -1。 
//
// 我们这里说的距离是「曼哈顿距离」（ Manhattan Distance）：(x0, y0) 和 (x1, y1) 这两个单元格之间的距离是 |x0 - 
//x1| + |y0 - y1| 。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：grid = [[1,0,1],[0,0,0],[1,0,1]]
//输出：2
//解释： 
//海洋单元格 (1, 1) 和所有陆地单元格之间的距离都达到最大，最大距离为 2。
// 
//
// 示例 2： 
//
// 
//
// 
//输入：grid = [[1,0,0],[0,0,0],[0,0,0]]
//输出：4
//解释： 
//海洋单元格 (2, 2) 和所有陆地单元格之间的距离都达到最大，最大距离为 4。
// 
//
// 
//
// 提示： 
//
// 
// 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 100 
// grid[i][j] 不是 0 就是 1 
// 
//
// Related Topics 广度优先搜索 数组 动态规划 矩阵 👍 362 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.LinkedList;
import java.util.Queue;

public class L1162_AsFarFromLandAsPossible{
      
  public static void main(String[] args) {
       Solution solution = new L1162_AsFarFromLandAsPossible().new Solution();
       int[][] grid = new int[][]{
               {1,0,1},
               {0,0,0},
               {1,0,1},
       };
      System.out.println(solution.maxDistance(grid));
  }

    /**
     * 常规bfs(单源bfs)：对所有海洋(n^2)进行一次bfs搜索(O(n^2))，找到距离最近的小岛，再对所有海洋区域的最近小岛取最大值
     * 折腾半天终于写出来，当全部都为海洋时直接超时(O(n^4)/O(n^2))
     *
     * 多源bfs
     * 先收集所有的陆地区域到队列中，作为第1批搜索节点
     * 依次弹出陆地区域并向外扩散1圈，搜索海洋区域，此时的圈数即为海洋到最近陆地的距离
     * 被搜索到的海洋区域修改其值为与最近陆地的距离，防止重复搜索，这些海洋区域继续放入队列，作为下一批搜索节点
     * 如此往复，直到搜索到的最后一个区域即为 【离最近陆地区域的距离】最大的海洋区域
     * O(n^2)/O(n^2)
     *
     *
     */

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    public int maxDistance(int[][] grid) {
        int res = -1;
        Queue<int[]> queue = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j]==1){
                    //收集所有陆地位置
                    queue.offer(new int[]{i,j});
                }
            }
        }
        if (queue.isEmpty() || queue.size() == grid.length * grid[0].length) {
            //全是海洋或者全是陆地
            return res;
        }
        int[][] directions = {{-1,0}, {1, 0}, {0, -1},{0, 1}};
        while (!queue.isEmpty()){
            int[] curPosition = queue.poll();
            int x = curPosition[0];
            int y = curPosition[1];
            //向外扩散一圈搜索
            for (int[] direction : directions) {
                int newX = x + direction[0];
                int newY = y + direction[1];
                if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length) {
                    //不在网格内
                    continue;
                }
                if (grid[newX][newY]!=0){
                    //不是海洋 或者已被搜索
                    continue;
                }
                queue.offer(new int[]{newX, newY});
                //已经搜索过的位置，设置其值为距离值，避免重复搜索
                grid[newX][newY] = grid[x][y] + 1;
                res = Math.max(res, grid[newX][newY]);
            }
        }
        //grid[x][y]是从1开始计算，最终结果-1
        return res - 1;
    }

}
//leetcode submit region end(Prohibit modification and deletion)


    class Solution1 {

        //当前位置的上、下、左、右位置偏移值
        public  int[][] DIRECTIONS = {{-1,0}, {1, 0}, {0, -1},{0, 1}};
        public int maxDistance(int[][] grid) {
            int res = -1;
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j]==0){
                        //当前位置是海洋
                        res = Math.max(res, findNearestLand(i, j, grid));
                    }
                }
            }
            return res;
        }

        private int findNearestLand(int x, int y, int[][] grid) {
            boolean[][] vis = new boolean[grid.length][grid[0].length];
            Queue<int[]> queue = new LinkedList<>();
            //x,y,dis
            queue.offer(new int[]{x, y, 0});
            vis[x][y] = true;
            while (!queue.isEmpty()){
                int[] curPosition = queue.poll();
                for (int[] direction : DIRECTIONS) {
                    int newX = curPosition[0] + direction[0];
                    int newY = curPosition[1] + direction[1];

                    if (newX < 0 || newX >= grid.length || newY < 0 || newY >= grid[0].length) {
                        //不在网格内
                        continue;
                    }

                    if (!vis[newX][newY]){
                        int dis = curPosition[2] + 1;
                        if (grid[newX][newY] == 1) {
                            //搜索到岛屿，必定是最近的，返回距离
                            return dis;
                        }
                        //加入队列，继续搜索
                        queue.offer(new int[]{newX, newY, dis});
                        //当前位置标记已搜索
                        vis[newX][newY] = true;
                    }
                }
            }
            return -1;
        }
    }
}