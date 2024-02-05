//给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。 
//
// candidates 中的每个数字在每个组合中只能使用 一次 。 
//
// 注意：解集不能包含重复的组合。 
//
// 
//
// 示例 1: 
//
// 
//输入: candidates = [10,1,2,7,6,1,5], target = 8,
//输出:
//[
//[1,1,6],
//[1,2,5],
//[1,7],
//[2,6]
//] 
//
// 示例 2: 
//
// 
//输入: candidates = [2,5,2,1,2], target = 5,
//输出:
//[
//[1,2,2],
//[5]
//] 
//
// 
//
// 提示: 
//
// 
// 1 <= candidates.length <= 100 
// 1 <= candidates[i] <= 50 
// 1 <= target <= 30 
// 
//
// Related Topics 数组 回溯 👍 1513 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;
import java.util.stream.Collectors;

public class L40_CombinationSumIi{
      
  public static void main(String[] args) {
       Solution solution = new L40_CombinationSumIi().new Solution();
      System.out.println(solution.combinationSum2(new int[]{10,1,2,7,6,1,5},8));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 与39题的区别在于 candidates数组中的元素不可重复使用
     * 所以在进行递归时，下一个待选择元素需要+1
     *
     * 观察示例1可以发现，因为candidates数组存在重复元素，所以结果集中会出现相同组合，所以还需要进行去重剪枝处理
     *
     * 画出树形图，可以观察到同一层中的元素不可以重复，同一个元素仅一次有效
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        int length = candidates.length;
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(candidates);
        Deque<Integer> queue = new ArrayDeque<>();
        dfs(candidates,0,target,length,queue,res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int target, int length, Deque<Integer> queue, List<List<Integer>> res) {
        if (target==0){
            res.add(new ArrayList<>(queue));
            System.out.println();
            return;
        }

        for (int i = begin; i < length; i++) {
            if (target - candidates[i] < 0) {
                break;
            }
            if (i > begin && candidates[i] == candidates[i - 1]) {
                continue;
            }
            queue.addLast(candidates[i]);

            dfs(candidates, i + 1, target - candidates[i], length, queue, res);

            queue.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}