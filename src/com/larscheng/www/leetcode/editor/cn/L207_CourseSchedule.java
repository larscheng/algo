//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。 
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 2000 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1941 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.List;

public class L207_CourseSchedule{
      
  public static void main(String[] args) {
       Solution solution = new L207_CourseSchedule().new Solution();
      System.out.println(solution.canFinish(4, new int[][]{{2, 1}, {3, 1}}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      List<List<Integer>> edges;
      int[] visited;
      boolean valid = true;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        edges = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            edges.add(new ArrayList<>());
        }
        visited = new int[numCourses];
        //1,0
        //2,1
        //3,2
        for (int[] prerequisite : prerequisites) {
            edges.get(prerequisite[1]).add(prerequisite[0]);
        }
        //依次搜索
        for (int i = 0; i < numCourses && valid; i++) {
            //未搜索状态
            if (visited[i]==0){
                dfs(i);
            }
        }
        return valid;
    }

    private void dfs(int i) {
        //搜索中
        visited[i] =1;
        for (Integer target : edges.get(i)) {
            if (visited[target]==0){
                //未搜索，继续dfs
                dfs(target);
                if (!valid){
                    return;
                }
            }else if (visited[target]==1){
                //搜索中，存在环，无拓扑排序
                valid = false;
                return;
            }
        }
        //搜索完成
        visited[i] = 2;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}