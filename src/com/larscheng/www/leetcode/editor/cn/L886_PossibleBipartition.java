//给定一组 n 人（编号为 1, 2, ..., n）， 我们想把每个人分进任意大小的两组。每个人都可能不喜欢其他人，那么他们不应该属于同一组。 
//
// 给定整数 n 和数组 dislikes ，其中 dislikes[i] = [ai, bi] ，表示不允许将编号为 ai 和 bi的人归入同一组。当可以用
//这种方法将所有人分进两组时，返回 true；否则返回 false。 
//
// 
//
// 
// 
//
// 示例 1： 
//
// 
//输入：n = 4, dislikes = [[1,2],[1,3],[2,4]]
//输出：true
//解释：group1 [1,4], group2 [2,3]
// 
//
// 示例 2： 
//
// 
//输入：n = 3, dislikes = [[1,2],[1,3],[2,3]]
//输出：false
// 
//
// 示例 3： 
//
// 
//输入：n = 5, dislikes = [[1,2],[2,3],[3,4],[4,5],[1,5]]
//输出：false
// 
//
// 
//
// 提示： 
//
// 
// 1 <= n <= 2000 
// 0 <= dislikes.length <= 10⁴ 
// dislikes[i].length == 2 
// 1 <= dislikes[i][j] <= n 
// ai < bi 
// dislikes 中每一组都 不同 
// 
//
// 
//
// Related Topics 深度优先搜索 广度优先搜索 并查集 图 👍 389 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L886_PossibleBipartition{
      
  public static void main(String[] args) {
       Solution solution = new L886_PossibleBipartition().new Solution();
       solution.possibleBipartition(4, new int[][]{{1,2},{1,3},{2,4}});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean possibleBipartition(int n, int[][] dislikes) {
        List<Integer>[] matrix = new ArrayList[n + 1];
        for (int i = 1; i < matrix.length; i++) {
            matrix[i] = new ArrayList(n + 1);
        }
        //邻接矩阵记录dislikes 矩阵中记录每行元素的dislike元素
        for (int[] item : dislikes) {
            matrix[item[0]].add(item[1]);
            matrix[item[1]].add(item[0]);
        }
        // 记录分组情况 0，-1，1
        int[] record = new int[n + 1];
        for (int i = 1; i < matrix.length ; i ++){
            if (record[i] == 0 && !dfs(matrix, record, i, 1)) {
                //未分组，分组失败
                return false;
            }
        }
        return true;
    }

    private boolean dfs(List<Integer>[] matrix, int[] record, int index, int group) {
        record[index] = group;
        //检查当前元素的所有dislike
        List<Integer> dislike = matrix[index];
        for (int i = 0; i < dislike.size() ; i++) {
            int num = dislike.get(i);
            // 已分组且同一组，直接返回失败
            if (record[num] == group) {
                return false;
            }
            // 没分组，dfs进行分组，扔进对立组
            if (record[num] == 0 && !dfs(matrix, record, num, group * -1)) {
                return false;
            }
        }
        return true;
    }
}

//leetcode submit region end(Prohibit modification and deletion)


}