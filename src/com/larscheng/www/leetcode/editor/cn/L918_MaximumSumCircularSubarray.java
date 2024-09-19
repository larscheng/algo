//给定一个长度为 n 的环形整数数组 nums ，返回 nums 的非空 子数组 的最大可能和 。 
//
// 环形数组 意味着数组的末端将会与开头相连呈环状。形式上， nums[i] 的下一个元素是 nums[(i + 1) % n] ， nums[i] 的前一个
//元素是 nums[(i - 1 + n) % n] 。 
//
// 子数组 最多只能包含固定缓冲区 nums 中的每个元素一次。形式上，对于子数组 nums[i], nums[i + 1], ..., nums[j] ，不
//存在 i <= k1, k2 <= j 其中 k1 % n == k2 % n 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [1,-2,3,-2]
//输出：3
//解释：从子数组 [3] 得到最大和 3
// 
//
// 示例 2： 
//
// 
//输入：nums = [5,-3,5]
//输出：10
//解释：从子数组 [5,5] 得到最大和 5 + 5 = 10
// 
//
// 示例 3： 
//
// 
//输入：nums = [3,-2,2,-3]
//输出：3
//解释：从子数组 [3] 和 [3,-2,2] 都可以得到最大和 3
// 
//
// 
//
// 提示： 
//
// 
// n == nums.length 
// 1 <= n <= 3 * 10⁴ 
// -3 * 10⁴ <= nums[i] <= 3 * 10⁴ 
// 
//
// Related Topics 队列 数组 分治 动态规划 单调队列 👍 735 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L918_MaximumSumCircularSubarray{
      
  public static void main(String[] args) {
       Solution solution = new L918_MaximumSumCircularSubarray().new Solution();
      System.out.println(solution.maxSubarraySumCircular(new int[]{-3, -2, -3}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * 情况1:s1区间是最大和数组，不在环首尾交替处
     * [a1,a2][a3,a4,a5][a6,a7]
     * |-----||---s1---||-----|
     *
     *
     * 情况二: s3+s1是最大和数组，处在环首尾交替
     * [a1,a2,a3][a4][a5,a6,a7]
     * |---s1---||s2||---s3---|
     *
     * s1和s3构成了最大和数组，s2即位最小和数组
     * s1+s3=sum-s2，s2可以通过求最小和数组得来，sum可通过累加得来
     * 特殊情况全是负数,-3,-2,-3,sum(-8)==min(-8),此时情况1=-2，情况2=max，无需计算
     *
     */
    public int maxSubarraySumCircular(int[] nums) {
        int[] maxdp = new int[nums.length];
        int[] mindp = new int[nums.length];
        maxdp[0] = mindp[0] = nums[0];
        int sum = nums[0];
        int max = nums[0];
        int min = nums[0];
        for (int i = 1; i < nums.length ; i++) {
            sum+=nums[i];

            maxdp[i] = Math.max(nums[i],maxdp[i-1]+nums[i]);
            max = Math.max(max,maxdp[i]);

            mindp[i] = Math.min(nums[i],mindp[i-1]+nums[i]);
            min = Math.min(min,mindp[i]);

        }
        return Math.max(max,sum==min?max:sum-min);
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}