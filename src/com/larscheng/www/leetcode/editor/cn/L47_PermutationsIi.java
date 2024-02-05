//给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,1,2]
//输出：
//[[1,1,2],
// [1,2,1],
// [2,1,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 8 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 数组 回溯 👍 1530 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.*;

public class L47_PermutationsIi{
      
  public static void main(String[] args) {
       Solution solution = new L47_PermutationsIi().new Solution();
      System.out.println(solution.permuteUnique(new int[]{1,1,2}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(nums);

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] checked = new boolean[length];

        dfs(nums,length,0,checked,queue,res);

        return res;
    }

    private void dfs(int[] nums, int length, int depth, boolean[] checked, Deque<Integer> queue, List<List<Integer>> res) {
        if (depth==length){
            //已经遍历到最后一个
            res.add(new ArrayList<>(queue));
            return;
        }
        for (int i = 0; i < length; i++) {
            if (checked[i]){
                continue;
            }
            if (i > 0 && nums[i] == nums[i - 1] && !checked[i - 1]) {
                //同一层相同元素只能组合一次
                continue;
            }
            checked[i] = true;
            queue.addLast(nums[i]);

            dfs(nums, length, depth+1, checked, queue, res);

            checked[i] = false;
            queue.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}