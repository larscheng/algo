//给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。 
//
// 请必须使用时间复杂度为 O(log n) 的算法。 
//
// 
//
// 示例 1: 
//
// 
//输入: nums = [1,3,5,6], target = 5
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: nums = [1,3,5,6], target = 2
//输出: 1
// 
//
// 示例 3: 
//
// 
//输入: nums = [1,3,5,6], target = 7
//输出: 4
// 
//
// 
//
// 提示: 
//
// 
// 1 <= nums.length <= 10⁴ 
// -10⁴ <= nums[i] <= 10⁴ 
// nums 为 无重复元素 的 升序 排列数组 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 二分查找 👍 2220 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L35_SearchInsertPosition{
      
  public static void main(String[] args) {
       Solution solution = new L35_SearchInsertPosition().new Solution();
//      System.out.println(solution.searchInsert(new int[]{1, 2, 3, 5, 6}, 4));
      System.out.println(solution.searchInsert(new int[]{1, 3, 5, 6}, 2));
  }

    /**
     * 原始2分查找，可以通过指针遍历元素，查找目标元素
     * 本题稍作变化，当找不到目标元素时，返回第一个大于他的元素下标
     * O(logn)/O(1)
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int searchInsert(int[] nums, int target) {
        int mid, left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            //数组可能元素重复，所以要一直搜索
            if (nums[mid] >= target){
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution1 {
    public int searchInsert(int[] nums, int target) {
        int mid, left = 0, result = nums.length;
        int right = nums.length - 1;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                //存在直接返回
                return mid;
            }else if (nums[mid] > target){
                //不存在记录大于target的位置
                result = mid;
                right = mid - 1;
            }else {
                left = mid + 1;
            }
        }
        return result;
    }
}

}