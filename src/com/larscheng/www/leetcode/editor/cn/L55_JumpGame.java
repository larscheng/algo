//给你一个非负整数数组 nums ，你最初位于数组的 第一个下标 。数组中的每个元素代表你在该位置可以跳跃的最大长度。 
//
// 判断你是否能够到达最后一个下标，如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [2,3,1,1,4]
//输出：true
//解释：可以先跳 1 步，从下标 0 到达下标 1, 然后再从下标 1 跳 3 步到达最后一个下标。
// 
//
// 示例 2： 
//
// 
//输入：nums = [3,2,1,0,4]
//输出：false
//解释：无论怎样，总会到达下标为 3 的位置。但该下标的最大跳跃长度是 0 ， 所以永远不可能到达最后一个下标。
// 
//
// 
//
// 提示： 
//
// 
// 1 <= nums.length <= 10⁴ 
// 0 <= nums[i] <= 10⁵ 
// 
//
// Related Topics 贪心 数组 动态规划 👍 2752 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L55_JumpGame{
      
  public static void main(String[] args) {
       Solution solution = new L55_JumpGame().new Solution();
//      System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
//      System.out.println(solution.canJump(new int[]{2, 0}));
      System.out.println(solution.canJump(new int[]{0, 1}));
  }

//leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    /**
     * dp[i] 表示从[0,i]任意位置出发，最大可跳跃的位置
     * dp[i] = max(dp[i-1],i+nums[i])
     */
    public boolean canJump(int[] nums) {
        if (nums.length > 1 && nums[0] == 0) {
            return false;
        }
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], i + nums[i]);
            if (dp[i] >= nums.length - 1) {
                return true;
            }
            if (dp[i] == i) {
                return false;
            }
        }
        return true;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
class Solution2 {
    /**
     * 记录每个节点可以跳到的最远位置
     * 遍历的过程中如果当前位置大于历史最远位置，说明无法跳到该位置，否则说明可以跳完所有位置
     *
     */
    public boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }
}
    class Solution1 {
        /**
         * dp[i]表示能否跳跃到第i位
         * dp[i-1]=true,dp[i]=dp[i-1]&nums[i-1]>0
         * dp[i-1]=false,dp[i-2]=true,dp[i]=dp[i-2]&num[i-2]>1
         * ...
         * dp[i] = dp[i-1]&&nums[i-1]>0 || dp[i-2]&&nums[i-2]>1||...dp[0]&&dp[0]>i-0-1
         */
        public boolean canJump(int[] nums) {
            boolean[] dp = new boolean[nums.length];
            for (int i = 0; i <= nums[0] && i < nums.length; i++) {
                dp[i] = true;
            }
            for (int i = nums[0] + 1; i < nums.length; i++) {
                for (int j = i - 1; j >= 0; j--) {
                    dp[i] = dp[j] && nums[j] >= i - j;
                    if (dp[i]) {
                        break;
                    }
                }
            }
            return dp[nums.length-1];
        }
    }
}