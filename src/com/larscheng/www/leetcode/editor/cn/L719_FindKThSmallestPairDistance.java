//数对 (a,b) 由整数 a 和 b 组成，其数对距离定义为 a 和 b 的绝对差值。 
//
// 给你一个整数数组 nums 和一个整数 k ，数对由 nums[i] 和 nums[j] 组成且满足 0 <= i < j < nums.length 。
//返回 所有数对距离中 第 k 小的数对距离。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,3,1], k = 1
//输出：0
//解释：数对和对应的距离如下：
//(1,3) -> 2
//(1,1) -> 0
//(3,1) -> 2
//距离第 1 小的数对是 (1,1) ，距离为 0 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [1,1,1], k = 2
//输出：0
// 
//
// 示例 3： 
//
// 
//输入：nums = [1,6,1], k = 3
//输出：5
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 2 <= n <= 10⁴ 
// 0 <= nums[i] <= 10⁶ 
// 1 <= k <= n * (n - 1) / 2 
// 
//
// Related Topics 数组 双指针 二分查找 排序 👍 436 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L719_FindKThSmallestPairDistance{
      
  public static void main(String[] args) {
       Solution solution = new L719_FindKThSmallestPairDistance().new Solution();
       
  }

    /**
     *
     *
     * 暴力：计算所有可能的对数距离，使用堆找出第k小的数
     *
     * 二分：从距离数组dis中，查找一个数(距离)满足：在所有数对距离中第k小的数对距离
     * 则有距离数组有left、right、mid，
     * 有 x 组数对的距离 小于 dis[mid]， 则dis[mid]是第x+1小的数，x+1记为cnt
     * cnt=k时：此时mid可能刚好是第k小
     * cnt>k时：mid距离过大，小于该距离的对数过多，需要查看左区间 right=mid
     * cnt<k时：mid距离过小，小于该距离的对数过少，需要查看右区间 left=mid+1
     *
     * 有多少对数距离小于 dis[mid],使用双指针
     */
//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int smallestDistancePair(int[] nums, int k) {
        Arrays.sort(nums);
        int left = 0;
        int right = nums[nums.length-1]-nums[0];
        while (left < right) {
            int mid = left + (right - left) / 2;
            int cnt = 0;
            for (int i = 0, j = 0; i < nums.length; i++) {
                while (j < nums.length && nums[j] - nums[i] <= mid) {
                    j++;
                }
                cnt += j - i - 1;
            }
            if (cnt >= k) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}