//给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。 
//
// 如果数组中不存在目标值 target，返回 [-1, -1]。 
//
// 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 8
//输出：[3,4] 
//
// 示例 2： 
//
// 
//输入：nums = [5,7,7,8,8,10], target = 6
//输出：[-1,-1] 
//
// 示例 3： 
//
// 
//输入：nums = [], target = 0
//输出：[-1,-1] 
//
// 
//
// 提示： 
//
// 
// 0 <= nums.length <= 10⁵ 
// -10⁹ <= nums[i] <= 10⁹ 
// nums 是一个非递减数组 
// -10⁹ <= target <= 10⁹ 
// 
//
// Related Topics 数组 二分查找 👍 2648 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L34_FindFirstAndLastPositionOfElementInSortedArray{
      
  public static void main(String[] args) {
       Solution solution = new L34_FindFirstAndLastPositionOfElementInSortedArray().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //两次二分查找，分别找开始和结束位置
    public int[] searchRange(int[] nums, int target) {
        int start = -1 ,end = -1;
        int left = 0, right = nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if (nums[mid] == target){
                start = mid;
                //继续向左找，看还有没有相等的值，更新开始位置
                right = mid -1;
            } else if (nums[mid]>target) {
                right = mid-1;
            }else {
                left = mid +1;
            }
        }
        left = 0;
        right=nums.length-1;
        while (left<=right){
            int mid = left + (right-left)/2;
            if (nums[mid] == target){
                end = mid;
                //继续向右找，看还有没有相等的值，更新开始位置
                left = mid +1;
            } else if (nums[mid]>target) {
                right = mid-1;
            }else {
                left = mid +1;
            }
        }
        return new int[]{start,end};
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}