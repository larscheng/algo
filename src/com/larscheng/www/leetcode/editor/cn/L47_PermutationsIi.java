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


    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used;
    public List<List<Integer>> permuteUnique(int[] nums) {

        Arrays.sort(nums);
        used = new boolean[nums.length];
        backtrack(nums);
        return res;
    }

    private void backtrack(int[] nums) {
        if (nums.length==track.size()){
            res.add(new ArrayList<>(track));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            //剪枝逻辑（避免跳过第一次遇到的重复元素）发现重复元素，前一个元素没有被使用说明这是新一轮，上一轮已经用过了当前元素，可跳过
            //[2,2',3]的全排列中[2,2',3]和[2',2,3]的去重，
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                //2‘只能在2已经被使用的前提下才能用
                continue;
            }
            track.addLast(nums[i]);
            used[i] = true;
            backtrack(nums);
            track.removeLast();
            used[i] = false;
        }
    }


}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
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
}