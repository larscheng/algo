//给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，找出 candidates 中可以使数字和为目标数 target 的
// 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。 
//
// candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
//
// 对于给定的输入，保证和为 target 的不同组合数少于 150 个。 
//
// 
//
// 示例 1： 
//
// 
//输入：candidates = [2,3,6,7], target = 7
//输出：[[2,2,3],[7]]
//解释：
//2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
//7 也是一个候选， 7 = 7 。
//仅有这两种组合。 
//
// 示例 2： 
//
// 
//输入: candidates = [2,3,5], target = 8
//输出: [[2,2,2,2],[2,3,3],[3,5]] 
//
// 示例 3： 
//
// 
//输入: candidates = [2], target = 1
//输出: []
// 
//
// 
//
// 提示： 
//
// 
// 1 <= candidates.length <= 30 
// 2 <= candidates[i] <= 40 
// candidates 的所有元素 互不相同 
// 1 <= target <= 40 
// 
//
// Related Topics 数组 回溯 👍 2714 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L39_CombinationSum{
      
  public static void main(String[] args) {
       Solution solution = new L39_CombinationSum().new Solution();
      System.out.println(solution.combinationSum(new int[]{2, 3, 6, 7}, 7));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 以[2,3,6,7],target=7为例
     * 7-2-2-3=0，则[2,2,3]符合
     * 7-7=0，则[7]符合
     * 画出树形图，可以看出以7为根节点，遍历数组元素不断做减法，数组元素可以重复使用，直到减到结果为0，属于一次有效组合
     * 当原数组递增有序时，遍历元素做减法，如果相减结果必然小于0，则明显结果无效，可以剪枝处理
     *
     *
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int length = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (length == 0) {
            return res;
        }
        Arrays.sort(candidates);
        Deque<Integer> queue = new ArrayDeque<>();
        dfs(candidates, 0, length, target, queue, res);
        return res;
    }

    private void dfs(int[] candidates, int begin, int length, int target, Deque<Integer> queue, List<List<Integer>> res) {
        if (target == 0) {
            res.add(new ArrayList<>(queue));
            return;
        }

        for (int i = begin; i < length; i++) {
            //剪枝
            if (target-candidates[i] < 0) {
                break;
            }
            queue.addLast(candidates[i]);

            //数字可重复使用，但是在递归中已经走过的位置就不用再拿来用了，防止结果重复
            //[2,2,3]和[2,3,2]重复
            dfs(candidates, i, length, target-candidates[i], queue, res);
            //回溯，状态恢复
            queue.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}