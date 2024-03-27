//给定两个大小分别为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。请你找出并返回这两个正序数组的 中位数 。 
//
// 算法的时间复杂度应该为 O(log (m+n)) 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums1 = [1,3], nums2 = [2]
//输出：2.00000
//解释：合并数组 = [1,2,3] ，中位数 2
// 
//
// 示例 2： 
//
// 
//输入：nums1 = [1,2], nums2 = [3,4]
//输出：2.50000
//解释：合并数组 = [1,2,3,4] ，中位数 (2 + 3) / 2 = 2.5
// 
//
// 
//
// 
//
// 提示： 
//
// 
// nums1.length == m 
// nums2.length == n 
// 0 <= m <= 1000 
// 0 <= n <= 1000 
// 1 <= m + n <= 2000 
// -10⁶ <= nums1[i], nums2[i] <= 10⁶ 
// 
//
// Related Topics 数组 二分查找 分治 👍 7059 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L4_MedianOfTwoSortedArrays{
      
  public static void main(String[] args) {
       Solution solution = new L4_MedianOfTwoSortedArrays().new Solution();
      System.out.println(solution.findMedianSortedArrays(new int[]{1,2},new int[]{3,4}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      // 合并数组，根据长度奇偶计算中位数
    //O(m+n)/O(1)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length;
        int[] nums = new int[m + n];
        //如果nums1或者nums2有空数组，则直接计算中位数
        if (m == 0) {
            return n % 2 == 0 ? (nums2[n / 2 - 1] + nums2[n / 2]) / 2.0 : nums2[n / 2];
        }
        if (n == 0) {
            return m % 2 == 0 ? (nums1[m / 2 - 1] + nums1[m / 2]) / 2.0 : nums1[m / 2];
        }
        //两数组都不为空，合并数组后，计算中位数
        int count = 0;
        int i=0,j=0;
        while (count<(m+n)){
            if (i==m){
                while (j!=n){
                    nums[count++]=nums2[j++];
                }
                break;
            }
            if (j==n){
                while (i!=m){
                    nums[count++] = nums1[i++];
                }
                break;
            }

            if (nums1[i]<nums2[j]){
                nums[count++] = nums1[i++];
            }else {
                nums[count++] = nums2[j++];
            }
        }

        return count % 2 == 0 ? (nums[count / 2 - 1] + nums[count / 2]) / 2.0 : nums[count / 2];
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}