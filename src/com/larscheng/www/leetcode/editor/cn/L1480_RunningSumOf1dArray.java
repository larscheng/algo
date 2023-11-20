//给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。 
//
// 请返回 nums 的动态和。 
//
// 
//
// 示例 1： 
//
// 输入：nums = [1,2,3,4]
//输出：[1,3,6,10]
//解释：动态和计算过程为 [1, 1+2, 1+2+3, 1+2+3+4] 。 
//
// 示例 2： 
//
// 输入：nums = [1,1,1,1,1]
//输出：[1,2,3,4,5]
//解释：动态和计算过程为 [1, 1+1, 1+1+1, 1+1+1+1, 1+1+1+1+1] 。 
//
// 示例 3： 
//
// 输入：nums = [3,1,2,10,1]
//输出：[3,4,6,16,17]
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 1000 
// -10^6 <= nums[i] <= 10^6 
// 
//
// Related Topics 数组 前缀和 👍 441 👎 0

package com.larscheng.www.leetcode.editor.cn;


import java.util.Arrays;

public class L1480_RunningSumOf1dArray{
      
  public static void main(String[] args) {
       Solution solution = new L1480_RunningSumOf1dArray().new Solution();
      System.out.println(Arrays.toString(solution.runningSum(new int[]{3, 1, 2, 10, 1})));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
      //时间复杂度O(n)，空间复杂度O(1)
    public int[] runningSum(int[] nums) {
        int[] sum = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            sum[i] = nums[i] + (i < 1 ? 0 : sum[i - 1]);
        }
        return sum;
    }
}
//leetcode submit region end(Prohibit modification and deletion)


}