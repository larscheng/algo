//给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的 子集（幂集）。 
//
// 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。 
//
// 
// 
// 
// 
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,2]
//输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0]
//输出：[[],[0]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10 
// -10 <= nums[i] <= 10 
// 
//
// Related Topics 位运算 数组 回溯 👍 1231 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class L90_SubsetsIi{
      
  public static void main(String[] args) {
       Solution solution = new L90_SubsetsIi().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //先将元素排序，确保重复元素都是连续的后面好剪枝
        Arrays.sort(nums);
        backtrack(nums,0);
        return  res;
    }

    private void backtrack(int[] nums, int start) {
        res.add(new ArrayList<>(track));
        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                //避免出现[1,2,2]中，[1,2]和[1,2]的重复现象
                continue;
            }
            track.addLast(nums[i]);
            backtrack(nums,i+1);
            track.removeLast();
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}