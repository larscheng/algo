//在一个 n x n 的整数矩阵 grid 中，每一个方格的值 grid[i][j] 表示位置 (i, j) 的平台高度。 
//
// 当开始下雨时，在时间为 t 时，水池中的水位为 t 。你可以从一个平台游向四周相邻的任意一个平台，但是前提是此时水位必须同时淹没这两个平台。假定你可以瞬间
//移动无限距离，也就是默认在方格内部游动是不耗时的。当然，在你游泳的时候你必须待在坐标方格里面。 
//
// 你从坐标方格的左上平台 (0，0) 出发。返回 你到达坐标方格的右下平台 (n-1, n-1) 所需的最少时间 。 
//
// 
//
// 示例 1: 
//
// 
//
// 
//输入: grid = [[0,2],[1,3]]
//输出: 3
//解释:
//时间为0时，你位于坐标方格的位置为 (0, 0)。
//此时你不能游向任意方向，因为四个相邻方向平台的高度都大于当前时间为 0 时的水位。
//等时间到达 3 时，你才可以游向平台 (1, 1). 因为此时的水位是 3，坐标方格中的平台没有比水位 3 更高的，所以你可以游向坐标方格中的任意位置
// 
//
// 示例 2: 
//
// 
//
// 
//输入: grid = [[0,1,2,3,4],[24,23,22,21,5],[12,13,14,15,16],[11,17,18,19,20],[10,
//9,8,7,6]]
//输出: 16
//解释: 最终的路线用加粗进行了标记。
//我们必须等到时间为 16，此时才能保证平台 (0, 0) 和 (4, 4) 是连通的
// 
//
// 
//
// 提示: 
//
// 
// n == grid.length 
// n == grid[i].length 
// 1 <= n <= 50 
// 0 <= grid[i][j] < n² 
// grid[i][j] 中每个值 均无重复 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 数组 二分查找 矩阵 堆（优先队列） 👍 289 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L778_SwimInRisingWater{
      
  public static void main(String[] args) {
       Solution solution = new L778_SwimInRisingWater().new Solution();
       
  }

    /***
     * 在方格数值区间[0,N*N-1]中找一个数作为等待时间，使其满足：存在从左上角到右下角的路径，且该数要最小
     * 通过二分查找在区间内找符合条件的数值
     * 如果mid满足条件，继续检查小于mid的数，right=mid
     * 如果mid不满足，说明等待时间太小，需要继续检查更大的数值，left=mid+1
     *
     * 通过深度优先遍历，从左上角（0,0）开始检查当前等待时间下，是否存在有效路径
     */
//leetcode submit region begin(Prohibit modification and deletion)
public class Solution {

    private int N;

    public  int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int swimInWater(int[][] grid) {
        this.N = grid.length;
        int left = 0;
        int right = N * N - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            boolean[][] visited = new boolean[N][N];
            if (grid[0][0] <= mid && dfs(grid, 0, 0, visited, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private boolean dfs(int[][] grid, int x, int y, boolean[][] visited, int threshold) {
        visited[x][y] = true;
        for (int[] direction : DIRECTIONS) {
            int newX = x + direction[0];
            int newY = y + direction[1];
            if (inArea(newX, newY) && !visited[newX][newY] && grid[newX][newY] <= threshold) {
                if (newX == N - 1 && newY == N - 1) {
                    return true;
                }

                if (dfs(grid, newX, newY, visited, threshold)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean inArea(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < N;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}