//给定一个未排序的整数数组
// nums ， 返回最长递增子序列的个数 。 
//
// 注意 这个数列必须是 严格 递增的。 
//
// 
//
// 示例 1: 
//
// 
//输入: [1,3,5,4,7]
//输出: 2
//解释: 有两个最长递增子序列，分别是 [1, 3, 4, 7] 和[1, 3, 5, 7]。
// 
//
// 示例 2: 
//
// 
//输入: [2,2,2,2,2]
//输出: 5
//解释: 最长递增子序列的长度是1，并且存在5个子序列的长度为1，因此输出5。
// 
//
// 
//
// 提示: 
//
// 
// 
//
// 
// 1 <= nums.length <= 2000 
// -10⁶ <= nums[i] <= 10⁶ 
// 
//
// Related Topics 树状数组 线段树 数组 动态规划 👍 833 👎 0

package com.larscheng.www.leetcode.editor.cn;


public class L673_NumberOfLongestIncreasingSubsequence{
      
  public static void main(String[] args) {
       Solution solution = new L673_NumberOfLongestIncreasingSubsequence().new Solution();
      solution.findNumberOfLIS(new int[]{10,9,2,5,3,7,101,18});
  }

    /**
     * 从长度为n的数组nums中找最长递增子序列，并统计一共有多少个
     * 一共两个问题：求最长递增子序列的长度，求这个最大长度的子序列一共有多少个
     *
     * 遍历nums数组，以nums[i]结尾的最长递增子序列长度dp[i],共有cnt[i]个; dp[i]和cnt[i]默认为1
     *  i=1时, dp[i] = 1, cnt[i] = 1
     *  i>1时，dp[i] = Max(dp[i], dp[j] + 1), 0<=j<i,num[j]<num[i]
     *  当dp[j] + 1  >  dp[i]时，意味着有新的最长递增子序列，记录其个数cnt[j]
     *  当dp[j] + 1 ==  dp[i]时，意味着同一长度的多个递增子序列，cnt[j]个数累加
     *
     *  最终取dp最大的cnt值，如果dp最大值有多个则进行累加
     *
     *
     */

//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int findNumberOfLIS(int[] nums) {
            int n = nums.length, maxLen = 0, ans = 0;
            int[] dp = new int[n];
            int[] cnt = new int[n];
            for (int i = 0; i < n; ++i) {
                dp[i] = 1;
                cnt[i] = 1;
                for (int j = 0; j < i; ++j) {
                    if (nums[i] > nums[j]) {
                        if (dp[j] + 1 > dp[i]) {
                            //新长度
                            dp[i] = dp[j] + 1;
                            //新长度子序列个数
                            cnt[i] = cnt[j];
                        } else if (dp[j] + 1 == dp[i]) {
                            //又找到1个当前长度的子序列，累加次数
                            cnt[i] += cnt[j];
                        }
                    }
                }
                if (dp[i] > maxLen) {
                    maxLen = dp[i];
                    ans = cnt[i];
                } else if (dp[i] == maxLen) {
                    ans += cnt[i];
                }
            }
            return ans;
        }
    }

//leetcode submit region end(Prohibit modification and deletion)


}