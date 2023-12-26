//给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
//
// 你需要返回给定数组中的重要翻转对的数量。 
//
// 示例 1: 
//
// 
//输入: [1,3,2,3,1]
//输出: 2
// 
//
// 示例 2: 
//
// 
//输入: [2,4,3,5,1]
//输出: 3
// 
//
// 注意: 
//
// 
// 给定数组的长度不会超过50000。 
// 输入数组中的所有数字都在32位整数的表示范围内。 
// 
//
// Related Topics 树状数组 线段树 数组 二分查找 分治 有序集合 归并排序 👍 433 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L493_ReversePairs{
      
  public static void main(String[] args) {
       Solution solution = new L493_ReversePairs().new Solution();
       
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {

    private int count;

    public int reversePairs(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }
        count = 0;
        mergeSort(nums, 0, nums.length - 1);
        return count;
    }

    private void mergeSort(int[] nums, int start, int end) {
        if (start == end) {
            return;
        }
        int mid = start + (end - start) / 2;
        mergeSort(nums, start, mid);
        mergeSort(nums, mid + 1, end);
        int i = start;
        int j = mid + 1;
        while (i <= mid && j <= end) {
            if ((long) nums[i] > 2 * (long) nums[j]) {
                count += mid - i + 1;
                j++;
            } else {
                i++;
            }
        }
        // 统计完之后合并
        int[] tempArr = new int[end - start + 1];
        i = start;
        j = mid + 1;
        int idx = 0;
        while (i <= mid && j <= end) {
            tempArr[idx++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            tempArr[idx++] = nums[i++];
        }
        while (j <= end) {
            tempArr[idx++] = nums[j++];
        }
        for (i = 0, j = start; j <= end; i++, j++) {
            nums[j] = tempArr[i];
        }
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}