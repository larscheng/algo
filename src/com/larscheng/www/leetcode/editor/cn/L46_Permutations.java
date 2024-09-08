//给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,2,3]
//输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,1]
//输出：[[0,1],[1,0]]
// 
//
// 示例 3： 
//
// 
//输入：nums = [1]
//输出：[[1]]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 6 
// -10 <= nums[i] <= 10 
// nums 中的所有整数 互不相同 
// 
//
// Related Topics 数组 回溯 👍 2873 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class L46_Permutations{
      
  public static void main(String[] args) {
       Solution solution = new L46_Permutations().new Solution();
       solution.permute(new int[]{1,2,3});
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    List<List<Integer>> res = new ArrayList<>();
    LinkedList<Integer> track = new LinkedList<>();
    boolean[] used ;
    public List<List<Integer>> permute(int[] nums) {
       used = new boolean[nums.length];
       backtrack(nums);
       return res;
    }

    private void backtrack(int[] nums) {
        if (nums.length==track.size()){
            res.add(new ArrayList<>(track));
            return;
        }
        //i从0开始，因为要全排列，从头开始寻找没有使用过的元素
        for (int i = 0; i < nums.length; i++) {
            if (used[i]){
                continue;
            }
            used[i]=true;
            track.addLast(nums[i]);
            backtrack(nums);

            track.removeLast();
            used[i]=false;
        }
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        //回溯
        // 声明状态变量used来记录元素的使用状态，depth为当前收集深度
        // 回溯终止条件：已收集到需要的数字，denpth==length时
        // O(n*n!)/ O(n*n!)
        public List<List<Integer>> permute(int[] nums) {
            List<List<Integer>> res = new ArrayList<>();
            if (nums.length==0){
                return res;
            }
            boolean[] used = new boolean[nums.length];
            List<Integer> path  = new ArrayList<>();

            dfs(nums,nums.length,0,used,path,res);

            return res;

        }

        private void dfs(int[] nums, int length, int depth, boolean[] used, List<Integer> path, List<List<Integer>> res) {
            if (depth==length){
                res.add(new ArrayList<>(path));
                return;
            }
            for (int i = 0; i < nums.length; i++) {
                if (used[i]){
                    continue;
                }
                path.add(nums[i]);
                used[i] = true;

                dfs(nums,length,depth+1,used,path,res);

                used[i] = false;
                path.remove(path.size()-1);

            }

        }
    }
}